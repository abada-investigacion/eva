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
 * @author Don Griffin
 *
 * This class is a sequential id generator. A simple use of this class would be like so:
 *
 *     Ext.define('MyApp.data.MyModel', {
 *         extend: 'Ext.data.Model',
 *         idgen: 'sequential'
 *     });
 *     // assign id's of 1, 2, 3, etc.
 *
 * An example of a configured generator would be:
 *
 *     Ext.define('MyApp.data.MyModel', {
 *         extend: 'Ext.data.Model',
 *         idgen: {
 *             type: 'sequential',
 *             prefix: 'ID_',
 *             seed: 1000
 *         }
 *     });
 *     // assign id's of ID_1000, ID_1001, ID_1002, etc.
 *
 */
Ext.define('Ext.data.SequentialIdGenerator', {
    extend: 'Ext.data.IdGenerator',
    alias: 'idgen.sequential',

    constructor: function() {
        var me = this;

        me.callParent(arguments);

        me.parts = [ me.prefix, ''];
    },

    /**
     * @cfg {String} prefix
     * The string to place in front of the sequential number for each generated id. The
     * default is blank.
     */
    prefix: '',

    /**
     * @cfg {Number} seed
     * The number at which to start generating sequential id's. The default is 1.
     */
    seed: 1,

    /**
     * Generates and returns the next id.
     * @return {String} The next id.
     */
    generate: function () {
        var me = this,
            parts = me.parts;

        parts[1] = me.seed++;
        return parts.join('');
    }
});
