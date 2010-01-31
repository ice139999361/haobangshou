var HBSConvertHelper = { 
	 /**
	  * 获取表格中需要提交的数据
	  * 会对表格进行一些特殊的处理 （1. 删除空行，2. 校验，3. 获取数据）如果校验不通过则返回 false
	  * @param gridIds    (String)  要获取数据的表格 id  (id 之间用 "," 分隔) 例："grid1,grid2"
	  * @param paramsName (String)  要获取数据的表格参数名设置 (参数名之间用 "," 分隔，与表格 id 顺序对应) 例："paramName1,paramName1"
	  * @param isCheck    (Boolean) 是否需要校验，默认为（true）
	  * @param splitChar  (String)  数据分隔符，默认分隔符为（||;;）
	  */
	 getGridSubmitData: function(gridIds, paramsName, isCheck, splitChar) {
	 		// 对参数进行格式化
	 		gridIds    = gridIds.split(",");
	 		paramsName = paramsName.split(",");
	 		isCheck    = isCheck == false ? false : true;
	 		splitChar  = splitChar || "||;;";
	 		
	 		// 定义存放数据的集合
	 		var _data = [];
	 		
	 		// 获取并手机数据，及业务逻辑处理
	 		Ext.each(gridIds, function(item, index, itemsAll) {
	 			
	 			// 获取 grid
	 			var _grid = Ext.getCmp(item);
	 			// 获取对应的参数名
	 			var _paramName = paramsName[index];
	 			
	 			// 删除表格中所有的空行
	 			_grid.removeEmptyRows();
	 			// 对表格数据进行校验
	 			if(isCheck) if(!_grid.isValid()) return false;
	 			// 获取数据
	 			var tmp = _grid.getAllDatatToFormat(_paramName, splitChar);
	 			
	 			// 装载数据到集合
	 			if(tmp) _data.push(tmp);
	 			
	 		});
	 		
	 		return _data.join("&");
	 }
};
