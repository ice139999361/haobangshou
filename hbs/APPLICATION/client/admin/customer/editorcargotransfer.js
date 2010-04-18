
HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取提交按钮
	var submitBtn 	= Ext.getCmp("submitBtn");
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	
	
	// -------------------------------------- 应用逻辑处理
	
	(function() {
		// 当提交按钮被单击时
		submitBtn.on("click", function() {
			// 验证 form 内容是符满足要求
			if(!ExtConvertHelper.isFormValid("form")) return;
			
			// 提交数据
			ExtConvertHelper.submitForm("form", "/adjustInfo/adjustInfo!save.action", null, function(form, action) {
				// 获取成功后的提示信息
				var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
				
				// 弹出提示框给用户
				Ext.Msg.alert("提示", msg, function() {
					// 用户单击后重载此页面
					location.reload();
				});
			});
		});
		
		
		// 当单机取消按钮时，调用默认的关闭窗口方法
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);
	}())
	
	Ext.getCmp("acPartNo").setProcessConfig("/partNo/partNo!get.action", "partNo.partNo", null, function(action){
		if(!action.success)
			return;
		Ext.getCmp("acPnDesc").setValue(action.data.partNo.pnDesc);
	});
	Ext.getCmp("acFromCCode").setProcessConfig("/customerInfo/customerInfoMgr!getInfo.action?custInfo.state=0", "custInfo.commCode", null, function(action){
		if(!action.success)
			return;
		Ext.getCmp("acFromCName").setValue(action.data.custInfo.shortName);
	});
	Ext.getCmp("acToCCode").setProcessConfig("/customerInfo/customerInfoMgr!getInfo.action?custInfo.state=0", "custInfo.commCode", null, function(action){
		if(!action.success)
			return;
		Ext.getCmp("acToCName").setValue(action.data.custInfo.shortName);
	});
	Ext.getCmp("acVendorCode").setProcessConfig("/vendorInfo/vendorInfoMgr!getInfo.action?vendorInfo.state=0", "vendorInfo.commCode", null, function(action){
		if(!action.success)
			return;
		Ext.getCmp("acShortName").setValue(action.data.vendorInfo.shortName);
	});
});