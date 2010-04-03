panelSyncSizeFlagIds = "checkboxgrouppanel";

HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取提交按钮
	var submitBtn 	= Ext.getCmp("submitBtn");
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	
	
	
	// -------------------------------------- 应用逻辑处理
	
	// 当提交按钮被单击时
	submitBtn.on("click", function() {
		// 提交数据
		ExtConvertHelper.submitForm("form", this.url, null, function(form, action) {
			// 获取成功后的提示信息
			var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
			
			// 弹出提示框给用户
			Ext.Msg.alert("提示", msg, function() {
				// 用户单击后关闭此页面
				HBSConvertHelper.defaultCloseTab();
			});
		});
	});
	
	// 当单机取消按钮时，调用默认的关闭窗口方法
	backBtn.on("click", HBSConvertHelper.defaultCloseTab);
	
	
	// -------------------------------------- 页面操作逻辑处理
	
	
	
	// 新增页面的处理逻辑
	function addInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("创建角色");
		// 新增时提交数据的Url
		submitBtn.url = "/auth/role!save.action";
	}
	
	// 修改页面的处理逻辑
	function updateInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("修改角色");
		// 修改时提交数据的Url
		submitBtn.url = "/auth/role!save.action";
		// 组装需要的参数
		var params = ["role.roleId=", urlPs.roleId].join("");
		// 加载数据
		ExtConvertHelper.loadForm("form", "/auth/role!getInfo.action", params, function(form, action) {
			Ext.each(action.result.data.role.resources, function(item) {
				ExtConvertHelper.setCheckValues("r_" + item.resourceId, item.operations, "a_")
			});
		});
	}
	
	// 根据不同的操作类型，做出不同的处理
	eval(urlPs.editorType + "InitFun")();
});