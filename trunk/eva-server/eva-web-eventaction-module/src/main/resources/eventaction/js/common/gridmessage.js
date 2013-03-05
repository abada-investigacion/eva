/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


Ext.define('Eva.eventaction.js.common.gridmessage', {
    requires: ['Abada.data.JsonStore','Ext.toolbar.Paging','Ext.ux.grid.FiltersFeature',
    'Ext.util.*',
    'Ext.ux.RowExpander','Ext.selection.CheckboxModel', 'Ext.ux.CheckColumn',
    'Ext.util.*','Abada.grid.column.CheckBox','Ext.grid.column.Date'],
    extend:'Ext.grid.Panel',
    config:{
        checkboxse:undefined,
        loadMask: true,
        page:14
    },
    
    columns:[     {
        header: 'Message', 
        dataIndex: 'message',
        width:50
      
    },{
        header: 'new event', 
        dataIndex: 'newevent'
    }
    
    
    ],
    plugins: [{
        ptype: 'rowexpander',
        id: 'xml',
        rowBodyTpl : [

       '<textarea readonly="readonly" style="width:100%; height:150px; padding:15px;">{xml}</textarea></p>'
        ]
    }],
    features:[{
        ftype: 'filters',
        autoReload: true,
        local: false, 
        encode:true,
        filters: [{
            
            
            type: 'string',
            dataIndex: 'message'
        }]
    }],
    forceFit:true,   
    constructor:function(config){
        this.initConfig(config);  
        if (config.url){
            this.store=Ext.create('Abada.data.JsonStore',{
                storeId:'gridStore',
                
                fields:[
                {
                    name:'message',
                    mapping:'message'
                   
                    
                 
                },{
                    name:'xml',
                    mapping:'xml'
        
                },{
                    name:'newevent',
                    mapping:'newevent'
        
                }],
                url:this.config.url,                
                root:'data',                                
                scope:this,
                pageSize:config.page                                    
            }); 
        }
        
        if(config.checkboxse){
            this.selModel=Ext.create('Ext.selection.CheckboxModel');
        } else{
            this.selModel= Ext.create('Ext.selection.RowModel');
        }
        
        this.bbar= Ext.create('Ext.toolbar.Paging', {
            store: this.store,
            pageSize: this.store.pageSize
        });
        
        this.callParent([config]);
    },
    renderTo: Ext.getBody()    
});