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
 * Provides {@link Ext.direct.Manager} support for loading form data.
 *
 * This example illustrates usage of Ext.direct.Direct to **load** a form through Ext.Direct.
 *
 *     var myFormPanel = new Ext.form.Panel({
 *         // configs for FormPanel
 *         title: 'Basic Information',
 *         renderTo: document.body,
 *         width: 300, height: 160,
 *         padding: 10,
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
 *     // load the form
 *     myFormPanel.getForm().load({
 *         // pass 2 arguments to server side getBasicInfo method (len=2)
 *         params: {
 *             foo: 'bar',
 *             uid: 34
 *         }
 *     });
 *
 * The data packet sent to the server will resemble something like:
 *
 *     [
 *         {
 *             "action":"Profile","method":"getBasicInfo","type":"rpc","tid":2,
 *             "data":[34,"bar"] // note the order of the params
 *         }
 *     ]
 *
 * The form will process a data packet returned by the server that is similar to the following format:
 *
 *     [
 *         {
 *             "action":"Profile","method":"getBasicInfo","type":"rpc","tid":2,
 *             "result":{
 *                 "success":true,
 *                 "data":{
 *                     "name":"Fred Flintstone",
 *                     "company":"Slate Rock and Gravel",
 *                     "email":"fred.flintstone@slaterg.com"
 *                 }
 *             }
 *         }
 *     ]
 */
Ext.define('Ext.form.action.DirectLoad', {
    extend:'Ext.form.action.Load',
    requires: ['Ext.direct.Manager'],
    alternateClassName: 'Ext.form.Action.DirectLoad',
    alias: 'formaction.directload',

    type: 'directload',

    run: function() {
        var me = this,
            form = me.form,
            fn = form.api.load,
            method = fn.directCfg.method,
            args = method.getArgs(me.getParams(), form.paramOrder, form.paramsAsHash);
            
        args.push(me.onComplete, me);
        fn.apply(window, args);
    },

    // Direct actions have already been processed and therefore
    // we can directly set the result; Direct Actions do not have
    // a this.response property.
    processResponse: function(result) {
        return (this.result = result);
    },

    onComplete: function(data, response) {
        if (data) {
            this.onSuccess(data);
        } else {
            this.onFailure(null);
        }
    }
});


