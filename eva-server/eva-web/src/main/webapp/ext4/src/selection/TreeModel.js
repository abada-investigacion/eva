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
 * Adds custom behavior for left/right keyboard navigation for use with a tree.
 * Depends on the view having an expand and collapse method which accepts a
 * record.
 * 
 * @private
 */
Ext.define('Ext.selection.TreeModel', {
    extend: 'Ext.selection.RowModel',
    alias: 'selection.treemodel',
    
    // typically selection models prune records from the selection
    // model when they are removed, because the TreeView constantly
    // adds/removes records as they are expanded/collapsed
    pruneRemoved: false,
    
    onKeyRight: function(e, t) {
        var focused = this.getLastFocused(),
            view    = this.view;
            
        if (focused) {
            // tree node is already expanded, go down instead
            // this handles both the case where we navigate to firstChild and if
            // there are no children to the nextSibling
            if (focused.isExpanded()) {
                this.onKeyDown(e, t);
            // if its not a leaf node, expand it
            } else if (focused.isExpandable()) {
                view.expand(focused);
            }
        }
    },
    
    onKeyLeft: function(e, t) {
        var focused = this.getLastFocused(),
            view    = this.view,
            viewSm  = view.getSelectionModel(),
            parentNode, parentRecord;

        if (focused) {
            parentNode = focused.parentNode;
            // if focused node is already expanded, collapse it
            if (focused.isExpanded()) {
                view.collapse(focused);
            // has a parentNode and its not root
            // TODO: this needs to cover the case where the root isVisible
            } else if (parentNode && !parentNode.isRoot()) {
                // Select a range of records when doing multiple selection.
                if (e.shiftKey) {
                    viewSm.selectRange(parentNode, focused, e.ctrlKey, 'up');
                    viewSm.setLastFocused(parentNode);
                // just move focus, not selection
                } else if (e.ctrlKey) {
                    viewSm.setLastFocused(parentNode);
                // select it
                } else {
                    viewSm.select(parentNode);
                }
            }
        }
    },
    
    onKeySpace: function(e, t) {
        this.toggleCheck(e);
    },
    
    onKeyEnter: function(e, t) {
        this.toggleCheck(e);
    },
    
    toggleCheck: function(e){
        e.stopEvent();
        var selected = this.getLastSelected();
        if (selected) {
            this.view.onCheckChange(selected);
        }
    }
});
