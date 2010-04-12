
HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取提交按钮
	var submitBtn 	= Ext.getCmp("submitBtn");
	// 获取保存按钮
	var saveBtn 	  = Ext.getCmp("saveBtn");
	// 获取返回按钮
	var backBtn 		= Ext.getCmp("backBtn");
	// 获取订单详情表格
	var warehousegrid   = Ext.getCmp("warehousegrid");
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
		var girdData = HBSConvertHelper.getGridSubmitData("warehousegrid", "warehouselist");
		
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
			submitData("/warehouse/warehouseRec!save.action");
		});
		
		// 当保存按钮被单击时
		saveBtn.on("click", function() {
			submitData("/warehouse/warehouseRec!saveTemp.action");
		});
		
		// 当单机取消按钮时，调用默认的关闭窗口方法
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);
		
		Ext.getCmp("addInfoBtn").on("click", function() {
			selectWindow.show();
			HBSConvertHelper.refreshGrid("querygrid");
		});

		warehousegrid.getView().on("refresh", function(view) {
			// 删除按钮触发事件
			var deleteBtnFun = function() {
				warehousegrid.store.remove(this.config);
			};
		
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				// 获取操作列
				var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
				// 创建按钮到操作列
				var operatorBtn = HBSConvertHelper.renderButton2Cell(["删除"], operator_cell, view.ds.getAt(i));
				// 删除按钮的单击事件
				operatorBtn.on("click", deleteBtnFun);
			}
		})
	}())
	
	// -------------------------------------- 页面操作逻辑处理
	// 新增页面的处理逻辑
	function addInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("出库");
		
		// 提交完成后的操作
		submitSuccessPro = function() {
			// 用户单击后重载此页面
			location.reload();
		}
	}
	
	// 修改页面的处理逻辑
	function updateInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("出库修改");
		// 隐藏不需要的控件
		if(urlPs.state != 1) ExtConvertHelper.hideItems("saveBtn");
		
		// 组装需要的参数
		var params = ["vendorInfo.baseSeqId=", urlPs.baseSeqId].join("");
		
		// 加载数据
		ExtConvertHelper.loadForm("form", "/vendorInfo/vendorInfo!getInfo.action", params, function(form, action) {
				Ext.getCmp("warehousegrid").addData(action.result.data.vendorInfo.listBankInfo);
		});
	}
	
	// 根据不同的操作类型，做出不同的处理
	eval(urlPs.editorType + "InitFun")();
	
	// -------------------------------------- window 部分功能实现代码
	(function() {
		// 点击取消按钮的事件
		Ext.getCmp("wbackBtn").on("click", function() {
			selectWindow.hide();
		});
		
		// 点击确定按钮的事件
		Ext.getCmp("wokBtn").on("click", function() {
			// 获取查询列表
			var querygrid = Ext.getCmp("querygrid");
			// 获取选择的数据集
			var records = querygrid.getSelectionModel().getSelections();
			// 添加标示
			Ext.each(records, function(record) {
				record.selectType = "window";
			});
			// 添加选择的数据至订单详情表格
			warehousegrid.store.add(records);
			// 刷新表格
			setTimeout(function() {	warehousegrid.getView().refresh() }, 0);
			// 隐藏 window 控件
			selectWindow.hide();
			
		});
	}())
});