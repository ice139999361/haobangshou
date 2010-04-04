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
		if(!ExtConvertHelper.isFormValid("form")) return;
		
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
		submitSuccessPro = function() {
			var cmbClsCode = Ext.getCmp("cmbClsCode");
			var _passValue = cmbClsCode.getPassValue();
			Ext.getCmp("form").getForm().reset();
			cmbClsCode.setPassValue(_passValue);
		};
		submitData("/partNo/partNo!save.action");
	});
	
	// 当提交按钮被单击时
	submitBtn.on("click", function() {
		submitSuccessPro = HBSConvertHelper.defaultCloseTab;
		submitData("/partNo/partNo!save.action");
	});
	
	// 当单机取消按钮时，调用默认的关闭窗口方法
	backBtn.on("click", HBSConvertHelper.defaultCloseTab);
	
	
	// -------------------------------------- 页面操作逻辑处理
	
	// 新增页面的处理逻辑
	function addInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("本公司物料录入");
		
	}
	
	// 修改页面的处理逻辑
	function updateInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("本公司物料修改");
		// 隐藏新增按钮
		ExtConvertHelper.hideItems("addBtn");
		
		// 组装需要的参数
		var params = ["partNo.partNo=", urlPs.partNo].join("");
		// 加载数据
		ExtConvertHelper.loadForm("form", "/partNo/partNo!get.action", params);
		// 设置客户编码的编辑框为只读	；同时不到后台校验
		ExtConvertHelper.setItemsReadOnly("partNo", true);
	}
	
	// 根据不同的操作类型，做出不同的处理
	eval(urlPs.editorType + "InitFun")();
});
