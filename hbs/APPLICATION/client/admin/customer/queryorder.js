var querygridUrl;

// 初始方法
(function() {
	switch(urlPs.roleType) {
		case "sccustomers":
			querygridUrl = "/custOrder/custOrder!list.action";
			break;
		case "scmanager":
			querygridUrl = "/custOrder/custOrderScMgr!list.action";
			break; 
	}
}())

HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取表格
	var querygrid = Ext.getCmp("querygrid");
	//HBSConvertHelper.getATagString("ffff", "abc.action", "open");
	
	
	
	// -------------------------------------- 应用逻辑处理
	
	querygrid.getView().on("refresh", function(view) {
		// 处理按钮触发事件
		var processBtnFun = function() {
			// 提示语定义，操作 Url 定义
			var msg, url, record = this.config;
			// 提交参数组装
			var params = {
				 "custOrder.poNo"     : record.get("poNo")
				,"custOrder.commCode" : record.get("commCode")
			};
			
			// 处理操作执行
			switch(record.get("activeState")) {
				// 暂停状态，继续操作处理
				case "PAUSE":
					msg = "您要执行的是继续操作，请确认是否继续？";
					url = "/custOrder/custOrder!controlActiveState.action";
					break;
				// 继续状态，暂停操作处理
				case "ACTIVE":
					msg = "您要执行的是暂停操作，请确认是否继续？";
					url = "/custOrder/custOrder!controlActiveState.action";
					break;
			}
			
			Ext.Msg.confirm("提示", msg, function(btn) {
				if(btn == "no") return;
				
				ExtConvertHelper.request(url, params, function() {
					HBSConvertHelper.refreshGrid("querygrid");
				});
			}, this);
		};
		
		// 修改按钮触发事件
		var updateBtnFun = function() {
			// 要访问的 url 地址
			var url = ["/customer/editororder.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=", this.config.get("state")].join("");
			// 打开指定页面
			HBSConvertHelper.openNewWin(url);
		};
		
		// 删除按钮触发事件
		var cancelBtnFun = function() {
			Ext.Msg.confirm("提示", "您要执行的是取消操作，请确认是否继续？", function(btn) {
				if(btn == "no") return;
				
				ExtConvertHelper.request("/success.action?baseSeqId=" + this.config.get("baseSeqId"), null, function() {
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
		
		// 市场业务员的处理方法
		var sccustomersViewFun = function(record, operator_cell) {
			// 如果是暂停状态
			if(record.get("activeState") == "PAUSE") {
				// 创建操作按钮
				var operatorBtn = HBSConvertHelper.renderButton2Cell(["继续", "查看操作历史"], operator_cell, record);
				// 添加继续按钮事件
				operatorBtn.get(0).on("click", processBtnFun);
			  // 添加查看操作历史按钮事件
				operatorBtn.get(1).on("click", historyBtnFun);
				
				return;
			} else {
				switch(record.get("state")) {
					case "01":
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["修改", "查看操作历史"], operator_cell, record);
						// 添加修改按钮事件
						operatorBtn.get(0).on("click", updateBtnFun);
						// 添加查看操作历史按钮事件
						operatorBtn.get(1).on("click", historyBtnFun);
						break;
					case "04":
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["客户同意", "客户不同意", "客户取消", "查看操作历史"], operator_cell, record);
						// 添加客户同意按钮事件
						operatorBtn.get(0).on("click", function() {
							Ext.Msg.confirm("提示", "您要执行的是客户同意操作，请确认是否继续？", function(btn) {
								if(btn == "no") return;
								
								ExtConvertHelper.request("/success.action?baseSeqId=" + this.config.get("baseSeqId"), null, function() {
									HBSConvertHelper.refreshGrid("querygrid");
								});
							}, this);
						});
						// 添加客户不同意按钮事件
						operatorBtn.get(1).on("click", function() {
							Ext.Msg.prompt("提示", "请输入客户指定的交期", function(btn) {
								if(btn == "no") return;
								
								ExtConvertHelper.request("/success.action?baseSeqId=" + this.config.get("baseSeqId"), null, function() {
									HBSConvertHelper.refreshGrid("querygrid");
								});
							}, this);
						});
						// 添加客户取消按钮事件
						operatorBtn.get(2).on("click", function() {
							Ext.Msg.confirm("提示", "您要执行的是客户取消操作，请确认是否继续？", function(btn) {
								if(btn == "no") return;
								
								ExtConvertHelper.request("/success.action?baseSeqId=" + this.config.get("baseSeqId"), null, function() {
									HBSConvertHelper.refreshGrid("querygrid");
								});
							}, this);
						});
						// 添加查看操作历史按钮事件
						operatorBtn.get(3).on("click", historyBtnFun);
						break;
					case "05":
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["客户要求发货", "查看操作历史"], operator_cell, record);
						// 添加客户要求发货按钮事件
						operatorBtn.get(0).on("click", function() {
							Ext.Msg.confirm("提示", "您要执行的是客户要求发货操作，请确认是否继续？", function(btn) {
								if(btn == "no") return;
								
								ExtConvertHelper.request("/success.action?baseSeqId=" + this.config.get("baseSeqId"), null, function() {
									HBSConvertHelper.refreshGrid("querygrid");
								});
							}, this);
						})
						// 添加查看操作历史按钮事件
						operatorBtn.get(1).on("click", historyBtnFun);
						break;
					case "39":
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["取消", "查看操作历史"], operator_cell, record);
						// 添加删除按钮事件
						operatorBtn.get(0).on("click", cancelBtnFun);
						// 添加查看操作历史按钮事件
						operatorBtn.get(1).on("click", historyBtnFun);
						break;
					case "52":
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["修改", "取消", "查看操作历史"], operator_cell, record);
						// 添加修改按钮事件
						operatorBtn.get(0).on("click", updateBtnFun);
						// 添加删除按钮事件
						operatorBtn.get(1).on("click", cancelBtnFun);
						// 添加查看操作历史按钮事件
						operatorBtn.get(2).on("click", historyBtnFun);
						break;
				}
			}
		};
		
		//alert(this.ds.getCount())
		for(var i = 0 ; i < view.ds.getCount() ; i++) {
			// 获取订单号所在的列
			var poNo_cell = view.getCell(i, view.grid.getColumnIndexById("poNo"));
			// 获取操作列
			var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
			// 将需要的链接渲染到此列
			HBSConvertHelper.renderATag2Cell(poNo_cell.innerText, "abc.action", "open", poNo_cell);
			
			switch(roleType) {
				// 市场业务员的处理方法
				case "sccustomers":
					sccustomersViewFun(view.ds.getAt(i), operator_cell);
					break;
			}
			
			/*
			// 操作列显示逻辑
			switch(view.ds.getAt(i).get("state")) {
				case "1":
				case "0":
				  // 创建按钮到操作列
					var btns = HBSConvertHelper.renderButton2Cell(["修改", "处理", "查看操作历史"], operator_cell, view.ds.getAt(i));
					
					// 修改按钮事件
					btns.get(0).on("click", function() {
						// 要访问的 url 地址
						var url = ["/customer/editororder.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=", this.config.get("state")].join("");
						// 打开指定页面
						HBSConvertHelper.openNewWin(url);
					});
					
					// 处理按钮事件
					btns.get(1).on("click", function() { alert(2) });
					
					// 查看操作历史按钮事件
					btns.get(2).on("click", function() { alert(3) });
					
					break;
			}
			*/
		}
	})
});