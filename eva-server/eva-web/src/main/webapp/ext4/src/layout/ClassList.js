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
 * This class provides a DOM ClassList API to buffer access to an element's class.
 * Instances of this class are created by {@link Ext.layout.ContextItem#getClassList}.
 */
Ext.define('Ext.layout.ClassList', (function () {

    var splitWords = Ext.String.splitWords,
        toMap = Ext.Array.toMap;

    return {
        dirty: false,

        constructor: function (owner) {
            this.owner = owner;
            this.map = toMap(this.classes = splitWords(owner.el.className));
        },

        /**
         * Adds a single class to the class list.
         */
        add: function (cls) {
            var me = this;

            if (!me.map[cls]) {
                me.map[cls] = true;
                me.classes.push(cls);
                if (!me.dirty) {
                    me.dirty = true;
                    me.owner.markDirty();
                }
            }
        },

        /**
         * Adds one or more classes in an array or space-delimited string to the class list.
         */
        addMany: function (classes) {
            Ext.each(splitWords(classes), this.add, this);
        },

        contains: function (cls) {
            return this.map[cls];
        },

        flush: function () {
            this.owner.el.className = this.classes.join(' ');
            this.dirty = false;
        },

        /**
         * Removes a single class from the class list.
         */
        remove: function (cls) {
            var me = this;

            if (me.map[cls]) {
                delete me.map[cls];
                me.classes = Ext.Array.filter(me.classes, function (c) {
                    return c != cls;
                });
                if (!me.dirty) {
                    me.dirty = true;
                    me.owner.markDirty();
                }
            }
        },

        /**
         * Removes one or more classes in an array or space-delimited string from the class
         * list.
         */
        removeMany: function (classes) {
            var me = this,
                remove = toMap(splitWords(classes));

            me.classes = Ext.Array.filter(me.classes, function (c) {
                if (!remove[c]) {
                    return true;
                }

                delete me.map[c];
                if (!me.dirty) {
                    me.dirty = true;
                    me.owner.markDirty();
                }
                return false;
            });
        }
    };
}()));
