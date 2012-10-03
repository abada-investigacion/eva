/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


Ext.define('Eva.eventaction.js.common.gridEventaction', {
 requires: ['Abada.data.JsonStore','Ext.toolbar.Paging','Ext.ux.grid.FiltersFeature',
    'Abada.grid.RowExpander','Ext.selection.CheckboxModel', 'Ext.ux.CheckColumn',
    'Ext.util.*','Abada.grid.column.CheckBox','Ext.grid.column.Date','Abada.grid.RowExpander'],
    extend:'Ext.grid.Panel',
    config:{
        checkboxse:undefined,
        loadMask: true,
        page:14
    },
    
    columns:[    {
        header: 'id', 
        dataIndex: 'id'
        
    },  {
        header: 'EPLName', 
        dataIndex: 'EPLName',
        width:50
      
    },{
        header: 'epl', 
        dataIndex: 'epl'
        
    }
   
    , {
        header: 'fecha', 
        dataIndex: 'run',
        xtype: 'datecolumn',
        width:40,
        sortable: true,
        align: 'right',
        format: 'd-m-Y'
    }     
    
    
    
    ],
    features:[{
        ftype: 'filters',
        autoReload: true,
        local: false, 
        encode:true,
        filters: [{
            
            
            type: 'date',
            dataIndex: 'fecha',
            dateFormat : 'd/m/Y'
        }]
    }],
    forceFit:true,   
    constructor:function(config){
        this.initConfig(config);  
        if (config.url){
            this.store=Ext.create('Abada.data.JsonStore',{
                storeId:'gridStore',
                sorters: {
                    property: 'run',
                    direction: 'DESC'
                },
                fields:[
                {
                    name:'run',
                    mapping:'run',
                    type: 'date',
                    
                    dateFormat : 'c' 
                },{
                    name:'EPLName',
                    mapping:'EPLName',
                    type: 'string'
        
                },{
                    name:'epl',
                    mapping:'epl'
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