HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取提交按钮
	var submitBtn = Ext.getCmp("submitBtn");
	// 获取取消按钮
	var backBtn   = Ext.getCmp("backBtn");
	// 获取表格
	var querygrid = Ext.getCmp("querygrid");
	
	// -------------------------------------- 应用逻辑处理
	
	(function() {
		// 提交按钮的单击事件
		submitBtn.on("click", function() {
			
		});
		
		// 取消按钮的单击事件
		backBtn.on("click", function() {
			
		});

		querygrid.getView().on("refresh", function(view) {
			// 查看已有发票记录按钮事件
			var queryinvoiceFun = function() {
				showModalDialog(CONTEXT_PATH + "/invoice/querybeingvendorinvoice.jsp", null, "dialogWidth:900px");
			};
			
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				// 获取操作列
				var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
				// 创建查看已有发票记录按钮
				var operatorBtn = HBSConvertHelper.renderButton2Cell(["查看已有发票记录"], operator_cell, view.ds.getAt(i));
				operatorBtn.on("click", queryinvoiceFun);
			}
		});
	}())
	
});