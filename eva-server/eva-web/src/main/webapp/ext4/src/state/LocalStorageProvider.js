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
 * @class Ext.state.LocalStorageProvider
 * A Provider implementation which saves and retrieves state via the HTML5 localStorage object.
 * If the browser does not support local storage, an exception will be thrown upon instantiating
 * this class.
 */

Ext.define('Ext.state.LocalStorageProvider', {
    /* Begin Definitions */
    
    extend: 'Ext.state.Provider',
    
    alias: 'state.localstorage',
    
    /* End Definitions */
   
    constructor: function(){
        var me = this;
        me.callParent(arguments);
        me.store = me.getStorageObject();
        me.state = me.readLocalStorage();
    },
    
    readLocalStorage: function(){
        var store = this.store,
            i = 0,
            len = store.length,
            prefix = this.prefix,
            prefixLen = prefix.length,
            data = {},
            key;
            
        for (; i < len; ++i) {
            key = store.key(i);
            if (key.substring(0, prefixLen) == prefix) {
                data[key.substr(prefixLen)] = this.decodeValue(store.getItem(key));
            }            
        }
        return data;
    },
    
    set : function(name, value){
        var me = this;
        
        me.clear(name);
        if (typeof value == "undefined" || value === null) {
            return;
        }
        me.store.setItem(me.prefix + name, me.encodeValue(value));
        me.callParent(arguments);
    },

    // private
    clear : function(name){
        this.store.removeItem(this.prefix + name);
        this.callParent(arguments);
    },
    
    getStorageObject: function(){
        try {
            var supports = 'localStorage' in window && window['localStorage'] !== null;
            if (supports) {
                return window.localStorage;
            }
        } catch (e) {
            return false;
        }
        //<debug>
        Ext.Error.raise('LocalStorage is not supported by the current browser');
        //</debug>
    }    
});
