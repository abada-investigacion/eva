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
 * @class Ext.fx.target.ElementCSS
 * 
 * This class represents a animation target for an {@link Ext.Element} that supports CSS
 * based animation. In general this class will not be created directly, the {@link Ext.Element} 
 * will be passed to the animation and the appropriate target will be created.
 */
Ext.define('Ext.fx.target.ElementCSS', {

    /* Begin Definitions */

    extend: 'Ext.fx.target.Element',

    /* End Definitions */

    setAttr: function(targetData, isFirstFrame) {
        var cssArr = {
                attrs: [],
                duration: [],
                easing: []
            },
            ln = targetData.length,
            attributes,
            attrs,
            attr,
            easing,
            duration,
            o,
            i,
            j,
            ln2;
        for (i = 0; i < ln; i++) {
            attrs = targetData[i];
            duration = attrs.duration;
            easing = attrs.easing;
            attrs = attrs.attrs;
            for (attr in attrs) {
                if (Ext.Array.indexOf(cssArr.attrs, attr) == -1) {
                    cssArr.attrs.push(attr.replace(/[A-Z]/g, function(v) {
                        return '-' + v.toLowerCase();
                    }));
                    cssArr.duration.push(duration + 'ms');
                    cssArr.easing.push(easing);
                }
            }
        }
        attributes = cssArr.attrs.join(',');
        duration = cssArr.duration.join(',');
        easing = cssArr.easing.join(', ');
        for (i = 0; i < ln; i++) {
            attrs = targetData[i].attrs;
            for (attr in attrs) {
                ln2 = attrs[attr].length;
                for (j = 0; j < ln2; j++) {
                    o = attrs[attr][j];
                    o[0].setStyle(Ext.supports.CSS3Prefix + 'TransitionProperty', isFirstFrame ? '' : attributes);
                    o[0].setStyle(Ext.supports.CSS3Prefix + 'TransitionDuration', isFirstFrame ? '' : duration);
                    o[0].setStyle(Ext.supports.CSS3Prefix + 'TransitionTimingFunction', isFirstFrame ? '' : easing);
                    o[0].setStyle(attr, o[1]);

                    // Must trigger reflow to make this get used as the start point for the transition that follows
                    if (isFirstFrame) {
                        o = o[0].dom.offsetWidth;
                    }
                    else {
                        // Remove transition properties when completed.
                        o[0].on(Ext.supports.CSS3TransitionEnd, function() {
                            this.setStyle(Ext.supports.CSS3Prefix + 'TransitionProperty', null);
                            this.setStyle(Ext.supports.CSS3Prefix + 'TransitionDuration', null);
                            this.setStyle(Ext.supports.CSS3Prefix + 'TransitionTimingFunction', null);
                        }, o[0], { single: true });
                    }
                }
            }
        }
    }
});