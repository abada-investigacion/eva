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
 * Provides Ext.direct support for submitting form data.
 *
 * This example illustrates usage of Ext.direct.Direct to **submit** a form through Ext.Direct.
 *
 *     var myFormPanel = new Ext.form.Panel({
 *         // configs for FormPanel
 *         title: 'Basic Information',
 *         renderTo: document.body,
 *         width: 300, height: 160,
 *         padding: 10,
 *         buttons:[{
 *             text: 'Submit',
 *             handler: function(){
 *                 myFormPanel.getForm().submit({
 *                     params: {
 *                         foo: 'bar',
 *                         uid: 34
 *                     }
 *                 });
 *             }
 *         }],
 *
 *         // configs apply to child items
 *         defaults: {anchor: '100%'},
 *         defaultType: 'textfield',
 *         items: [{
 *             fieldLabel: 'Name',
 *             name: 'name'
 *         },{
 *             fieldLabel: 'Email',
 *             name: 'email'
 *         },{
 *             fieldLabel: 'Company',
 *             name: 'company'
 *         }],
 *
 *         // configs for BasicForm
 *         api: {
 *             // The server-side method to call for load() requests
 *             load: Profile.getBasicInfo,
 *             // The server-side must mark the submit handler as a 'formHandler'
 *             submit: Profile.updateBasicInfo
 *         },
 *         // specify the order for the passed params
 *         paramOrder: ['uid', 'foo']
 *     });
 *
 * The data packet sent to the server will resemble something like:
 *
 *     {
 *         "action":"Profile","method":"updateBasicInfo","type":"rpc","tid":"6",
 *         "result":{
 *             "success":true,
 *             "id":{
 *                 "extAction":"Profile","extMethod":"updateBasicInfo",
 *                 "extType":"rpc","extTID":"6","extUpload":"false",
 *                 "name":"Aaron Conran","email":"aaron@sencha.com","company":"Sencha Inc."
 *             }
 *         }
 *     }
 *
 * The form will process a data packet returned by the server that is similar to the following:
 *
 *     // sample success packet (batched requests)
 *     [
 *         {
 *             "action":"Profile","method":"updateBasicInfo","type":"rpc","tid":3,
 *             "result":{
 *                 "success":true
 *             }
 *         }
 *     ]
 *
 *     // sample failure packet (one request)
 *     {
 *             "action":"Profile","method":"updateBasicInfo","type":"rpc","tid":"6",
 *             "result":{
 *                 "errors":{
 *                     "email":"already taken"
 *                 },
 *                 "success":false,
 *                 "foo":"bar"
 *             }
 *     }
 *
 * Also see the discussion in {@link Ext.form.action.DirectLoad}.
 */
Ext.define('Ext.form.action.DirectSubmit', {
    extend:'Ext.form.action.Submit',
    requires: ['Ext.direct.Manager'],
    alternateClassName: 'Ext.form.Action.DirectSubmit',
    alias: 'formaction.directsubmit',

    type: 'directsubmit',

    doSubmit: function() {
        var me = this,
            callback = Ext.Function.bind(me.onComplete, me),
            formEl = me.buildForm();
        me.form.api.submit(formEl, callback, me);
        Ext.removeNode(formEl);
    },

    // Direct actions have already been processed and therefore
    // we can directly set the result; Direct Actions do not have
    // a this.response property.
    processResponse: function(result) {
        return (this.result = result);
    },
    
    onComplete: function(data, response){
        if (data) {
            this.onSuccess(data);
        } else {
            this.onFailure(null);
        }
    }
});
