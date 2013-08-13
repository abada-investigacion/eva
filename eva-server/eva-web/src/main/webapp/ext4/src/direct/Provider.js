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
 * Ext.direct.Provider is an abstract class meant to be extended.
 *
 * For example Ext JS implements the following subclasses:
 *
 *     Provider
 *     |
 *     +---{@link Ext.direct.JsonProvider JsonProvider}
 *         |
 *         +---{@link Ext.direct.PollingProvider PollingProvider}
 *         |
 *         +---{@link Ext.direct.RemotingProvider RemotingProvider}
 *
 * @abstract
 */
Ext.define('Ext.direct.Provider', {

    /* Begin Definitions */

   alias: 'direct.provider',

    mixins: {
        observable: 'Ext.util.Observable'
    },

    /* End Definitions */

   /**
     * @cfg {String} id
     * The unique id of the provider (defaults to an {@link Ext#id auto-assigned id}).
     * You should assign an id if you need to be able to access the provider later and you do
     * not have an object reference available, for example:
     *
     *     Ext.direct.Manager.addProvider({
     *         type: 'polling',
     *         url:  'php/poll.php',
     *         id:   'poll-provider'
     *     });
     *     var p = {@link Ext.direct.Manager}.{@link Ext.direct.Manager#getProvider getProvider}('poll-provider');
     *     p.disconnect();
     *
     */

    constructor : function(config){
        var me = this;

        Ext.apply(me, config);
        me.addEvents(
            /**
             * @event connect
             * Fires when the Provider connects to the server-side
             * @param {Ext.direct.Provider} provider The {@link Ext.direct.Provider Provider}.
             */
            'connect',
            /**
             * @event disconnect
             * Fires when the Provider disconnects from the server-side
             * @param {Ext.direct.Provider} provider The {@link Ext.direct.Provider Provider}.
             */
            'disconnect',
            /**
             * @event data
             * Fires when the Provider receives data from the server-side
             * @param {Ext.direct.Provider} provider The {@link Ext.direct.Provider Provider}.
             * @param {Ext.direct.Event} e The Ext.direct.Event type that occurred.
             */
            'data',
            /**
             * @event exception
             * Fires when the Provider receives an exception from the server-side
             */
            'exception'
        );
        me.mixins.observable.constructor.call(me, config);
    },

    /**
     * Returns whether or not the server-side is currently connected.
     * Abstract method for subclasses to implement.
     */
    isConnected: function(){
        return false;
    },

    /**
     * Abstract methods for subclasses to implement.
     * @method
     */
    connect: Ext.emptyFn,

    /**
     * Abstract methods for subclasses to implement.
     * @method
     */
    disconnect: Ext.emptyFn
});
