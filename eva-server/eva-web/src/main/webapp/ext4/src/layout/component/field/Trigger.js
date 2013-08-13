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
 * Layout class for {@link Ext.form.field.Trigger} fields. Adjusts the input field size to accommodate
 * the trigger button(s).
 * @private
 */
Ext.define('Ext.layout.component.field.Trigger', {

    /* Begin Definitions */

    alias: 'layout.triggerfield',

    extend: 'Ext.layout.component.field.Field',

    /* End Definitions */

    type: 'triggerfield',

    beginLayout: function(ownerContext) {
        var me = this,
            owner = me.owner,
            flags;

        ownerContext.triggerWrap = ownerContext.getEl('triggerWrap');

        me.callParent(arguments);

        // if any of these important states have changed, sync them now:
        flags = owner.getTriggerStateFlags();
        if (flags != owner.lastTriggerStateFlags) {
            owner.lastTriggerStateFlags = flags;
            me.updateEditState();
        }
    },

    beginLayoutFixed: function (ownerContext, width, suffix) {
        var me = this,
            owner = ownerContext.target,
            ieInputWidthAdjustment = me.ieInputWidthAdjustment || 0,
            inputWidth = '100%',
            triggerWrap = owner.triggerWrap;

        me.callParent(arguments);

        owner.inputCell.setStyle('width', '100%');
        if(ieInputWidthAdjustment) {
            // adjust for IE 6/7 strict content-box model
            // RTL: This might have to be padding-left unless the senses of the padding styles switch when in RTL mode.
            owner.inputCell.setStyle('padding-right', ieInputWidthAdjustment + 'px');
            if(suffix === 'px') {
                if (owner.inputWidth) {
                    inputWidth = owner.inputWidth - owner.getTriggerWidth();
                } else {
                    inputWidth = width - ieInputWidthAdjustment - owner.getTriggerWidth();
                }
                inputWidth += 'px';
            }
        }
        owner.inputEl.setStyle('width', inputWidth);
        inputWidth = owner.inputWidth;
        if (inputWidth) {
            triggerWrap.setStyle('width', inputWidth + (ieInputWidthAdjustment) + 'px');
        } else {
            triggerWrap.setStyle('width', width + suffix);
        }
        triggerWrap.setStyle('table-layout', 'fixed');
    },

    beginLayoutShrinkWrap: function (ownerContext) {
        var owner = ownerContext.target,
            emptyString = '',
            inputWidth = owner.inputWidth,
            triggerWrap = owner.triggerWrap,
            ieInputWidthAdjustment = this.ieInputWidthAdjustment || 0;

        this.callParent(arguments);

        if (inputWidth) {
            triggerWrap.setStyle('width', inputWidth + 'px');
            inputWidth = (inputWidth - owner.getTriggerWidth()) + 'px';
            owner.inputEl.setStyle('width', inputWidth);
            owner.inputCell.setStyle('width', inputWidth);
        } else {
            owner.inputCell.setStyle('width', emptyString);
            owner.inputEl.setStyle('width', emptyString);
            triggerWrap.setStyle('width', emptyString);
            triggerWrap.setStyle('table-layout', 'auto');
        }
    },

    getTextWidth: function () {
        var me = this,
            owner = me.owner,
            inputEl = owner.inputEl,
            value;

        // Find the width that contains the whole text value
        value = (inputEl.dom.value || (owner.hasFocus ? '' : owner.emptyText) || '') + owner.growAppend;
        return inputEl.getTextWidth(value);
    },

    measureContentWidth: function (ownerContext) {
        var me = this,
            owner = me.owner,
            width = me.callParent(arguments),
            inputContext = ownerContext.inputContext,
            calcWidth, max, min;

        if (owner.grow && !ownerContext.state.growHandled) {
            calcWidth = me.getTextWidth() + ownerContext.inputContext.getFrameInfo().width;

            max = owner.growMax;
            min = Math.min(max, width);
            max = Math.max(owner.growMin, max, min);

            // Constrain
            calcWidth = Ext.Number.constrain(calcWidth, owner.growMin, max);
            inputContext.setWidth(calcWidth);
            ownerContext.state.growHandled = true;
            
            // Now that we've set the inputContext, we need to recalculate the width
            inputContext.domBlock(me, 'width');
            width = NaN;
        }
        return width;
    },

    updateEditState: function() {
        var me = this,
            owner = me.owner,
            inputEl = owner.inputEl,
            noeditCls = Ext.baseCSSPrefix + 'trigger-noedit',
            displayed,
            readOnly;

        if (me.owner.readOnly) {
            inputEl.addCls(noeditCls);
            readOnly = true;
            displayed = false;
        } else {
            if (me.owner.editable) {
                inputEl.removeCls(noeditCls);
                readOnly = false;
            } else {
                inputEl.addCls(noeditCls);
                readOnly = true;
            }
            displayed = !me.owner.hideTrigger;
        }

        owner.triggerCell.setDisplayed(displayed);
        inputEl.dom.readOnly = readOnly;
    }
});
