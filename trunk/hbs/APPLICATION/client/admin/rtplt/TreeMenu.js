Ext.namespace('ExtUx.widget');
/**
 * ExtUx.widget.TreeMenu Extension Class
 * 树形样式的菜单，支持多级菜单显示，数据来源于服务器端返回的JSON格式数据，菜单点击后的动作需要由用户自行实现回调函数。
 */

ExtUx.widget.TreeMenu = function(config) {
	this.loader = new ExtUx.widget.MenuTreeLoader(config.menutreeloader);
	ExtUx.widget.TreeMenu.superclass.constructor.call(this, config);
	this.addEvents("menuclick");

};

Ext.extend(ExtUx.widget.TreeMenu, Ext.tree.TreePanel, {
	// 是否预先加载子节点
	preloadChildren : true,
	// 是否允许拖拽菜单
	enableDD : false,
	// 是否根节点可见
	rootVisible : true,
	// 是否允许自动出现滚动条
	autoScroll : true,
	// 是否折叠根节点
	collapseFirst : false,
	// 初始化方法
	initComponent : function() {
		ExtUx.widget.TreeMenu.superclass.initComponent.call(this);
		this.on("click", this.handleOnClick);
	},
	// 点击菜单后的回调方法
	setHandler : function(f) {
		//this.un("click", this.menuHandler);
		this.menuHandler = f;
		this.on("menuclick", f);//;)this.menuHandler);//this.attributes );
	},
	// 菜单点击事件
	handleOnClick : function(node,e){
		// 停止事件传递
		e.stopEvent();
		// 如果是叶节点，触发自定义方法
		if(node.leaf) this.fireEvent("menuclick", node.attributes,e);
	},
	// 定位到指定的菜单, 完整的菜单路径，使用各个菜单节点的id，以/作为分隔符，如/menu1/menu11/menu112
	locate : function(path) {
		this.expendPath(path);
	},
	// 重新刷新菜单
	refresh : function() {
		this.getRootNode().reload();
	}
});
Ext.reg('treemenu', ExtUx.widget.TreeMenu);