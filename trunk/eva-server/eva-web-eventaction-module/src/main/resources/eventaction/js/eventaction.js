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
        height:420
    });
  
 
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
  