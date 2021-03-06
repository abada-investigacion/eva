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
 * @class Ext.ux.FieldReplicator
 * <p>A plugin for Field Components which creates clones of the Field for as
 * long as the user keeps filling them. Leaving the final one blank ends the repeating series.</p>
 * <p>Usage:</p>
 * <pre><code>
    {
        xtype: 'combo',
        plugins: [ Ext.ux.FieldReplicator ],
        triggerAction: 'all',
        fieldLabel: 'Select recipient',
        store: recipientStore
    }
 * </code></pre>
 */
Ext.define('Ext.ux.FieldReplicator', {
    singleton: true,

    init: function(field) {
        // Assign the field an id grouping it with fields cloned from it. If it already
        // has an id that means it is itself a clone.
        if (!field.replicatorId) {
            field.replicatorId = Ext.id();
        }

        field.on('blur', this.onBlur, this);
    },

    onBlur: function(field) {
        var ownerCt = field.ownerCt,
            replicatorId = field.replicatorId,
            isEmpty = Ext.isEmpty(field.getRawValue()),
            siblings = ownerCt.query('[replicatorId=' + replicatorId + ']'),
            isLastInGroup = siblings[siblings.length - 1] === field,
            clone, idx;

        // If a field before the final one was blanked out, remove it
        if (isEmpty && !isLastInGroup) {
            Ext.Function.defer(field.destroy, 10, field); //delay to allow tab key to move focus first
        }
        // If the field is the last in the list and has a value, add a cloned field after it
        else if(!isEmpty && isLastInGroup) {
            if (field.onReplicate) {
                field.onReplicate();
            }
            clone = field.cloneConfig({replicatorId: replicatorId});
            idx = ownerCt.items.indexOf(field);
            ownerCt.add(idx + 1, clone);
        }
    }

});