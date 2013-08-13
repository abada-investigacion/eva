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
 * Component layout for grid column headers which have a title element at the top followed by content.
 * @private
 */
Ext.define('Ext.grid.ColumnComponentLayout', {
    extend: 'Ext.layout.component.Auto',
    alias: 'layout.columncomponent',

    type: 'columncomponent',

    setWidthInDom: true,

    getContentHeight : function(ownerContext) {
        // If we are a group header return container layout's contentHeight, else default to AutoComponent's answer
        return this.owner.isGroupHeader ? ownerContext.getProp('contentHeight') : this.callParent(arguments);
    },

    calculateOwnerHeightFromContentHeight: function (ownerContext, contentHeight) {
        var result = this.callParent(arguments);
        if (this.owner.isGroupHeader) {
            result += this.owner.titleEl.dom.offsetHeight;
        }
        return result;
    },
    
    getContentWidth : function(ownerContext) {
        // If we are a group header return container layout's contentHeight, else default to AutoComponent's answer
        return this.owner.isGroupHeader ? ownerContext.getProp('contentWidth') : this.callParent(arguments);
    },

    calculateOwnerWidthFromContentWidth: function (ownerContext, contentWidth) {
        return contentWidth + ownerContext.getPaddingInfo().width;
    }
});