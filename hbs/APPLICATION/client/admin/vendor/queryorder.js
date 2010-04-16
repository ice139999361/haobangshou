var querygridUrl;

// 初始方法
(function() {
	switch(urlPs.roleType) {
		// 采购部采购员
		case "cgy":
			querygridUrl = "/vendorOrder/vendorOrder!list.action";
			break;
		// 财务
		case "finance":
			querygridUrl = "/vendorOrder/vendorOrderCw!list.action";
			break;
		case "financemanager":
			querygridUrl = "/vendorOrder/vendorOrderCwMgr!list.action";
			break;
	}
}())

HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取表格
	var querygrid = Ext.getCmp("querygrid");
	
	
	
	// -------------------------------------- 应用逻辑处理
	
	querygrid.getView().on("refresh", function(view) {
		
		// 处理按钮触发事件
		var processBtnFun = function() {
			// 获取数据容器
			var record = this.config;
			
			// 要访问的 url 地址
			var url = ["/vendor/detailorder.jsp?pageType=process&poNo=", record.get("poNo")
								,"&commCode="   , record.get("commCode")
								,"&state="      , record.get("state")
								,"&activeState=", record.get("activeState")
								,"&roleType="   , urlPs.roleType
								].join("");
			// 打开指定页面
			HBSConvertHelper.openNewWin(url);
		}
		
		// 修改按钮触发事件
		var updateBtnFun = function() {
			// 要访问的 url 地址
			var url = ["/vendor/editororder.jsp?editorType=update&commCode=", this.config.get("commCode")
						, "&poNo=", this.config.get("poNo")
						, "&poNoType=", this.config.get("poNoType")
						, "&state=", this.config.get("state")].join("");
			// 打开指定页面
			HBSConvertHelper.openNewWin(url);
		};
		
		// 取消按钮触发事件
		var cancelBtnFun = function() {
			Ext.Msg.confirm("提示", "您要执行的是取消操作，请确认是否继续？", function(btn) {
				if(btn == "no") return;
				
				ExtConvertHelper.request("/vendorOrder/vendorOrder!cancel.action?vendorOrder.commCode="+ this.config.get("commCode")
						+ "&vendorOrder.poNo="+ this.config.get("poNo"), null, ExtConvertHelper.defaultDeleteFun);
			}, this);
		}
		
		// 删除按钮触发事件
		var deleteBtnFun = function() {
			Ext.Msg.confirm("提示", "您要执行的是删除操作，请确认是否继续？", function(btn) {
				if(btn == "no") return;
				
				ExtConvertHelper.request("/vendorOrder/vendorOrder!cancel.action?vendorOrder.commCode="+ this.config.get("commCode")
						+ "&vendorOrder.poNo="+ this.config.get("poNo"), null, function() {
					HBSConvertHelper.refreshGrid("querygrid");
				});
			}, this);
		}
		
		// 查看操作历史按钮触发事件
		var historyBtnFun = function() {
			// 获取 record
			var record = this.config;
			// 打开查看历史记录页面
			HBSConvertHelper.open("/complex/detailhistory.jsp", 800, 500, {gridurl: ["/vendorInfo/vendorPartNoInfoMgr!list.action?custOrder.poNo=", record.get("poNo"), "&custOrder.commCode=", record.get("commCode")].join("")})
		}
		
		
		// 获取 roleType 
		var roleType = urlPs.roleType;

		// 采购部采购员的处理方法
		var cgyViewFun = function(record, operator_cell) {
			// 如果是暂停状态
			if(record.get("activeState") == "PAUSE") {
				// 创建操作按钮
				var operatorBtn = HBSConvertHelper.renderButton2Cell(["处理"], operator_cell, record);
				// 添加继续按钮事件
				operatorBtn.on("click", processBtnFun);
			} else {
				switch(record.get("state")) {
					// 临时保存数据
					case "01":
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["删除", "修改"], operator_cell, record);
						// 添加删除按钮事件
						operatorBtn.get(0).on("click", deleteBtnFun);
						// 添加修改按钮事件
						operatorBtn.get(1).on("click", updateBtnFun);
						break;
					// 待交期确认
					case "04":
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["处理"], operator_cell, record);
						// 添加删除按钮事件
						operatorBtn.on("click", processBtnFun);
						break;
				}
			}
			
		};
		
		// 财务的处理方法
		var financeViewFun = function(record, operator_cell) {
			// 如果是暂停状态
			if(record.get("activeState") == "PAUSE") return
			
			switch(record.get("state")) {
				// 待财务确认预付
				case "30":
					// 创建操作按钮
					var operatorBtn = HBSConvertHelper.renderButton2Cell(["处理"], operator_cell, record);
					// 添加继续按钮事件
					operatorBtn.on("click", processBtnFun);
					break;
			}
			
		};
		
		// 财务经理的处理方法
		var financemanagerViewFun = function(record, operator_cell) {
			// 如果是暂停状态
			if(record.get("activeState") == "PAUSE") return
			
			switch(record.get("state")) {
				// 款到发货而款未到，申请待经理审批（针对预付X%，剩余款到发货）
				case "33":
					// 创建操作按钮
					var operatorBtn = HBSConvertHelper.renderButton2Cell(["审批"], operator_cell, record);
					// 添加继续按钮事件
					operatorBtn.on("click", processBtnFun);
					break;
			}
			
		};
		
		
		//alert(this.ds.getCount())
		for(var i = 0 ; i < view.ds.getCount() ; i++) {
			// 获取数据容器
			var record = view.ds.getAt(i);
			// 获取订单号所在的列
			var poNo_cell = view.getCell(i, view.grid.getColumnIndexById("poNo"));
			// 获取操作列
			var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
			// 将需要的链接渲染到此列
			HBSConvertHelper.renderATag2Cell(poNo_cell.innerText, "/vendor/detailorder.jsp?pageType=query&poNo=" + record.get("poNo") + "&commCode=" + record.get("commCode"), "open", poNo_cell);
			
			switch(roleType) {
				case "cgy":
					cgyViewFun(record, operator_cell);
					break;
				case "finance":
					financeViewFun(record, operator_cell);
					break;
			}
		}
	})
});