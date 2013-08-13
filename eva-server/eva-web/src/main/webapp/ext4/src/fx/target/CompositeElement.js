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
 * @class Ext.fx.target.CompositeElement
 * 
 * This class represents a animation target for a {@link Ext.CompositeElement}. It allows
 * each {@link Ext.Element} in the group to be animated as a whole. In general this class will not be
 * created directly, the {@link Ext.CompositeElement} will be passed to the animation and
 * and the appropriate target will be created.
 */
Ext.define('Ext.fx.target.CompositeElement', {

    /* Begin Definitions */

    extend: 'Ext.fx.target.Element',

    /* End Definitions */

    /**
     * @property {Boolean} isComposite
     * `true` in this class to identify an object as an instantiated CompositeElement, or subclass thereof.
     */
    isComposite: true,
    
    constructor: function(target) {
        target.id = target.id || Ext.id(null, 'ext-composite-');
        this.callParent([target]);
    },

    getAttr: function(attr, val) {
        var out      = [],
            elements = this.target.elements,
            length   = elements.length,
            i,
            el;

        for (i = 0; i < length; i++) {
            el = elements[i];

            if (el) {
                el = this.target.getElement(el);
                out.push([el, this.getElVal(el, attr, val)]);
            }
        }

        return out;
    }
});
