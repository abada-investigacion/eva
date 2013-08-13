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
 * Component layout for tabs
 * @private
 */
Ext.define('Ext.layout.component.Tab', {

    extend: 'Ext.layout.component.Button',
    alias: 'layout.tab',

    beginLayout: function(ownerContext) {
        var me = this,
            closable = me.owner.closable;

        // Changing the close button visibility causes our cached measurements to be wrong,
        // so we must convince our base class to re-cache those adjustments...
        if (me.lastClosable !== closable) {
            me.lastClosable = closable;
            me.clearTargetCache();
        }

        me.callParent(arguments);
    }
});
