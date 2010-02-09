HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取关联按钮
	var submitBtn 	= Ext.getCmp("submitBtn");
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	
	
	// -------------------------------------- 应用逻辑处理
	
	// 当提交按钮被单击时
	submitBtn.on("click", function() {
		//submitData("/customerInfo/customerInfo!save.action");
	});
	
	// 当单机取消按钮时，调用默认的关闭窗口方法
	backBtn.on("click", HBSConvertHelper.defaultCloseTab);
});