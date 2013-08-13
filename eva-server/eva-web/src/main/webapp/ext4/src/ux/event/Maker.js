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
 * Event maker.
 */
Ext.define('Ext.ux.event.Maker', {

    eventQueue: [],
    
    startAfter: 500,
    
    timerIncrement: 500,
    
    currentTiming: 0,
    
    
    constructor: function(config) {
        var me = this;
        
        me.currentTiming = me.startAfter;
        
        if(!Ext.isArray(config)) {
            config = [config];
        }
        
        Ext.Array.each(config, function(item) {
            item.el = item.el || 'el';
            Ext.Array.each(Ext.ComponentQuery.query(item.cmpQuery), function(cmp) {
                var event = {}, x, y, el;
             
                if (!item.domQuery) {
                    el = cmp[item.el];
                } else {
                    el = cmp.el.down(item.domQuery);
                }

                event.target = '#' + el.dom.id;
                
                event.type = item.type;
                
                event.button = config.button || 0;
                
                x = el.getX() + (el.getWidth() / 2);
                y = el.getY() + (el.getHeight() / 2);
                
                event.xy = [x,y];
                
                event.ts = me.currentTiming;
                
                me.currentTiming += me.timerIncrement;
                    
                me.eventQueue.push(event);
            });
 
            if (item.screenshot) {
                me.eventQueue[me.eventQueue.length - 1].screenshot = true;
            }
        });
        return me.eventQueue;
    }
});