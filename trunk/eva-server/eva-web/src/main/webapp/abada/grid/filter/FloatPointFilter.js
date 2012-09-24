
Ext.define('Abada.grid.filter.FloatPointFilter', {
    extend: 'Ext.ux.grid.filter.NumericFilter',
    alias: 'gridfilter.integer',
    uses: ['Ext.form.field.Number'],
    
    /**
     * @private
     * Template method that is to get and return serialized filter data for
     * transmission to the server.
     * @return {Object/Array} An object or collection of objects containing
     * key value pairs representing the current configuration of the filter.
     */
    getSerialArgs : function () {
        var key,
            args = [],
            values = this.menu.getValue();
        for (key in values) {
            args.push({
                type: 'floatPoint',
                comparison: key,
                value: values[key]
            });
        }
        return args;
    }
});

