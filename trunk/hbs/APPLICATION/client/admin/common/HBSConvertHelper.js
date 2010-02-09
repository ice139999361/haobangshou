


/**
 * getGridSubmitData 获取表格中需要提交的数据
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

























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
	 			
	 			// 装载提交的 fields
	 			_data.push((_paramName + "Fields=" + _grid.getSubmitFields()));
	 		});
	 		
	 		return _data.join("&");
	 }
	 /**
	  * 功能JS的初始化方法
	  * @param readyFun    onReady中要执行的方法
	  * @param initFun     在进入ready中要第一个要执行的方法
	  */
	,init: function(readyFun, initFun, xmpid) {
			app = new ExtUx.widget.Application({"xmpid": xmpid});
			urlPs.openerTabId = this.getOpenerTabId();
			
			app.onReady(function(){
				if(initFun) initFun();
				if(Ext.getCmp("query_btn")) Ext.getCmp("query_btn").fireEvent("click");
				if(readyFun) readyFun();
			});
	 }
	 /**
	  * 返回打开这个页面的 tabId 如果使用的是非 Portal 会返回一个应该回退的历史数
	  */
	,getOpenerTabId: function() {
			// 获取主 Panel
			var mainTab = this.getMainTab();
			
			// Portal 返回父页面的 tabId
			return mainTab.tmpTabId;
	 }
	 /**
	  * 说明：获取主标签的引用
	  */	
	,getMainTab: function() {
			// 返回主的 Panel 模板
			return app.getPortal().contentMgr.mainPanel;
	 }
	 /**
	  * 说明：刷新指定表格
	  * @params gridId (String) 要刷新的表格 id
	  */
	,refreshGrid: function(gridId) {
			if(!Ext.getCmp(gridId).bbar) {
				Ext.getCmp(gridId).store.reload();
				return;
			}
			
			var tmpBtns = Ext.getCmp(gridId).bbar.dom.getElementsByTagName("button");
			tmpBtns[tmpBtns.length-1].click();
	 }
	 /**
	  * 刷新父页面 
	  * @param gridId 要刷新的gridId
	  * @param flag 是否要关闭当前页面  true 关闭  false 不关闭
	  * @param openerTabId 父页面的TabId
	  */
	,refurbishOpenerTab: function(gridId, flag, openerTabId) {
			// 获取主 Panel
			var mainTab = 	this.getMainTab();
			
			// Portal 获取父标签页
			var openerTab = mainTab.getItem(openerTabId || urlPs.openerTabId);
	
			// 如果标签页还未关闭则对其表格进行刷新
			if(openerTab && gridId) openerTab.iframe.dom.contentWindow[this.__name].refreshGrid(gridId);
				
			// 根据需要来关闭当前标签页
			if(flag) this.closeActiveTab();
	 }
	 /**
	  * 关闭当前活动标签的
	  */
	,closeActiveTab: function() {
			// 如果 Portal 框架存在，则关闭当前活动标签
			app.getPortal().contentMgr.closeActiveTab();
	 }
	 /**
	  * 默认的关闭窗口方法
	  */
	,defaultCloseTab: function() {
			ExtConvertHelper.refurbishOpenerTab(urlPs.openerTabId, "querygrid", true);
	 }
	 /**
	  * 获取当前的活动页签
	  */
	,getCurrTab: function() {
			return this.getMainTab().getActiveTab();
	 }
	 /**
	  * 打开新的窗口
	  * @param url  (String) 要访问的页面地址
	  * @param flag (String) 是否要打开新的页签，默认：true
	  */
	,openNewWin: function(url, flag) {
		  // 设置 flag 的值
			flag = flag != false || false;
			// 获取主 Panel
			var mainTab = this.getMainTab();
			// 在 Portal 中将当前页面的Id 存入 mainTab 的 tmpTabId 属性中做为中转
			mainTab.tmpTabId = urlPs.openerTabId || this.getCurrTab().id;
			// 打开页面
			app.switchContent(url, flag);
	 }
	 /**
	  * 返回一个 stirng 格式的 a 标签
	  * @param text (String) 显示给用户的标提
	  * @param url  (String) 单击后要请求的地址
	  * @param type (String) 【可选】，对 url 自动做一些组装
	  *         .open   打开新页签
	  *         .export 导出链接
	  */
	,getATagString: function(text, url, type) {
			switch(type) {
				case "open":
					url = "javascript:" + this.__name + ".openNewWin('" + url + "')";
					break;
				case "export":
					break;
			}
			
			return ExtConvertHelper.getATagString(text, url);
	 }
	 /**
	  * 渲染一个超链接到单元格
	  * @param text 参考 getATagString 方法
	  * @param url  参考 getATagString 方法
	  * @param type 参考 getATagString 方法
	  * @param cell (HTMLElement) 要将链接渲染到的单元格对象
	  */
	,renderATag2Cell: function(text, url, type, cell) {
			// 获取链接的字符串
			var sA = this.getATagString(text, url, type);
			
			// 填充
			cell.firstChild.innerHTML = sA;
			//Ext.DomQuery.select("div:nodeValue(" + (cell.innerText) + ")", cell)[0].innerHTML = sA; // 谷歌居然不支持
	 }
	 /**
	  * 渲染按钮到单元格上
	  * @param btns   (Array)       按钮文本的集合
	  * @param cell   (HTMLElement) td 元素
	  * @param config (Object)      要缓存的数据对象
	  */
	,renderButton2Cell: function(btns, cell, config) {
			// 清空单元格中的 html 代码
			cell.innerHTML = "";
			var tmpItems = new Array;
			var tmpLayout = "table";
			
			Ext.each(btns, function(item, index, itemsAll) {
				if(item.len() > 14) tmpLayout = "form";
				tmpItems.push({xtype: "button", minWidth: 80, text: item, "config": config});
			});
			
			var tmpPanel = new Ext.Panel({
				layout: tmpLayout,
				renderTo: cell,
				items: tmpItems
			});
				
			return btns.length == 1 ? tmpPanel.items.get(0) : tmpPanel.items;
	 }
	 /**
	  * 更改页签的标题
	  * @param title (String) 标题
	  */
	,setDocumentTitle: function(title) {
			document.title = title;
			this.getMainTab().getActiveTab().setTitle(title);
	 }
	 // 属性定义
	,__name: "HBSConvertHelper"
};
