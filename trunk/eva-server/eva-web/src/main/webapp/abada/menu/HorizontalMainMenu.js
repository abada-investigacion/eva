/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('Abada.menu.HorizontalMainMenu', {
    requires: ['Abada.Ajax', 'Ext.menu.*', 'Ext.button.Button', 'Ext.button.Split'],
    extend: 'Ext.toolbar.Toolbar',
    url: undefined,
    method: 'POST',
    autoLoadData: false,
    initComponent: function() {
        this.callParent();
        if (this.autoLoadData)
            this.loadData();
    },
    loadData: function() {
        this.removeAll();
        Abada.Ajax.requestJsonData({
            url: this.url,
            scope: this,
            method: this.method,
            success: function(json) {
                json.data.sort(function(a, b) {
                    return parseInt(a.order) - parseInt(b.order)
                });
                for (var y = 0; y < json.data.length; y++) {
                    if (json.data[y].childs) {
                        this.addMainMenuParent(json.data[y], this);
                    }
                }
            },
            failure: function() {
            }
        });
    },
    addMainMenuParent: function(parent, container) {
        var menu = this.setSubMenuChilds(parent);
        container.add(Ext.create('Ext.button.Button', {
            menu: menu,
            text: parent.text,
            icon: parent.icon
        }));
    },
    addSubMenuParent: function(parent, container) {
        var menu = this.setSubMenuChilds(parent);
        if (menu) {
            container.add(Ext.create('Ext.menu.Item', {
                menu: menu,
                text: parent.text,
                icon: parent.icon
            }));
        } else {
            container.add(Ext.create('Ext.menu.Item', {
                text: parent.text,
                icon: parent.icon,
                href:parent.url
            }));
        }
    },
    setSubMenuChilds: function(parent) {
        if (parent.childs && parent.childs.length > 0) {
            var result = Ext.create('Ext.menu.Menu');
            parent.childs.sort(function(a, b) {
                return parseInt(a.order) - parseInt(b.order)
            });

            for (var x = 0; x < parent.childs.length; x++) {
                this.addSubMenuParent(parent.childs[x], result);
            }
            return result;
        }
        return undefined;
    }
});

