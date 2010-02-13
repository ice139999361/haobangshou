Ext.namespace('ExtUx.widget');

ExtUx.widget.ComboBoxTree = function(config) {
	if(!config.tree) this._createTree(config);
	
	ExtUx.widget.ComboBoxTree.superclass.constructor.call(this, config);
}

Ext.extend(ExtUx.widget.ComboBoxTree, Ext.form.ComboBox, {

passName : 'id', 

allowUnLeafClick : false, 

// tpl: '<div id="treeTpl"></div>', //html代码 

treeHeight : 180, 

_createTree: function(config) {
	config.tree = new ExtUx.widget.TreeMenu({
		 id: config.id + "_tree"
		,collapseMode: "mini"
		,rootVisible: false
		,menutreeloader: {
				 displayIcon: false
				,dataUrl: SERVER_PATH + config.dataUrl
				,preloadChildren: false
				,root: config.root
		 }
		,root: {
				 xtype: "asynctreenode"
				,expanded: true
				,singleClickExpand: true
		 }
	});
	
	// 删除没用的属性
	ExtConvertHelper.deletePro("dataUrl,root", config);
},

store : new Ext.data.SimpleStore({ 
	fields : [], 
	data : [[]] 
}), 

//Default 
editable : false, // 禁止手写及联想功能 
mode : 'local', 
triggerAction : 'all', 
maxHeight : 500, 
selectedClass : '', 
onSelect : Ext.emptyFn, 
emptyText : '请选择...', 


clearValue : function() { 
	if (this.passField) this.passField.value = '';
	this.setRawValue(''); 
}, 


setPassValue: function(passvalue){ 
	if (this.passField) this.passField.value = passvalue;
	var node = this.tree.getNodeById(passvalue);
	this.setValue(node.text);
	// 选中树节点后的触发事件 
	this.fireEvent('treeselected', node); 
},
getPassValue: function() {
	return this.passField.value;
},

onTreeSelected : function(node) { 

}, 


treeClk : function(node, e) { 
	if (!node.isLeaf() && !this.allowUnLeafClick) { 
		e.stopEvent();// 非叶子节点则不触发 
		return; 
	} 
	
	this.setValue(node.text);// 设置option值
	this.collapse();// 隐藏option列表 
	
	if (this.passField) this.passField.value = node.id;// 以树的节点ID传递 
	
	// 选中树节点后的触发事件 
	this.fireEvent('treeselected', node); 
}, 


initComponent : function() { 
	ExtUx.widget.ComboBoxTree.superclass.initComponent.call(this); 
	/*
	this.tree.on('load', function(node, checked) {
		node.expand(); 
		node.eachChild(function(child) {
			child.expand(); 
		}); 
	});
	*/
	this.tree.autoScroll = true; 
	this.tree.height = this.treeHeight; 
	this.tree.containerScroll = false; 
	this.tplId = Ext.id(); 
	// overflow:auto" 
	this.tpl = '<div id="' + this.tplId + '" style="' + this.treeHeight 
	+ '";overflow:hidden;"></div>'; 
	
	
	this.addEvents('treeselected'); 
	// this.on('treeselected',this.onTreeSelected,this); 
}, 


listeners : { 
	'expand' : { 
		fn : function() { 
			if (!this.tree.rendered && this.tplId) this.tree.render(this.tplId); 
			this.tree.show(); 
		}, 
		single : true 
	}, 
	'render' : { 
		fn : function() { 
			this.tree.on('click', this.treeClk, this); 
			
			if (this.passName) { 
				this.passField = this.getEl().insertSibling({ 
					tag : 'input', 
					type : 'hidden', 
					name : this.passName, 
					id : this.passId || Ext.id() 
				}, 'before', true) 
			} 
			
			this.passField.value = this.passValue !== undefined 
			? this.passValue 
			: (this.value !== undefined ? this.value : ''); 
			
			this.el.dom.removeAttribute('name'); 
		} 
	}, 
	'beforedestroy' : { 
		fn : function(cmp) { 
			this.purgeListeners(); 
			this.tree.purgeListeners(); 
		} 
	} 
} 
}); 

Ext.reg('combotree', ExtUx.widget.ComboBoxTree);