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
		// 修改按钮触发事件
		var updateBtnFun = function() {				
			// 要访问的 url 地址
			var url = ["/vendor/pnrelation.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=", this.config.get("state")].join("");
			// 打开指定页面
			HBSConvertHelper.openNewWin(url);
		};
		
		// 删除按钮触发事件
		var deleteBtnFun = function() {
			Ext.Msg.confirm("提示", "您要执行的是删除操作，请确认是否继续？", function(btn) {
				if(btn == "no") return;
				
				ExtConvertHelper.request("/vendorInfo/vendorPartNoInfo!delete.action?vendorPartNoInfo.seqId=" + this.config.get("seqId") + "&vendorPartNoInfo.state="+ this.config.get("state")+"&vendorPartNoInfo.partNo="+ this.config.get("partNo")+"&vendorPartNoInfo.custPartNo="+ this.config.get("custPartNo")+"&vendorPartNoInfo.commCode="+ this.config.get("commCode"), null, ExtConvertHelper.defaultDeleteFun);
			}, this);
		};
		
		for(var i = 0 ; i < view.ds.getCount() ; i++) {
			// 获取客户简称所在的列
			var commCode_cell = view.getCell(i, view.grid.getColumnIndexById("commCode"));
			// 将需要的链接渲染到此列
			HBSConvertHelper.renderATag2Cell(commCode_cell.innerText, "/vendor/detailpnrelation.jsp?pageType=query&seqId="+view.ds.getAt(i).get("seqId"), "open", commCode_cell);
			
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
				case "3":
					if(urlPs.roleType == "cgy") {
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["删除", "修改"], operator_cell, view.ds.getAt(i));
						// 删除按钮事件
						operatorBtn.get(0).on("click", deleteBtnFun);
						// 修改按钮事件
						operatorBtn.get(1).on("click", updateBtnFun);
					}
					break;
				case "2":
					if(urlPs.roleType == "cgm") {
				  		// 创建按钮到操作列
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["审批"], operator_cell, view.ds.getAt(i));
						operatorBtn.on("click", function() {
							var url= ["/vendor/detailpnrelation.jsp?editorType=update&seqId=", this.config.get("seqId"), "&state=2"].join("");
							HBSConvertHelper.openNewWin(url);
						});
					}
					if(urlPs.roleType == "cgy") {
						var operatorBtn = HBSConvertHelper.renderButton2Cell(["删除"], operator_cell, view.ds.getAt(i));
						// 删除按钮事件
						operatorBtn.on("click", deleteBtnFun);
						// 修改按钮事件
						operatorBtn.get(1).on("click", updateBtnFun);
					}
					break;
			}
			
		}
	})
});