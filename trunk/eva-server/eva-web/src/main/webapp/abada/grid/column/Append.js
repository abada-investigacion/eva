/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/***
 * Columna que concatena los valores de los nombres de las columnas que aparezca en dataIndex,
 * siendo dataIndex un array
 */
Ext.define('Abada.grid.column.Append',{
    extend:'Ext.grid.column.Template',
    alias:'widget.appendcolumn',
    constructor:function(config){
        this.setConfig(config);
        var tplTemplate=this.generateTemplate(this.dataIndex);
        this.tpl=new Ext.XTemplate(tplTemplate);
        this.callParent([config]);
    },
    generateTemplate:function(array){
        var result='';
        for (i=0;i<array.length;i++){
            result+='<tpl if="'+array[i]+'" >{'+array[i]+'} </tpl>';
        }
        return result;
    }
});

/*AppendColumn = Ext.extend(Ext.grid.TemplateColumn,{
    constructor:function(cfg){
        Ext.apply(this, cfg);
        var tplTemplate=this.generateTemplate(this.dataIndex);
        this.tpl=new Ext.XTemplate(tplTemplate);
        AppendColumn.superclass.constructor.call(this,cfg);
    },
    generateTemplate:function(array){
        var result='';
        for (i=0;i<array.length;i++){
            result+='<tpl if="'+array[i]+'" >{'+array[i]+'} </tpl>';
        }
        return result;
    }
});*/