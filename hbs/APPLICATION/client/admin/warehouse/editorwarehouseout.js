var isManulSelect = true;	//标记是否人为选择commCode

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

	/**
	 * 提交数据
	 * @param url  (String) 提交的url
	 */
	function submitData(url) {
		// 验证 form 内容是符满足要求
		//if(!ExtConvertHelper.isFormValid("form")) return;

		// 获取（客户联系人信息、客户收货人信息、客户银行信息）表格中的提交数据
		var girdData = HBSConvertHelper.getGridSubmitData("warehousegrid", "orderlist");

		// 提交数据
		ExtConvertHelper.submitForm("form", url, girdData, function(form, action) {
			// 获取成功后的提示信息
			var msg = ExtConvertHelper.getMessageInfo(action, "操作成功！");
			if(action && action.result && action.result.data && action.result.data.sendPoNo){
				msg += " 编号：" + action.result.data.sendPoNo;
			}
			// 弹出提示框给用户
			Ext.Msg.alert("提示", msg, function() {
				// 用户单击后重载此页面
				location.reload();
			});
		});
	}

	function afterListLoad(){
		if(!(this && this.list))
			return
		if(this.list.selectPrimary){
			selectPrimary(this.list)
		}else if(this.list.getValue()){
			this.list.fireEvent("select");
		}
	}

	function selectPrimary(list){
		list.selectPrimary = false;
		// DONE:选择主联系人
		if(!list || !list.store)
			return;
		var i = -1;
		if(list.store.getCount() == 1)
			i = 0;
		else
			i = list.store.findExact("isPrimary", "0");
		if(i >= 0){
			list.setValue(list.store.getAt(i).get("conName"));
			list.selectedIndex = i;
		}else
			list.selectedIndex = -1;
		list.fireEvent("select");
	}

	(function() {
		function setCommCode(action){
			if(!action.success)
				return;
			Ext.getCmp("acCommCode").setValue(action.data.custInfo.commCode);
			Ext.getCmp("acShortName").setValue(action.data.custInfo.shortName);
			// 给需要的隐藏域赋值
			Ext.getCmp("hidSettlementType").setValue(action.data.custInfo.settlementType);
			Ext.getCmp("acCompanyBranch").setValue(action.data.custInfo.companyBranch);
			Ext.getCmp("acWinCommCode").setValue(action.data.custInfo.commCode);

			var list = Ext.getCmp("areceiveName");
			list.setParam("custCode", action.data.custInfo.commCode);
			list.store.list = list;
			list.store.on("load", afterListLoad);
			if(isManulSelect){
				list.setValue("");
				list.selectPrimary = true;
				list.store.load();
			}else{
				if(list.getValue()){
					list.selectPrimary = false;
					list.store.load();
				}
			}
		}
		Ext.getCmp("acCommCode").setProcessConfig("/customerInfo/customerInfoMgr!getInfo.action?CustInfo.state=0", "custInfo.commCode", null, setCommCode);
		Ext.getCmp("acShortName").setProcessConfig("/customerInfo/customerInfoMgr!getInfo.action?CustInfo.state=0", "custInfo.shortName", null, setCommCode);

		// 当提交按钮被单击时
		submitBtn.on("click", function() {
			submitData("/warehouseSend/warehouseSend!commit.action");
		});

		// 当保存按钮被单击时
		saveBtn.on("click", function() {
			submitData("/warehouseSend/warehouseSend!saveTemp.action");
		});

		// 当单机取消按钮时，调用默认的关闭窗口方法
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);

		Ext.getCmp("addInfoBtn").on("click", function() {
			selectWindow.show();
			HBSConvertHelper.refreshGrid("querygrid");
		});

		// 根据收货人自动填充 联系电话，邮编，收货地址，传真
		Ext.getCmp("areceiveName").on("select", function() {
			//alert(this.selectedIndex);
			if(this.selectedIndex < 0){
				var val = this.getValue();
				if(val){
					// 根据val设置selectedIndex
					this.selectedIndex = this.store.findExact("conName", val);
				}
				if(this.selectedIndex < 0){
					Ext.getCmp("tconTel").setValue("");
					Ext.getCmp("treceiveZip").setValue("");
					Ext.getCmp("tconFax").setValue("");
					Ext.getCmp("treceiveAddress").setValue("");
					return;
				}
			}
			var data = this.store.getAt(this.selectedIndex);

			// 设置联系电话
			Ext.getCmp("tconTel").setValue(data.get("conTel"));
			// 设置邮编
			Ext.getCmp("treceiveZip").setValue(data.get("conZip"));
			// 设置传真
			Ext.getCmp("tconFax").setValue(data.get("conFax"));
			// 设置收货地址
			Ext.getCmp("treceiveAddress").setValue(data.get("conAddress"));
		});

		warehousegrid.getView().on("refresh", function(view) {
			// 删除按钮触发事件
			var deleteBtnFun = function() {

				var seqid =this.config.get("sendSeqId");


				if(seqid > 0){
					Ext.Msg.confirm("提示", "您要执行的是出库单明细取消，请确认是否继续？", function(btn) {
					if(btn == "no") return;

							ExtConvertHelper.request("/warehouseSend/warehouseSend!cancelDetail.action?sendSeqId=" + this.config.get("sendSeqId"), null, ExtConvertHelper.defaultDeleteFun);
						}, this);

		        	}else{
			       	Ext.Msg.confirm("提示", "您要执行的是出库单明细操作，本条明细还没有保存，请确认是否继续删除？", function(btn) {
									if(btn == "no") return;
			        		warehousegrid.store.remove(this.config);
			        	}, this);
		        	}
				//warehousegrid.store.remove(this.config);
			};
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				// 获取操作列
				var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
				switch((view.ds.getAt(i).get("state") ? view.ds.getAt(i).get("state") : "")) {
					case "":
					case "01":
						// 创建按钮到操作列
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["删除"], operator_cell, view.ds.getAt(i));
						// 删除按钮的单击事件
						operatorBtn.on("click", deleteBtnFun);
						break;
				}
			}
		})
	}())

	// -------------------------------------- 页面操作逻辑处理
	// 新增页面的处理逻辑
	function addInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("出库");
		ExtConvertHelper.setItemsReadOnly("sendPoNo", true);
		ExtConvertHelper.hideItems("sendPoNo,acCreateDate");
	}

	// 修改页面的处理逻辑
	function updateInitFun() {
		// 更改页签标题
		HBSConvertHelper.setDocumentTitle("出库修改");
		// 设置客户编码的编辑框为只读	；同时不到后台校验
		ExtConvertHelper.setItemsReadOnly("acCommCode", true);
		ExtConvertHelper.setItemsReadOnly("acHouseType", true);
		// 组装需要的参数
		var params = ["warehouseSend.sendPoNo=", urlPs["warehouseSend.sendPoNo"],"&warehouseSend.custCode=" , urlPs["warehouseSend.custCode"],"&warehouseSend.poNoType=", urlPs["warehouseSend.poNoType"]].join("");

		// 加载数据
		ExtConvertHelper.loadForm("form", "/warehouseSend/warehouseSend!getInfo.action", params, function(form, action) {
				var warehouseSend = action.result.data.warehouseSend;
			  Ext.getCmp("acCreateDate").setValue(FormatUtil.data2string(warehouseSend.createDate));
			  Ext.getCmp("warehousegrid").addData(warehouseSend.detailList);
			  Ext.getCmp("acCommCode").fireEvent("select");

			  switch(warehouseSend.state) {
			  	// 临时状态
			  	case "01":
			  		ExtConvertHelper.hideItems("printBtn");
			  		break;
			  	// 正式状态
			  	case "02":
			  		ExtConvertHelper.hideItems("addInfoBtn,submitBtn,saveBtn");
			  		break;
			  	// 取消状态
			  	case "03":
			  		ExtConvertHelper.hideItems("addInfoBtn,submitBtn,saveBtn,printBtn");
			  		break;
			  }
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

			// 缓存数据
			var oldRecords = {};
			warehousegrid.store.each(function() {
				oldRecords[this.get("rltPoNo")] = true;
			});

			var newRecords = [];
			// 添加标示
			Ext.each(records, function(record) {
				if(!oldRecords[record.get("rltPoNo")]) {
					record.selectType = "window";
					newRecords.push(record);
				}
			});

			// 添加标示
			Ext.each(records, function(record) {
				record.selectType = "window";
			});
			// 添加选择的数据至订单详情表格
			warehousegrid.store.add(newRecords);
			// 刷新表格
			//setTimeout(function() {	warehousegrid.getView().refresh() }, 0);
			// 隐藏 window 控件
			selectWindow.hide();

		});
	}())
});