Ext.define('Abada.grid.column.CheckBox', {
  extend: 'Ext.grid.column.Template',
  alias: 'widget.checkboxcolumn',
  constructor: function(cfg) {
    var me = this;
    me.tpl = ['<tpl for=".">','placeholder','</tpl>']; // shouldn't
    // ever be used. Ignore it.
    var tplChecked = Ext.create('Ext.XTemplate', [
      '<tpl for=".">',
      '<div class="x-field x-form-item x-field-default x-form-cb-checked">',
      '<div class="x-form-item-body x-form-cb-wrap" role="presentation">',
      '<input class="x-form-field x-form-checkbox" type="button" hidefocus="true" autocomplete="off" aria-checked="false" aria-invalid="false" role="checkbox" data-errorqtip="">',
      '</div>',
      '</div>',
      '</tpl>'
    ]);
    var tplUnchecked = Ext.create('Ext.XTemplate', [
      '<tpl for=".">',
      '<div class="x-field x-form-item x-field-default">',
      '<div class="x-form-item-body x-form-cb-wrap" role="presentation">',
      '<input class="x-form-field x-form-checkbox" type="button" hidefocus="true" autocomplete="off" aria-checked="false" aria-invalid="false" role="checkbox" data-errorqtip="">',
      '</div>',
      '</div>',
      '</tpl>'
    ]);

    me.callParent(arguments);
    me.renderer = function(value, p, record) {
      var data = Ext.apply({}, record.data, record.getAssociatedData());
      if (data[me.dataIndex]) {
        return tplChecked.apply(data);
      } else {
        return tplUnchecked.apply(data);
      }
    };

  }/*,
    processEvent : function(type, view, cell, recordIndex, cellIndex, e){
      var me = this;
      if (type == 'click') {
        var rec = view.getStore().getAt(recordIndex);
        rec.set(this.dataIndex, (rec.get(this.dataIndex))?false:true);
      } else if (type == 'mousedown') {
        return false;
      }
      return me.callParent(arguments);
    }*/
});  