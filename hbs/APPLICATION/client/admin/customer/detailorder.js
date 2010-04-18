HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取返回按钮
	var backBtn 		 = Ext.getCmp("backBtn");
	// 获取提交按钮
	var submitBtn    = Ext.getCmp("submitBtn");
	// 获取操作1按钮
	var operatorBtn1 = Ext.getCmp("operatorBtn1");
	// 获取操作2按钮
	var operatorBtn2 = Ext.getCmp("operatorBtn2");
	// 获取操作3按钮
	var operatorBtn3 = Ext.getCmp("operatorBtn3");
	// 获取历史变更查看按钮
	var historyBtn   = Ext.getCmp("historyBtn");
	// 组装需要的参数
	var params = ["custOrder.poNo=", urlPs.poNo, "&custOrder.commCode=", urlPs.commCode, "&custOrder.poNoType=", urlPs.poNoType].join("");
	
	
	// -------------------------------------- 应用逻辑处理
	
	// 隐藏不需要的按钮
	ExtConvertHelper.hideItems("submitBtn,04process,auditPanel");
	
	// 定义 submitFun
	var submitFun = function() {
		ExtConvertHelper.submitForm("form", this.url, params, function(form, action) {
			// 获取成功后的提示信息
			var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
			
			// 弹出提示框给用户
			Ext.Msg.alert("提示", msg, function() {
				// 用户单击后关闭此页面
				HBSConvertHelper.defaultCloseTab();
			});
		});
	}
	
	// 当单击提交按钮时，调用的方法
	submitBtn.on("click", submitFun);
	// 当单击继续按钮时，调用的方法
	Ext.getCmp("goonBtn").on("click", submitFun);
	
	// 当单击暂停按钮时，调用的方法
	Ext.getCmp("stopBtn").on("click", submitFun);
	
	// 初始化方法
	(function() {		
		// 当单击取消按钮时，调用默认的关闭窗口方法
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);
		
		// 当单击历史变更查看按钮时，调用默认的关闭窗口方法
		historyBtn.on("click", function() {
			HBSConvertHelper.open("/complex/detailhistory.jsp", 800, 500, {gridurl: ["/vendorInfo/vendorPartNoInfoMgr!list.action?", params].join("")})
		});
		
		Ext.getCmp("custbankgrid").getView().on("refresh", function(view) {
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				// 获取数据容器
				var record = view.ds.getAt(i);
				// 操作列如果存在
				if(view.grid.getColumnIndexById("operator") != -1) {
					// 获取操作列
					var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
					switch(urlPs.roleType){
					case "cgy":
						//alert("state= " + record.get("state"));
						
						if(record.get("state") == "20" 
							|| record.get("state") == "21"
							|| record.get("state") == "71"){
							// 创建按钮到操作列
							var operatorBtn = HBSConvertHelper.renderButton2Cell(["查看库存"], operator_cell, view.ds.getAt(i));
							// 添加处理按钮事件
							operatorBtn.on("click", function(){
								// 要访问的 url 地址
								var url = ["/customer/detailstockinfo.jsp?operSeqId=", this.config.get("operSeqId")
											, "&cpartNo=", this.config.get("cpartNo")
											, "&partNo=", this.config.get("partNo")].join("");
								// 打开指定页面
								HBSConvertHelper.openNewWin(url);
							});
						} 
						break;
					}
				}
			}
		});
	
		// 加载数据
		ExtConvertHelper.loadForm("form", "/custOrder/custOrder!getInfo.action", params, function(form, action) {
				Ext.getCmp("custbankgrid").addData(action.result.data.custOrder.orderDetailList);
		});
	}())
	
	var queryInitFun = function() {
	};
	
	var processInitFun = function() {
		
		// 市场业务员的处理方法
		var sccustomersViewFun = function() {
			// 如果是暂停状态
			if(urlPs.activeState == "PAUSE") {
				// 显示需要的控件
				ExtConvertHelper.showItems("goonBtn");
			} else {
				// 显示需要的控件
				ExtConvertHelper.showItems("stopBtn");
				
				switch(urlPs.state) {
					// 待业务确认交期(只对账期订单有效，本状态是采购修改交期后，显示的状态，等待业务确认交期，交期的概念针对订单明细，只要有一个订单明细需要确认交期，订单状态就为待交期确认)
					case "04":
						// 显示需要的控件
						ExtConvertHelper.showItems("submitBtn,04process");
						// 客户意见的选择事件，下边 01,02,03是假设的数据，需要和字典更对
						Ext.getCmp("04khyj").on("select", function() {
							switch(this.getValue()) {
								case "1":
									submitBtn.url = "/success.action";
									break;
								case "2":
									submitBtn.url = "/success.action";
									break;
								case "3":
									submitBtn.url = "/success.action";
									break;
							}
						});
						break;
					// 交期到，待业务确认发货（货未备齐）
					case "05":
						// 显示需要的控件
						ExtConvertHelper.showItems("submitBtn");
						// 设置提交按钮
						submitBtn.setText("客户要求发货");
						submitBtn.url = "/success.action";
						break;
				}
			}
		};
		
		// 市场经理的处理方法
		var scmanagerViewFun = function() {
			switch(urlPs.state) {
				// 待经理审批最大金额（账期交易，本账期订单超出了最大金额）
				case "50":
					// 显示需要的控件
					ExtConvertHelper.showItems("submitBtn,auditPanel");
					submitBtn.url = "/success.action";
					break;
			}			
		};
		
		// 采购部采购员的处理方法
		var cgyViewFun = function() {
			switch(urlPs.state) {
				// 待采购确认交期（对账期订单有效，采购修改交期，状态变为此状态）
				case "20":
				// 采购备货中
				case "21":
					// 显示需要的控件
					ExtConvertHelper.showItems("submitBtn");
					submitBtn.setText("下单");
					submitBtn.url = "/success.action";
					break;
			}			
		};
		
		// 财务的处理方法
		var financeViewFun = function() {
			switch(urlPs.state) {
				// 待财务确认预付
				case "30":
					ExtConvertHelper.showItems("operatorBtn2");
					operatorBtn2.setText("退回");
					operatorBtn2.url = "/success.action";
					operatorBtn2.on("click", submitFun);
				// 待财务确认发货（针对预付X%，款到发货）
				case "31":
				// 待财务确认收到剩余货款
				case "32":
					ExtConvertHelper.showItems("operatorBtn1");
					operatorBtn1.setText("确认");
					operatorBtn1.url = "/success.action";
					operatorBtn1.on("click", submitFun);
					break;
			}
			
		};
		
		// 财务经理的处理方法
		var financemanagerViewFun = function() {
			switch(urlPs.state) {
				// 款到发货而款未到，申请待经理审批（针对预付X%，剩余款到发货）
				case "33":
					// 显示需要的控件
					ExtConvertHelper.showItems("submitBtn,auditPanel");
					submitBtn.url = "/success.action";
					break;
			}			
		};
		
		eval(urlPs.roleType + "ViewFun")();
	};
	
	// 根据不同的操作类型，做出不同的处理
	if(urlPs.pageType) eval(urlPs.pageType + "InitFun")();
});