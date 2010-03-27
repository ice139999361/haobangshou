
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
		
		// 获取表格的列模型
		var cm = warehousegrid.getColumnModel();
		
		// 输入客户P/N或本公司P/N时，自动填写名称、描述、单价、税率
		var warehouseacfun = function(action){
			if(!action.success)
				return;
				
			var sm = warehousegrid.getSelectionModel();
			sm.getSelected().set("l4"   , "描述");
			sm.getSelected().set("l5"   , "订单数量");
		};
		
		// 获取GLE编码控件并加载事件
		cm.getColumnById("cglecode").editor.setProcessConfig("/customerInfo/customerInfo!list.action", "custInfo.commCode", null, warehouseacfun);
		
		// 获取供应商编码控件并加载事件
		cm.getColumnById("ccommcode").editor.setProcessConfig("/customerInfo/customerInfo!list.action", "custInfo.commCode", null, warehouseacfun);
	}())
	
	// -------------------------------------- 页面操作逻辑处理
	// 新增页面的处理逻辑
	function addInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("入库");
		
		// 提交完成后的操作
		submitSuccessPro = function() {
			// 用户单击后重载此页面
			location.reload();
		}
	}
	
	// 修改页面的处理逻辑
	function updateInitFun() {
		
	}
	
	// 根据不同的操作类型，做出不同的处理
	eval(urlPs.editorType + "InitFun")();
});