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
 * @class Ext.data.writer.Json

This class is used to write {@link Ext.data.Model} data to the server in a JSON format.
The {@link #allowSingle} configuration can be set to false to force the records to always be
encoded in an array, even if there is only a single record being sent.

 * @markdown
 */
Ext.define('Ext.data.writer.Json', {
    extend: 'Ext.data.writer.Writer',
    alternateClassName: 'Ext.data.JsonWriter',
    alias: 'writer.json',
    
    /**
     * @cfg {String} root The key under which the records in this Writer will be placed. Defaults to <tt>undefined</tt>.
     * Example generated request, using root: 'records':
<pre><code>
{'records': [{name: 'my record'}, {name: 'another record'}]}
</code></pre>
     */
    root: undefined,
    
    /**
     * @cfg {Boolean} encode True to use Ext.encode() on the data before sending. Defaults to <tt>false</tt>.
     * The encode option should only be set to true when a {@link #root} is defined, because the values will be
     * sent as part of the request parameters as opposed to a raw post. The root will be the name of the parameter
     * sent to the server.
     */
    encode: false,
    
    /**
     * @cfg {Boolean} allowSingle False to ensure that records are always wrapped in an array, even if there is only
     * one record being sent. When there is more than one record, they will always be encoded into an array.
     * Defaults to <tt>true</tt>. Example:
     * <pre><code>
// with allowSingle: true
"root": {
    "first": "Mark",
    "last": "Corrigan"
}

// with allowSingle: false
"root": [{
    "first": "Mark",
    "last": "Corrigan"
}]
     * </code></pre>
     */
    allowSingle: true,
    
    //inherit docs
    writeRecords: function(request, data) {
        var root = this.root;
        
        if (this.allowSingle && data.length == 1) {
            // convert to single object format
            data = data[0];
        }
        
        if (this.encode) {
            if (root) {
                // sending as a param, need to encode
                request.params[root] = Ext.encode(data);
            } else {
                //<debug>
                Ext.Error.raise('Must specify a root when using encode');
                //</debug>
            }
        } else {
            // send as jsonData
            request.jsonData = request.jsonData || {};
            if (root) {
                request.jsonData[root] = data;
            } else {
                request.jsonData = data;
            }
        }
        return request;
    }
});
