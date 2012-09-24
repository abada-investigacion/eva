/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('Abada.menu.MainMenu',{
    requires:['Abada.Ajax','Ext.menu.Item'],
    extend:'Ext.panel.Panel',    
    frame:true,    
    url:undefined,
    method:'POST',   
    autoLoadData:false,
    initComponent:function(){                                        
        this.callParent();
        if (this.autoLoadData)
            this.loadData();
    },
    loadData:function(){
        this.removeAll();
        Abada.Ajax.requestJsonData({
            url: this.url,
            scope:this,
            method:this.method,
            success: function(json) {                                 
                for(var y=0;y<json.data.length;y++){
                    if(json.data[y].childs){
                        this.add(this.setMainMenuParent(json.data[y]));
                    }                    
                }                                
            },
            failure:function(){                
            }
        });
    },
    setMainMenuParent:function(parent){       
        return Ext.create('Ext.panel.Panel',{            
            title:parent.text,
            icon:parent.icon,
            frame:true,
            collapsible:true,
            titleCollapse: true,
            items:this.setMainMenuChilds(parent.childs)
        });        
    },
    setMainMenuChilds:function(childs){
        var childsArray=new Array();
        if(childs){
            childs.sort(function(a,b) {
                return parseInt(a.order) - parseInt(b.order)
            } );
         
            for(var x=0; x<childs.length;x++){            
                childsArray[x]= Ext.create('Ext.menu.Item',{
                    text:childs[x].text,
                    style: {
                        marginBottom: '9px'
                    },
                    icon:childs[x].icon,
                    href:childs[x].url
                });
            }
        }
        return childsArray;
    }
});

