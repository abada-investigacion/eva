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
  