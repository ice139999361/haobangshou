ExtConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取提交按钮
	var submitBtn 	= Ext.getCmp("submitBtn");
	// 获取保存按钮
	var saveBtn 		= Ext.getCmp("saveBtn");
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	// 获取客户联系人信息表格
	var contactgrid = Ext.getCmp("contactgrid");
	// 获取客户收货人信息表格
	var consigneegrid = Ext.getCmp("consigneegrid");
	// 获取客户银行信息表格
	var custbankgrid = Ext.getCmp("custbankgrid");
	
	
	
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
		
		alert(girdData)
		
		ExtConvertHelper.submitForm("form", url);
	}
	
	// 当提交按钮被单击时
	submitBtn.on("click", function() {
		submitData("aaa.action");
	});
	
	// 当保存按钮被单击时
	saveBtn.on("click", function() {
		submitData("aaa.action");
	});
	
	// 当单机取消按钮时，调用默认的关闭窗口方法
	backBtn.on("click", ExtConvertHelper.defaultCloseTab);
});