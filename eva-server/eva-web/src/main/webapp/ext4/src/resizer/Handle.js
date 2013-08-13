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
 * Provides a handle for 9-point resizing of Elements or Components.
 */
Ext.define('Ext.resizer.Handle', {
    extend: 'Ext.Component',
    handleCls: '',
    baseHandleCls: Ext.baseCSSPrefix + 'resizable-handle',
    // Ext.resizer.Resizer.prototype.possiblePositions define the regions
    // which will be passed in as a region configuration.
    region: '',

    beforeRender: function() {
        var me = this;

        me.callParent();

        me.addCls(
            me.baseHandleCls,
            me.baseHandleCls + '-' + me.region,
            me.handleCls
        );
    },

    onRender: function() {
        this.callParent(arguments);

        this.el.unselectable();
    }
});
