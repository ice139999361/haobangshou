HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取提交按钮
	var submitBtn 	= Ext.getCmp("submitBtn");
	// 获取保存按钮
	var saveBtn 		= Ext.getCmp("saveBtn");
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	
	
	
	// -------------------------------------- 应用逻辑处理
	
	/**
	 * 提交数据
	 * @param url  (String) 提交的url
	 */
	function submitData(url) {
		// 验证 form 内容是符满足要求
		//if(!ExtConvertHelper.isFormValid("form")) return;
		
		// 获取（客户联系人信息、客户收货人信息、客户银行信息）表格中的提交数据
		var girdData = HBSConvertHelper.getGridSubmitData("contactgrid,consigneegrid,custbankgrid", "contactlist,consigneelist,custbanklist");
		
		// 提交数据
		ExtConvertHelper.submitForm("form", url, girdData, function(form, action) {
			// 获取成功后的提示信息
			var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
			
			// 弹出提示框给用户
			Ext.Msg.alert("提示", msg, function() {
				// 用户单击后重载此页面
				location.reload();
			});
		});
	}
	
	// 当提交按钮被单击时
	submitBtn.on("click", function() {
		submitData("/customerInfo/customerInfo!save.action");
	});
	
	// 当保存按钮被单击时
	saveBtn.on("click", function() {
		submitData("/customerInfo/customerInfo!saveTemp.action");
	});
	
	// 当单机取消按钮时，调用默认的关闭窗口方法
	backBtn.on("click", ExtConvertHelper.defaultCloseTab);
});