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
 * The grid View class provides extra {@link Ext.grid.Panel} specific functionality to the
 * {@link Ext.view.Table}. In general, this class is not instanced directly, instead a viewConfig
 * option is passed to the grid:
 *
 *     Ext.create('Ext.grid.Panel', {
 *         // other options
 *         viewConfig: {
 *             stripeRows: false
 *         }
 *     });
 *
 * ## Drag Drop
 *
 * Drag and drop functionality can be achieved in the grid by attaching a {@link Ext.grid.plugin.DragDrop} plugin
 * when creating the view.
 *
 *     Ext.create('Ext.grid.Panel', {
 *         // other options
 *         viewConfig: {
 *             plugins: {
 *                 ddGroup: 'people-group',
 *                 ptype: 'gridviewdragdrop',
 *                 enableDrop: false
 *             }
 *         }
 *     });
 */
Ext.define('Ext.grid.View', {
    extend: 'Ext.view.Table',
    alias: 'widget.gridview',

    /**
     * @cfg
     * True to stripe the rows.
     *
     * This causes the CSS class **`x-grid-row-alt`** to be added to alternate rows of the grid. A default CSS rule is
     * provided which sets a background color, but you can override this with a rule which either overrides the
     * **background-color** style using the `!important` modifier, or which uses a CSS selector of higher specificity.
     */
    stripeRows: true,

    autoScroll: true
});
