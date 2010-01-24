Ext.namespace('ExtUx.widget');

/**
 * ExtUx.widget.MenuTreeLoader
 * 提供从某个url加载某个节点的子节点功能，扩展了对 JSON 数据的解析方法
 */
ExtUx.widget.MenuTreeLoader = function(config) {
	ExtUx.widget.MenuTreeLoader.superclass.constructor.call(this, config);

};

// extend
Ext.extend(ExtUx.widget.MenuTreeLoader, Ext.tree.TreeLoader, {
	// 是否显示自定义icon
	displayIcon : false,
	// 处理返回的json数据，并完全加载第一个子节点的数据
	processResponse : function(response, node, callback) {
		// 获取服务器返回的菜单数据
		var data = Ext.util.JSON.decode(response.responseText).data;
		// 装载创建的 TreeNode 节点
		var nodes = [];
		Ext.each(data, function(item, index, itemsAll) {
			nodes.push(this.createNode(item));
		}, this);
		
		// 添加节点集合到根节点
		node.beginUpdate();
		node.appendChild(nodes);
		node.endUpdate();
		// 调用回调函数
		if(callback)callback(this, node); 
		//for(var i = 0, len = o.length; i < len; i++) nodes.push(this.createNode(o[i]));
	},
	
	// 创建所需的节点
  createNode : function(attr){
  		// 如果 baseAttrs 存在则复制 attr 中属性到 baseAttrs 中，如果 baseAttrs 中已存在对应属性，则不覆盖
      if(this.baseAttrs) Ext.applyIf(attr, this.baseAttrs);
      // 如果不需要自定义图片
      if(!this.displayIcon && attr.icon) delete attr.icon;
     	
     	// 返回创建成创建到节点
      return attr.leaf ? new Ext.tree.TreeNode(attr) : new Ext.tree.AsyncTreeNode(attr);
  }
});