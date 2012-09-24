/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Date que se instancia el dia de hoy
 */
Ext.define('Abada.form.field.Date',{
    extend:'Ext.form.field.Date',
    config:{
        format:'d/m/Y',
        hideLabel:false,
        editable:false,
        allowBlank:false
    },
    constructor:function(config){
        this.initConfig(config);
        this.callParent([config]);
        if (!this.value)
            this.setValue(new Date());
    }
});

/*DateField=Ext.extend(Ext.form.DateField,{
    format:'d/m/Y',
    hideLabel:false,
    editable:false,
    allowBlank:false,
    constructor:function(cfg){
        Ext.apply(this, cfg);
        DateField.superclass.constructor.call(this,cfg);
        if (!this.value)
            this.setValue(new Date());
    }
});*/