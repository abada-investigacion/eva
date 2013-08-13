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
 * Private utility class that manages the internal Shadow cache.
 * @private
 */
Ext.define('Ext.ShadowPool', {
    singleton: true,
    requires: ['Ext.DomHelper'],

    markup: (function() {
        return Ext.String.format(
            '<div class="{0}{1}-shadow" role="presentation"></div>',
            Ext.baseCSSPrefix,
            Ext.isIE && !Ext.supports.CSS3BoxShadow ? 'ie' : 'css'
        );
    }()),

    shadows: [],

    pull: function() {
        var sh = this.shadows.shift();
        if (!sh) {
            sh = Ext.get(Ext.DomHelper.insertHtml("beforeBegin", document.body.firstChild, this.markup));
            sh.autoBoxAdjust = false;
        }
        return sh;
    },

    push: function(sh) {
        this.shadows.push(sh);
    },
    
    reset: function() {
        var shadows = [].concat(this.shadows),
            s,
            sLen    = shadows.length;

        for (s = 0; s < sLen; s++) {
            shadows[s].remove();
        }

        this.shadows = [];
    }
});