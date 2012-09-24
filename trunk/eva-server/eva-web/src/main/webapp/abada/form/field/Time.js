/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

Ext.define('Abada.form.field.Time',{
    extend:'Ext.form.field.Time',
    config:{
        format:'H:i',
        hideLabel:false,
        editable:false,
        allowBlank:false,
        increment: 60
    },
    constructor:function(config){
        this.initConfig(config);
        this.callParent([config]);
    }
});

/*TimeField=Ext.extend(Ext.form.TimeField,{
    format:'H:i',
    hideLabel:false,
    editable:false,
    allowBlank:false,
    increment: 60,
    constructor:function(cfg){
        Ext.apply(this, cfg);
        TimeField.superclass.constructor.call(this,cfg);
        //pongo hora actual
        this.onLoadedStore(this.getStore());
    },
    onLoadedStore:function(store){
        data={            
            field1: '23:59'
        };
        record=new store.recordType(data);
        store.add(record);

        this.setValue(new Date());
    }
});*/