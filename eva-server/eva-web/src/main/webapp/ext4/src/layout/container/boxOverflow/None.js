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
 * @private
 * Base class for Box Layout overflow handlers. These specialized classes are invoked when a Box Layout
 * (either an HBox or a VBox) has child items that are either too wide (for HBox) or too tall (for VBox)
 * for its container.
 */
Ext.define('Ext.layout.container.boxOverflow.None', {
    alternateClassName: 'Ext.layout.boxOverflow.None',
    
    constructor: function(layout, config) {
        this.layout = layout;
        Ext.apply(this, config);
    },

    handleOverflow: Ext.emptyFn,

    clearOverflow: Ext.emptyFn,

    beginLayout: Ext.emptyFn,
    beginLayoutCycle: Ext.emptyFn,
    finishedLayout: Ext.emptyFn,

    completeLayout: function (ownerContext) {
        var me = this,
            plan = ownerContext.state.boxPlan,
            overflow;

        if (plan && plan.tooNarrow) {
            overflow = me.handleOverflow(ownerContext);

            if (overflow) {
                if (overflow.reservedSpace) {
                    me.layout.publishInnerCtSize(ownerContext, overflow.reservedSpace);
                }

                // TODO: If we need to use the code below then we will need to pass along
                // the new targetSize as state and use it calculate somehow...
                //
                //if (overflow.recalculate) {
                //    ownerContext.invalidate({
                //        state: {
                //            overflow: overflow
                //        }
                //    });
                //}
            }
        } else {
            me.clearOverflow();
        }
    },

    onRemove: Ext.emptyFn,

    /**
     * @private
     * Normalizes an item reference, string id or numerical index into a reference to the item
     * @param {Ext.Component/String/Number} item The item reference, id or index
     * @return {Ext.Component} The item
     */
    getItem: function(item) {
        return this.layout.owner.getComponent(item);
    },
    
    getOwnerType: function(owner){
        var type;
        if (owner.isToolbar) {
            type = 'toolbar';
        } else if (owner.isTabBar) {
            type = 'tabbar';
        } else if (owner.isMenu) {
            type = 'menu';
        } else {
            type = owner.getXType();
        }
        
        return type;
    },

    getPrefixConfig: Ext.emptyFn,
    getSuffixConfig: Ext.emptyFn,
    getOverflowCls: function() {
        return '';
    }
});