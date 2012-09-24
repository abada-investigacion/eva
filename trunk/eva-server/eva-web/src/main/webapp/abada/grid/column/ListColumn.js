/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Columna para representar los valores de una lista
 */
Ext.define('Abada.grid.column.List',{
    extend:'Ext.grid.column.Template',
    alias:'widget.listcolumn',
    constructor:function(config){
        this.setConfig(config);
        tplTemplate = '<tpl if="'+this.dataIndex+'" >'+
        '<tpl for="'+this.dataIndex+'">'+
        '<p>{.}</p>'+
        '</tpl>'+
        '</tpl>';
        this.tpl=new Ext.XTemplate(tplTemplate);
        this.callParent([config]);
    }
});

/*ListColumn = Ext.extend(Ext.grid.TemplateColumn,{
    constructor:function(cfg){
        Ext.apply(this, cfg);
        tplTemplate = '<tpl if="'+this.dataIndex+'" >'+
        '<tpl for="'+this.dataIndex+'">'+
        '<p>{.}</p>'+
        '</tpl>'+
        '</tpl>';
        this.tpl=new Ext.XTemplate(tplTemplate);
        ListColumn.superclass.constructor.call(this,cfg);
    }
});*/