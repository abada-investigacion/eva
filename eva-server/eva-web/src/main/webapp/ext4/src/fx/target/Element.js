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
 * @class Ext.fx.target.Element
 * 
 * This class represents a animation target for an {@link Ext.Element}. In general this class will not be
 * created directly, the {@link Ext.Element} will be passed to the animation and
 * and the appropriate target will be created.
 */
Ext.define('Ext.fx.target.Element', {

    /* Begin Definitions */
    
    extend: 'Ext.fx.target.Target',
    
    /* End Definitions */

    type: 'element',

    getElVal: function(el, attr, val) {
        if (val == undefined) {
            if (attr === 'x') {
                val = el.getX();
            }
            else if (attr === 'y') {
                val = el.getY();
            }
            else if (attr === 'scrollTop') {
                val = el.getScroll().top;
            }
            else if (attr === 'scrollLeft') {
                val = el.getScroll().left;
            }
            else if (attr === 'height') {
                val = el.getHeight();
            }
            else if (attr === 'width') {
                val = el.getWidth();
            }
            else {
                val = el.getStyle(attr);
            }
        }
        return val;
    },

    getAttr: function(attr, val) {
        var el = this.target;
        return [[ el, this.getElVal(el, attr, val)]];
    },

    setAttr: function(targetData) {
        var target = this.target,
            ln = targetData.length,
            attrs, attr, o, i, j, ln2, element, value;
        for (i = 0; i < ln; i++) {
            attrs = targetData[i].attrs;
            for (attr in attrs) {
                if (attrs.hasOwnProperty(attr)) {
                    ln2 = attrs[attr].length;
                    for (j = 0; j < ln2; j++) {
                        o = attrs[attr][j];
                        element = o[0];
                        value = o[1];
                        if (attr === 'x') {
                            element.setX(value);
                        } else if (attr === 'y') {
                            element.setY(value);
                        } else if (attr === 'scrollTop') {
                            element.scrollTo('top', value);
                        } else if (attr === 'scrollLeft') {
                            element.scrollTo('left',value);
                        } else if (attr === 'width') {
                            element.setWidth(value);
                        } else if (attr === 'height') {
                            element.setHeight(value);
                        } else {
                            element.setStyle(attr, value);
                        }
                    }
                }
            }
        }
    }
});
