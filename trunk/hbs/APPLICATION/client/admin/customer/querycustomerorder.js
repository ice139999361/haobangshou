

HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取表格
	var querygrid = Ext.getCmp("querygrid");
	
	
	// -------------------------------------- 应用逻辑处理
	
	function getParams(record) {
			return ['roleType=', urlPs.roleType
							// 客户编码
						 ,'&commCode=', record.get("commCode")
						  // 摘要
						 ,'&summery=', record.get("summery")
						  // 结算方式
						 ,'&settlementType=', record.get("settlementType")
						].join('');
	}
	
	function getSaveParams(record) {
			return ['&settlement.commCode=', record.get("commCode")
						  ,'&settlement.shortName=', record.get("shortName")
						  // 摘要
						 ,'&settlement.summery=', record.get("summery")
						 ,'&settlement.settlementType=', record.get("settlementType")
						  // 结算方式
						 ,'&settlement.totalMoney=', record.get("totalMoney")
						 ,'&settlement.needMoney=', record.get("needMoney")
						 ,'&settlement.dealMoney=', record.get("dealMoney")
						 ,'&settlement.curMoney=', record.get("curMoney")
						  // 本次收款金额
						 ,'&settlement.financeState=', record.get("financeState")
						 ,'&settlement.salesId=', record.get("salesId")
						 ,'&settlement.salesName=', record.get("salesName")
						 ,'&settlement.assId=', record.get("assId")
						 ,'&settlement.assName=', record.get("assName")
						].join('');
	}
	
	(function() {
		querygrid.getView().on("refresh", function(view) {
			// 保存按钮触发事件
			var saveBtnFun = function() {
				
				 Ext.Msg.confirm("提示", "您要执行的是保存操作，请确认是否继续？", function(btn) {
					 if(btn == "no") return;
					 
					 ExtConvertHelper.request("/invoice/CustSettlement!save.action", getSaveParams(this.config), ExtConvertHelper.defaultOperatorFun);
				 }, this);
				 
			};
			
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
			
				// 获取数据容器
				var record = view.ds.getAt(i);
				
				// 获取编码所在的列
				var commCode_cell = view.getCell(i, view.grid.getColumnIndexById("commCode"));
			
				// 创建url
				var _url = ['/customer/detailcustomerorder.jsp?', getParams(record)].join('');

				// 将需要的链接渲染到此列
				HBSConvertHelper.renderATag2Cell(commCode_cell.innerText, _url, "open", commCode_cell);
				
				// 获取操作列
				var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
				// 创建操作按钮
				var operatorBtn = HBSConvertHelper.renderButton2Cell(["保存"], operator_cell, record);
				// 添加审批按钮事件
				operatorBtn.on("click", saveBtnFun);
			}
		
		});
		
		querygrid.getSelectionModel().on("beforerowselect", function(sm, rowIndex, keepExisting, record) {
			
			// 如果结算状态为结算完毕
			querygrid.getColumnById("bcskje").editable = (record.get("jszt") != 1);
			
		});
	}())
	
});