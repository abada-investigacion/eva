/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


Ext.define('Abada.form.field.SimpleGroupingComboBox', {
    extend: 'Abada.form.field.GroupingComboBox',    
    alias: ['widget.simplegroupingcombobox', 'widget.simplegroupingcombo'],
    config:{
        displayField:'value',
        valueField:'id',        
        editable:false,
        mode: 'local',
        triggerAction: 'all',
        groupField:['groupingField']
    },
    constructor:function(config){
        this.initConfig(config);
                
        if (config.url){
            this.store=Ext.create('Abada.data.JsonStore',{
                fields:[{
                    name:'id'
                },{
                    name:'value'
                },{
                    name:'groupingField'
                }],
                url:config.url,
                root:'data',
                idProperty:'id',                
                scope:this,
                sorters:[{
                    property:'groupingField',
                    direction:'ASC'
                }],                
                listeners:{
                    load:function(store,records, successful,operation){
                        store.scope.fireEvent('load',store.scope,records, successful, operation);
                    }
                }
            });
        }
        this.addEvents('load');
        this.callParent([config]);//ComboBox.superclass.constructor.call(this,cfg);
        return this;
    },
    loadStore:function(){
        if (this.store)
            this.store.load();
    },
    getStore:function(){
        return this.store;
    }
});