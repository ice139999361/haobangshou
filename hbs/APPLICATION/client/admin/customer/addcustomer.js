ExtConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取提交按钮
	var submitBtn = Ext.getCmp("submitBtn");
	// 获取保存按钮
	var saveBtn 	= Ext.getCmp("saveBtn");
	// 获取返回按钮
	var backBtn 	= Ext.getCmp("backBtn");
	
	
	
	// -------------------------------------- 应用逻辑处理
	
	// 当提交按钮被单击时
	submitBtn.on("click", function() {
		// 验证 form 内容是符满足要求
		if(!ExtConvertHelper.isFormValid("form")) return;
		
		ExtConvertHelper.submitForm("form", "/aaa.action");
	});
	
	// 当保存按钮被单击时
	saveBtn.on("click", function() {
		ExtConvertHelper.submitForm("form", "/aaa.action");
	});
	
	// 当单机取消按钮时，调用默认的关闭窗口方法
	backBtn.on("click", ExtConvertHelper.defaultCloseTab);
});