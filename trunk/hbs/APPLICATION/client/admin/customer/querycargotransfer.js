HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取表格
	var querygrid = Ext.getCmp("querygrid");
	
	// -------------------------------------- 应用逻辑处理

	(function() {
		
		querygrid.getView().on("refresh", function(view) {

			// 审批按钮触发事件
			var auditBtnFun = function() {
				// 要访问的 url 地址
				var url = ["/customer/detailcargotransfer.jsp?editorType=audit&applySeqId=", this.config.get("applySeqId")
						  ].join("");
				// 打开指定页面
				HBSConvertHelper.openNewWin(url);
			};
			
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				// 获取数据容器
				var record = view.ds.getAt(i);
				
				// 获取编码所在的列
				var commCode_cell = view.getCell(i, view.grid.getColumnIndexById("commCode"));
				// 将需要的链接渲染到此列
				HBSConvertHelper.renderATag2Cell(commCode_cell.innerText, "/customer/detailcargotransfer.jsp?pageType=query&applySeqId=" + record.get("applySeqId"), "open", commCode_cell);
			
				if(view.grid.getColumnIndexById("operator") != -1) {
					// 获取操作列
					var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
					// 创建操作按钮
					var operatorBtn = HBSConvertHelper.renderButton2Cell(["审批"], operator_cell, record);
					// 添加审批按钮事件
					operatorBtn.on("click", auditBtnFun);
				}
			}
		});
	}())
	
});