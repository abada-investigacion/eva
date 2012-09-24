/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('Abada.toolbar.ToolbarInsertUpdateDelete', {
    requires: ['Ext.button.Button'],
    extend:'Ext.toolbar.Toolbar',
    config:{
       
    },
   
    initComponent : function(config){

        this.items=[Ext.create('Ext.button.Button',{
            text: 'Insertar',
            id:'insertar',
            icon:getRelativeURI('images/custom/add.gif'),
            scope:this,
            handler : this.submitInsert
        }),Ext.create('Ext.button.Button',{
            text:'Modificar',
            id:'modificar',
            icon:getRelativeURI('images/custom/changestatus.png'),
            scope:this,
            handler: this.submitUpdate
        }),Ext.create('Ext.button.Button',{
            text:'Borrar',
            id:'Borrar',
            icon:getRelativeURI('images/custom/delete.gif'),
            scope:this,
            handler: this.submitDelete
        })];

        this.addEvents('submitInsert','submitUpdate','submitDelete');

        this.callParent([config]);

    },/*Evento que dispara submitInsert*/
    submitInsert:function(){
        this.fireEvent('submitInsert');
    },/*Evento que dispara submitUpdate*/
    submitUpdate:function(){
        this.fireEvent('submitUpdate');
    },/*Evento que dispara submitDelete*/
    submitDelete:function(){
        this.fireEvent('submitDelete');
    }



});

