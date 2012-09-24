
/** 
* Abada.form.field.PageSize
*/  
Ext.define('Abada.form.field.PageSize', {  
    extend      : 'Ext.form.field.ComboBox',  
    alias       : 'plugin.pagesize',  
    beforeText  : 'Nº',  
    afterText   : '',  
    mode        : 'local',  
    displayField: 'text',  
    valueField  : 'value',  
    allowBlank  : false,  
    triggerAction: 'all',  
    width       : 50, 
    maskRe      : /[0-9]/,      
    /** 
    * initialize the paging combo after the pagebar is randered 
    */  
    init: function(paging) {  
        paging.on('afterrender', this.onInitView, this);  
    },  
    /** 
    * create a local store for availabe range of pages 
    */  
    store: new Ext.data.SimpleStore({ 
        //pageSize: 10,
        fields: ['text', 'value'],  
        data: [['5', 5], ['10', 10], ['20', 20], ['50', 50], ['100', 100], ['250', 250]]  
    }),      
    /** 
    * assing the select and specialkey events for the combobox  
    * after the pagebar is rendered. 
    */  
    onInitView: function(paging) {  
        this.setValue(paging.store.pageSize);
        //this.setValue(10);
        paging.add('-', this.beforeText, this, this.afterText);  
        this.on('select', this.onPageSizeChanged, paging);  
        this.on('specialkey', function(combo, e) {  
            if(13 === e.getKey()) {  
                this.onPageSizeChanged.call(paging, this);          
            }  
        });  
    },  
    /** 
    * refresh the page when the value is changed 
    */  
    onPageSizeChanged: function(combo) {  
        this.store.pageSize = parseInt(combo.getRawValue());  
        this.doRefresh();  
    }  
});