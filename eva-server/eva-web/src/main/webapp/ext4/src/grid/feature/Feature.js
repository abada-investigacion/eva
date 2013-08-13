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
 * A feature is a type of plugin that is specific to the {@link Ext.grid.Panel}. It provides several
 * hooks that allows the developer to inject additional functionality at certain points throughout the 
 * grid creation cycle. This class provides the base template methods that are available to the developer,
 * it should be extended.
 * 
 * There are several built in features that extend this class, for example:
 *
 *  - {@link Ext.grid.feature.Grouping} - Shows grid rows in groups as specified by the {@link Ext.data.Store}
 *  - {@link Ext.grid.feature.RowBody} - Adds a body section for each grid row that can contain markup.
 *  - {@link Ext.grid.feature.Summary} - Adds a summary row at the bottom of the grid with aggregate totals for a column.
 * 
 * ## Using Features
 * A feature is added to the grid by specifying it an array of features in the configuration:
 * 
 *     var groupingFeature = Ext.create('Ext.grid.feature.Grouping');
 *     Ext.create('Ext.grid.Panel', {
 *         // other options
 *         features: [groupingFeature]
 *     });
 * 
 * @abstract
 */
Ext.define('Ext.grid.feature.Feature', {
    extend: 'Ext.util.Observable',
    alias: 'feature.feature',

    /*
     * @property {Boolean} isFeature
     * `true` in this class to identify an object as an instantiated Feature, or subclass thereof.
     */
    isFeature: true,

    /**
     * True when feature is disabled.
     */
    disabled: false,

    /**
     * @property {Boolean}
     * Most features will expose additional events, some may not and will
     * need to change this to false.
     */
    hasFeatureEvent: true,

    /**
     * @property {String}
     * Prefix to use when firing events on the view.
     * For example a prefix of group would expose "groupclick", "groupcontextmenu", "groupdblclick".
     */
    eventPrefix: null,

    /**
     * @property {String}
     * Selector used to determine when to fire the event with the eventPrefix.
     */
    eventSelector: null,

    /**
     * @property {Ext.view.Table}
     * Reference to the TableView.
     */
    view: null,

    /**
     * @property {Ext.grid.Panel}
     * Reference to the grid panel
     */
    grid: null,

    /**
     * Most features will not modify the data returned to the view.
     * This is limited to one feature that manipulates the data per grid view.
     */
    collectData: false,
    
    constructor: function(config) {
        this.initialConfig = config;
        this.callParent(arguments);
    },

    clone: function() {
        return new this.self(this.initialConfig);
    },

    init: Ext.emptyFn,

    getFeatureTpl: function() {
        return '';
    },

    /**
     * Abstract method to be overriden when a feature should add additional
     * arguments to its event signature. By default the event will fire:
     *
     * - view - The underlying Ext.view.Table
     * - featureTarget - The matched element by the defined {@link #eventSelector}
     *
     * The method must also return the eventName as the first index of the array
     * to be passed to fireEvent.
     * @template
     */
    getFireEventArgs: function(eventName, view, featureTarget, e) {
        return [eventName, view, featureTarget, e];
    },

    /**
     * Approriate place to attach events to the view, selectionmodel, headerCt, etc
     * @template
     */
    attachEvents: function() {

    },

    getFragmentTpl: Ext.emptyFn,

    /**
     * Allows a feature to mutate the metaRowTpl.
     * The array received as a single argument can be manipulated to add things
     * on the end/begining of a particular row.
     * @param {Array} metaRowTplArray A String array to be used constructing an {@link Ext.XTemplate XTemplate}
     * to render the rows. This Array may be changed to provide extra DOM structure.
     * @template
     */
    mutateMetaRowTpl: Ext.emptyFn,

    /**
     * Allows a feature to inject member methods into the metaRowTpl. This is
     * important for embedding functionality which will become part of the proper
     * row tpl.
     * @template
     */
    getMetaRowTplFragments: function() {
        return {};
    },

    getTableFragments: function() {
        return {};
    },

    /**
     * Provide additional data to the prepareData call within the grid view.
     * @param {Object} data The data for this particular record.
     * @param {Number} idx The row index for this record.
     * @param {Ext.data.Model} record The record instance
     * @param {Object} orig The original result from the prepareData call to massage.
     * @template
     */
    getAdditionalData: function(data, idx, record, orig) {
        return {};
    },

    /**
     * Enables the feature.
     */
    enable: function() {
        this.disabled = false;
    },

    /**
     * Disables the feature.
     */
    disable: function() {
        this.disabled = true;
    }

});