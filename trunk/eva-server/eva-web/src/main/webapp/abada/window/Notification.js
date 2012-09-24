/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Notification Window
 */
NotificationMgr = {
    positions: []
};

Ext.define('Abada.window.Notification',{
    extend:'Ext.window.Window',    
    initComponent: function(){
        Ext.apply(this, {
            iconCls: this.iconCls || 'x-icon-information',
            cls: 'x-notification',
            width:200,
            autoHeight: true,
            plain: false,
            draggable: false,
            shadow:false,
            bodyStyle: 'text-align:center'
        });
        this.label=Ext.create('Ext.form.Label',{});

        this.items=[this.label];

        if(this.autoDestroy) {
            this.task = new Ext.util.DelayedTask(this.hide, this);
        } else {
            this.closable = true;
        }
        this.callParent();
        return this;
    },
    setMessage: function(msg){
        this.label.setText(msg);
    },
    setTitle: function(title, iconCls){
        this.superclass.setTitle.call(this, title, iconCls||this.iconCls);  
    },
    onDestroy: function(){
        NotificationMgr.positions.splice(this.pos);
        this.superclass.onDestroy.call(this);
    },
    cancelHiding: function(){
        this.addClass('fixed');
        if(this.autoDestroy) {
            this.task.cancel();
        }
        this.animHide();
    },
    afterShow: function(){          
        Ext.fly(this.body.dom).on('click', this.cancelHiding, this);
        if(this.autoDestroy) {
            this.task.delay(this.hideDelay || 2000);
        }           
        this.pos = 0;      
        while(NotificationMgr.positions.indexOf(this.pos)>-1)
            this.pos++;
        NotificationMgr.positions.push(this.pos);
        this.setSize(200,100);
        this.el.alignTo(document, "br-br", [ -20, -20-((this.getHeight()+10)*this.pos) ]);
        this.el.slideIn();
        this.superclass.afterShow.call(this);  
    },
    animHide: function(){
        NotificationMgr.positions.splice(this.pos);
        this.el.slideOut(); 
    },
    focus: Ext.emptyFn

});
