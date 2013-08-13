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
 * A basic hidden field for storing hidden values in forms that need to be passed in the form submit.
 *
 * This creates an actual input element with type="submit" in the DOM. While its label is
 * {@link #hideLabel not rendered} by default, it is still a real component and may be sized according
 * to its owner container's layout.
 *
 * Because of this, in most cases it is more convenient and less problematic to simply
 * {@link Ext.form.action.Action#params pass hidden parameters} directly when
 * {@link Ext.form.Basic#submit submitting the form}.
 *
 * Example:
 *
 *     new Ext.form.Panel({
 *         title: 'My Form',
 *         items: [{
 *             xtype: 'textfield',
 *             fieldLabel: 'Text Field',
 *             name: 'text_field',
 *             value: 'value from text field'
 *         }, {
 *             xtype: 'hiddenfield',
 *             name: 'hidden_field_1',
 *             value: 'value from hidden field'
 *         }],
 *
 *         buttons: [{
 *             text: 'Submit',
 *             handler: function() {
 *                 this.up('form').getForm().submit({
 *                     params: {
 *                         hidden_field_2: 'value from submit call'
 *                     }
 *                 });
 *             }
 *         }]
 *     });
 *
 * Submitting the above form will result in three values sent to the server:
 *
 *     text_field=value+from+text+field&hidden;_field_1=value+from+hidden+field&hidden_field_2=value+from+submit+call
 *
 */
Ext.define('Ext.form.field.Hidden', {
    extend:'Ext.form.field.Base',
    alias: ['widget.hiddenfield', 'widget.hidden'],
    alternateClassName: 'Ext.form.Hidden',

    // private
    inputType : 'hidden',
    hideLabel: true,
    
    initComponent: function(){
        this.formItemCls += '-hidden';
        this.callParent();    
    },
    
    /**
     * @private
     * Override. Treat undefined and null values as equal to an empty string value.
     */
    isEqual: function(value1, value2) {
        return this.isEqualAsString(value1, value2);
    },

    // These are all private overrides
    initEvents: Ext.emptyFn,
    setSize : Ext.emptyFn,
    setWidth : Ext.emptyFn,
    setHeight : Ext.emptyFn,
    setPosition : Ext.emptyFn,
    setPagePosition : Ext.emptyFn,
    markInvalid : Ext.emptyFn,
    clearInvalid : Ext.emptyFn
});
