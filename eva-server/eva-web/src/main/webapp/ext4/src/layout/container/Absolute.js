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
 * This is a layout that inherits the anchoring of {@link Ext.layout.container.Anchor} and adds the
 * ability for x/y positioning using the standard x and y component config options.
 *
 * This class is intended to be extended or created via the {@link Ext.container.Container#layout layout}
 * configuration property.  See {@link Ext.container.Container#layout} for additional details.
 *
 *     @example
 *     Ext.create('Ext.form.Panel', {
 *         title: 'Absolute Layout',
 *         width: 300,
 *         height: 275,
 *         layout: {
 *             type: 'absolute'
 *             // layout-specific configs go here
 *             //itemCls: 'x-abs-layout-item',
 *         },
 *         url:'save-form.php',
 *         defaultType: 'textfield',
 *         items: [{
 *             x: 10,
 *             y: 10,
 *             xtype:'label',
 *             text: 'Send To:'
 *         },{
 *             x: 80,
 *             y: 10,
 *             name: 'to',
 *             anchor:'90%'  // anchor width by percentage
 *         },{
 *             x: 10,
 *             y: 40,
 *             xtype:'label',
 *             text: 'Subject:'
 *         },{
 *             x: 80,
 *             y: 40,
 *             name: 'subject',
 *             anchor: '90%'  // anchor width by percentage
 *         },{
 *             x:0,
 *             y: 80,
 *             xtype: 'textareafield',
 *             name: 'msg',
 *             anchor: '100% 100%'  // anchor width and height
 *         }],
 *         renderTo: Ext.getBody()
 *     });
 */
Ext.define('Ext.layout.container.Absolute', {

    /* Begin Definitions */

    alias: 'layout.absolute',
    extend: 'Ext.layout.container.Anchor',
    alternateClassName: 'Ext.layout.AbsoluteLayout',

    /* End Definitions */

    targetCls: Ext.baseCSSPrefix + 'abs-layout-ct',
    itemCls: Ext.baseCSSPrefix + 'abs-layout-item',

    /**
     * @cfg {Boolean} ignoreOnContentChange
     * True indicates that changes to one item in this layout do not effect the layout in
     * general. This may need to be set to false if {@link Ext.Component#autoScroll}
     * is enabled for the container.
     */
    ignoreOnContentChange: true,

    type: 'absolute',

    // private
    adjustWidthAnchor: function(value, childContext) {
        var padding = this.targetPadding,
            x = childContext.getStyle('left');

        return value - x + padding.left;
    },

    // private
    adjustHeightAnchor: function(value, childContext) {
        var padding = this.targetPadding,
            y = childContext.getStyle('top');

        return value - y + padding.top;
    },

    isItemLayoutRoot: function (item) {
        return this.ignoreOnContentChange || this.callParent(arguments);
    },

    isItemShrinkWrap: function (item) {
        return true;
    },

    beginLayout: function (ownerContext) {
        var me = this,
            target = me.getTarget();

        me.callParent(arguments);

        // Do not set position: relative; when the absolute layout target is the body
        if (target.dom !== document.body) {
            target.position();
        }

        me.targetPadding = ownerContext.targetContext.getPaddingInfo();
    },

    isItemBoxParent: function (itemContext) {
        return true;
    },

    onContentChange: function () {
        if (this.ignoreOnContentChange) {
            return false;
        }
        return this.callParent(arguments);
    }
});
