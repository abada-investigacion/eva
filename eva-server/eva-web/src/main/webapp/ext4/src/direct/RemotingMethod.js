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
 * Small utility class used internally to represent a Direct method.
 * @private
 */
Ext.define('Ext.direct.RemotingMethod', {

    constructor: function(config){
        var me = this,
            params = Ext.isDefined(config.params) ? config.params : config.len,
            name, pLen, p, param;

        me.name = config.name;
        me.formHandler = config.formHandler;
        if (Ext.isNumber(params)) {
            // given only the number of parameters
            me.len = params;
            me.ordered = true;
        } else {
            /*
             * Given an array of either
             * a) String
             * b) Objects with a name property. We may want to encode extra info in here later
             */
            me.params = [];
			pLen = params.length;
            for (p = 0; p < pLen; p++) {
                param = params[p];
                name  = Ext.isObject(param) ? param.name : param;
                me.params.push(name);
            }
        }
    },
    
    getArgs: function(params, paramOrder, paramsAsHash){
        var args = [],
            i,
            len;
        
        if (this.ordered) {
            if (this.len > 0) {
                // If a paramOrder was specified, add the params into the argument list in that order.
                if (paramOrder) {
                    for (i = 0, len = paramOrder.length; i < len; i++) {
                        args.push(params[paramOrder[i]]);
                    }
                } else if (paramsAsHash) {
                    // If paramsAsHash was specified, add all the params as a single object argument.
                    args.push(params);
                }
            }
        } else {
            args.push(params);
        } 
        
        return args;
    },

    /**
     * Takes the arguments for the Direct function and splits the arguments
     * from the scope and the callback.
     * @param {Array} args The arguments passed to the direct call
     * @return {Object} An object with 3 properties, args, callback & scope.
     */
    getCallData: function(args){
        var me = this,
            data = null,
            len  = me.len,
            params = me.params,
            callback,
            scope,
            name;

        if (me.ordered) {
            callback = args[len];
            scope = args[len + 1];
            if (len !== 0) {
                data = args.slice(0, len);
            }
        } else {
            data = Ext.apply({}, args[0]);
            callback = args[1];
            scope = args[2];

            // filter out any non-existent properties
            for (name in data) {
                if (data.hasOwnProperty(name)) {
                    if (!Ext.Array.contains(params, name)) {
                        delete data[name];
                    }
                }
            }
        }

        return {
            data: data,
            callback: callback,
            scope: scope
        };
    }
});
