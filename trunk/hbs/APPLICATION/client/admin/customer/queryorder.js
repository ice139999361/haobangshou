var querygridUrl;

// 初始方法
(function() {
	switch(urlPs.roleType) {
		case "sccustomers":
			querygridUrl = "/customerInfo/customerInfo!list.action";
			break;
		case "scmanager":
			querygridUrl = "/customerInfo/customerInfoMgr!list.action";
			break; 
	}
}())

HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取表格
	var querygrid = Ext.getCmp("querygrid");
	//HBSConvertHelper.getATagString("ffff", "abc.action", "open");
	
	
	
	// -------------------------------------- 应用逻辑处理
	
	querygrid.getView().on("refresh", function(view) {
		//alert(this.ds.getCount())
		for(var i = 0 ; i < view.ds.getCount() ; i++) {
			// 获取客户简称所在的列
			var shortName_cell = view.getCell(i, view.grid.getColumnIndexById("shortName"));
			// 获取操作列
			var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
			// 将需要的链接渲染到此列
			HBSConvertHelper.renderATag2Cell(shortName_cell.innerText, "abc.action", "open", shortName_cell);
			
			// 操作列显示逻辑
			switch(view.ds.getAt(i).get("state")) {
				case "1":
				case "0":
				  // 创建按钮到操作列
					var btns = HBSConvertHelper.renderButton2Cell(["修改", "处理", "查看操作历史"], operator_cell, view.ds.getAt(i));
					
					// 修改按钮事件
					btns.get(0).on("click", function() {
						// 要访问的 url 地址
						var url = ["/customer/editororder.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=", this.config.get("state")].join("");
						// 打开指定页面
						HBSConvertHelper.openNewWin(url);
					});
					
					// 处理按钮事件
					btns.get(1).on("click", function() { alert(2) });
					
					// 查看操作历史按钮事件
					btns.get(2).on("click", function() { alert(3) });
					
					break;
			}
			
		}
	})
});