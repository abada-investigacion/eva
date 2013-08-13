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
 * A class which handles submission of data from {@link Ext.form.Basic Form}s using a standard `<form>` element submit.
 * It does not handle the response from the submit.
 *
 * If validation of the form fields fails, the Form's afterAction method will be called. Otherwise, afterAction will not
 * be called.
 *
 * Instances of this class are only created by a {@link Ext.form.Basic Form} when
 * {@link Ext.form.Basic#submit submit}ting, when the form's {@link Ext.form.Basic#standardSubmit} config option is true.
 */
Ext.define('Ext.form.action.StandardSubmit', {
    extend:'Ext.form.action.Submit',
    alias: 'formaction.standardsubmit',

    /**
     * @cfg {String} target
     * Optional target attribute to be used for the form when submitting.
     * 
     * Defaults to the current window/frame.
     */

    /**
     * @private
     * Perform the form submit. Creates and submits a temporary form element containing an input element for each
     * field value returned by {@link Ext.form.Basic#getValues}, plus any configured {@link #params params} or
     * {@link Ext.form.Basic#baseParams baseParams}.
     */
    doSubmit: function() {
        var form = this.buildForm();
        form.submit();
        Ext.removeNode(form);
    }

});
