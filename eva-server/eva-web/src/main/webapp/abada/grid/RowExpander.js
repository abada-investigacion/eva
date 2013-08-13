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


/****
 * Expander que hace una llamada Ajax a una url, espera una respuesta
 * igual que la que espera un jsonstore
 */

Ext.define('Abada.grid.RowExpander',{
    requires: ['Abada.Ajax','Ext.JSON','Ext.grid.feature.RowBody','Ext.grid.feature.RowWrap'],
    alias:'plugin.abada.rowexpander',
    extend:'Ext.ux.RowExpander',
    config:{
        url:undefined,
        method:'GET',
        searchFields:[]
    },
    constructor:function(config){  
        this.initConfig(config);
        this.callParent(arguments);
        this.tpl=Ext.create('Ext.XTemplate', this.rowBodyTpl);
    },
    getURLData:function(row,nextBd,record,rowNode){
        Abada.Ajax.requestJsonObject({
            url:this.url,
            scope:this,
            method:this.method,
            params:{
                search:Ext.JSON.encode(this.generateSearchParam(record))
            },
            failure:function(){                
            },
            success:function(data){
                if (data){
                    var xgb=nextBd.first().first();
                    var html=this.tpl.applyTemplate(data);
                    xgb.update(html);
                    this.onSuccessURLData(row,nextBd,record,rowNode);
                }
            }
        });        
    },
    onSuccessURLData:function(row,nextBd,record,rowNode){
        row.removeCls(this.rowCollapsedCls);
        nextBd.removeCls(this.rowBodyHiddenCls);
        this.recordsExpanded[record.internalId] = true;
        this.view.fireEvent('expandbody', rowNode, record, nextBd.dom);
    },
    toggleRow: function(rowIdx) {
        var rowNode = this.view.getNode(rowIdx),
        row = Ext.get(rowNode),
        nextBd = Ext.get(row).down(this.rowBodyTrSelector),
        record = this.view.getRecord(rowNode);
        
        if (row.hasCls(this.rowCollapsedCls)) {
            if (this.url)
                this.getURLData(row,nextBd,record,rowNode);            
            else
                this.onSuccessURLData(row,nextBd,record,rowNode);
        } else {
            row.addCls(this.rowCollapsedCls);
            nextBd.addCls(this.rowBodyHiddenCls);
            this.recordsExpanded[record.internalId] = false;
            this.view.fireEvent('collapsebody', rowNode, record, nextBd.dom);
        }
        this.view.up('gridpanel').invalidateScroller();
    },
    generateSearchParam:function(record){
        var result=[];
        for (var i=0;i<this.searchFields.length;i++){
            result.push({
                value:record.get(this.searchFields[i]),
                key:this.searchFields[i]
            });
        }
        return result;
    } ,
    collapseAll:function(){
        var n= this.view.getNodes();
        for(i=0;i<n.length;i++){
            rowNode = n[i],
            row = Ext.get(rowNode),
            nextBd = Ext.get(row).down(this.rowBodyTrSelector),
            record = this.view.getRecord(rowNode);
            row.addCls(this.rowCollapsedCls);
            nextBd.addCls(this.rowBodyHiddenCls);
            this.recordsExpanded[record.internalId] = false;
            this.view.fireEvent('collapsebody', rowNode, record, nextBd.dom);
        }
        this.view.up('gridpanel').invalidateScroller();
        
    }
});