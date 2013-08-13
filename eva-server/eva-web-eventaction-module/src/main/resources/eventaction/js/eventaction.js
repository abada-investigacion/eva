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
    'Ext.form.Panel','Ext.form.field.Checkbox','Abada.Ajax','Ext.JSON','Ext.Ajax',
    'Ext.layout.container.Table','Abada.form.field.ComboBox','Eva.eventaction.js.common.gridEventaction','Abada.grid.RowExpander','Abada.form.field.SimpleGroupingComboBox'

    ])


Ext.onReady(function(){
    var grid=Ext.create('Eva.eventaction.js.common.gridEventaction',{
        url:getRelativeURI('/event/historicepl.do'),
        height:420,
        listeners : { 
            itemdblclick:function( view, record, item, index,  e,  options)
            { 
                var id=record.data.id;
                var messageGrid=Ext.create('Eva.eventaction.js.common.gridmessage',{
                    url:getRelativeURI('/event/gridmessage.do'),
                    height:400,
                    checkboxse:false,
                    page:14
                });

                messageGrid.getStore().load({
                    params:{
                        filter:'[{"type":"string","value":"'+id+'","field":"id"}]',
                        start:0,
                        limit: 14
                    }
                });
    
    
                var toolbar=Ext.create('Abada.tab.panel.TabPanel',{
                    width: 800,
     
                    renderTo: Ext.getBody(),
                    activeTab: 0,
                    frame:false,
                    defaults:{
                        autoHeight: true,
                        layout:'fit'
                    },
                    items:[
                    {
                        xtype: 'panel',
                        title: 'Event',
                        items:[messageGrid]

                    },

            
                    ], 
                    renderTo : Ext.getBody()
                });
        
                var winda=Ext.create('Ext.window.Window',{
                    id:'windo',
                    autoScroll:false,
                    closable:true,
                    modal:true,
                    items:[toolbar]
                });

                winda.show();
            
       
 
            }
                                                        
        }
    });
  
    var sm = grid.getSelectionModel();

    grid.getStore().load({
        params:{
            start:0,
            limit: 15
        }
    });
  

    var panel = Ext.create('Ext.panel.Panel', {
        frame : true,
        autoWidth:true,
        autoHeight:true,
        title:'EPL',
        items :[grid]//ponemos el grid
    });
    setCentralPanel(panel); 

   
}); 
  