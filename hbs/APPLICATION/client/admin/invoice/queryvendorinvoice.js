HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取新增按钮
	var addBtn    = Ext.getCmp("addBtn");
	// 获取表格
	var querygrid = Ext.getCmp("querygrid");
	
	// -------------------------------------- 应用逻辑处理

	(function() {
		addBtn.on("click", function() {
			// 要访问的 url 地址
			var url = "/invoice/editorvendorinvoice.jsp";
			// 打开指定页面
			HBSConvertHelper.openNewWin(url);
		});
		
		querygrid.getView().on("refresh", function(view) {
			// 删除按钮触发事件
			var deleteBtnFun = function() {
				Ext.Msg.confirm("提示", "您要执行的是删除操作，请确认是否继续？", function(btn) {
					if(btn == "no") return;
					
					ExtConvertHelper.request("/success.action?baseSeqId=" + this.config.get("baseSeqId"), null, function() {
						HBSConvertHelper.refreshGrid("querygrid");
					});
				}, this);
			}
			
			// 修改按钮触发事件
			var updateBtnFun = function() {
				// 要访问的 url 地址
				var url = ["/invoice/editorvendorinvoice.jsp?editorType=update&commCode=", this.config.get("commCode")
							, "&poNo=", this.config.get("poNo")
							, "&poNoType=", this.config.get("poNoType")
							, "&state=", this.config.get("state")].join("");
				// 打开指定页面
				HBSConvertHelper.openNewWin(url);
			};
			
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				// 获取数据容器
				var record = view.ds.getAt(i);
				// 获取操作列
				var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
				// 创建操作按钮
				var operatorBtn = HBSConvertHelper.renderButton2Cell(["删除", "修改"], operator_cell, record);
				// 添加删除按钮事件
				operatorBtn.get(0).on("click", deleteBtnFun);
				// 添加修改按钮事件
				operatorBtn.get(1).on("click", updateBtnFun);
			}
		});
	}())
	
});