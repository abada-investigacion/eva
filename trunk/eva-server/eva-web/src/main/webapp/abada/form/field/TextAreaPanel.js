/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Date que se instancia el dia de hoy
 */
Ext.define('Abada.form.field.TextAreaPanel',{
    requires: ['Ext.form.field.TextArea'],
    extend:'Ext.form.Panel',
    config:{
        collapsible:true,
        titleCollapse: true,
        plain:true,
        border:false,
        collapsed:true,
        allowBlank:true,
        preventMark:false,
        maxLength:1204
    },
    constructor:function(config){
        this.initConfig(config);
        this.callParent([config]);
      
    },
    initComponent:function(){                
        this.taText=new Ext.form.TextArea({            
            allowBlank:this.allowBlank,
            preventMark:this.preventMark,
            width:this.width,
            maxLength:this.maxLength
        });
        this.items=[this.taText];

        this.callParent();
    },
    getValue:function(){
        return this.taText.getValue();
    },
    setValue:function(text){
        this.taText.setValue(text);
    },
    isValid:function(preventMark){
        return this.taText.isValid(preventMark);
    }
});


/*/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TextAreaPanel, panel con un textarea en un interior
 *//*
TextAreaPanel=Ext.extend(Ext.Panel,{
    collapsible:true,
    titleCollapse: true,
    plain:true,
    border:false,
    collapsed:true,
    allowBlank:true,
    preventMark:false,
    maxLength:1204,
    initComponent:function(){                
        this.taText=new Ext.form.TextArea({            
            allowBlank:this.allowBlank,
            preventMark:this.preventMark,
            width:this.width,
            maxLength:this.maxLength
        });
        this.items=[this.taText];

        TextAreaPanel.superclass.initComponent.call(this);
    },
    getValue:function(){
        return this.taText.getValue();
    },
    setValue:function(text){
        this.taText.setValue(text);
    },
    isValid:function(preventMark){
        return this.taText.isValid(preventMark);
    }
});

;*/