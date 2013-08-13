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
 */
Ext.define('Ext.layout.component.field.Slider', {

    /* Begin Definitions */

    alias: ['layout.sliderfield'],

    extend: 'Ext.layout.component.field.Field',

    /* End Definitions */

    type: 'sliderfield',

    beginLayout: function(ownerContext) {
        this.callParent(arguments);

        ownerContext.endElContext   = ownerContext.getEl('endEl');
        ownerContext.innerElContext = ownerContext.getEl('innerEl');
        ownerContext.bodyElContext  = ownerContext.getEl('bodyEl');
    },

    publishInnerHeight: function (ownerContext, height) {
        var innerHeight = height - this.measureLabelErrorHeight(ownerContext),
            endElPad,
            inputPad;
        if (this.owner.vertical) {
            endElPad = ownerContext.endElContext.getPaddingInfo();
            inputPad = ownerContext.inputContext.getPaddingInfo();
            ownerContext.innerElContext.setHeight(innerHeight - inputPad.height - endElPad.height);
        } else {
            ownerContext.bodyElContext.setHeight(innerHeight);
        }
    },

    publishInnerWidth: function (ownerContext, width) {
        if (!this.owner.vertical) {
            var endElPad = ownerContext.endElContext.getPaddingInfo(),
                inputPad = ownerContext.inputContext.getPaddingInfo();

            ownerContext.innerElContext.setWidth(width - inputPad.left - endElPad.right - ownerContext.labelContext.getProp('width'));
        }
    },

    beginLayoutFixed: function(ownerContext, width, suffix) {
        var me = this,
            ieInputWidthAdjustment = me.ieInputWidthAdjustment;

        if (ieInputWidthAdjustment) {
            // adjust for IE 6/7 strict content-box model
            // RTL: This might have to be padding-left unless the senses of the padding styles switch when in RTL mode.
            me.owner.bodyEl.setStyle('padding-right', ieInputWidthAdjustment + 'px');
        }

        me.callParent(arguments);
    }
});
