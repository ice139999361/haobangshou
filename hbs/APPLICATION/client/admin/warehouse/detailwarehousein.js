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
		var params = ["warehouseRec.recPoNo=", urlPs["warehouseRec.recPoNo"],"&warehouseRec.vendorCode=" , urlPs["warehouseRec.vendorCode"]].join("");
		
		// 加载数据
		ExtConvertHelper.loadForm("form", "/warehouseRec/warehouseRec!getInfo.action", params, function(form, action) {
			var warehouseRec = action.result.data.warehouseRec;			
			Ext.getCmp("acOperTime").setValue(FormatUtil.data2string(warehouseRec.operTime));
			Ext.getCmp("acPoNoDate").setValue(FormatUtil.data2string(warehouseRec.poNoDate));
			Ext.getCmp("acApplyDate").setValue(FormatUtil.data2string(warehouseRec.applyDate));
			Ext.getCmp("warehousegrid").addData(action.result.data.warehouseRec.detailList);
		});
	}())
});