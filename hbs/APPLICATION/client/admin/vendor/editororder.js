//TODO：从配置中获取此值
var VendorDate2CustDate = 3;

HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象

	// 获取提交按钮
	var submitBtn 	 = Ext.getCmp("submitBtn");
	// 获取保存按钮
	var saveBtn 	   = Ext.getCmp("saveBtn");
	// 获取返回按钮
	var backBtn 		 = Ext.getCmp("backBtn");
	// 获取订单详情表格
	var ordergrid    = Ext.getCmp("ordergrid");
	// 获取查询客户订单 window 控件
	var selectWindow = Ext.getCmp("selectWindow");


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
			if(action && action.result && action.result.data && action.result.data.poNo){
				msg += " 订单编号：" + action.result.data.poNo;
			}
			// 弹出提示框给用户
			Ext.Msg.alert("提示", msg, submitSuccessPro);
		});
	}

	(function() {
		// 当提交按钮被单击时
		submitBtn.on("click", function() {
			submitData("/vendorOrder/vendorOrder!save.action");
		});

		// 当保存按钮被单击时
		saveBtn.on("click", function() {
			submitData("/vendorOrder/vendorOrder!saveTemp.action");
		});

		// 当单机取消按钮时，调用默认的关闭窗口方法
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);

		Ext.getCmp("sxkhddBtn").on("click", function() {
			var commcode = Ext.getCmp("acCommCode").getValue();
			if(commcode) {
				Ext.getCmp("acVendorCode").setValue(commcode);
				selectWindow.show();
				Ext.getCmp("selectform").buttons[0].fireEvent("click");
			} else {
				Ext.Msg.alert("提示", "请先填写正确的供应商。");
			}
		});

		ordergrid.getSelectionModel().on("beforerowselect", function(sm, rowIndex, keepExisting, record) {
			// 初始化 selectType
			if(Ext.isEmpty(record.selectType)) record.selectType = "none";

			switch(record.selectType) {
				case "window":

					ordergrid.getColumnById("cPartNo").editable = false;
					break;
				default:
					ordergrid.getColumnById("cPartNo").editable = true;
					break;
			}
		});

		// 获取表格的列模型
		var cm = ordergrid.getColumnModel();

		// 输入客户P/N或本公司P/N时，自动填写名称、描述、单价、税率
		var orderacfun = function(action){
			if(!action.success)
				return;

			var sm = ordergrid.getSelectionModel();
			//sm.getSelected().set("pnName"   , action.data.vendorPartNoInfo.pnName);
			sm.getSelected().set("pnDesc"   , action.data.vendorPartNoInfo.pnDesc);
			sm.getSelected().set("cprice"   , action.data.vendorPartNoInfo.price);
			sm.getSelected().set("cpriceTax", action.data.vendorPartNoInfo.priceTax);
			sm.getSelected().set("cpartNo"   , action.data.vendorPartNoInfo.custPartNo);
			sm.getSelected().set("partNo", action.data.vendorPartNoInfo.partNo);
		};

		// 获取客户型号控件并加载事件
		cm.getColumnById("cCpartNo").editor.setProcessConfig("/vendorInfo/vendorPartNoInfo!getInfoDict.action", "cpartNo", null, orderacfun);

		// 获取GLE型号控件并加载事件
		cm.getColumnById("cPartNo").editor.setProcessConfig("/vendorInfo/vendorPartNoInfo!getInfoDict.action", "partNo", null, orderacfun);

		//根据税率设置是否含税交易：税率为0时，可选；否则设为是，并不可修改。
		//输入数量时，自动填写金额。
		ordergrid.getSelectionModel().on("beforerowselect", function(sm, rowIndex, keepExisting, record) {
			// 初始化 selectType
			if(Ext.isEmpty(record.get("cpriceTax")) || record.get("cpriceTax") == 0) ordergrid.getColumnById("cisTax").editable = true;
			else ordergrid.getColumnById("cisTax").editable = false;
		});
	}())

	function afterListLoad(){
		if(this.getCount() > 0 && this.list && this.list.getValue()){
			this.list.fireEvent("select");
		}
	}

	Ext.getCmp("acCommCode").setProcessConfig("/vendorInfo/vendorInfo!getInfo.action?vendorInfo.state=0", "vendorInfo.commCode", null, function(action){
		if(!action.success)
			return;
		Ext.getCmp("acCompanyBranch").setValue(action.data.vendorInfo.companyBranchDesc);
		Ext.getCmp("acShortName").setValue(action.data.vendorInfo.shortName);
		Ext.getCmp("acSettlementType").setValue(action.data.vendorInfo.settlementDesc);

		// 给需要的隐藏域赋值
		Ext.getCmp("hidIsShowPrice").setValue(action.data.vendorInfo.isShowPrice);
		Ext.getCmp("hidSettlementType").setValue(action.data.vendorInfo.settlementType);
		Ext.getCmp("hidCompanyBranch").setValue(action.data.vendorInfo.companyBranch);
		Ext.getCmp("hidShortName").setValue(action.data.vendorInfo.shortName);

		var o = this.getValue();
		Ext.getCmp("acVendorCode").setValue(o);
		var list = Ext.getCmp("acContactList");
		list.store.baseParams["vendorInfo.commCode"] = o;
		list.store.baseParams["vendorInfo.state"] = "0";
		if(list.getValue()){
			list.store.list = list;
			list.store.on("load", afterListLoad);
			list.store.load();
		}list = Ext.getCmp("acConsigneeList");
		list.store.baseParams["vendorInfo.commCode"] = o;
		list.store.baseParams["vendorInfo.state"] = "0";
		if(list.getValue()){
			list.store.list = list;
			list.store.on("load", afterListLoad);
			list.store.load();
		}
	});

	Ext.getCmp("acContactList").on("select", function() {
		if(this.selectedIndex < 0){
			var val = this.getValue();
			if(val){
				// 根据val设置selectedIndex
				this.selectedIndex = this.store.findExact("conName", val);
			}
			if(this.selectedIndex < 0)
				return;
		}
		var data = this.store.getAt(this.selectedIndex);
		var o = data.get("conTel");
		Ext.getCmp("acTel").setValue(o);
		Ext.getCmp("acTelHidden").setValue(o);
		o = data.get("conFax");
		Ext.getCmp("acFax").setValue(o);
		Ext.getCmp("acFaxHidden").setValue(o);
	});

	Ext.getCmp("acConsigneeList").on("select", function() {
		if(this.selectedIndex < 0){
			var val = this.getValue();
			if(val){
				// 根据val设置selectedIndex
				this.selectedIndex = this.store.findExact("conName", val);
			}
			if(this.selectedIndex < 0)
				return;
		}
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
		HBSConvertHelper.setDocumentTitle("下供应商订单");

		// 提交完成后的操作
		submitSuccessPro = function() {
			// 用户单击后重载此页面
			location.reload();
		}
	}

	// 修改页面的处理逻辑
	function updateInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("供应商订单修改");
		// 隐藏不需要的控件
		if(urlPs.state != "01") ExtConvertHelper.hideItems("saveBtn");

		// 组装需要的参数
		var params = ["vendorOrder.commCode=", urlPs.commCode, "&vendorOrder.poNo=", urlPs.poNo, "&vendorOrder.poNoType=", urlPs.poNoType].join("");

		// 加载数据
		ExtConvertHelper.loadForm("form", "/vendorOrder/vendorOrder!getInfo.action", params, function(form, action) {
				Ext.getCmp("ordergrid").addData(action.result.data.vendorOrder.vendorOrderDetailList);
				Ext.getCmp("acCommCode").fireEvent("select");
		});

		// 提交完成后的操作
		submitSuccessPro = function() {
			// 用户单击后关闭此页面
			HBSConvertHelper.defaultCloseTab();
		}
	}

	// 根据不同的操作类型，做出不同的处理
	eval(urlPs.editorType + "InitFun")();


	// -------------------------------------- window 部分功能实现代码
	(function() {
		// 点击取消按钮的事件
		Ext.getCmp("wbackBtn").on("click", function() {
			selectWindow.hide();
		});

		Ext.getCmp("querygrid").getView().on("refresh", function(view) {
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				var record = view.ds.getAt(i);
				record.set("fromTo", "window");
			}
		});

		// 点击确定按钮的事件
		Ext.getCmp("wokBtn").on("click", function() {
			// 获取查询列表
			var querygrid = Ext.getCmp("querygrid");
			// 获取选择的数据集
			var records = querygrid.getSelectionModel().getSelections();
			// 订单详情需要的字段集
			var ordergridFields = ['operSeqId', 'pnName', 'cpartNo', 'partNo', 'pnDesc', 'cprice', 'cpriceTax', 'isTax', 'amount', 'money', 'orgDeliveryDate', 'specDesc', 'commDesc', 'custCcode', 'hastenReminder', 'selectType', 'fromTo'];
			// 添加标示
			Ext.each(records, function(record) {
				//record.set("selectType", "window");
				//DONE:获取客户订单明细的交期(按照ver、pre、org的顺序获取非空时间)，减(VendorDate2CustDate)天，放入orgDeliveryDate
				var s = record.get("verDeliveryDate");
				if(!s)
					s = record.get("preDeliveryDate");
				if(!s)
					s = record.get("orgDeliveryDate");
				if(s){
					var d = new Date(s.replace(/-/g,   "/"));
					var a   =   d.valueOf();
  					a   -=   VendorDate2CustDate   *   24   *   60   *   60   *   1000;
  					d = new Date(a);
  					record.set("orgDeliveryDate", d);
				}

				if(record.get('orgDeliveryDate') instanceof Date) 
				{
					record.set('orgDeliveryDate', Ext.util.Format.date(record.get('orgDeliveryDate'), 'Y-m-d'))
				}	
			});

			// 添加选择的数据至订单详情表格
			ordergrid.store.add(records);

			// 隐藏 window 控件
			selectWindow.hide();
		});
	}())
});