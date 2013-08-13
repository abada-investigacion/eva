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
 * @author Ed Spencer
 * 
 * Simple class that represents a Request that will be made by any {@link Ext.data.proxy.Server} subclass.
 * All this class does is standardize the representation of a Request as used by any ServerProxy subclass,
 * it does not contain any actual logic or perform the request itself.
 */
Ext.define('Ext.data.Request', {
    /**
     * @cfg {String} action
     * The name of the action this Request represents. Usually one of 'create', 'read', 'update' or 'destroy'.
     */
    action: undefined,
    
    /**
     * @cfg {Object} params
     * HTTP request params. The Proxy and its Writer have access to and can modify this object.
     */
    params: undefined,
    
    /**
     * @cfg {String} method
     * The HTTP method to use on this Request. Should be one of 'GET', 'POST', 'PUT' or 'DELETE'.
     */
    method: 'GET',
    
    /**
     * @cfg {String} url
     * The url to access on this Request
     */
    url: undefined,

    /**
     * Creates the Request object.
     * @param {Object} [config] Config object.
     */
    constructor: function(config) {
        Ext.apply(this, config);
    }
});