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
 * Represents a 2D point with x and y properties, useful for comparison and instantiation
 * from an event:
 *
 *     var point = Ext.util.Point.fromEvent(e);
 *
 */
Ext.define('Ext.util.Point', {

    /* Begin Definitions */
    extend: 'Ext.util.Region',

    statics: {

        /**
         * Returns a new instance of Ext.util.Point base on the pageX / pageY values of the given event
         * @static
         * @param {Event} e The event
         * @return {Ext.util.Point}
         */
        fromEvent: function(e) {
            e = (e.changedTouches && e.changedTouches.length > 0) ? e.changedTouches[0] : e;
            return new this(e.pageX, e.pageY);
        }
    },

    /* End Definitions */

    /**
     * Creates a point from two coordinates.
     * @param {Number} x X coordinate.
     * @param {Number} y Y coordinate.
     */
    constructor: function(x, y) {
        this.callParent([y, x, y, x]);
    },

    /**
     * Returns a human-eye-friendly string that represents this point,
     * useful for debugging
     * @return {String}
     */
    toString: function() {
        return "Point[" + this.x + "," + this.y + "]";
    },

    /**
     * Compare this point and another point
     * @param {Ext.util.Point/Object} The point to compare with, either an instance
     * of Ext.util.Point or an object with left and top properties
     * @return {Boolean} Returns whether they are equivalent
     */
    equals: function(p) {
        return (this.x == p.x && this.y == p.y);
    },

    /**
     * Whether the given point is not away from this point within the given threshold amount.
     * @param {Ext.util.Point/Object} p The point to check with, either an instance
     * of Ext.util.Point or an object with left and top properties
     * @param {Object/Number} threshold Can be either an object with x and y properties or a number
     * @return {Boolean}
     */
    isWithin: function(p, threshold) {
        if (!Ext.isObject(threshold)) {
            threshold = {
                x: threshold,
                y: threshold
            };
        }

        return (this.x <= p.x + threshold.x && this.x >= p.x - threshold.x &&
                this.y <= p.y + threshold.y && this.y >= p.y - threshold.y);
    },

    /**
     * Compare this point with another point when the x and y values of both points are rounded. E.g:
     * [100.3,199.8] will equals to [100, 200]
     * @param {Ext.util.Point/Object} p The point to compare with, either an instance
     * of Ext.util.Point or an object with x and y properties
     * @return {Boolean}
     */
    roundedEquals: function(p) {
        return (Math.round(this.x) == Math.round(p.x) && Math.round(this.y) == Math.round(p.y));
    }
}, function() {
    /**
     * @method
     * Alias for {@link #translateBy}
     * @inheritdoc Ext.util.Region#translateBy
     */
    this.prototype.translate = Ext.util.Region.prototype.translateBy;
});
