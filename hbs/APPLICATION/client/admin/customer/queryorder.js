var querygridUrl;
var custInfoUrl;
var custInfoUrl2;

// 初始方法
(function() {
	switch(urlPs.roleType) {
		// 市场业务员
		case "sccustomers":
			querygridUrl = "/custOrder/custOrder!list.action";
			custInfoUrl = "/customerInfo/customerInfo!list.action?custInfo.state=0";
			custInfoUrl2 = "/customerInfo/customerInfo!getInfo.action?custInfo.state=0";
			break;
		// 市场经理
		case "scmanager":
			querygridUrl = "/custOrder/custOrderScMgr!list.action";
			custInfoUrl = "/customerInfo/customerInfoMgr!list.action?custInfo.state=0";
			custInfoUrl2 = "/customerInfo/customerInfoMgr!getInfo.action?custInfo.state=0";
			break;
		// 采购部采购员
		case "cgy":
			querygridUrl = "/custOrder/custOrderScMgr!list.action";
			custInfoUrl = "/customerInfo/customerInfoMgr!list.action?custInfo.state=0";
			custInfoUrl2 = "/customerInfo/customerInfoMgr!getInfo.action?custInfo.state=0";
			break;
		// 财务
		case "finance":
			querygridUrl = "/custOrder/custOrderScMgr!list.action";
			custInfoUrl = "/customerInfo/customerInfoMgr!list.action?custInfo.state=0";
			custInfoUrl2 = "/customerInfo/customerInfoMgr!getInfo.action?custInfo.state=0";
			break;
		case "financemanager":
			querygridUrl = "/custOrder/custOrderScMgr!list.action";
			custInfoUrl = "/customerInfo/customerInfoMgr!list.action?custInfo.state=0";
			custInfoUrl2 = "/customerInfo/customerInfoMgr!getInfo.action?custInfo.state=0";
			break;
	}
}())

HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象

	// 获取表格
	var querygrid = Ext.getCmp("querygrid");

	function selectCustomer(action){
		if(!action.success)
			return;
		Ext.getCmp("acCommCode").setValue(action.data.custInfo.commCode);
		Ext.getCmp("acShortName").setValue(action.data.custInfo.shortName);
	}

	//Ext.getCmp("acCommCode").setProcessConfig(custInfoUrl2, "custInfo.commCode", null, selectCustomer);
	//Ext.getCmp("acShortName").setProcessConfig(custInfoUrl2, "custInfo.shortName", null, selectCustomer);


	// -------------------------------------- 应用逻辑处理

	querygrid.getView().on("refresh", function(view) {

		// 处理按钮触发事件
		var processBtnFun = function() {
			// 获取数据容器
			var record = this.config;

			// 要访问的 url 地址
			var url = ["/customer/detailorder.jsp?pageType=process&poNo=", record.get("poNo")
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
			var url = ["/customer/editororder.jsp?editorType=update&commCode=", this.config.get("commCode")
						, "&poNo=", this.config.get("poNo")
						, "&poNoType=", this.config.get("poNoType")
						, "&state=", this.config.get("state")].join("");
			// 打开指定页面
			HBSConvertHelper.openNewWin(url);
		};

		// 删除按钮触发事件
		var cancelBtnFun = function() {
			Ext.Msg.confirm("提示", "您要执行的是取消操作，请确认是否继续？", function(btn) {
				if(btn == "no") return;

				ExtConvertHelper.request("/custOrder/custOrder!cancel.action?commCode=" + this.config.get("commCode")
					+ "&poNo=" + this.config.get("poNo")
					+ "&poNoType=", this.config.get("poNoType"), null, ExtConvertHelper.defaultDeleteFun);
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
				var operatorBtn = HBSConvertHelper.renderButton2Cell(["处理"], operator_cell, record);
				// 添加继续按钮事件
				operatorBtn.on("click", processBtnFun);
			} else {
				switch(record.get("state")) {
					// 业务创建客户订单（业务临时保存订单，其他人无法查看）
					case "01":
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["修改"], operator_cell, record);
						// 添加修改按钮事件
						operatorBtn.on("click", updateBtnFun);
						break;
					// 财务对预付款订单退回
					case "39":
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["取消"], operator_cell, record);
						// 添加删除按钮事件
						operatorBtn.on("click", cancelBtnFun);
						break;
					// 经理审批不通过超出账期最大金额
					case "52":
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["修改", "取消"], operator_cell, record);
						// 添加修改按钮事件
						operatorBtn.get(0).on("click", updateBtnFun);
						// 添加删除按钮事件
						operatorBtn.get(1).on("click", cancelBtnFun);
						break;
					// 待业务确认交期(只对账期订单有效，本状态是采购修改交期后，显示的状态，等待业务确认交期，交期的概念针对订单明细，只要有一个订单明细需要确认交期，订单状态就为待交期确认)
					case "04":
					// 交期到，待业务确认发货（货未备齐）
					case "05":
						// 创建操作按钮
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["处理"], operator_cell, record);
						// 添加继续按钮事件
						operatorBtn.on("click", processBtnFun);
						break;
				}
			}
		};

		// 市场经理的处理方法
		var scmanagerViewFun = function(record, operator_cell) {
			// 如果是暂停状态
			if(record.get("activeState") == "PAUSE") return

			switch(record.get("state")) {
				// 待经理审批最大金额（账期交易，本账期订单超出了最大金额）
				case "50":
					// 创建操作按钮
					var operatorBtn = HBSConvertHelper.renderButton2Cell(["审批"], operator_cell, record);
					// 添加继续按钮事件
					operatorBtn.on("click", processBtnFun);
					break;
			}

		};

		// 采购部采购员的处理方法
		var cgyViewFun = function(record, operator_cell) {
			// 如果是暂停状态
			if(record.get("activeState") == "PAUSE") return

			var operatorBtn = HBSConvertHelper.renderButton2Cell(["处理"], operator_cell, record);
			// 添加处理按钮事件
			operatorBtn.on("click", processBtnFun);

		};

		// 财务的处理方法
		var financeViewFun = function(record, operator_cell) {
			// 如果是暂停状态
			if(record.get("activeState") == "PAUSE") return

			var operatorBtn = HBSConvertHelper.renderButton2Cell(["处理"], operator_cell, record);
			// 添加处理按钮事件
			operatorBtn.on("click", processBtnFun);

		};

		// 财务经理的处理方法
		var financemanagerViewFun = function(record, operator_cell) {
			// 如果是暂停状态
			if(record.get("activeState") == "PAUSE") return

			var operatorBtn = HBSConvertHelper.renderButton2Cell(["处理"], operator_cell, record);
			// 添加处理按钮事件
			operatorBtn.on("click", processBtnFun);

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
			HBSConvertHelper.renderATag2Cell(poNo_cell.innerText, "/customer/detailorder.jsp?pageType=query&roleType=" + urlPs.roleType + "&poNo=" + record.get("poNo") + "&commCode=" + record.get("commCode") + "&poNoType=" + record.get("poNoType"), "open", poNo_cell);

			switch(roleType) {
				// 市场业务员的处理方法
				case "sccustomers":
					sccustomersViewFun(record, operator_cell);
					break;
				case "scmanager":
					scmanagerViewFun(record, operator_cell);
					break;
				case "cgy":
					cgyViewFun(record, operator_cell);
					break;
				case "finance":
					financeViewFun(record, operator_cell);
					break;
				case "financemanager":
					financemanagerViewFun(record, operator_cell);
					break;
			}
		}
	})
});