HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	
	
	// -------------------------------------- 应用逻辑处理
	
	// 初始化方法
	(function() {		
		
		// 当单击取消按钮时，调用默认的关闭窗口方法
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);
		
		Ext.getCmp("printBtn").on("click", function() {
			open(CONTEXT_PATH + "/print/shd.jsp" + location.search, null, "location=0")
		});
		
		// 组装需要的参数
		var params = ["warehouseSend.sendPoNo=", urlPs["warehouseSend.sendPoNo"],"&warehouseSend.custCode=" , urlPs["warehouseSend.custCode"],"&warehouseSend.poNoType=" , urlPs["warehouseSend.poNoType"]].join("");
		
		// 加载数据
		ExtConvertHelper.loadForm("form", "/warehouseSend/warehouseSend!getInfo.action", params, function(form, action) {
			var warehouseSend = action.result.data.warehouseSend;
			Ext.getCmp("acCreateDate").setValue(FormatUtil.data2string(warehouseSend.createDate));
			Ext.getCmp("warehousegrid").addData(warehouseSend.detailList);
			
			switch(warehouseSend.state) {
				// 正式状态
				case "02":
					ExtConvertHelper.showItems("printBtn");
					break;
			}
		});
	}())
});