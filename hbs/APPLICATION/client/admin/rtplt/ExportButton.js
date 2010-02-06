Ext.namespace('ExtUx.widget');

ExtUx.widget.ExportButton = function(config) {
	// 处理 config 信息
	ColumnPanelHelper.processConfig(config);
	
	ExtUx.widget.ExportButton.superclass.constructor.call(this, config);
};

Ext.extend(ExtUx.widget.ExportButton, Ext.Button, {
	initComponent : function(){
		ExtUx.widget.ExportButton.superclass.initComponent.call(this);
	},
	handler: function() {
		// 获取用来导出的 form 对象
		var _exportForm = Ext.getCmp((this.formId || urlPs.__exportFormId));
		// 导出数据
		ExtConvertHelper.exportFile(this.url, _exportForm);
	}
});
Ext.reg('exportbutton', ExtUx.widget.ExportButton);
