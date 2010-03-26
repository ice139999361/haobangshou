var querygridUrl = window.pageconfig.gridurl;

HBSConvertHelper.init(function() {
	// 获取取消按钮
	var backBtn = Ext.getCmp("backBtn");
	
	// 单击取消按钮时的事情处理
	backBtn.on("click", function() { window.close() });
});
