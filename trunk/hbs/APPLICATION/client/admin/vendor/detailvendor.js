var submitUrl = "";

HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	// 获取提交按钮
	var submitBtn   = Ext.getCmp("submitBtn");
	
	// -------------------------------------- 应用逻辑处理
	
	// 当单击提交按钮时，调用默认的关闭窗口方法
	submitBtn.on("click", function() {
		ExtConvertHelper.submitForm("form", submitUrl, null, function(form, action) {
			// 获取成功后的提示信息
			var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
			
			// 弹出提示框给用户
			Ext.Msg.alert("提示", msg, function() {
				// 用户单击后关闭此页面
				HBSConvertHelper.defaultCloseTab();
			});
		});
	});
	
	// 当单击取消按钮时，调用默认的关闭窗口方法
	backBtn.on("click", HBSConvertHelper.defaultCloseTab);
	
	// 初始化方法
	(function() {		
		// 组装需要的参数
		var params = ["vendorInfo.baseSeqId=", urlPs.baseSeqId].join("");
		
		// 加载数据
		ExtConvertHelper.loadForm("form", "/vendorInfo/vendorInfo!getInfo.action", params, function(form, action) {
				Ext.getCmp("contactgrid").addData(action.result.data.vendorInfo.dynamicFields.contactlist);
				Ext.getCmp("consigneegrid").addData(action.result.data.vendorInfo.dynamicFields.consigneelist);
				Ext.getCmp("custbankgrid").addData(action.result.data.vendorInfo.listBankInfo);
		});
	}())
	
	var queryInitFun = function() {
		// 隐藏不需要的按钮
		ExtConvertHelper.hideItems("auditPanel,submitBtn");
	};
	
	var auditInitFun = function() {
		submitUrl = "/vendorInfo/vendorInfoMgr!audit.action";
	};
	
	// 根据不同的操作类型，做出不同的处理
	if(urlPs.pageType) eval(urlPs.pageType + "InitFun")();

});