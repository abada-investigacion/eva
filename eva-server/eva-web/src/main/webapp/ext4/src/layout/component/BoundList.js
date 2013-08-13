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
 * Component layout for {@link Ext.view.BoundList}.
 * @private
 */
Ext.define('Ext.layout.component.BoundList', {
    extend: 'Ext.layout.component.Auto',
    alias: 'layout.boundlist',

    type: 'component',
    
    beginLayout: function(ownerContext) {
        var me = this,
            owner = me.owner,
            toolbar = owner.pagingToolbar;

        me.callParent(arguments);
        
        if (owner.floating) {
            ownerContext.savedXY = owner.el.getXY();
            // move way offscreen to prevent any constraining
            owner.el.setXY([-9999, -9999]);
        }
        
        if (toolbar) {
            ownerContext.toolbarContext = ownerContext.context.getCmp(toolbar);
        }
        ownerContext.listContext = ownerContext.getEl('listEl');
    },
    
    beginLayoutCycle: function(ownerContext){
        var owner = this.owner;
        
        this.callParent(arguments);
        if (ownerContext.heightModel.auto) {
            // Set the el/listEl to be autoHeight since they may have been previously sized
            // by another layout process. If the el was at maxHeight first, the listEl will
            // always size to the maxHeight regardless of the content.
            owner.el.setHeight('auto');
            owner.listEl.setHeight('auto');
        }
    },

    getLayoutItems: function() {
        var toolbar = this.owner.pagingToolbar;
        return toolbar ? [toolbar] : [];
    },
    
    isValidParent: function() {
        // this only ever gets called with the toolbar, since it's rendered inside we
        // know the parent is always valid
        return true;
    },

    finishedLayout: function(ownerContext) {
        var xy = ownerContext.savedXY;
        
        this.callParent(arguments);
        if (xy) {
            this.owner.el.setXY(xy);
        }
    },
    
    measureContentWidth: function(ownerContext) {
        return this.owner.listEl.getWidth();
    },
    
    measureContentHeight: function(ownerContext) {
        return this.owner.listEl.getHeight();
    },
    
    publishInnerHeight: function(ownerContext, height) {
        var toolbar = ownerContext.toolbarContext,
            toolbarHeight = 0;
            
        if (toolbar) {
            toolbarHeight = toolbar.getProp('height');
        }
        
        if (toolbarHeight === undefined) {
            this.done = false;
        } else {
            ownerContext.listContext.setHeight(height - ownerContext.getFrameInfo().height - toolbarHeight);
        }
    },
    
    calculateOwnerHeightFromContentHeight: function(ownerContext){
        var height = this.callParent(arguments),
            toolbar = ownerContext.toolbarContext;
            
        if (toolbar) {
            height += toolbar.getProp('height');
        }
        return height;
    }
});