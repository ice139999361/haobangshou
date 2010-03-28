
HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取提交按钮
	var submitBtn 	= Ext.getCmp("submitBtn");
	// 获取保存按钮
	var saveBtn 	  = Ext.getCmp("saveBtn");
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	// 获取订单详情表格
	var ordergrid   = Ext.getCmp("ordergrid");
	
	
	// -------------------------------------- 应用逻辑处理
	
	// 提交操作成功后要做的事情
	var submitSuccessPro;
	
	/**
	 * 提交数据
	 * @param url  (String) 提交的url
	 */
	function submitData(url) {
		// 验证 form 内容是符满足要求
		//if(!ExtConvertHelper.isFormValid("form")) return;
		
		// 获取（客户联系人信息、客户收货人信息、客户银行信息）表格中的提交数据
		var girdData = HBSConvertHelper.getGridSubmitData("ordergrid", "orderlist");
		
		// 提交数据
		ExtConvertHelper.submitForm("form", url, girdData, function(form, action) {
			// 获取成功后的提示信息
			var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
			
			// 弹出提示框给用户
			Ext.Msg.alert("提示", msg, submitSuccessPro);
		});
	}
	
	(function() {
		// 当提交按钮被单击时
		submitBtn.on("click", function() {
			submitData("/custOrder/custOrder!save.action");
		});
		
		// 当保存按钮被单击时
		saveBtn.on("click", function() {
			submitData("/custOrder/custOrder!saveTemp.action");
		});
		
		// 当单机取消按钮时，调用默认的关闭窗口方法
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);
		
		// 获取表格的列模型
		var cm = ordergrid.getColumnModel();
		
		// 输入客户P/N或本公司P/N时，自动填写名称、描述、单价、税率
		var orderacfun = function(action){
			if(!action.success)	return;
			
			var sm = ordergrid.getSelectionModel();
			sm.getSelected().set("pnName"   , action.data.custPartNoInfo.pnDesc);
			sm.getSelected().set("pnDesc"   , action.data.custPartNoInfo.pnDesc);
			sm.getSelected().set("cprice"   , action.data.custPartNoInfo.price);
			sm.getSelected().set("cpriceTax", action.data.custPartNoInfo.priceTax);
		};
		
		// 获取客户型号控件并加载事件
		cm.getColumnById("cCpartNo").editor.setProcessConfig("/customerInfo/customerInfo!list.action", "cpartNo", null, orderacfun);
		
		// 获取GLE型号控件并加载事件
		cm.getColumnById("cPartNo").editor.setProcessConfig("/customerInfo/customerInfo!list.action", "partNo", null, orderacfun);
		
		//根据税率设置是否含税交易：税率为0时，可选；否则设为是，并不可修改。 
		//输入数量时，自动填写金额。 

	}())
	
	
	
	/*
	Ext.getCmp("acContactList").store = new Ext.data.JsonStore({
		url : "/server/customerInfo/customerInfo!getContactList.action",
		root : "data.list",
		fields : ["seqId", "conName", "conTel", "conFax"]
	});
	Ext.getCmp("acConsigneeList").store = new Ext.data.JsonStore({
		url : "/server/customerInfo/customerInfo!getConsigneeList.action",
		root : "data.list",
		fields : ["seqId", "conName", "conAddress", "conZip"]
	});
	*/

	Ext.getCmp("acCommCode").setProcessConfig("/customerInfo/customerInfo!getInfo.action?custInfo.state=0", "custInfo.commCode", null, function(action){
		if(!action.success)
			return;
		Ext.getCmp("acCompanyBranch").setValue(action.data.custInfo.companyBranchDesc);
		Ext.getCmp("acShortName").setValue(action.data.custInfo.shortName);
		Ext.getCmp("acSettlementType").setValue(action.data.custInfo.settlementDesc);

		var o = this.getValue();
		var list = Ext.getCmp("acContactList");
		list.store.baseParams["custInfo.commCode"] = o;
		list.store.baseParams["custInfo.state"] = "0";
		list = Ext.getCmp("acConsigneeList");
		list.store.baseParams["custInfo.commCode"] = o;
		list.store.baseParams["custInfo.state"] = "0";
	});
	
	Ext.getCmp("acContactList").on("select", function() {
		if(this.selectedIndex < 0)
			return;
		var data = this.store.getAt(this.selectedIndex);
		var o = data.get("conTel");
		Ext.getCmp("acTel").setValue(o);
		Ext.getCmp("acTelHidden").setValue(o);
		o = data.get("conFax");
		Ext.getCmp("acFax").setValue(o);
		Ext.getCmp("acFaxHidden").setValue(o);
	});
	
	Ext.getCmp("acConsigneeList").on("select", function() {
		if(this.selectedIndex < 0)
			return;
		var data = this.store.getAt(this.selectedIndex);
		var o = data.get("conAddress");
		Ext.getCmp("acAddress").setValue(o);
		Ext.getCmp("acAddressHidden").setValue(o);
		o = data.get("conZip");
		Ext.getCmp("acZip").setValue(o);
		Ext.getCmp("acZipHidden").setValue(o);
	});
	
	// -------------------------------------- 页面操作逻辑处理
	
	// 提交操作成功后要做的事情
	var submitSuccessPro;
	
	// 新增页面的处理逻辑
	function addInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("客户订单录入");
		
		// 提交完成后的操作
		submitSuccessPro = function() {
			// 用户单击后重载此页面
			location.reload();
		}
	}
	
	// 修改页面的处理逻辑
	function updateInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("客户订单修改");
		// 隐藏不需要的控件
		if(urlPs.state != "01") ExtConvertHelper.hideItems("saveBtn");
		
		// 组装需要的参数
		var params = ["custOrder.commCode=", urlPs.commCode, "&custOrder.poNo=", urlPs.poNo, "&custOrder.poNoType=", urlPs.poNoType].join("");
		
		// 加载数据
		ExtConvertHelper.loadForm("form", "/custOrder/custOrder!getInfo.action", params, function(form, action) {
				Ext.getCmp("ordergrid").addData(action.result.data.custOrder.orderlist);
		});
		
		// 提交完成后的操作
		submitSuccessPro = function() {
			// 用户单击后关闭此页面
			HBSConvertHelper.defaultCloseTab();
		}
	}
	
	// 根据不同的操作类型，做出不同的处理
	eval(urlPs.editorType + "InitFun")();
});