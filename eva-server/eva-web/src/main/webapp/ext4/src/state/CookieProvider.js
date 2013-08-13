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
 * A Provider implementation which saves and retrieves state via cookies. The CookieProvider supports the usual cookie
 * options, such as:
 *
 * - {@link #path}
 * - {@link #expires}
 * - {@link #domain}
 * - {@link #secure}
 *
 * Example:
 *
 *     var cp = Ext.create('Ext.state.CookieProvider', {
 *         path: "/cgi-bin/",
 *         expires: new Date(new Date().getTime()+(1000*60*60*24*30)), //30 days
 *         domain: "sencha.com"
 *     });
 *
 *     Ext.state.Manager.setProvider(cp);
 *
 */
Ext.define('Ext.state.CookieProvider', {
    extend: 'Ext.state.Provider',

    /**
     * @cfg {String} path
     * The path for which the cookie is active. Defaults to root '/' which makes it active for all pages in the site.
     */

    /**
     * @cfg {Date} expires
     * The cookie expiration date. Defaults to 7 days from now.
     */

    /**
     * @cfg {String} domain
     * The domain to save the cookie for. Note that you cannot specify a different domain than your page is on, but you can
     * specify a sub-domain, or simply the domain itself like 'sencha.com' to include all sub-domains if you need to access
     * cookies across different sub-domains. Defaults to null which uses the same domain the page is running on including
     * the 'www' like 'www.sencha.com'.
     */

    /**
     * @cfg {Boolean} [secure=false]
     * True if the site is using SSL
     */

    /**
     * Creates a new CookieProvider.
     * @param {Object} [config] Config object.
     */
    constructor : function(config){
        var me = this;
        me.path = "/";
        me.expires = new Date(new Date().getTime()+(1000*60*60*24*7)); //7 days
        me.domain = null;
        me.secure = false;
        me.callParent(arguments);
        me.state = me.readCookies();
    },

    // private
    set : function(name, value){
        var me = this;

        if(typeof value == "undefined" || value === null){
            me.clear(name);
            return;
        }
        me.setCookie(name, value);
        me.callParent(arguments);
    },

    // private
    clear : function(name){
        this.clearCookie(name);
        this.callParent(arguments);
    },

    // private
    readCookies : function(){
        var cookies = {},
            c = document.cookie + ";",
            re = /\s?(.*?)=(.*?);/g,
            prefix = this.prefix,
            len = prefix.length,
            matches,
            name,
            value;

        while((matches = re.exec(c)) != null){
            name = matches[1];
            value = matches[2];
            if (name && name.substring(0, len) == prefix){
                cookies[name.substr(len)] = this.decodeValue(value);
            }
        }
        return cookies;
    },

    // private
    setCookie : function(name, value){
        var me = this;

        document.cookie = me.prefix + name + "=" + me.encodeValue(value) +
           ((me.expires == null) ? "" : ("; expires=" + me.expires.toGMTString())) +
           ((me.path == null) ? "" : ("; path=" + me.path)) +
           ((me.domain == null) ? "" : ("; domain=" + me.domain)) +
           ((me.secure == true) ? "; secure" : "");
    },

    // private
    clearCookie : function(name){
        var me = this;

        document.cookie = me.prefix + name + "=null; expires=Thu, 01-Jan-70 00:00:01 GMT" +
           ((me.path == null) ? "" : ("; path=" + me.path)) +
           ((me.domain == null) ? "" : ("; domain=" + me.domain)) +
           ((me.secure == true) ? "; secure" : "");
    }
});
