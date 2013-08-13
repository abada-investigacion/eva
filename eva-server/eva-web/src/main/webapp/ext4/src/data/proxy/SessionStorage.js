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
 * Proxy which uses HTML5 session storage as its data storage/retrieval mechanism. If this proxy is used in a browser
 * where session storage is not supported, the constructor will throw an error. A session storage proxy requires a
 * unique ID which is used as a key in which all record data are stored in the session storage object.
 *
 * It's important to supply this unique ID as it cannot be reliably determined otherwise. If no id is provided but the
 * attached store has a storeId, the storeId will be used. If neither option is presented the proxy will throw an error.
 *
 * Proxies are almost always used with a {@link Ext.data.Store store}:
 *
 *     new Ext.data.Store({
 *         proxy: {
 *             type: 'sessionstorage',
 *             id  : 'myProxyKey'
 *         }
 *     });
 *
 * Alternatively you can instantiate the Proxy directly:
 *
 *     new Ext.data.proxy.SessionStorage({
 *         id  : 'myOtherProxyKey'
 *     });
 *
 * Note that session storage is different to local storage (see {@link Ext.data.proxy.LocalStorage}) - if a browser
 * session is ended (e.g. by closing the browser) then all data in a SessionStorageProxy are lost. Browser restarts
 * don't affect the {@link Ext.data.proxy.LocalStorage} - the data are preserved.
 */
Ext.define('Ext.data.proxy.SessionStorage', {
    extend: 'Ext.data.proxy.WebStorage',
    alias: 'proxy.sessionstorage',
    alternateClassName: 'Ext.data.SessionStorageProxy',
    
    //inherit docs
    getStorageObject: function() {
        return window.sessionStorage;
    }
});
