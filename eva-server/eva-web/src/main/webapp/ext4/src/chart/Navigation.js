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
 * @class Ext.chart.Navigation
 *
 * Handles panning and zooming capabilities.
 *
 * Used as mixin by Ext.chart.Chart.
 */
Ext.define('Ext.chart.Navigation', {

    constructor: function() {
        this.originalStore = this.store;
    },

    /**
     * Zooms the chart to the specified selection range.
     * Can be used with a selection mask. For example:
     *
     *     items: {
     *         xtype: 'chart',
     *         animate: true,
     *         store: store1,
     *         mask: 'horizontal',
     *         listeners: {
     *             select: {
     *                 fn: function(me, selection) {
     *                     me.setZoom(selection);
     *                     me.mask.hide();
     *                 }
     *             }
     *         }
     *     }
     */
    setZoom: function(zoomConfig) {
        var me = this,
            axes = me.axes,
            axesItems = axes.items,
            i, ln, axis,
            bbox = me.chartBBox,
            xScale = 1 / bbox.width,
            yScale = 1 / bbox.height,
            zoomer = {
                x : zoomConfig.x * xScale,
                y : zoomConfig.y * yScale,
                width : zoomConfig.width * xScale,
                height : zoomConfig.height * yScale
            },
            ends, from, to;
        for (i = 0, ln = axesItems.length; i < ln; i++) {
            axis = axesItems[i];
            ends = axis.calcEnds();
            if (axis.position == 'bottom' || axis.position == 'top') {
                from = (ends.to - ends.from) * zoomer.x + ends.from;
                to = (ends.to - ends.from) * zoomer.width + from;
                axis.minimum = from;
                axis.maximum = to;
            } else {
                to = (ends.to - ends.from) * (1 - zoomer.y) + ends.from;
                from = to - (ends.to - ends.from) * zoomer.height;
                axis.minimum = from;
                axis.maximum = to;
            }
        }
        me.redraw(false);
    },

    /**
     * Restores the zoom to the original value. This can be used to reset
     * the previous zoom state set by `setZoom`. For example:
     *
     *     myChart.restoreZoom();
     */
    restoreZoom: function() {
        if (this.originalStore) {
            this.store = this.substore = this.originalStore;
            this.redraw(true);
        }
    }

});
