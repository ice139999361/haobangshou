var submitUrl = "/customerInfo/customerInfoMgr!updateSales.action";

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
	
	Ext.getCmp("commCode").setProcessConfig("/customerInfo/customerInfoMgr!getInfo.action?custInfo.state=0", "custInfo.commCode", null, function(action){
		if(!action.success)
			return;
		Ext.getCmp("shortName").setValue(action.data.custInfo.shortName);
		Ext.getCmp("representative").setValue(action.data.custInfo.representative);
		//Ext.getCmp("commCode").setValue(action.data.custInfo.commCode);
		Ext.getCmp("enShortName").setValue(action.data.custInfo.enShortName);
		Ext.getCmp("creditDesc").setValue(action.data.custInfo.creditDesc);
		Ext.getCmp("importantDesc").setValue(action.data.custInfo.importantDesc);
		Ext.getCmp("commType").setValue(action.data.custInfo.commType);
		Ext.getCmp("commScale").setValue(action.data.custInfo.commScale);
		Ext.getCmp("allName").setValue(action.data.custInfo.allName);
		Ext.getCmp("address").setValue(action.data.custInfo.address);
		Ext.getCmp("enName").setValue(action.data.custInfo.enName);
		Ext.getCmp("enAddress").setValue(action.data.custInfo.enAddress);
		Ext.getCmp("taxCode").setValue(action.data.custInfo.taxCode);
		Ext.getCmp("webSite").setValue(action.data.custInfo.webSite);
		Ext.getCmp("companyBranchDesc").setValue(action.data.custInfo.companyBranchDesc);
		Ext.getCmp("isShowPriceDesc").setValue(action.data.custInfo.isShowPriceDesc);
		Ext.getCmp("commDesc").setValue(action.data.custInfo.commDesc);
		Ext.getCmp("staffName").setValue(action.data.custInfo.staffName);
		Ext.getCmp("assStaffName").setValue(action.data.custInfo.assStaffName);
		Ext.getCmp("baseSeqId").setValue(action.data.custInfo.baseSeqId);		
	});

	Ext.getCmp("shortName").setProcessConfig("/customerInfo/customerInfoMgr!getInfo.action?custInfo.state=0", "custInfo.shortName", null, function(action){
		if(!action.success)
			return;
		//Ext.getCmp("shortName").setValue(action.data.custInfo.shortName);
		Ext.getCmp("representative").setValue(action.data.custInfo.representative);
		Ext.getCmp("commCode").setValue(action.data.custInfo.commCode);
		Ext.getCmp("enShortName").setValue(action.data.custInfo.enShortName);
		Ext.getCmp("creditDesc").setValue(action.data.custInfo.creditDesc);
		Ext.getCmp("importantDesc").setValue(action.data.custInfo.importantDesc);
		Ext.getCmp("commType").setValue(action.data.custInfo.commType);
		Ext.getCmp("commScale").setValue(action.data.custInfo.commScale);
		Ext.getCmp("allName").setValue(action.data.custInfo.allName);
		Ext.getCmp("address").setValue(action.data.custInfo.address);
		Ext.getCmp("enName").setValue(action.data.custInfo.enName);
		Ext.getCmp("enAddress").setValue(action.data.custInfo.enAddress);
		Ext.getCmp("taxCode").setValue(action.data.custInfo.taxCode);
		Ext.getCmp("webSite").setValue(action.data.custInfo.webSite);
		Ext.getCmp("companyBranchDesc").setValue(action.data.custInfo.companyBranchDesc);
		Ext.getCmp("isShowPriceDesc").setValue(action.data.custInfo.isShowPriceDesc);
		Ext.getCmp("commDesc").setValue(action.data.custInfo.commDesc);
		Ext.getCmp("staffName").setValue(action.data.custInfo.staffName);
		Ext.getCmp("assStaffName").setValue(action.data.custInfo.assStaffName);
		Ext.getCmp("baseSeqId").setValue(action.data.custInfo.baseSeqId);	
	});
	
});