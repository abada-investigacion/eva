/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

Ext.define('Abada.data.JsonStore',{
    requires: ['Ext.data.proxy.Ajax','Ext.data.reader.Json'],
    extend:'Ext.data.Store',
    alias:'store.jsonstore',
    config:{
        root:'data',
        idProperty:'id',
        extraParams:undefined
    },
    constructor:function(config){
        //if (config){
        this.setConfig(config);
        this.proxy=new Ext.data.proxy.Ajax({
            url:config.url,
            reader: {
                type:'json',
                root:config.root,
                idProperty:config.idProperty
            },
            extraParams:config.extraParams
        });         
        this.callParent([config]);
        //}        
        return this;
    },
    addParameters:function(params){
        Ext.apply(this.proxy.extraParams,params);
    }
});
