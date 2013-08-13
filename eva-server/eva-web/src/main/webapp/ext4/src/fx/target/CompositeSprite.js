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
 * @class Ext.fx.target.CompositeSprite

This class represents a animation target for a {@link Ext.draw.CompositeSprite}. It allows
each {@link Ext.draw.Sprite} in the group to be animated as a whole. In general this class will not be
created directly, the {@link Ext.draw.CompositeSprite} will be passed to the animation and
and the appropriate target will be created.

 * @markdown
 */

Ext.define('Ext.fx.target.CompositeSprite', {

    /* Begin Definitions */

    extend: 'Ext.fx.target.Sprite',

    /* End Definitions */

    getAttr: function(attr, val) {
        var out     = [],
            sprites = [].concat(this.target.items),
            length  = sprites.length,
            i,
            sprite;

        for (i = 0; i < length; i++) {
            sprite = sprites[i];
            out.push([sprite, val != undefined ? val : this.getFromPrim(sprite, attr)]);
        }

        return out;
    }
});
