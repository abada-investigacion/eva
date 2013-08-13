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
 * @class Ext.chart.Tip
 * Provides tips for Ext.chart.series.Series.
 */
Ext.define('Ext.chart.Tip', {

    /* Begin Definitions */

    requires: ['Ext.tip.ToolTip', 'Ext.chart.TipSurface'],

    /* End Definitions */

    constructor: function(config) {
        var me = this,
            surface,
            sprites,
            tipSurface;
        if (config.tips) {
            me.tipTimeout = null;
            me.tipConfig = Ext.apply({}, config.tips, {
                renderer: Ext.emptyFn,
                constrainPosition: true,
                autoHide: true
            });
            me.tooltip = new Ext.tip.ToolTip(me.tipConfig);
            me.chart.surface.on('mousemove', me.tooltip.onMouseMove, me.tooltip);
            me.chart.surface.on('mouseleave', function() {
                me.hideTip();
            });
            if (me.tipConfig.surface) {
                //initialize a surface
                surface = me.tipConfig.surface;
                sprites = surface.sprites;
                tipSurface = new Ext.chart.TipSurface({
                    id: 'tipSurfaceComponent',
                    sprites: sprites
                });
                if (surface.width && surface.height) {
                    tipSurface.setSize(surface.width, surface.height);
                }
                me.tooltip.add(tipSurface);
                me.spriteTip = tipSurface;
            }
        }
    },

    showTip: function(item) {
        var me = this,
            tooltip,
            spriteTip,
            tipConfig,
            trackMouse,
            sprite,
            surface,
            surfaceExt,
            pos,
            x,
            y;
        if (!me.tooltip) {
            return;
        }
        clearTimeout(me.tipTimeout);
        tooltip = me.tooltip;
        spriteTip = me.spriteTip;
        tipConfig = me.tipConfig;
        trackMouse = tooltip.trackMouse;
        if (!trackMouse) {
            tooltip.trackMouse = true;
            sprite = item.sprite;
            surface = sprite.surface;
            surfaceExt = Ext.get(surface.getId());
            if (surfaceExt) {
                pos = surfaceExt.getXY();
                x = pos[0] + (sprite.attr.x || 0) + (sprite.attr.translation && sprite.attr.translation.x || 0);
                y = pos[1] + (sprite.attr.y || 0) + (sprite.attr.translation && sprite.attr.translation.y || 0);
                tooltip.targetXY = [x, y];
            }
        }
        if (spriteTip) {
            tipConfig.renderer.call(tooltip, item.storeItem, item, spriteTip.surface);
        } else {
            tipConfig.renderer.call(tooltip, item.storeItem, item);
        }
        tooltip.show();
        tooltip.trackMouse = trackMouse;
    },

    hideTip: function(item) {
        var tooltip = this.tooltip;
        if (!tooltip) {
            return;
        }
        clearTimeout(this.tipTimeout);
        this.tipTimeout = setTimeout(function() {
            tooltip.hide();
        }, 0);
    }
});