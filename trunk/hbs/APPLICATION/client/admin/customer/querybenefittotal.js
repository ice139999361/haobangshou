

HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取表格
	var querygrid = Ext.getCmp("querygrid");
	
	
	// -------------------------------------- 应用逻辑处理
	
	function getParams(record) {
			return [
							// 客户编码
						 'custCode=', record.get("custCode")
						  // 摘要
						 ,'&sendMonth=', record.get("sendMonth")
						  // 销售人员
						 ,'&salesId=', record.get("salesId")
						 ,'&custPartNo=', record.get("custPartNo")
						].join('');
	}
	


	(function() {
		querygrid.getView().on("refresh", function(view) {
			
			for(var i = 0 ; i < view.ds.getCount() ; i++) {
			
				// 获取数据容器
				var record = view.ds.getAt(i);
				
				// 获取编码所在的列
				var commCode_cell = view.getCell(i, view.grid.getColumnIndexById("custCode"));
				
				// 创建url
				var _url = ['/customer/detailbenefit.jsp?', getParams(record)].join('');

				// 将需要的链接渲染到此列
				HBSConvertHelper.renderATag2Cell(commCode_cell.innerText, _url, "open", commCode_cell);				
				
			}
		
		});
		
		}())
	
	
});