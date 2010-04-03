var querygridUrl;

// 初始方法
(function() {
	switch(urlPs.roleType) {
		case "sccustomers":
			querygridUrl = "/customerInfo/custPartNoInfo!list.action";
			break;
		case "scmanager":
			querygridUrl = "/customerInfo/custPartNoInfoMgr!list.action";
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
			var url = ["/customer/pnrelation.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=", this.config.get("state")].join("");
			// 打开指定页面
			HBSConvertHelper.openNewWin(url);
		};
		
		// 删除按钮触发事件
		var deleteBtnFun = function() {
			Ext.Msg.confirm("提示", "您要执行的是删除操作，请确认是否继续？", function(btn) {
				if(btn == "no") return;
				
				ExtConvertHelper.request("/vendorInfo/vendorInfo!del.action?seqId=" + this.config.get("seqId"), null, function() {
					HBSConvertHelper.refreshGrid("querygrid");
				});
			}, this);
		};
		
		// 恢复按钮触发事件
		var recoverBtnFun = function() {
			Ext.Msg.confirm("提示", "您要执行的是恢复操作，请确认是否继续？", function(btn) {
				if(btn == "no") return;
				
				ExtConvertHelper.request("/vendorInfo/vendorInfo!recover.action?seqId=" + this.config.get("seqId"), null, function() {
					HBSConvertHelper.refreshGrid("querygrid");
				});
			}, this);
		};
		
		for(var i = 0 ; i < view.ds.getCount() ; i++) {
			// 获取客户简称所在的列
			var commCode_cell = view.getCell(i, view.grid.getColumnIndexById("commCode"));
			// 将需要的链接渲染到此列
			HBSConvertHelper.renderATag2Cell(commCode_cell.innerText, "/customer/detailpnrelation.jsp?pageType=query&seqId="+view.ds.getAt(i).get("seqId"), "open", commCode_cell);
		
		
			// 获取客户简称所在的列
			var custPartNo_cell = view.getCell(i, view.grid.getColumnIndexById("custPartNo"));
			// 获取操作列
			var operator_cell  = view.getCell(i, view.grid.getColumnIndexById("operator"));
			// 将需要的链接渲染到此列
			HBSConvertHelper.renderATag2Cell(custPartNo_cell.innerText, "/customer/detailpnrelation.jsp?pageType=query&seqId="+view.ds.getAt(i).get("seqId"), "open", custPartNo_cell);
			
			// 操作列显示逻辑
			switch(view.ds.getAt(i).get("state")) {
				case "1":
				case "0":
					if(urlPs.roleType == "sccustomers") {
					  // 创建按钮到操作列
						var btns = HBSConvertHelper.renderButton2Cell(["删除", "恢复", "修改"], operator_cell, view.ds.getAt(i));
	
						// 删除按钮事件
						btns.get(0).on("click", deleteBtnFun);
						// 恢复按钮事件
						btns.get(1).on("click", recoverBtnFun);
						// 修改按钮事件
						btns.get(2).on("click", updateBtnFun);
					}
					break;
				case "2":
					if(urlPs.roleType == "scmanager") {
				  		// 创建按钮到操作列
						var btns = HBSConvertHelper.renderButton2Cell(["审批"], operator_cell, view.ds.getAt(i));
						btns.on("click", function() {
							var url= ["/customer/detailpnrelation.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=2"].join("");
							HBSConvertHelper.openNewWin(url);
						});
					}
					break;
				case "3":
					if(urlPs.roleType == "sccustomers") {
						var btns = HBSConvertHelper.renderButton2Cell(["删除", "修改"], operator_cell, view.ds.getAt(i));
						// 删除按钮事件
						btns.get(0).on("click", deleteBtnFun);
						// 修改按钮事件
						btns.get(1).on("click", updateBtnFun);
					}
					break;
			}
			
		}
	})
});