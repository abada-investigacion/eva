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
    
    columns:[ {
        header: 'id', 
        dataIndex: 'id',
        width:50,
        hidden:true
      
    },    {
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