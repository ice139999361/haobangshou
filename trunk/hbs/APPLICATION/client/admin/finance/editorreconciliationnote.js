HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取提交按钮
	var submitBtn 	= Ext.getCmp("submitBtn");
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	// 获取编码控件
	var ccommcode   = Ext.getCmp("ccommcode");
	// 获取信息控件
	var cinfo       = Ext.getCmp("cinfo");
	
	
	
	// -------------------------------------- 应用逻辑处理
	
	// 当提交按钮被单击时
	submitBtn.on("click", function() {
		// 验证 form 内容是符满足要求
		if(!ExtConvertHelper.isFormValid("form")) return;
		
		// 提交数据
		ExtConvertHelper.submitForm("form", this.url, null, function(form, action) {
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
	
	
	// -------------------------------------- 页面操作逻辑处理
	
	switch(urlPs.beau) {
		case "customer":
			HBSConvertHelper.setDocumentTitle("客户对帐注意事项");
			ccommcode.setFieldLabel("客户编码");
			ccommcode.queryParam = "custInfo.commCode" ;
			ccommcode.changeUrl("/customerInfo/customerInfo!list.action");
			ccommcode.setProcessConfig("/invoice/periodSpec!getInfo.action", "specMemo.commCode", null, function(action) {
				if(!action.success)
				return;
				
				cinfo.setValue(action.data.specMemo.memo);
			});
      
      // 设置提交的 URL
      submitBtn.url = "/invoice/periodSpec!save.action";
			break;
		case "vendor":
			HBSConvertHelper.setDocumentTitle("供应商对帐注意事项");
			ccommcode.setFieldLabel("供应商编码");
			ccommcode.queryParam = "vendorInfo.commCode" ;
			ccommcode.changeUrl("/vendorInfo/vendorInfo!listDict.action");
			ccommcode.setProcessConfig("/invoice/periodSpec!getInfo.action", "specMemo.commCode", null, function(action) {
				if(!action.success)
				return;
				
				cinfo.setValue(action.data.specMemo.memo);
			});
			
			// 设置提交的 URL
      submitBtn.url = "/invoice/periodSpec!save.action";
			break;
	}
	

});