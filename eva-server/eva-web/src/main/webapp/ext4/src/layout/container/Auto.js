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
 * @class Ext.layout.container.Auto
 *
 * The AutoLayout is the default layout manager delegated by {@link Ext.container.Container} to
 * render any child Components when no `{@link Ext.container.Container#layout layout}` is configured into
 * a `{@link Ext.container.Container Container}.` AutoLayout provides only a passthrough of any layout calls
 * to any child containers.
 *
 *     @example
 *     Ext.create('Ext.Panel', {
 *         width: 500,
 *         height: 280,
 *         title: "AutoLayout Panel",
 *         layout: 'auto',
 *         renderTo: document.body,
 *         items: [{
 *             xtype: 'panel',
 *             title: 'Top Inner Panel',
 *             width: '75%',
 *             height: 90
 *         },
 *         {
 *             xtype: 'panel',
 *             title: 'Bottom Inner Panel',
 *             width: '75%',
 *             height: 90
 *         }]
 *     });
 */
Ext.define('Ext.layout.container.Auto', {

    /* Begin Definitions */

    alias: ['layout.auto', 'layout.autocontainer'],

    extend: 'Ext.layout.container.Container',

    /* End Definitions */

    type: 'autocontainer',

    childEls: [
        'clearEl'
    ],

    renderTpl: [
        '{%this.renderBody(out,values)%}',
        // clear element is needed to prevent the bottom margins of the last child element from collapsing
        '<div id="{ownerId}-clearEl" class="', Ext.baseCSSPrefix, 'clear" role="presentation"></div>'
    ],

    // TODO - do we need to clear sizes in beginLayout?

    calculate: function(ownerContext) {
        var me = this,
            containerSize;

        if (!ownerContext.hasDomProp('containerChildrenDone')) {
            me.done = false;
        } else {
            // Once the child layouts are done we can determine the content sizes...
            containerSize = me.getContainerSize(ownerContext);
            if (!containerSize.gotAll) {
                me.done = false;
            }

            me.calculateContentSize(ownerContext);
        }
    }
});
