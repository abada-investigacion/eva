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
 * A Column definition class which renders a passed date according to the default locale, or a configured
 * {@link #format}.
 *
 *     @example
 *     Ext.create('Ext.data.Store', {
 *         storeId:'sampleStore',
 *         fields:[
 *             { name: 'symbol', type: 'string' },
 *             { name: 'date',   type: 'date' },
 *             { name: 'change', type: 'number' },
 *             { name: 'volume', type: 'number' },
 *             { name: 'topday', type: 'date' }                        
 *         ],
 *         data:[
 *             { symbol: "msft",   date: '2011/04/22', change: 2.43, volume: 61606325, topday: '04/01/2010' },
 *             { symbol: "goog",   date: '2011/04/22', change: 0.81, volume: 3053782,  topday: '04/11/2010' },
 *             { symbol: "apple",  date: '2011/04/22', change: 1.35, volume: 24484858, topday: '04/28/2010' },            
 *             { symbol: "sencha", date: '2011/04/22', change: 8.85, volume: 5556351,  topday: '04/22/2010' }            
 *         ]
 *     });
 *     
 *     Ext.create('Ext.grid.Panel', {
 *         title: 'Date Column Demo',
 *         store: Ext.data.StoreManager.lookup('sampleStore'),
 *         columns: [
 *             { text: 'Symbol',   dataIndex: 'symbol', flex: 1 },
 *             { text: 'Date',     dataIndex: 'date',   xtype: 'datecolumn',   format:'Y-m-d' },
 *             { text: 'Change',   dataIndex: 'change', xtype: 'numbercolumn', format:'0.00' },
 *             { text: 'Volume',   dataIndex: 'volume', xtype: 'numbercolumn', format:'0,000' },
 *             { text: 'Top Day',  dataIndex: 'topday', xtype: 'datecolumn',   format:'l' }            
 *         ],
 *         height: 200,
 *         width: 450,
 *         renderTo: Ext.getBody()
 *     });
 */
Ext.define('Ext.grid.column.Date', {
    extend: 'Ext.grid.column.Column',
    alias: ['widget.datecolumn'],
    requires: ['Ext.Date'],
    alternateClassName: 'Ext.grid.DateColumn',

    /**
     * @cfg {String} format
     * A formatting string as used by {@link Ext.Date#format} to format a Date for this Column.
     *
     * Defaults to the default date from {@link Ext.Date#defaultFormat} which itself my be overridden
     * in a locale file.
     */
    /**
     * @cfg renderer
     * @hide
     */
    /**
     * @cfg scope
     * @hide
     */

    initComponent: function(){
        if (!this.format) {
            this.format = Ext.Date.defaultFormat;
        }
        
        this.callParent(arguments);
    },
    
    defaultRenderer: function(value){
        return Ext.util.Format.date(value, this.format);
    }
});