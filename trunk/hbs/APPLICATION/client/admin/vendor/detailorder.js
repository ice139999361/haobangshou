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
	var params = ["custOrder.poNo=", urlPs.poNo, "&custOrder.commCode=", urlPs.commCode].join("");
	
	
	// -------------------------------------- 应用逻辑处理
	
	// 隐藏不需要的按钮
	ExtConvertHelper.hideItems("submitBtn,04process");
	
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
	
		// 加载数据
		ExtConvertHelper.loadForm("form", "/customerInfo/customerInfo!getInfo.action", params, function(form, action) {
				Ext.getCmp("custbankgrid").addData(action.result.data);
		});
	}())
	
	var queryInitFun = function() {
	};
	
	var processInitFun = function() {
		
		// 市场业务员的处理方法
		var cgyViewFun = function() {
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
						ExtConvertHelper.showItems("submitBtn,04process,operatorBtn1,operatorBtn2");
						operatorBtn1.setText("确认交期");
						operatorBtn1.url = "/success.action";
						operatorBtn1.on("click", submitFun);
						
						operatorBtn2.setText("取消订单");
						operatorBtn2.url = "/success.action";
						operatorBtn2.on("click", submitFun);
						break;
				}
			}
		};
		
		// 财务的处理方法
		var financeViewFun = function() {
			switch(urlPs.state) {
				// 待财务确认预付
				case "30":
					ExtConvertHelper.showItems("operatorBtn1,operatorBtn2");
					operatorBtn1.setText("预付款已付");
					operatorBtn1.url = "/success.action";
					operatorBtn1.on("click", submitFun);
					
					operatorBtn2.setText("取消订单");
					operatorBtn2.url = "/success.action";
					operatorBtn2.on("click", submitFun);
					break;
			}
			
		};
		
		eval(urlPs.roleType + "ViewFun")();
	};
	
	// 根据不同的操作类型，做出不同的处理
	if(urlPs.pageType) eval(urlPs.pageType + "InitFun")();
});