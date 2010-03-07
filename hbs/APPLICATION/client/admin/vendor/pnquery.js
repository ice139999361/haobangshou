var querygridUrl;

// 初始方法
(function() {
	switch(urlPs.roleType) {
		case "cgy":
			querygridUrl = "/vendorInfo/vendorPartNoInfo!list.action";
			break;
		case "cgm":
			querygridUrl = "/vendorInfo/vendorPartNoInfoMgr!list.action";
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
			var custPartNo_cell = view.getCell(i, view.grid.getColumnIndexById("custPartNo"));
			// 获取操作列
			var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
			// 将需要的链接渲染到此列
			HBSConvertHelper.renderATag2Cell(custPartNo_cell.innerText, "/vendor/detailpnrelation.jsp?pageType=query&seqId="+view.ds.getAt(i).get("seqId"), "open", custPartNo_cell);
			
			// 操作列显示逻辑
			switch(view.ds.getAt(i).get("state")) {
				case "1":
				case "0":
					if(urlPs.roleType == "cgy") {
					  // 创建按钮到操作列
						var btns = HBSConvertHelper.renderButton2Cell(["删除", "恢复", "修改"], operator_cell, view.ds.getAt(i));
	
						// 删除按钮事件
						btns.get(0).on("click", function() { alert(1) });
						
						// 恢复按钮事件
						btns.get(1).on("click", function() { alert(2) });
						
						// 修改按钮事件
						btns.get(2).on("click", function() {
							// 要访问的 url 地址
							var url = ["/vendor/pnrelation.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=", this.config.get("state")].join("");
							// 打开指定页面
							HBSConvertHelper.openNewWin(url);
						});
					}
					break;
				case "2":
					if(urlPs.roleType == "cgm") {
				  		// 创建按钮到操作列
						var btns = HBSConvertHelper.renderButton2Cell(["审批"], operator_cell, view.ds.getAt(i));
						btns.on("click", function() {
							var url= ["/vendor/detailpnrelation.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=2"].join("");
							HBSConvertHelper.openNewWin(url);
						});
					}
					break;
				case "3":
					if(urlPs.roleType == "cgy") {
						var btns = HBSConvertHelper.renderButton2Cell(["修改","删除"], operator_cell, view.ds.getAt(i));
						btns.get(0).on("click", function() {
							var url= ["/vendor/pnrelation.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=2"].join("");
							HBSConvertHelper.openNewWin(url);
						});
						/*
						btns.get(1).on("click", function() {
							var url= ["/vendor/pnrelation.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=2"].join("");
							HBSConvertHelper.openNewWin(url);
						});
						*/
					}
					break;
			}
			
		}
	})
});