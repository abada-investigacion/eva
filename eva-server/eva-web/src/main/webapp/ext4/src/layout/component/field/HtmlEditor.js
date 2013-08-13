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
 * Layout class for {@link Ext.form.field.HtmlEditor} fields. Sizes the toolbar, textarea, and iframe elements.
 * @private
 */
Ext.define('Ext.layout.component.field.HtmlEditor', {
    extend: 'Ext.layout.component.field.Field',
    alias: ['layout.htmleditor'],

    type: 'htmleditor',

    // Flags to say that the item is autosizing itself.
    toolbarSizePolicy: {
        setsWidth: 0,
        setsHeight: 0
    },

    beginLayout: function(ownerContext) {
        this.callParent(arguments);

        ownerContext.textAreaContext = ownerContext.getEl('textareaEl');
        ownerContext.iframeContext   = ownerContext.getEl('iframeEl');
        ownerContext.toolbarContext  = ownerContext.context.getCmp(this.owner.getToolbar());
    },
    
    // It's not a container, can never add/remove dynamically
    renderItems: Ext.emptyFn,

    getItemSizePolicy: function (item) {
        // we are only ever called by the toolbar
        return this.toolbarSizePolicy;
    },

    getLayoutItems: function () {
        var toolbar = this.owner.getToolbar();
        // The toolbar may not exist if we're destroying
        return toolbar ? [toolbar] : [];
    },

    getRenderTarget: function() {
        return this.owner.bodyEl;
    },

    publishInnerHeight: function (ownerContext, height) {
        var me = this,
            innerHeight = height - me.measureLabelErrorHeight(ownerContext) -
                          ownerContext.toolbarContext.getProp('height') -
                          ownerContext.bodyCellContext.getPaddingInfo().height;

        // If the Toolbar has not acheieved a height yet, we are not done laying out.
        if (Ext.isNumber(innerHeight)) {
            ownerContext.textAreaContext.setHeight(innerHeight);
            ownerContext.iframeContext.setHeight(innerHeight);
        } else {
            me.done = false;
        }
    }
});