/*
 * #%L
 * Eva
 * %%
 * Copyright (C) 2013 Abada Servicios Desarrollo (investigacion@abadasoft.com)
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
/**
 * History management component that allows you to register arbitrary tokens that signify application
 * history state on navigation actions.  You can then handle the history {@link #change} event in order
 * to reset your application UI to the appropriate state when the user navigates forward or backward through
 * the browser history stack.
 *
 * ## Initializing
 *
 * The {@link #init} method of the History object must be called before using History. This sets up the internal
 * state and must be the first thing called before using History.
 */
Ext.define('Ext.util.History', {
    singleton: true,
    alternateClassName: 'Ext.History',
    mixins: {
        observable: 'Ext.util.Observable'
    },

    /**
     * @property
     * True to use `window.top.location.hash` or false to use `window.location.hash`.
     */
    useTopWindow: true,

    /**
     * @property
     * The id of the hidden field required for storing the current history token.
     */
    fieldId: Ext.baseCSSPrefix + 'history-field',
    /**
     * @property
     * The id of the iframe required by IE to manage the history stack.
     */
    iframeId: Ext.baseCSSPrefix + 'history-frame',

    constructor: function() {
        var me = this;
        me.oldIEMode = Ext.isIE6 || Ext.isIE7 || !Ext.isStrict && Ext.isIE8;
        me.iframe = null;
        me.hiddenField = null;
        me.ready = false;
        me.currentToken = null;
        me.mixins.observable.constructor.call(me);
    },

    getHash: function() {
        var href = window.location.href,
            i = href.indexOf("#");

        return i >= 0 ? href.substr(i + 1) : null;
    },

    setHash: function (hash) {
        var me = this,
            win = me.useTopWindow ? window.top : window;
        try {
            win.location.hash = hash;
        } catch (e) {
            // IE can give Access Denied (esp. in popup windows)
        }
    },

    doSave: function() {
        this.hiddenField.value = this.currentToken;
    },


    handleStateChange: function(token) {
        this.currentToken = token;
        this.fireEvent('change', token);
    },

    updateIFrame: function(token) {
        var html = '<html><body><div id="state">' +
                    Ext.util.Format.htmlEncode(token) +
                    '</div></body></html>',
            doc;

        try {
            doc = this.iframe.contentWindow.document;
            doc.open();
            doc.write(html);
            doc.close();
            return true;
        } catch (e) {
            return false;
        }
    },

    checkIFrame: function () {
        var me = this,
            contentWindow = me.iframe.contentWindow,
            doc, elem, oldToken, oldHash;

        if (!contentWindow || !contentWindow.document) {
            Ext.Function.defer(this.checkIFrame, 10, this);
            return;
        }

        doc = contentWindow.document;
        elem = doc.getElementById("state");
        oldToken = elem ? elem.innerText : null;
        oldHash = me.getHash();

        Ext.TaskManager.start({
            run: function () {
                var doc = contentWindow.document,
                    elem = doc.getElementById("state"),
                    newToken = elem ? elem.innerText : null,
                    newHash = me.getHash();

                if (newToken !== oldToken) {
                    oldToken = newToken;
                    me.handleStateChange(newToken);
                    me.setHash(newToken);
                    oldHash = newToken;
                    me.doSave();
                } else if (newHash !== oldHash) {
                    oldHash = newHash;
                    me.updateIFrame(newHash);
                }
            },
            interval: 50,
            scope: me
        });
        me.ready = true;
        me.fireEvent('ready', me);
    },

    startUp: function () {
        var me = this,
            hash;

        me.currentToken = me.hiddenField.value || this.getHash();

        if (me.oldIEMode) {
            me.checkIFrame();
        } else {
            hash = me.getHash();
            Ext.TaskManager.start({
                run: function () {
                    var newHash = me.getHash();
                    if (newHash !== hash) {
                        hash = newHash;
                        me.handleStateChange(hash);
                        me.doSave();
                    }
                },
                interval: 50,
                scope: me
            });
            me.ready = true;
            me.fireEvent('ready', me);
        }

    },

    /**
     * Initializes the global History instance.
     * @param {Function} [onReady] A callback function that will be called once the history
     * component is fully initialized.
     * @param {Object} [scope] The scope (`this` reference) in which the callback is executed.
     * Defaults to the browser window.
     */
    init: function (onReady, scope) {
        var me = this,
            DomHelper = Ext.DomHelper;

        if (me.ready) {
            Ext.callback(onReady, scope, [me]);
            return;
        }

        if (!Ext.isReady) {
            Ext.onReady(function() {
                me.init(onReady, scope);
            });
            return;
        }

        /*
        <form id="history-form" class="x-hide-display">
            <input type="hidden" id="x-history-field" />
            <iframe id="x-history-frame"></iframe>
        </form>
        */
        me.hiddenField = Ext.getDom(me.fieldId);
        if (!me.hiddenField) {
            me.hiddenField = Ext.getBody().createChild({
                id: Ext.id(),
                tag: 'form',
                cls: Ext.baseCSSPrefix + 'hide-display',
                children: [{
                    tag: 'input',
                    type: 'hidden',
                    id: me.fieldId
                }]
            }, false, true).firstChild;
        }

        if (me.oldIEMode) {
            me.iframe = Ext.getDom(me.iframeId);
            if (!me.iframe) {
                me.iframe = DomHelper.append(me.hiddenField.parentNode, {
                    tag: 'iframe',
                    id: me.iframeId,
                    src: Ext.SSL_SECURE_URL
                });
            }
        }

        me.addEvents(
            /**
             * @event ready
             * Fires when the Ext.util.History singleton has been initialized and is ready for use.
             * @param {Ext.util.History} The Ext.util.History singleton.
             */
            'ready',
            /**
             * @event change
             * Fires when navigation back or forwards within the local page's history occurs.
             * @param {String} token An identifier associated with the page state at that point in its history.
             */
            'change'
        );

        if (onReady) {
            me.on('ready', onReady, scope, {single: true});
        }
        me.startUp();
    },

    /**
     * Add a new token to the history stack. This can be any arbitrary value, although it would
     * commonly be the concatenation of a component id and another id marking the specific history
     * state of that component. Example usage:
     *
     *     // Handle tab changes on a TabPanel
     *     tabPanel.on('tabchange', function(tabPanel, tab){
     *          Ext.History.add(tabPanel.id + ':' + tab.id);
     *     });
     *
     * @param {String} token The value that defines a particular application-specific history state
     * @param {Boolean} [preventDuplicates=true] When true, if the passed token matches the current token
     * it will not save a new history step. Set to false if the same state can be saved more than once
     * at the same history stack location.
     */
    add: function (token, preventDup) {
        var me = this;

        if (preventDup !== false) {
            if (me.getToken() === token) {
                return true;
            }
        }

        if (me.oldIEMode) {
            return me.updateIFrame(token);
        } else {
            me.setHash(token);
            return true;
        }
    },

    /**
     * Programmatically steps back one step in browser history (equivalent to the user pressing the Back button).
     */
    back: function() {
        window.history.go(-1);
    },

    /**
     * Programmatically steps forward one step in browser history (equivalent to the user pressing the Forward button).
     */
    forward: function(){
        window.history.go(1);
    },

    /**
     * Retrieves the currently-active history token.
     * @return {String} The token
     */
    getToken: function() {
        return this.ready ? this.currentToken : this.getHash();
    }
});