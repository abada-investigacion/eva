/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * tab panel
 */
Ext.define('Abada.tab.panel.TabPanel',{
    extend:'Ext.tab.Panel',
    /*
    constructor:function(config){
        this.initConfig(config);
        this.callParent([config]);
      
    },*/
   getIndexActiveTab:function(){
        actTab=this.getActiveTab();
        for (i=0;i<this.items.length;i++){
            aux=this.items.get(i);
            if (actTab==aux){
                return i;
            }
        }
        return -1;
    }
});


/*/* 

TabPanel=Ext.extend(Ext.TabPanel,{
    constructor:function(cfg){
        Ext.apply(this, cfg);
        TabPanel.superclass.constructor.call(this,cfg);
    },
    getIndexActiveTab:function(){
        actTab=this.getActiveTab();
        for (i=0;i<this.items.length;i++){
            aux=this.items.get(i);
            if (actTab==aux){
                return i;
            }
        }
        return -1;
    }
});


;*/