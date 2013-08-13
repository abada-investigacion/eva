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
 * This is a utility class that can be passed into a {@link Ext.grid.column.Column} as a column config that provides
 * an automatic row numbering column.
 * 
 * Usage:
 *
 *     columns: [
 *         {xtype: 'rownumberer'},
 *         {text: "Company", flex: 1, sortable: true, dataIndex: 'company'},
 *         {text: "Price", width: 120, sortable: true, renderer: Ext.util.Format.usMoney, dataIndex: 'price'},
 *         {text: "Change", width: 120, sortable: true, dataIndex: 'change'},
 *         {text: "% Change", width: 120, sortable: true, dataIndex: 'pctChange'},
 *         {text: "Last Updated", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'lastChange'}
 *     ]
 *
 */
Ext.define('Ext.grid.RowNumberer', {
    extend: 'Ext.grid.column.Column',
    alias: 'widget.rownumberer',

    /**
     * @cfg {String} text
     * Any valid text or HTML fragment to display in the header cell for the row number column.
     */
    text: "&#160",

    /**
     * @cfg {Number} width
     * The default width in pixels of the row number column.
     */
    width: 23,

    /**
     * @cfg {Boolean} sortable
     * @hide
     */
    sortable: false,
    
    /**
     * @cfg {Boolean} [draggable=false]
     * False to disable drag-drop reordering of this column.
     */
    draggable: false,

    align: 'right',

    constructor : function(config){

        // Copy the prototype's default width setting into an instance property to provide
        // a default width which will not be overridden by AbstractContainer.applyDefaults use of Ext.applyIf
        this.width = this.width;

        this.callParent(arguments);
        if (this.rowspan) {
            this.renderer = Ext.Function.bind(this.renderer, this);
        }
    },

    // private
    resizable: false,
    hideable: false,
    menuDisabled: true,
    dataIndex: '',
    cls: Ext.baseCSSPrefix + 'row-numberer',
    rowspan: undefined,

    // private
    renderer: function(value, metaData, record, rowIdx, colIdx, store) {
        if (this.rowspan){
            metaData.cellAttr = 'rowspan="'+this.rowspan+'"';
        }

        metaData.tdCls = Ext.baseCSSPrefix + 'grid-cell-special';
        return store.indexOfTotal(record) + 1;
    }
});
