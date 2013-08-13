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
 * Component layout for editors
 * @private
 */
Ext.define('Ext.layout.container.Editor', {

    /* Begin Definitions */

    alias: 'layout.editor',

    extend: 'Ext.layout.container.Container',

    /* End Definitions */

    autoSizeDefault: {
        width: 'field',
        height: 'field'    
    },

    getItemSizePolicy: function (item) {
        var me = this,
            autoSize = me.owner.autoSize;

        return me.sizePolicy || (me.sizePolicy = {
            setsWidth:  autoSize && autoSize.width  === 'boundEl' ? 1 : 0,
            setsHeight: autoSize && autoSize.height === 'boundEl' ? 1 : 0
        });
    },

    calculate: function(ownerContext) {
        var me = this,
            owner = me.owner,
            autoSize = owner.autoSize,
            fieldWidth,
            fieldHeight;
            
        if (autoSize === true) {
            autoSize = me.autoSizeDefault;
        }

        // Calculate size of both Editor, and its owned Field
        if (autoSize) {
            fieldWidth  = me.getDimension(owner, autoSize.width,  'getWidth',  owner.width);
            fieldHeight = me.getDimension(owner, autoSize.height, 'getHeight', owner.height);
        }

        // Set Field size
        ownerContext.childItems[0].setSize(fieldWidth, fieldHeight);

        // Bypass validity checking. Container layouts should not usually set their owner's size.
        ownerContext.setWidth(fieldWidth);
        ownerContext.setHeight(fieldHeight);

        // This is a Container layout, so publish content size
        ownerContext.setContentSize(fieldWidth || owner.field.getWidth(),
                                    fieldHeight || owner.field.getHeight());
    },

    getDimension: function(owner, type, getMethod, ownerSize){
        switch (type) {
            // Size to boundEl's dimension
            case 'boundEl':
                return owner.boundEl[getMethod]();

            // Auto size (shrink wrap the Field's size
            case 'field':
                return undefined;

            // Size to the Editor's configured size
            default:
                return ownerSize;
        }
    }
});