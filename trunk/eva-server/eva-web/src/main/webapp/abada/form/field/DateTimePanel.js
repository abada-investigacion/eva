/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Panel para seleccionar una fecha y hora 
 */
Ext.define('Abada.form.field.DateTimePanel',{
     requires: ['Abada.form.field.Date','Abada.form.field.Time'],
    extend:'Ext.Panel',
    config:{
        fallowBlank:true,
        preventMark:false,
        plain:true,
        layout:'hbox'
    },
    initComponent:function(){
        this.tfDate=new Abada.form.field.Date({
            width:100,
            allowBlank:this.allowBlank,
            preventMark:this.preventMark
        });
        this.tfTime=new Abada.form.field.Time({
            width:100,
            allowBlank:this.allowBlank,
            preventMark:this.preventMark
        });
        this.items=[this.tfDate,this.tfTime];

        if (this.value){
            this.tfDate.setValue(this.value);
            
            this.tfTime.setValue(Ext.util.Format.date(this.value, 'H:i'));
        }

        this.callParent();
    },
    getValue:function(){
        function parseValue(date,stringtime){
            aux=stringtime.split(':');
            if (aux.length==2){
                date.setHours(parseInt(aux[0]), parseInt(aux[1]), 0, 0);
                return date;
            }else{
                return undefined;
            }
        }

        if (this.isValid()){
            return parseValue(this.tfDate.getValue(),this.tfTime.getValue());
        }
    },
    isValid:function(preventMark){
        return this.tfDate.isValid(preventMark) && this.tfTime.isValid(preventMark);
    }
});
/*
/**
 * Panel para seleccionar una fecha y hora 
 *//*
DateTimePanel=Ext.extend(Ext.Panel,{
    allowBlank:true,
    preventMark:false,
    plain:true,
    layout:'hbox',
    initComponent:function(){
        this.tfDate=new DateField({
            width:100,
            allowBlank:this.allowBlank,
            preventMark:this.preventMark
        });
        this.tfTime=new TimeField({
            width:100,
            allowBlank:this.allowBlank,
            preventMark:this.preventMark
        });
        this.items=[this.tfDate,this.tfTime];

        if (this.value){
            this.tfDate.setValue(this.value);
            
            this.tfTime.setValue(Ext.util.Format.date(this.value, 'H:i'));
        }

        DateTimePanel.superclass.initComponent.call(this);
    },
    getValue:function(){
        function parseValue(date,stringtime){
            aux=stringtime.split(':');
            if (aux.length==2){
                date.setHours(parseInt(aux[0]), parseInt(aux[1]), 0, 0);
                return date;
            }else{
                return undefined;
            }
        }

        if (this.isValid()){
            return parseValue(this.tfDate.getValue(),this.tfTime.getValue());
        }
    },
    isValid:function(preventMark){
        return this.tfDate.isValid(preventMark) && this.tfTime.isValid(preventMark);
    }
});*/