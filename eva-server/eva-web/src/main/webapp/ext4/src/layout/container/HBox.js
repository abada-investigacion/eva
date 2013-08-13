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
 * A layout that arranges items horizontally across a Container. This layout optionally divides available horizontal
 * space between child items containing a numeric `flex` configuration.
 *
 * This layout may also be used to set the heights of child items by configuring it with the {@link #align} option.
 *
 *     @example
 *     Ext.create('Ext.Panel', {
 *         width: 500,
 *         height: 300,
 *         title: "HBoxLayout Panel",
 *         layout: {
 *             type: 'hbox',
 *             align: 'stretch'
 *         },
 *         renderTo: document.body,
 *         items: [{
 *             xtype: 'panel',
 *             title: 'Inner Panel One',
 *             flex: 2
 *         },{
 *             xtype: 'panel',
 *             title: 'Inner Panel Two',
 *             flex: 1
 *         },{
 *             xtype: 'panel',
 *             title: 'Inner Panel Three',
 *             flex: 1
 *         }]
 *     });
 */
Ext.define('Ext.layout.container.HBox', {

    /* Begin Definitions */

    alias: ['layout.hbox'],
    extend: 'Ext.layout.container.Box',
    alternateClassName: 'Ext.layout.HBoxLayout',

    /* End Definitions */

    /**
     * @cfg {String} align
     * Controls how the child items of the container are aligned. Acceptable configuration values for this property are:
     *
     * - **top** : **Default** child items are aligned vertically at the **top** of the container
     * - **middle** : child items are aligned vertically in the **middle** of the container
     * - **stretch** : child items are stretched vertically to fill the height of the container
     * - **stretchmax** : child items are stretched vertically to the height of the largest item.
     */
    align: 'top', // top, middle, stretch, strechmax

    type : 'hbox',

    direction: 'horizontal',

    horizontal: true,

    names: {
        // parallel
        lr: 'lr',
        left: 'left',// 'before',
        leftCap: 'Left',
        right: 'right',// 'after',
        position: 'left',
        width: 'width',
        contentWidth: 'contentWidth',
        minWidth: 'minWidth',
        maxWidth: 'maxWidth',
        widthCap: 'Width',
        widthModel: 'widthModel',
        widthIndex: 0,
        x: 'x',
        scrollLeft: 'scrollLeft',
        overflowX: 'overflowX',
        hasOverflowX: 'hasOverflowX',
        invalidateScrollX: 'invalidateScrollX',

        // perpendicular
        center: 'middle',
        top: 'top',
        topPosition: 'top',
        bottom: 'bottom',
        height: 'height',
        contentHeight: 'contentHeight',
        minHeight: 'minHeight',
        maxHeight: 'maxHeight',
        heightCap: 'Height',
        heightModel: 'heightModel',
        heightIndex: 1,
        y: 'y',
        scrollTop: 'scrollTop',
        overflowY: 'overflowY',
        hasOverflowY: 'hasOverflowY',
        invalidateScrollY: 'invalidateScrollY',

        // Methods
        getWidth: 'getWidth',
        getHeight: 'getHeight',
        setWidth: 'setWidth',
        setHeight: 'setHeight',
        gotWidth: 'gotWidth',
        gotHeight: 'gotHeight',
        setContentWidth: 'setContentWidth',
        setContentHeight: 'setContentHeight',
        setWidthInDom: 'setWidthInDom',
        setHeightInDom: 'setHeightInDom'
    },

    sizePolicy: {
        flex: {
            '': {
                setsWidth: 1,
                setsHeight: 0
            },
            stretch: {
                setsWidth: 1,
                setsHeight: 1
            },
            stretchmax: {
                readsHeight: 1,
                setsWidth: 1,
                setsHeight: 1
            }
        },
        '': {
            setsWidth: 0,
            setsHeight: 0
        },
        stretch: {
            setsWidth: 0,
            setsHeight: 1
        },
        stretchmax: {
            readsHeight: 1,
            setsWidth: 0,
            setsHeight: 1
        }
    }            
});
