var submitUrl = "/vendorInfo/vendorInfoMgr!updateSales.action";

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
			Ext.Msg.alert("提示", msg);
			// 弹出提示框给用户
			//Ext.Msg.alert("提示", msg, function() {
				// 用户单击后关闭此页面
			//	HBSConvertHelper.defaultCloseTab();
			//});
		});
	});
	
	// 当单击取消按钮时，调用默认的关闭窗口方法
	backBtn.on("click", HBSConvertHelper.defaultCloseTab);
	
	Ext.getCmp("commCode").setProcessConfig("/vendorInfo/vendorInfoMgr!getInfo.action?vendorInfo.state=0", "vendorInfo.commCode", null, function(action){
		if(!action.success)
			return;
		Ext.getCmp("shortName").setValue(action.data.vendorInfo.shortName);
		Ext.getCmp("representative").setValue(action.data.vendorInfo.representative);
		//Ext.getCmp("commCode").setValue(action.data.vendorInfo.commCode);
		Ext.getCmp("enShortName").setValue(action.data.vendorInfo.enShortName);
		Ext.getCmp("creditDesc").setValue(action.data.vendorInfo.creditDesc);
		Ext.getCmp("importantDesc").setValue(action.data.vendorInfo.importantDesc);
		Ext.getCmp("commType").setValue(action.data.vendorInfo.commType);
		Ext.getCmp("commScale").setValue(action.data.vendorInfo.commScale);
		Ext.getCmp("saleType").setValue(action.data.vendorInfo.saleType);
		Ext.getCmp("allName").setValue(action.data.vendorInfo.allName);
		Ext.getCmp("address").setValue(action.data.vendorInfo.address);
		Ext.getCmp("enName").setValue(action.data.vendorInfo.enName);
		Ext.getCmp("enAddress").setValue(action.data.vendorInfo.enAddress);
		Ext.getCmp("taxCode").setValue(action.data.vendorInfo.taxCode);
		Ext.getCmp("webSite").setValue(action.data.vendorInfo.webSite);
		Ext.getCmp("companyBranchDesc").setValue(action.data.vendorInfo.companyBranchDesc);
		Ext.getCmp("commDesc").setValue(action.data.vendorInfo.commDesc);
		Ext.getCmp("staffName").setValue(action.data.vendorInfo.staffName);		
		Ext.getCmp("baseSeqId").setValue(action.data.vendorInfo.baseSeqId);		
	});

	Ext.getCmp("shortName").setProcessConfig("/vendorInfo/vendorInfoMgr!getInfo.action?vendorInfo.state=0", "vendorInfo.commCode", null, function(action){
		if(!action.success)
			return;
		//Ext.getCmp("shortName").setValue(action.data.vendorInfo.shortName);
		Ext.getCmp("representative").setValue(action.data.vendorInfo.representative);
		Ext.getCmp("commCode").setValue(action.data.vendorInfo.commCode);
		Ext.getCmp("enShortName").setValue(action.data.vendorInfo.enShortName);
		Ext.getCmp("creditDesc").setValue(action.data.vendorInfo.creditDesc);
		Ext.getCmp("importantDesc").setValue(action.data.vendorInfo.importantDesc);
		Ext.getCmp("commType").setValue(action.data.vendorInfo.commType);
		Ext.getCmp("commScale").setValue(action.data.vendorInfo.commScale);
		Ext.getCmp("saleType").setValue(action.data.vendorInfo.saleType);
		Ext.getCmp("allName").setValue(action.data.vendorInfo.allName);
		Ext.getCmp("address").setValue(action.data.vendorInfo.address);
		Ext.getCmp("enName").setValue(action.data.vendorInfo.enName);
		Ext.getCmp("enAddress").setValue(action.data.vendorInfo.enAddress);
		Ext.getCmp("taxCode").setValue(action.data.vendorInfo.taxCode);
		Ext.getCmp("webSite").setValue(action.data.vendorInfo.webSite);
		Ext.getCmp("companyBranchDesc").setValue(action.data.vendorInfo.companyBranchDesc);
		Ext.getCmp("commDesc").setValue(action.data.vendorInfo.commDesc);
		Ext.getCmp("staffName").setValue(action.data.vendorInfo.staffName);		
		Ext.getCmp("baseSeqId").setValue(action.data.vendorInfo.baseSeqId);	
	});
	

});