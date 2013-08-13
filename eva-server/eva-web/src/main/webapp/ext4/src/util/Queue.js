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
 * An internal Queue class.
 * @private
 */
Ext.define('Ext.util.Queue', {

    constructor: function() {
        this.clear();
    },

    add : function(obj) {
        var me = this,
            key = me.getKey(obj);

        if (!me.map[key]) {
            ++me.length;
            me.items.push(obj);
            me.map[key] = obj;
        }

        return obj;
    },

    /**
     * Removes all items from the collection.
     */
    clear : function(){
        var me = this,
            items = me.items;

        me.items = [];
        me.map = {};
        me.length = 0;

        return items;
    },

    contains: function (obj) {
        var key = this.getKey(obj);

        return this.map.hasOwnProperty(key);
    },

    /**
     * Returns the number of items in the collection.
     * @return {Number} the number of items in the collection.
     */
    getCount : function(){
        return this.length;
    },

    getKey : function(obj){
         return obj.id;
    },

    /**
     * Remove an item from the collection.
     * @param {Object} obj The item to remove.
     * @return {Object} The item removed or false if no item was removed.
     */
    remove : function(obj){
        var me = this,
            key = me.getKey(obj),
            items = me.items,
            index;

        if (me.map[key]) {
            index = Ext.Array.indexOf(items, obj);
            Ext.Array.erase(items, index, 1);
            delete me.map[key];
            --me.length;
        }

        return obj;
    }
});