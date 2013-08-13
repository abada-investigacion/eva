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
 * Simple helper class for easily creating image components. This renders an image tag to
 * the DOM with the configured src.
 *
 * {@img Ext.Img/Ext.Img.png Ext.Img component}
 *
 * ## Example usage:
 *
 *     var changingImage = Ext.create('Ext.Img', {
 *         src: 'http://www.sencha.com/img/20110215-feat-html5.png',
 *         renderTo: Ext.getBody()
 *     });
 *
 *     // change the src of the image programmatically
 *     changingImage.setSrc('http://www.sencha.com/img/20110215-feat-perf.png');
 *
 * By default, only an img element is rendered and that is this component's primary
 * {@link Ext.AbstractComponent#getEl element}. If the {@link Ext.AbstractComponent#autoEl} property
 * is other than 'img' (the default), the a child img element will be added to the primary
 * element. This can be used to create a wrapper element around the img.
 *
 * ## Wrapping the img in a div:
 *
 *     var wrappedImage = Ext.create('Ext.Img', {
 *         src: 'http://www.sencha.com/img/20110215-feat-html5.png',
 *         autoEl: 'div', // wrap in a div
 *         renderTo: Ext.getBody()
 *     });
 */
Ext.define('Ext.Img', {
    extend: 'Ext.Component',
    alias: ['widget.image', 'widget.imagecomponent'],

    autoEl: 'img',

    /**
     * @cfg {String} src
     * The image src.
     */
    src: '',

    /**
     * @cfg {String} alt
     * The descriptive text for non-visual UI description.
     */
    alt: '',

    /**
     * @cfg {String} imgCls
     * Optional CSS classes to add to the img element.
     */
    imgCls: '',

    getElConfig: function() {
        var me = this,
            config = me.callParent(),
            img;

        // It is sometimes helpful (like in a panel header icon) to have the img wrapped
        // by a div. If our autoEl is not 'img' then we just add an img child to the el.
        if (me.autoEl == 'img') {
            img = config;
        } else {
            config.cn = [img = {
                tag: 'img',
                id: me.id + '-img'
            }];
        }

        if (me.imgCls) {
            img.cls = (img.cls ? img.cls + ' ' : '') + me.imgCls;
        }

        img.src = me.src || Ext.BLANK_IMAGE_URL;
        if (me.alt) {
            img.alt = me.alt;
        }

        return config;
    },

    onRender: function () {
        var me = this,
            el;

        me.callParent(arguments);

        el = me.el;
        me.imgEl = (me.autoEl == 'img') ? el : el.getById(me.id + '-img');
    },

    onDestroy: function () {
        Ext.destroy(this.imgEl);
        this.imgEl = null;
        this.callParent();
    },

    /**
     * Updates the {@link #src} of the image.
     * @param {String} src
     */
    setSrc: function(src) {
        var me = this,
            imgEl = me.imgEl;

        me.src = src;

        if (imgEl) {
            imgEl.dom.src = src || Ext.BLANK_IMAGE_URL;
        }
    }
});