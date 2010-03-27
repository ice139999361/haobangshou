HBSConvertHelper.init(function() {
	// -------------------------------------- 获取需要持久用到的对象
	
	// 获取表格
	var querygrid = Ext.getCmp("querygrid");
	
	
	
	// -------------------------------------- 应用逻辑处理
	
	querygrid.getView().on("refresh", function(view) {
		
		//alert(this.ds.getCount())
		for(var i = 0 ; i < view.ds.getCount() ; i++) {
	
		}
		
	})
});