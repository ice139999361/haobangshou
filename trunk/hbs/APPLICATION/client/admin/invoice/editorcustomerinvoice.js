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
			// 提交数据
			ExtConvertHelper.submitForm(ExtConvertHelper.getHiddenForm().id, "/success.action", null, function(form, action) {
				// 获取成功后的提示信息
				var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
				
				// 弹出提示框给用户
				Ext.Msg.alert("提示", msg, function() {
					HBSConvertHelper.defaultCloseTab();
				});
			});
		});
		
		// 取消按钮的单击事件
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);

		querygrid.getView().on("refresh", function(view) {
			// 查看已有发票记录按钮事件
			var queryinvoiceFun = function() {
				showModalDialog(CONTEXT_PATH + "/invoice/querybeingcustomerinvoice.jsp", null, "dialogWidth:900px");
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