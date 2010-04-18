HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取表格
	var settlementgrid = Ext.getCmp("settlementgrid");
	// 获取返回按钮
	var backBtn 		   = Ext.getCmp("backBtn");
	// 获取提交按钮
	var submitBtn      = Ext.getCmp("submitBtn");
	

	// -------------------------------------- 页面初始化
		
	(function() {
		Ext.getCmp("commcode").setProcessConfig("/customerInfo/customerInfoCw!getInfo.action", "custInfo.commCode", null, function(action) {
			if(!action.success) return;
				
			Ext.getCmp("allname").setValue(action.data.custInfo.allName);
			Ext.getCmp("offperiod").setValue(action.data.custInfo.settlementDesc);
			Ext.getCmp("cinfo").setValue(action.data.custInfo.specMemo);
			
			// 财务人员信息列表填充
			Ext.getCmp("financegrid").addData(action.data.custInfo.dynamicFields.contactlist);
			Ext.getCmp("wsdcustCode").setValue(Ext.getCmp("commcode").getValue());
			Ext.getCmp("query_btn").fireEvent("click");
		});
		
		// 当单击提交按钮时，调用默认的关闭窗口方法
		submitBtn.on("click", function() {
			// 提交的参数
			var params = {
				"baseSeqId" : settlementgrid.getCheckFields()
			};
			
			ExtConvertHelper.submitForm("form", this.url, params, function(form, action) {
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
	}())
	
	// -------------------------------------- 应用逻辑处理
	
	settlementgrid.getView().on("refresh", function(view) {
		
		//alert(this.ds.getCount())
		for(var i = 0 ; i < view.ds.getCount() ; i++) {
	
		}
		
	})
});