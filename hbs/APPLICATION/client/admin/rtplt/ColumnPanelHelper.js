ColumnPanelHelper = function() {
	 // 以下属性都属于私有
	 layoutpanel: null
}

ColumnPanelHelper.prototype = {
	 createLayoutPanel: function(columnNum) {
			// 如果 layoutpanel 属性未初始化
			if(!this.layoutpanel) this.layoutpanel = [];
			// 如果 columnNum 成立则认为需要自动处理
			if(columnNum) {
				// 创建需要的 layoutpanel 对象
				var lp = {"columnNum": columnNum, "__children": []};
				// 将对象加入集合中
				this.layoutpanel.push(lp);
				
				// 返回需要的对象
				return lp.__children;
			}
	 }
	,processItemsFunConfig: function(config) {
			// 如果 layoutpanel 对象不存在
			if(!this.layoutpanel) return;
			// 如果 config.layoutpanel 对象不存在，则创建
			if(!config.layoutpanel) config.layoutpanel = [];
			// 防止 config.layoutpanel 不是一个 Array 对象
			config.layoutpanel = ExtConvertHelper.convertObj2Array(config.layoutpanel);
			// 组装
			config.layoutpanel = config.layoutpanel.concat(this.layoutpanel);
	 }
};

Ext.apply(ColumnPanelHelper, {
	 processConfig: function(config) {
	 		// 处理扩展控件部分
			this.__processItemsFun__(config);
	 		// 处理布局管理
	 		this.__processLayoutPanel__(config);
	 	
	 		// 返回对象本身
	 		return this;
	 }
	,__processItemsFun__: function(config) {
			// 如果没有扩展
			if(!config.itemsFun) return;
			// 获取方法对象
			var itemsFun = eval(config.itemsFun);
			// 删除没用的属性
			delete config.itemsFun;
			// 调用扩展方法，获取 ComplexGridHelper 对象
			var cph = itemsFun.call(this);
			
			// 处理 config 对象，加入扩展属性
			cph.processItemsFunConfig(config);
	 }
	,__processLayoutPanel__: function(config) {
			// 如果 layoutpanel 只存在一个，则可能是对象形式存在，在此转换为数组统一处理
			var lps = ExtConvertHelper.convertObj2Array(config.layoutpanel);
			// layoutpanel 对象不存在不做任何处理
			if(!lps) return;
			
			// 创建 items 对象
			var items = [];
			
			Ext.each(lps, function(lp, index, lpsAll) {
				// 创建一个 column 布局的 panel
				Ext.apply(lp, { xtype: "panel", layout: "column", items: [] });

				// 如果 columnNum 属性存在，则表示需要动态分配
				if(lp.columnNum) this.__processAutoLayoutPanel__(lp);
				// 手动分配
				else this.__processHPLayoutPanel__(lp);
					
				// 删除不需要的属性
				ExtConvertHelper.deletePro("_columnCount,__children,columnNum", lp);
				// 将 panel 加入 items 集合中
				items.push(lp);
			}, this);
			
			config.items = items;
	 }
	,__processAutoLayoutPanel__: function(lp) {
			// 获取该 panel 的列宽对象
			var cwObj = this.__getColumnsWidth__(lp.columnNum);
			
			// 创建需要的 panel 对象
			for(var i = 0 ; i < cwObj.columnCount ; i++) {
				// 创建最底层的 panel 对象
				var _p = this.__createItemPanel__(this.__getColumnWidth__(cwObj, i), this.__getCmpItems__(lp, cwObj.columnCount));
				// 加入 lp 的 items 属性中
				lp.items.push(_p);
			}
	 }
	,__processHPLayoutPanel__: function(lp) {
	 }
	,__createItemPanel__: function(cw, cmps) {
			return {
				 xtype  : "panel"
				,layout : "form"
				,columnWidth : cw
				,items  : cmps
			};
	 }
	,__getCmpItems__: function(lp, columnCount) {
			// 获取剩余列的数量
			columnCount = lp._columnCount || columnCount;
			// 获取需要分配的控件
			var cmps = lp.__children;
			// 获取控件的数量
			var cmpCount = cmps.length;
			// 获取本次要获取的数量
			var currCount = Math.floor(cmpCount/columnCount);
			// 获取本次要返回的控件组
			var currCmps = cmps.splice(0, (cmpCount%columnCount ? currCount + 1 : currCount));
			// 重置剩余的列数
			lp._columnCount = columnCount - 1;
			
			// 返回需要的控件组
			return currCmps;
	 }
	,__getColumnWidth__: function(cwObj, colIndex) {
			// 如果不存在 columnsNum 属性，则返回公用宽度
			if(!cwObj.columnsNum) return cwObj.comWidth;
			
			// 如果当前列宽度属性为空，则返回公用宽度
			return cwObj.columnsNum[colIndex] || cwObj.comWidth;
	 }
	,__getColumnsWidth__: function(columnNum) {
			// 如果只有设置列的数量
			if(!isNaN(columnNum)) return { "comWidth": 1.0/columnNum, "columnCount": +columnNum };
			
			// 列的数量
			var columnCount = parseInt(columnNum);
			// 获取每列的宽度
			var columnsNum = columnNum.slice(columnNum.indexOf(":") + 1).split(",");
			
			// 统计没有分配列宽的列数
			var emptyCount = 0;
			// 已经使用掉的宽度
			var useWidth = 0;
			// 计算 emptyCount 和 useWidth
			for(var i = 0 ; i < columnCount ; i++) {
				// 如果列有设置宽度则统计已使用列宽
				if(columnsNum[i]) {
					columnsNum[i] = +columnsNum[i];
					useWidth += columnsNum[i];
				}
				// 如果没有值则认为需要自动计算
				else emptyCount++;
			}
			
			// 返回每列所占的宽度对象
			return { "comWidth": (1 - useWidth)/emptyCount, "columnsNum": columnsNum, "columnCount": +columnCount }
	 }
});