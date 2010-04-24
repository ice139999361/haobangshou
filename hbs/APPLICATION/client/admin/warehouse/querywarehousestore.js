var querygridUrl = "/warehouse/warehouse!list.action";
HBSConvertHelper.init(function() {
// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	(function() {
		
		// 当单机取消按钮时，调用默认的关闭窗口方法
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);
	});
});