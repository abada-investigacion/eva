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
 * @class Ext.fx.target.Target

This class specifies a generic target for an animation. It provides a wrapper around a
series of different types of objects to allow for a generic animation API.
A target can be a single object or a Composite object containing other objects that are 
to be animated. This class and it's subclasses are generally not created directly, the 
underlying animation will create the appropriate Ext.fx.target.Target object by passing 
the instance to be animated.

The following types of objects can be animated:

- {@link Ext.fx.target.Component Components}
- {@link Ext.fx.target.Element Elements}
- {@link Ext.fx.target.Sprite Sprites}

 * @markdown
 * @abstract
 */
Ext.define('Ext.fx.target.Target', {

    isAnimTarget: true,

    /**
     * Creates new Target.
     * @param {Ext.Component/Ext.Element/Ext.draw.Sprite} target The object to be animated
     */
    constructor: function(target) {
        this.target = target;
        this.id = this.getId();
    },
    
    getId: function() {
        return this.target.id;
    }
});
