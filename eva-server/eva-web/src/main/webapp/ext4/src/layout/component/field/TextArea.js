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
 * Layout class for {@link Ext.form.field.TextArea} fields. Handles sizing the textarea field.
 * @private
 */
Ext.define('Ext.layout.component.field.TextArea', {
    extend: 'Ext.layout.component.field.Text',
    alias: 'layout.textareafield',

    type: 'textareafield',
    
    canGrowWidth: false,
    
    naturalSizingProp: 'cols',
    
    beginLayout: function(ownerContext){
        this.callParent(arguments);
        ownerContext.target.inputEl.setStyle('height', '');
    },

    measureContentHeight: function (ownerContext) {
        var me = this,
            owner = me.owner,
            height = me.callParent(arguments),
            inputContext, inputEl, value, max, curWidth, calcHeight;

        if (owner.grow && !ownerContext.state.growHandled) {
            inputContext = ownerContext.inputContext;
            inputEl = owner.inputEl;
            curWidth = inputEl.getWidth(true); //subtract border/padding to get the available width for the text

            // Get and normalize the field value for measurement
            value = Ext.util.Format.htmlEncode(inputEl.dom.value) || '&#160;';
            value += owner.growAppend;
            
            // Translate newlines to <br> tags
            value = value.replace(/\n/g, '<br/>');

            // Find the height that contains the whole text value
            calcHeight = Ext.util.TextMetrics.measure(inputEl, value, curWidth).height +
                         inputContext.getBorderInfo().height + inputContext.getPaddingInfo().height;

            // Constrain
            calcHeight = Ext.Number.constrain(calcHeight, owner.growMin, owner.growMax);
            inputContext.setHeight(calcHeight);
            ownerContext.state.growHandled = true;
            
            // Now that we've set the inputContext, we need to recalculate the width
            inputContext.domBlock(me, 'height');
            height = NaN;
        }
        return height;
    }
});
