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
Ext.define('Ext.grid.feature.RowWrap', {
    extend: 'Ext.grid.feature.Feature',
    alias: 'feature.rowwrap',

    // turn off feature events.
    hasFeatureEvent: false,

    init: function() {
        if (!this.disabled) {
            this.enable();
        }
    },

    getRowSelector: function(){
        return 'tr:has(> ' + this.view.cellSelector + ')';
    },

    enable: function(){
        var me = this,
            view = me.view;

        me.callParent();
        // we need to mutate the rowSelector since the template changes the ordering
        me.savedRowSelector = view.rowSelector;
        view.rowSelector = me.getRowSelector();

        // Extra functionality needed on header resize when row is wrapped:
        // Every individual cell in a column needs its width syncing.
        // So we produce a different column selector which includes al TDs in a column
        view.getComponentLayout().getColumnSelector = me.getColumnSelector;
    },

    disable: function(){
        var me = this,
            view = me.view,
            saved = me.savedRowSelector;

        me.callParent();
        if (saved) {
            view.rowSelector = saved;
        }
        delete me.savedRowSelector;
    },

    mutateMetaRowTpl: function(metaRowTpl) {  
        var prefix = Ext.baseCSSPrefix;      
        // Remove "x-grid-row" from the first row, note this could be wrong
        // if some other feature unshifted things in front.
        metaRowTpl[0] = metaRowTpl[0].replace(prefix + 'grid-row', '');
        metaRowTpl[0] = metaRowTpl[0].replace("{[this.embedRowCls()]}", "");
        // 2
        metaRowTpl.unshift('<table class="' + prefix + 'grid-table ' + prefix + 'grid-table-resizer" style="width: {[this.embedFullWidth()]}px;">');
        // 1
        metaRowTpl.unshift('<tr class="' + prefix + 'grid-row {[this.embedRowCls()]}"><td colspan="{[this.embedColSpan()]}"><div class="' + prefix + 'grid-rowwrap-div">');

        // 3
        metaRowTpl.push('</table>');
        // 4
        metaRowTpl.push('</div></td></tr>');
    },

    embedColSpan: function() {
        return '{colspan}';
    },

    embedFullWidth: function() {
        return '{fullWidth}';
    },

    getAdditionalData: function(data, idx, record, orig) {
        var headerCt = this.view.headerCt,
            colspan  = headerCt.getColumnCount(),
            fullWidth = headerCt.getFullWidth(),
            items    = headerCt.query('gridcolumn'),
            itemsLn  = items.length,
            i = 0,
            o = {
                colspan: colspan,
                fullWidth: fullWidth
            },
            id,
            tdClsKey,
            colResizerCls;

        for (; i < itemsLn; i++) {
            id = items[i].id;
            tdClsKey = id + '-tdCls';
            colResizerCls = Ext.baseCSSPrefix + 'grid-col-resizer-'+id;
            // give the inner td's the resizer class
            // while maintaining anything a user may have injected via a custom
            // renderer
            o[tdClsKey] = colResizerCls + " " + (orig[tdClsKey] ? orig[tdClsKey] : '');
            // TODO: Unhackify the initial rendering width's
            o[id+'-tdAttr'] = " style=\"width: " + (items[i].hidden ? 0 : items[i].getDesiredWidth()) + "px;\" "/* + (i === 0 ? " rowspan=\"2\"" : "")*/;
            if (orig[id+'-tdAttr']) {
                o[id+'-tdAttr'] += orig[id+'-tdAttr'];
            }
        }

        return o;
    },

    getMetaRowTplFragments: function() {
        return {
            embedFullWidth: this.embedFullWidth,
            embedColSpan: this.embedColSpan
        };
    },

    getColumnSelector: function(header) {
        var s = Ext.baseCSSPrefix + 'grid-col-resizer-' + header.id;
        return 'th.' + s + ',td.' + s;
    }
});