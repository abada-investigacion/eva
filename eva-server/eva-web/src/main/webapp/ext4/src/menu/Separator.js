/*
 * #%L
 * Eva
 * %%
 * Copyright (C) 2013 Abada Servicios Desarrollo (investigacion@abadasoft.com)
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
/**
 * Adds a separator bar to a menu, used to divide logical groups of menu items. Generally you will
 * add one of these by using "-" in your call to add() or in your items config rather than creating one directly.
 *
 *     @example
 *     Ext.create('Ext.menu.Menu', {
 *         width: 100,
 *         height: 100,
 *         floating: false,  // usually you want this set to True (default)
 *         renderTo: Ext.getBody(),  // usually rendered by it's containing component
 *         items: [{
 *             text: 'icon item',
 *             iconCls: 'add16'
 *         },{
 *             xtype: 'menuseparator'
 *         },{
 *            text: 'separator above'
 *         },{
 *            text: 'regular item'
 *         }]
 *     });
 */
Ext.define('Ext.menu.Separator', {
    extend: 'Ext.menu.Item',
    alias: 'widget.menuseparator',

    /**
     * @cfg {String} activeCls
     * @private
     */

    /**
     * @cfg {Boolean} canActivate
     * @private
     */
    canActivate: false,

    /**
     * @cfg {Boolean} clickHideDelay
     * @private
     */

    /**
     * @cfg {Boolean} destroyMenu
     * @private
     */

    /**
     * @cfg {Boolean} disabledCls
     * @private
     */

    focusable: false,

    /**
     * @cfg {String} href
     * @private
     */

    /**
     * @cfg {String} hrefTarget
     * @private
     */

    /**
     * @cfg {Boolean} hideOnClick
     * @private
     */
    hideOnClick: false,

    /**
     * @cfg {String} icon
     * @private
     */

    /**
     * @cfg {String} iconCls
     * @private
     */

    /**
     * @cfg {Object} menu
     * @private
     */

    /**
     * @cfg {String} menuAlign
     * @private
     */

    /**
     * @cfg {Number} menuExpandDelay
     * @private
     */

    /**
     * @cfg {Number} menuHideDelay
     * @private
     */

    /**
     * @cfg {Boolean} plain
     * @private
     */
    plain: true,

    /**
     * @cfg {String} separatorCls
     * The CSS class used by the separator item to show the incised line.
     */
    separatorCls: Ext.baseCSSPrefix + 'menu-item-separator',

    /**
     * @cfg {String} text
     * @private
     */
    text: '&#160;',

    beforeRender: function(ct, pos) {
        var me = this;

        me.callParent();

        me.addCls(me.separatorCls);
    }
});