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
 * DD implementation for Panels.
 * @private
 */
Ext.define('Ext.panel.DD', {
    extend: 'Ext.dd.DragSource',
    requires: ['Ext.panel.Proxy'],

    constructor : function(panel, cfg){
        var me = this;
        
        me.panel = panel;
        me.dragData = {panel: panel};
        me.panelProxy = new Ext.panel.Proxy(panel, cfg);
        me.proxy = me.panelProxy.proxy;

        me.callParent([panel.el, cfg]);
        me.setupEl(panel);
    },
    
    setupEl: function(panel){
        var me = this,
            header = panel.header,
            el = panel.body;
            
        if (header) {
            me.setHandleElId(header.id);
            el = header.el;
        }
        if (el) {
            el.setStyle('cursor', 'move');
            me.scroll = false;
        } else {
            // boxready fires after first layout, so we'll definitely be rendered
            panel.on('boxready', me.setupEl, me, {single: true});
        }
    },

    showFrame: Ext.emptyFn,
    startDrag: Ext.emptyFn,
    
    b4StartDrag: function(x, y) {
        this.panelProxy.show();
    },
    
    b4MouseDown: function(e) {
        var x = e.getPageX(),
            y = e.getPageY();
            
        this.autoOffset(x, y);
    },
    
    onInitDrag : function(x, y){
        this.onStartDrag(x, y);
        return true;
    },
    
    createFrame : Ext.emptyFn,
    
    getDragEl : function(e){
        return this.panelProxy.ghost.el.dom;
    },
    
    endDrag : function(e){
        this.panelProxy.hide();
        this.panel.saveState();
    },

    autoOffset : function(x, y) {
        x -= this.startPageX;
        y -= this.startPageY;
        this.setDelta(x, y);
    },
    
    // Override this, we don't want to repair on an "invalid" drop, the panel
    // should main it's position
    onInvalidDrop: function(target, e, id) {
        var me = this;
        
        me.beforeInvalidDrop(target, e, id);
        if (me.cachedTarget) {
            if(me.cachedTarget.isNotifyTarget){
                me.cachedTarget.notifyOut(me, e, me.dragData);
            }
            me.cacheTarget = null;
        }

        if (me.afterInvalidDrop) {
            /**
             * An empty function by default, but provided so that you can perform a custom action
             * after an invalid drop has occurred by providing an implementation.
             * @param {Event} e The event object
             * @param {String} id The id of the dropped element
             * @method afterInvalidDrop
             */
            me.afterInvalidDrop(e, id);
        }
    }
});