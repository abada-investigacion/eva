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
 * @private
 * Private utility class for managing all {@link Ext.form.field.Radio} fields grouped by name.
 */
Ext.define('Ext.form.RadioManager', {
    extend: 'Ext.util.MixedCollection',
    singleton: true,

    getByName: function(name, formId) {
        return this.filterBy(function(item) {
            return item.name == name && item.getFormId() == formId;
        });
    },

    getWithValue: function(name, value, formId) {
        return this.filterBy(function(item) {
            return item.name == name && item.inputValue == value && item.getFormId() == formId;
        });
    },

    getChecked: function(name, formId) {
        return this.findBy(function(item) {
            return item.name == name && item.checked && item.getFormId() == formId;
        });
    }
});
