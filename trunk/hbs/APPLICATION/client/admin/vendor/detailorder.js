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
	var params = ["vendorOrder.poNo=", urlPs.poNo, "&vendorOrder.commCode=", urlPs.commCode].join("");
	// 详情列表中创建按钮的方法
	var detailcreatebuttonFun = Ext.emptyFn;


	// -------------------------------------- 应用逻辑处理

	// 隐藏不需要的按钮
	ExtConvertHelper.hideItems("submitBtn,04process");

	var getDetailSubParms = function(record) {
		return ["operSeqId=", record.get("operSeqId")
					, "&cpartNo=" , record.get("cpartNo")
					, "&partNo="  , record.get("partNo")].join("");
	}

	var reLoadFun = function(response, opts) {
		var action = Ext.util.JSON.decode(response.responseText);
		if(action.success == true || action.success == "true") {
			// 加载数据
			var getInfoUrl = urlPs.roleType == "cgy" ? "/vendorOrder/vendorOrder!getInfo.action" : "/vendorOrder/vendorOrderMgr!getInfo.action"
			ExtConvertHelper.loadForm(null, getInfoUrl, params, function(form, action) {
					Ext.getCmp("ordergrid").store.removeAll();
					Ext.getCmp("ordergrid").addData(action.result.data.vendorOrder.vendorOrderDetailList);
			});
		} else {
			var message = ExtConvertHelper.getMessageInfo(action, "请求失败：服务器异常");
			Ext.Msg.alert("提示", message);
		}
  };

  // 默认的处理方法，带提示
	var defualtProcessFun = function() {
		Ext.Msg.confirm("提示", "您要执行的是" + this.text + "操作，请确认是否继续？", function(btn) {
			if(btn == "no") return;

			ExtConvertHelper.request(this.url, getDetailSubParms(this.config), reLoadFun);
		}, this);
	};

	var promptProcessFun = function() {
		Ext.Msg.prompt('提示', this.message, function(btn, text){
	    if (btn == 'ok'){
	    	var _ps = getDetailSubParms(this.config) + "&" + this.paramName + "=" + text;
	    	ExtConvertHelper.request(this.url, _ps, reLoadFun);
	    }
		}, this);
	}

	var tearPromptProcessFun = function() {
		Ext.Msg.prompt('提示', this.message, function(btn, text){
	    if (btn == 'ok'){
	    	var _ps = getDetailSubParms(this.config) + "&" + this.paramName + "=" + text;
	    	ExtConvertHelper.request(this.url, _ps, reLoadFun);
	    }
		}, this, true);
	}

	var _querystoreFun = function() {
		// 要访问的 url 地址
		var url = "/customer/detailstockinfo.jsp?" + getDetailSubParms(this.config);
		// 打开指定页面
		HBSConvertHelper.openNewWin(url);
	}

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

		Ext.getCmp("printBtn").on("click", function() {
			open(CONTEXT_PATH + "/print/cgdd.jsp" + location.search, null, ["location=0,width=", screen.availWidth, ",height=", screen.availHeight].join(""))
		});

		Ext.getCmp("ordergrid").getView().on("refresh", function(view) {
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				// 获取数据容器
				var record = view.ds.getAt(i);
				// 操作列如果存在
				if(view.grid.getColumnIndexById("operator") != -1) {
					// 获取操作列
					var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
					detailcreatebuttonFun(record.get("state"), operator_cell, record);
				}
			}
		});

		var getInfoUrl = (urlPs.roleType == "cgy") ? "/vendorOrder/vendorOrder!getInfo.action" : "/vendorOrder/vendorOrderMgr!getInfo.action"
		// 加载数据
		ExtConvertHelper.loadForm("form", getInfoUrl, params, function(form, action) {
				//Ext.getCmp("ordergrid").addData(action.result.data);
				Ext.getCmp("ordergrid").addData(action.result.data.vendorOrder.vendorOrderDetailList);
				switch(action.result.data.vendorOrder.state) {
					// 正式状态
					case "2":
						ExtConvertHelper.showItems("printBtn");
						break;
				}
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

				detailcreatebuttonFun = function(state, operator_cell, record) {
					switch(state) {
						// 待业务确认交期(只对账期订单有效，本状态是采购修改交期后，显示的状态，等待业务确认交期，交期的概念针对订单明细，只要有一个订单明细需要确认交期，订单状态就为待交期确认)
						case "04":
							// 创建按钮到操作列
							var operatorBtn = HBSConvertHelper.renderButton2Cell(["确认交期", "取消订单"], operator_cell, record);
							// 确认交期按钮
							operatorBtn.get(0).on("click", promptProcessFun);
							operatorBtn.get(0).message = "请输入确认交期:";
							operatorBtn.get(0).paramName = "cgjq";
							operatorBtn.get(0).url = "/vendorOrderDetail/orderDetailCg!confirmDelivery.action";
							// 取消订单按钮
							operatorBtn.get(1).on("click", defualtProcessFun);
							operatorBtn.get(1).url = "/vendorOrderDetail/orderDetailCg!cancel.action";
							break;
					}
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