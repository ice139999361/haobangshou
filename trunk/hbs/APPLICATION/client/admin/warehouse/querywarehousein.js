HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取表格
	var querygrid = Ext.getCmp("querygrid");
	
	// -------------------------------------- 应用逻辑处理
	
	querygrid.getView().on("refresh", function(view) {
		// 修改按钮触发事件
		var updateBtnFun = function() {
			// 要访问的 url 地址
			var url = ["/warehouse/editorwarehousein.jsp?editorType=update&warehouseRec.recPoNo=", this.config.get("recPoNo"), "&warehouseRec.vendorCode=", this.config.get("vendorCode")].join("");
			// 打开指定页面
			HBSConvertHelper.openNewWin(url);
		};
		
		// 删除按钮触发事件
		var deleteBtnFun = function() {
			Ext.Msg.confirm("提示", "您要执行的是取消入库单及入库单明细操作，请确认是否继续？", function(btn) {
				if(btn == "no") return;
				
				ExtConvertHelper.request("/warehouseRec/warehouseRec!cancel.action?warehouseRec.recPoNo=" + this.config.get("recPoNo") + "&warehouseRec.vendorCode=" + this.config.get("vendorCode"), null, ExtConvertHelper.defaultDeleteFun);
			}, this);
		}
		
		//alert(this.ds.getCount())
		for(var i = 0 ; i < view.ds.getCount() ; i++) {
			// 获取数据容器
			var record = view.ds.getAt(i);
			// 获取客户简称所在的列
			var crecpono_cell = view.getCell(i, view.grid.getColumnIndexById("crecpono"));
			// 将需要的链接渲染到此列
			HBSConvertHelper.renderATag2Cell(crecpono_cell.innerText, "/warehouse/detailwarehousein.jsp?pageType=query&warehouseRec.recPoNo="+view.ds.getAt(i).get("recPoNo") + "&warehouseRec.vendorCode=" + view.ds.getAt(i).get("vendorCode"), "open", crecpono_cell);
			
			
			if(view.grid.getColumnIndexById("operator") != -1) {
				// 获取操作列
				var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
				
				switch(record.get("state")) {
					case "01":
						// 创建按钮到操作列
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["删除", "修改"], operator_cell, view.ds.getAt(i));
						// 删除按钮的单击事件
						operatorBtn.get(0).on("click", deleteBtnFun);
						// 修改按钮的单击事件
						operatorBtn.get(1).on("click", updateBtnFun);
						break;
				}
			}
		}
	});
});