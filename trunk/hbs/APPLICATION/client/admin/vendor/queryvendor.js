// 查询数据的地址
var querygridUrl;

// 初始方法
(function() {
	switch(urlPs.roleType) {
		case "cgy":
			querygridUrl = "/vendorInfo/vendorInfo!list.action";
			break;
		case "cgm":
			querygridUrl = "/vendorInfo/vendorInfoMgr!list.action";
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
		// 修改按钮触发事件
		var updateBtnFun = function() {
			// 要访问的 url 地址
			var url = ["/vendor/editorvendor.jsp?editorType=update&baseSeqId=", this.config.get("baseSeqId"), "&state=", this.config.get("state")].join("");
			// 打开指定页面
			HBSConvertHelper.openNewWin(url);
		};
		
		// 删除按钮触发事件
		var deleteBtnFun = function() {
			Ext.Msg.confirm("提示", "您要执行的是删除操作，请确认是否继续？", function(btn) {
				if(btn == "no") return;
				
				ExtConvertHelper.request("/vendorInfo/vendorInfo!del.action?baseSeqId=" + this.config.get("baseSeqId"), null, function() {
					HBSConvertHelper.refreshGrid("querygrid");
				});
			}, this);
		}
		
		//alert(this.ds.getCount())
		for(var i = 0 ; i < view.ds.getCount() ; i++) {
			// 获取客户简称所在的列
			var shortName_cell = view.getCell(i, view.grid.getColumnIndexById("shortName"));
			// 将需要的链接渲染到此列
			HBSConvertHelper.renderATag2Cell(shortName_cell.innerText, "/vendor/detailvendor.jsp?pageType=query&baseSeqId="+view.ds.getAt(i).get("baseSeqId"), "open", shortName_cell);
			
			// 操作列如果存在
			if(view.grid.getColumnIndexById("operator") != -1) {
				
				// 获取操作列
				var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
				
				// 操作列显示逻辑
				switch(view.ds.getAt(i).get("state")) {
					case "3":
					case "1":
					case "0":
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
	})
});