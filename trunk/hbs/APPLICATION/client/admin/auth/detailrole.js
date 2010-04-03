panelSyncSizeFlagIds = "checkboxgrouppanel";

HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	
	// -------------------------------------- 应用逻辑处理
	
	// 初始化方法
	(function() {		
		// 当单击取消按钮时，调用默认的关闭窗口方法
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);
	
		// 组装需要的参数
		var params = ["role.roleId=", urlPs.roleId].join("");
		
		// 加载数据
		ExtConvertHelper.loadForm("form", "/auth/role!getInfo.action", params, function(form, action) {
			Ext.each(action.result.data.role.resources, function(item) {
				ExtConvertHelper.setCheckValues("r_" + item.resourceId, item.operations, "a_")
			});
		});
		
		Ext.each(Ext.getCmp("checkboxgrouppanel").findByType("checkboxgroup"), function(item) {
			item.disable();
		});
	}())
	
});