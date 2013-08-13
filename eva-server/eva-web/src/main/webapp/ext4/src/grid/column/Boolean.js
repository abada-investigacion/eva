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
 * A Column definition class which renders boolean data fields.  See the {@link Ext.grid.column.Column#xtype xtype}
 * config option of {@link Ext.grid.column.Column} for more details.
 *
 *     @example
 *     Ext.create('Ext.data.Store', {
 *        storeId:'sampleStore',
 *        fields:[
 *            {name: 'framework', type: 'string'},
 *            {name: 'rocks', type: 'boolean'}
 *        ],
 *        data:{'items':[
 *            { 'framework': "Ext JS 4",     'rocks': true  },
 *            { 'framework': "Sencha Touch", 'rocks': true  },
 *            { 'framework': "Ext GWT",      'rocks': true  }, 
 *            { 'framework': "Other Guys",   'rocks': false } 
 *        ]},
 *        proxy: {
 *            type: 'memory',
 *            reader: {
 *                type: 'json',
 *                root: 'items'
 *            }
 *        }
 *     });
 *     
 *     Ext.create('Ext.grid.Panel', {
 *         title: 'Boolean Column Demo',
 *         store: Ext.data.StoreManager.lookup('sampleStore'),
 *         columns: [
 *             { text: 'Framework',  dataIndex: 'framework', flex: 1 },
 *             {
 *                 xtype: 'booleancolumn', 
 *                 text: 'Rocks',
 *                 trueText: 'Yes',
 *                 falseText: 'No', 
 *                 dataIndex: 'rocks'
 *             }
 *         ],
 *         height: 200,
 *         width: 400,
 *         renderTo: Ext.getBody()
 *     });
 */
Ext.define('Ext.grid.column.Boolean', {
    extend: 'Ext.grid.column.Column',
    alias: ['widget.booleancolumn'],
    alternateClassName: 'Ext.grid.BooleanColumn',

    //<locale>
    /**
     * @cfg {String} trueText
     * The string returned by the renderer when the column value is not falsey.
     */
    trueText: 'true',
    //</locale>

    //<locale>
    /**
     * @cfg {String} falseText
     * The string returned by the renderer when the column value is falsey (but not undefined).
     */
    falseText: 'false',
    //</locale>

    /**
     * @cfg {String} undefinedText
     * The string returned by the renderer when the column value is undefined.
     */
    undefinedText: '&#160;',

    /**
     * @cfg renderer
     * @hide
     */
    /**
     * @cfg scope
     * @hide
     */

    defaultRenderer: function(value){
        if (value === undefined) {
            return this.undefinedText;
        }
        
        if (!value || value === 'false') {
            return this.falseText;
        }
        return this.trueText;
    }
});