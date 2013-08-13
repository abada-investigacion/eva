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
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.require([
    'Ext.form.Panel',
    'Ext.form.field.Text',
    'Ext.form.action.StandardSubmit',
    'Ext.button.Button',
    'Ext.window.Window',
    'Ext.util.KeyNav',
    'Abada.Ajax',
    'Abada.Base64'
    ]);

Ext.onReady(function() {

    function formSubmit(){        
        /*authHeader=getBasicAuthentication();                
        Abada.Ajax.request({
            url: Oggi.urlJbpmServer+'rs/identity/secure/sid',
            headers:{
                Authorization: authHeader
            },
            method:'GET',
            success: function(result,request) {                               
                formSubmitPriv();
            },
            failure:function(){                            
            }
        });*/
        formSubmitPriv();
    }   
    
    function getBasicAuthentication(){
        return 'Basic '+Abada.Base64.encode(login.getForm().findField('j_username').getValue()+':'+login.getForm().findField('j_password').getValue());
    }
    
    function formSubmitPriv(){
        if(login.getForm().isValid()){
            login.getForm().submit({
                method:'POST',
                waitTitle:'Conectando',
                waitMsg:'Comprobando usuario y contrase&ntilde;a...',
                failure:function(form,action){
                },
                success:function(){      
                //window.location='main.htm';
                }
            });
        }
    }

    
    var login = Ext.create('Ext.form.Panel', {
        region:'center',        
        url : 'j_spring_security_check',               
        defaultType : 'textfield',
        monitorValid : true,
        frame:false,
        standardSubmit:true,        
        items : [ 
        {
            fieldLabel : 'Nombre de Usuario',
            name : 'j_username',
            id:'j_username',
            allowBlank : false
        }, {
            fieldLabel : 'Contrase&ntilde;a',
            name : 'j_password',
            id : 'j_password',
            allowBlank : false,
            inputType : 'password'
        } ],
        buttons:[{
            text:'Acceso',
            id:'blogin',            
            formBind:true,
            handler:formSubmit,
            tooltip:'Acceso a la aplicaci&oacute;n'
        }]
    });
   
    var win=Ext.create('Ext.window.Window',{
        id:'wlogin',
        closable:false,
        title : 'Acceder',        
        items:[login]
    });

    win.show();
    
    new Ext.util.KeyNav('wlogin', {
        'enter' : formSubmit,
        scope : login
    });

});