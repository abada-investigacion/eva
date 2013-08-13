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
 * @class Ext.app.EventBus
 * @private
 */
Ext.define('Ext.app.EventBus', {
    requires: [
        'Ext.util.Event',
        'Ext.Component'
    ],
    mixins: {
        observable: 'Ext.util.Observable'
    },

    constructor: function() {
        this.mixins.observable.constructor.call(this);

        this.bus = {};

        var me = this;
        Ext.override(Ext.Component, {
            fireEvent: function(ev) {
                if (Ext.util.Observable.prototype.fireEvent.apply(this, arguments) !== false) {
                    return me.dispatch.call(me, ev, this, arguments);
                }
                return false;
            }
        });
    },

    dispatch: function(ev, target, args) {
        var bus = this.bus,
            selectors = bus[ev],
            selector, controllers, id, events, event, i, ln;

        if (selectors) {
            // Loop over all the selectors that are bound to this event
            for (selector in selectors) {
                // Check if the target matches the selector
                if (selectors.hasOwnProperty(selector) && target.is(selector)) {
                    // Loop over all the controllers that are bound to this selector
                    controllers = selectors[selector];
                    for (id in controllers) {
                        if (controllers.hasOwnProperty(id)) {
                            // Loop over all the events that are bound to this selector on this controller
                            events = controllers[id];
                            for (i = 0, ln = events.length; i < ln; i++) {
                                event = events[i];
                                // Fire the event!
                                if (event.fire.apply(event, Array.prototype.slice.call(args, 1)) === false) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    },

    control: function(selectors, listeners, controller) {
        var bus = this.bus,
            hasListeners, tree, list,
            selector, options, listener, scope, event, listenerList, ev;

        if (Ext.isString(selectors)) {
            selector = selectors;
            selectors = {};
            selectors[selector] = listeners;
            this.control(selectors, null, controller);
            return;
        }

        hasListeners = Ext.util.Observable.HasListeners.prototype;
        for (selector in selectors) {
            if (selectors.hasOwnProperty(selector)) {
                listenerList = selectors[selector] || {};

                for (ev in listenerList) {
                    if (listenerList.hasOwnProperty(ev)) {
                        options  = {};
                        listener = listenerList[ev];
                        scope    = controller;
                        event    = new Ext.util.Event(controller, ev);

                        // Normalize the listener
                        if (Ext.isObject(listener)) {
                            options  = listener;
                            listener = options.fn;
                            scope    = options.scope || controller;

                            delete options.fn;
                            delete options.scope;
                        }

                        event.addListener(listener, scope, options);

                        hasListeners[ev] = 1;

                        // Create the bus tree if it is not there yet
                        tree = bus[ev] || (bus[ev] = {});
                        tree = tree[selector] || (tree[selector] = {});
                        list = tree[controller.id] || (tree[controller.id] = []);

                        // Push our listener in our bus
                        list.push(event);
                    }
                } //end inner loop
            }
        } //end outer loop
    }
});
