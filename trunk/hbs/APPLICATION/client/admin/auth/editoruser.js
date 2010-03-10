HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取提交按钮
	var submitBtn 	= Ext.getCmp("submitBtn");
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	
	
	
	// -------------------------------------- 应用逻辑处理
	
	submitBtn.on("click", function(){
		alert("操作失败。")
	})
	
	
	// -------------------------------------- 页面操作逻辑处理
	
	
	
	// 新增页面的处理逻辑
	function addInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("创建用户");
	}
	
	// 修改页面的处理逻辑
	function updateInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("修改用户");
	}
	
	// 根据不同的操作类型，做出不同的处理
	eval(urlPs.editorType + "InitFun")();
});