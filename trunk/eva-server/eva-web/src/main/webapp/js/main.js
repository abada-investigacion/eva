/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.require([    
    'Ext.container.Viewport',
    'Ext.layout.container.Border',
    'Abada.menu.MainMenu'
    ]);

Ext.onReady(function(){               
    var menu=Ext.create('Abada.menu.MainMenu',{
        url:getRelativeURI('mainmenu.do'),        
        autoLoadData:true,
        collapsible: true,
        title: 'Men&uacute;'        
    });
        
    var menuPanel=Ext.create('Ext.panel.Panel',{
        region: 'west',
        defaults:{
            collapsible: true
        },
        collapsible: true,
        autoScroll:true,
        collapseDirection:'left',
        width: 150,
        items:[
            menu            
        ]
        });
    //menu.loadData();
    var view=Ext.create('Ext.container.Viewport', {
        layout: 'border',
        renderTo: Ext.getBody(),
        items: [{
            region: 'north',
            html: '<h1 class="x-panel-header">Eva</h1>'+
                //'<div style=\"float:left;padding:10px;\"><img alt=\" \" src=\"'+getRelativeURI('/images/logos/oggi.png')+'\" height=\"70\" /></div>'+
                '<div style=\"float:left;padding:10px;\"><img alt=\" \" src=\"'+getRelativeURI('/images/logos/abada.png')+'\" height=\"60\" /></div>'
                //'<div style=\"float:left;padding:10px;\"><img style=\"width:700px;height:60px;\" alt=\" \" src=\"'+getRelativeURI('/images/logos/oncoguia.png')+'\"/></div>'
            
            ,autoHeight: true,
            border: false,
            margins: '0 0 5 0'
        },menuPanel
        , {
            region: 'center',
            id:'centralPanel',
            autoScroll:true
        }]
    });   
});

