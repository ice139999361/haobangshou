HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	// 获取新增用户按钮
	var addBtn    = Ext.getCmp("addBtn");
	// 获取用户列表控件
	var querygrid = Ext.getCmp("querygrid");

	
	// -------------------------------------- 应用逻辑处理
	(function() {
		// 新增用户按钮单击事件
		addBtn.on("click", function() {
			HBSConvertHelper.openNewWin("/auth/editorauth.jsp?editorType=add");
		});
		
		// 用户列表控件的刷新事件
		querygrid.getView().on("refresh", function(view) {
			// 修改按钮触发事件
			var updateBtnFun = function() {
				// 要访问的 url 地址
				var url = ["/auth/editorauth.jsp?editorType=update&staffName=", this.config.get("staffName")].join("");
				// 打开指定页面
				HBSConvertHelper.openNewWin(url);
			};
			
			// 删除按钮触发事件
			var deleteBtnFun = function() {
				Ext.Msg.confirm("提示", "您要执行的是删除操作，请确认是否继续？", function(btn) {
					if(btn == "no") return;
					
					ExtConvertHelper.request("/success.action?staffName=" + this.config.get("staffName"), null, function() {
						HBSConvertHelper.refreshGrid("querygrid");
					});
				}, this);
			};
			
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
				// 获取数据容器
				var record = view.ds.getAt(i);
				// 渲染链接到帐号列
				HBSConvertHelper.renderATag2Cell(record.get("staffName"), "/auth/detailauth.jsp?staffName=" + record.get("staffName") , "open", view.getCell(i, view.grid.getColumnIndexById("staffName")));
				
				// 获取操作列
				var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
				// 创建按钮到操作列
				var operatorBtn = HBSConvertHelper.renderButton2Cell(["删除", "修改"], operator_cell, view.ds.getAt(i));
				// 删除按钮的单击事件
				operatorBtn.get(0).on("click", deleteBtnFun);
				// 修改按钮的单击事件
				operatorBtn.get(1).on("click", updateBtnFun);
			}
		});
	}())

});