var params = {"orderDetail.operSeqId": urlPs.operSeqId};

HBSConvertHelper.init(function() {
	// 组织参数
	/*
	var params = {
		 commCode : urlPs.commCode
		,poNoType : urlPs.poNoType
		,state    : urlPs.state
		,poNo     : urlPs.poNo
	};
	*/
	
	(function() {
		// 取消按钮
		Ext.getCmp("backBtn").on("click", HBSConvertHelper.defaultCloseTab);
		
		// 锁定方法
		var stockFun = function() {
			// 验证 form 内容是符满足要求
			if(!ExtConvertHelper.isFormValid("form")) return;
			
			/*
			var cmp = Ext.getCmp(this.cmpt);
			var _parms = Ext.apply({}, params);
			_parms[cmp.name] = cmp.getValue();
			*/
			
			// 提交数据
			ExtConvertHelper.submitForm("form", this.url, params, function(form, action) {
				// 获取成功后的提示信息
				var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
				
				// 弹出提示框给用户
				Ext.Msg.alert("提示", msg);
			});
		}
		
		// 本客户库存
		Ext.getCmp("sstockBtn").on("click", stockFun);
		// 通用库存
		//Ext.getCmp("cstockBtn").on("click", stockFun);
		
		// 详细信息
		Ext.getCmp("storeinfogrid").getView().on("refresh", function(view) {
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				// 获取数据容器
				var record = view.ds.getAt(i);
				// 获取操作列
				var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
				// 创建操作按钮
				var operatorBtn = HBSConvertHelper.renderButton2Cell(["调货"], operator_cell, record);
				// 添加调货按钮事件
				operatorBtn.on("click", function() {
					Ext.Msg.prompt("提示", "请输入调货数量", function(btn, value) {
						if(btn == "no") return;
						
						// TODO: 校验value，确保是数值，且不大于record.get("useAmount")
						
						// 获取订单详情信息
						var orderDetailInfo = Ext.getCmp("orderdetailgrid").getView().ds.getAt(0);
						// 要访问的 url 参数
						var _parms = {
							"adjustInfo.partNo" : orderDetailInfo.get("partNo")
							,"adjustInfo.houseType" : record.get("houseType")
							,"adjustInfo.applyAmount" : value
							,"adjustInfo.vendorCode" : orderDetailInfo.get("vendorCode")
							,"adjustInfo.fromCustCode" : record.get("custCode")
							,"adjustInfo.toCustCode" : orderDetailInfo.get("commCode")
							//,"adjustInfo.poNo" : orderDetailInfo.get("poNo")
						};
						
						ExtConvertHelper.request("/adjustInfo/adjustInfo!save.action", _parms, function(response, opts) {
							// HBSConvertHelper.refreshGrid("querygrid");
							var jsonData = Ext.util.JSON.decode(response.responseText);
							// 获取成功后的提示信息
							var msg = ExtConvertHelper.getMessageInfo(jsonData, "操作成功！");
							// 弹出提示框给用户
							Ext.Msg.alert("提示", msg);
							
							if(jsonData.success === false) return;			
							// 加载数据
							location.reload();
						});
					}, this);
				});
			}
		});
				
		// 加载数据
		ExtConvertHelper.request("/custOrderDetail/orderDetailCg!list.action", params, function(response, options) {
			var action = Ext.util.JSON.decode(response.responseText);
			// TODO: 需备货数量 = 数量 - 已发送数量 - 本客户锁定数量 - 通用锁定数量
			Ext.getCmp("orderdetailgrid").addData(action.data.list);
    	});
		// 加载数据
		ExtConvertHelper.loadForm("form", "/custOrderDetail/orderDetailCg!getStockInfo.action", params, function(form, action) {
			Ext.getCmp("tsuserAmount").setValue(0);
			Ext.getCmp("tcuserAmount").setValue(0);
			Ext.getCmp("storeinfogrid").addData(action.result.data.otherList);
		});
		
	}())
});
