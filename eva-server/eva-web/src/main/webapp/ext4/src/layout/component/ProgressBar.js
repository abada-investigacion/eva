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
 */
Ext.define('Ext.layout.component.ProgressBar', {

    /* Begin Definitions */

    alias: ['layout.progressbar'],

    extend: 'Ext.layout.component.Auto',

    /* End Definitions */

    type: 'progressbar',

    beginLayout: function (ownerContext) {
        var me = this,
            i, textEls;

        me.callParent(arguments);

        if (!ownerContext.textEls) {
            textEls = me.owner.textEl; // an Ext.Element or CompositeList (raw DOM el's)

            if (textEls.isComposite) {
                ownerContext.textEls = [];
                textEls = textEls.elements;
                for (i = textEls.length; i--; ) {
                    ownerContext.textEls[i] = ownerContext.getEl(Ext.get(textEls[i]));
                }
            } else {
                ownerContext.textEls = [ ownerContext.getEl('textEl') ];
            }
        }
    },

    calculate: function(ownerContext) {
        var me = this,
            i, textEls, width;

        me.callParent(arguments);

        if (Ext.isNumber(width = ownerContext.getProp('width'))) {
            width -= ownerContext.getBorderInfo().width;
            textEls = ownerContext.textEls;

            for (i = textEls.length; i--; ) {
                textEls[i].setWidth(width);
            }
        } else {
            me.done = false;
        }
    }
});
