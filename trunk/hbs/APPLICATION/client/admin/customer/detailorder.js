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
	// 详情列表中创建按钮的方法
	var detailcreatebuttonFun;
	
	
	// -------------------------------------- 应用逻辑处理
	
	// 隐藏不需要的按钮
	ExtConvertHelper.hideItems("submitBtn,auditPanel");
	
	var getDetailSubParms = function(record) {
		return ["operSeqId=", record.get("operSeqId")
					, "&cpartNo=" , record.get("cpartNo")
					, "&partNo="  , record.get("partNo")].join("");
	}
	
	var reLoadFun = function(response, opts) {
		var action = Ext.util.JSON.decode(response.responseText);
		if(action.success == true || action.success == "true") {
			// 加载数据
			ExtConvertHelper.loadForm(null, "/custOrder/custOrder!getInfo.action", params, function(form, action) {
					Ext.getCmp("custbankgrid").store.removeAll();
					Ext.getCmp("custbankgrid").addData(action.result.data.custOrder.orderDetailList);
			});
		} else {
			var message = ExtConvertHelper.getMessageInfo(action, "请求失败：服务器异常");
			Ext.Msg.alert("提示", message);
		}
  };
	
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
					/*
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
				*/
					detailcreatebuttonFun(record.get("state"), operator_cell, record);
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
		// 默认的处理方法，带提示
		var defualtProcessFun = function() {
			Ext.Msg.confirm("提示", "您要执行的是" + this.text + "操作，请确认是否继续？", function(btn) {
				if(btn == "no") return;
				
				ExtConvertHelper.request(this.url, getDetailSubParms(this.config), reLoadFun);
			}, this);
		};
		
		
		
		// 市场业务员的处理方法
		var sccustomersViewFun = function() {
			
			// 如果是暂停状态
			if(urlPs.activeState == "PAUSE") {
				// 显示需要的控件
				ExtConvertHelper.showItems("goonBtn");
			} else {
				// 显示需要的控件
				ExtConvertHelper.showItems("stopBtn");
				
				// 创建详情列表操作按钮的方法
				detailcreatebuttonFun = function(state, operator_cell, record) {
					switch(state) {
						// 待业务确认交期(只对账期订单有效，本状态是采购修改交期后，显示的状态，等待业务确认交期，交期的概念针对订单明细，只要有一个订单明细需要确认交期，订单状态就为待交期确认)
						case "04":
							// 创建按钮到操作列
							var operatorBtn = HBSConvertHelper.renderButton2Cell(["客户同意", "客户不同意", "客户取消"], operator_cell, record);
							// 客户同意按钮
							operatorBtn.get(0).on("click", defualtProcessFun);
							operatorBtn.get(0).url = "";
							// 客户不同意按钮
							operatorBtn.get(1).on("click", function() {
								Ext.Msg.prompt('提示', '请输入客户指定交期:', function(btn, text){
							    if (btn == 'ok'){
							    	var _ps = getDetailSubParms(this.config) + "&khzdjq=" + text;
							    	ExtConvertHelper.request("/success.action", _ps, reLoadFun);
							    }
								}, this);
							});
							// 客户取消按钮
							operatorBtn.get(2).on("click", defualtProcessFun);
							operatorBtn.get(2).url = "";
							break;
						// 交期到，待业务确认发货（货未备齐）
						case "05":
							var operatorBtn = HBSConvertHelper.renderButton2Cell(["客户要求发货"], operator_cell, record);
							operatorBtn.on("click", defualtProcessFun);
							operatorBtn.url = "/success.action";
					}
					
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
					submitBtn.url = "/custOrder/custOrderScMgr!audit.action";
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
			switch(urlPs.state){
				case "30":
					operatorBtn1.url = "/custOrder/custOrderCw!financeAgree.action";
					operatorBtn2.url = "/custOrder/custOrderCw!financeDisAgree.action";
					break;
				case "31":
					break;
				case "32":
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
					submitBtn.url = "/custOrder/custOrderCwMgr!audit.action";
					break;
			}			
		};
		
		eval(urlPs.roleType + "ViewFun")();
	};
	
	// 根据不同的操作类型，做出不同的处理
	if(urlPs.pageType) eval(urlPs.pageType + "InitFun")();
});