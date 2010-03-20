Ext.namespace('ExtUx.widget');

ExtUx.widget.ListPanel = function(config) {
	// 处理 config 信息
	ColumnPanelHelper.processConfig(config);
	
	ExtUx.widget.ListPanel.superclass.constructor.call(this, config);
};

Ext.extend(ExtUx.widget.ListPanel, Ext.Panel, {
	initComponent : function(){
		ExtUx.widget.ListPanel.superclass.initComponent.call(this);
	},
	style         : "margin:5px 0px 0px 5px",
	autoHeight		: true
});
Ext.reg('listpanel', ExtUx.widget.ListPanel);
