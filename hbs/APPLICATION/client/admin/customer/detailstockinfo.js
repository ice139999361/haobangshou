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
	var params = {"orderDetail.operSeqId": urlPs.operSeqId};
	
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
			//var _parms = Ext.apply({}, params);
			//_parms["self.lockAmount"] = Ext.getCmp("tsuserAmount").getValue();
			//_parms["common.lockAmount"] = Ext.getCmp("tcuserAmount").getValue();
			
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
						// 要访问的 url 地址
						var url = ["/success.action?poNo=", record.get("poNo")
							,"&commCode="   , record.get("commCode")
							,"&data="       , value
						].join("");
						
						ExtConvertHelper.request(url, null, function(response, opts) {
							// HBSConvertHelper.refreshGrid("querygrid");
							var jsonData = Ext.util.JSON.decode(response.responseText);
							if(jsonData.success === false) return;			
							Ext.getCmp("storeinfogrid").store.removeAll();
							Ext.getCmp("storeinfogrid").addData(action.result.data.custOrder.orderDetailList);
						});
					}, this);
				});
			}
		});
				
		ExtConvertHelper.request("/custOrderDetail/orderDetailCg!list.action", params, function(response, options) {
			var action = Ext.util.JSON.decode(response.responseText);
			Ext.getCmp("orderdetailgrid").addData(action.data.list);
    	});

		// 加载数据
		ExtConvertHelper.loadForm("form", "/custOrderDetail/orderDetailCg!getStockInfo.action", params, function(form, action) {
			Ext.getCmp("storeinfogrid").addData(action.result.data.otherList);
		});
		
	}())
});