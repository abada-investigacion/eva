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
 * A specialized {@link Ext.util.KeyNav} implementation for navigating a {@link Ext.view.BoundList} using
 * the keyboard. The up, down, pageup, pagedown, home, and end keys move the active highlight
 * through the list. The enter key invokes the selection model's select action using the highlighted item.
 */
Ext.define('Ext.view.BoundListKeyNav', {
    extend: 'Ext.util.KeyNav',
    requires: 'Ext.view.BoundList',

    /**
     * @cfg {Ext.view.BoundList} boundList (required)
     * The {@link Ext.view.BoundList} instance for which key navigation will be managed.
     */

    constructor: function(el, config) {
        var me = this;
        me.boundList = config.boundList;
        me.callParent([el, Ext.apply({}, config, me.defaultHandlers)]);
    },

    defaultHandlers: {
        up: function() {
            var me = this,
                boundList = me.boundList,
                allItems = boundList.all,
                oldItem = boundList.highlightedItem,
                oldItemIdx = oldItem ? boundList.indexOf(oldItem) : -1,
                newItemIdx = oldItemIdx > 0 ? oldItemIdx - 1 : allItems.getCount() - 1; //wraps around
            me.highlightAt(newItemIdx);
        },

        down: function() {
            var me = this,
                boundList = me.boundList,
                allItems = boundList.all,
                oldItem = boundList.highlightedItem,
                oldItemIdx = oldItem ? boundList.indexOf(oldItem) : -1,
                newItemIdx = oldItemIdx < allItems.getCount() - 1 ? oldItemIdx + 1 : 0; //wraps around
            me.highlightAt(newItemIdx);
        },

        pageup: function() {
            //TODO
        },

        pagedown: function() {
            //TODO
        },

        home: function() {
            this.highlightAt(0);
        },

        end: function() {
            var me = this;
            me.highlightAt(me.boundList.all.getCount() - 1);
        },

        enter: function(e) {
            this.selectHighlighted(e);
        }
    },

    /**
     * Highlights the item at the given index.
     * @param {Number} index
     */
    highlightAt: function(index) {
        var boundList = this.boundList,
            item = boundList.all.item(index);
        if (item) {
            item = item.dom;
            boundList.highlightItem(item);
            boundList.getTargetEl().scrollChildIntoView(item, false);
        }
    },

    /**
     * Triggers selection of the currently highlighted item according to the behavior of
     * the configured SelectionModel.
     */
    selectHighlighted: function(e) {
        var me = this,
            boundList = me.boundList,
            highlighted = boundList.highlightedItem,
            selModel = boundList.getSelectionModel();
        if (highlighted) {
            selModel.selectWithEvent(boundList.getRecord(highlighted), e);
        }
    }

});