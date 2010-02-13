HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象

	var addBtn		= Ext.getCmp("addBtn");	
	// 获取提交按钮
	var submitBtn 	= Ext.getCmp("submitBtn");
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");

	// -------------------------------------- 应用逻辑处理
	
	// 提交操作成功后要做的事情
	var submitSuccessPro;
	
	/**
	 * 提交数据
	 * @param url  (String) 提交的url
	 */
	function submitData(url) {
		// 验证 form 内容是符满足要求
		//if(!ExtConvertHelper.isFormValid("form")) return;
		
		// 提交数据
		ExtConvertHelper.submitForm("form", url, null, function(form, action) {
			// 获取成功后的提示信息
			var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
			
			// 弹出提示框给用户
			Ext.Msg.alert("提示", msg, submitSuccessPro);
		});
	}
	
	// 当新增按钮被单击时
	addBtn.on("click", function(){
		submitData("/partNo/partNo!save.action");
		submitSuccessPro = function() {
			var cmbClsCode = Ext.getCmp("cmbClsCode");
			var _passValue = cmbClsCode.getPassValue();
			Ext.getCmp("form").getForm().reset();
			cmbClsCode.setPassValue(_passValue);
		};
	});
	
	// 当提交按钮被单击时
	submitBtn.on("click", function() {
		submitData("/partNo/partNo!save.action");
		submitSuccessPro = HBSConvertHelper.defaultCloseTab;
	});
	
	// 当单机取消按钮时，调用默认的关闭窗口方法
	backBtn.on("click", HBSConvertHelper.defaultCloseTab);
	
});
