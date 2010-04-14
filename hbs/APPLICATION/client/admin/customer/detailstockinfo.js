HBSConvertHelper.init(function() {
	// 组织参数
	var params = {
		 commCode : urlPs.commCode
		,poNoType : urlPs.poNoType
		,state    : urlPs.state
		,poNo     : urlPs.poNo
	};
	
	(function() {
		// 取消按钮
		Ext.getCmp("backBtn").on("click", HBSConvertHelper.defaultCloseTab);
		
		// 锁定方法
		var stockFun = function() {
			// 验证 form 内容是符满足要求
			if(!ExtConvertHelper.isFormValid("form")) return;
			
			var cmp = Ext.getCmp(this.cmpt);
			var _parms = Ext.apply({}, params);
			_parms[cmp.name] = cmp.getValue();
			
			// 提交数据
			ExtConvertHelper.submitForm(null, this.url, _parms, function(form, action) {
				// 获取成功后的提示信息
				var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
				
				// 弹出提示框给用户
				Ext.Msg.alert("提示", msg);
			});
		}
		
		// 本客户库存
		Ext.getCmp("sstockBtn").on("click", stockFun);
		// 通用库存
		Ext.getCmp("cstockBtn").on("click", stockFun);
		// 详细信息
		Ext.getCmp("storeinfogrid").getView().on("refresh", function(view) {
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				// 获取数据容器
				var record = view.ds.getAt(i);
				// 获取操作列
				var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
				// 创建操作按钮
				var operatorBtn = HBSConvertHelper.renderButton2Cell(["调货"], operator_cell, record);
				// 添加审批按钮事件
				operatorBtn.on("click", function() { alert("调货") });
			}
		});
		
		/*
		// 加载数据
		ExtConvertHelper.loadForm("form", "/custOrder/custOrder!getInfo.action", params, function(form, action) {
			Ext.getCmp("customerstoregrid").addData(action.result.data.custOrder.orderDetailList);
		});
		*/
		
		// 加载数据
		ExtConvertHelper.loadForm("form", "/custOrderDetail/orderDetailCg!getStockInfo.action", params, function(form, action) {
			//Ext.getCmp("customerstoregrid").addData(action.result.data.custOrder.orderDetailList);
			Ext.getCmp("storeinfogrid").addData(action.result.data.custOrder.orderDetailList);
		});
		
	}())
});