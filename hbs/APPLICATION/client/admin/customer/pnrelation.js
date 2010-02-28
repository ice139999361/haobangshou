HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取关联按钮
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
	
	Ext.getCmp("acCommCode").setProcessConfig("/success.action", "aaa", null, function(){});
	Ext.getCmp("acCustPartNo").setProcessConfig("/success.action", "aaa", null, function(){});
	Ext.getCmp("acPartNo").setProcessConfig("/success.action", "aaa", null, function(){});
	
	// -------------------------------------- 页面操作逻辑处理
	
	
	
	// 新增页面的处理逻辑
	function addInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("客户P/N对照录入");
		
		// 设置关联按钮的 url
		submitBtn.url = "/customerInfo/custPartNoInfo!save.action";
	}
	
	// 修改页面的处理逻辑
	function updateInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("客户P/N对照修改");
		
		// 设置关联按钮的 url
		submitBtn.url = "/customerInfo/custPartNoInfo!save.action";
		
		// 组装需要的参数
		var params = ["custInfo.seqId=", urlPs.seqId].join("");
		
		// 加载数据
		ExtConvertHelper.loadForm("form", "/customerInfo/customerInfo!getInfo.action", params, null);
	}
	
	// 根据不同的操作类型，做出不同的处理
	eval(urlPs.editorType + "InitFun")();
});