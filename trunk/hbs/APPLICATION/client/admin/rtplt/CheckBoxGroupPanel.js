Ext.namespace('ExtUx.widget');

ExtUx.widget.CheckBoxGroupPanel = function(config) {
	// 处理 config 信息
	ColumnPanelHelper.processConfig(config);
	
	ExtUx.widget.CheckBoxGroupPanel.superclass.constructor.call(this, config);
};

Ext.extend(ExtUx.widget.CheckBoxGroupPanel, Ext.Panel, {
	// 创建一个新的 fieldset 控件
	createFieldset: function(title, config) {
		// 创建一个空对象
		if(!config) config = { xtype: "fieldset", autoHeight: true, layout: "form", collapsible: true, items: [] };
		// 加载 title 属性
		if(title) config.title = title;
		
		// 返回 fieldset JSON Object
		return config;
	},
	// 创建一个新的 checkboxgroup 控件
	createCheckboxGroup: function(title, childName, config) {
		// 创建一个空对象
		if(!config) config = { xtype: "checkboxgroup", columns: 9, items: [] };
		// 加载 title 属性
		if(title) config.fieldLabel = title;
		// 加载 childName 属性
		if(childName) config.childName = childName;
		
		// 返回 checkboxgroup JSON Object
		return config;
	},
	// 创建一个新的 checkbox 控件
	createCheckbox: function(boxLabel, name, value, config) {
		// 创建一个空对象
		if(!config) config = { };
		// 加载 boxLabel 属性
		if(boxLabel) config.boxLabel = boxLabel;
		// 加载 name 属性
		if(name) config.name = name;
		// 加载 value 属性
		if(value)
		{
			config.value = value;
			config.inputValue = value;
		}
		// 返回 checkbox JSON Object
		return config;
	},
	initComponent : function(){
		// 创建容器对象
		var items  = [];
		// 获取初始化数据
		var action = ExtConvertHelper.syncRequest(this.url);
		// 定义全选按钮的单击方法
		var allCheckboxClick = function() {
			// 获取所有此控件关联的复选框
			var els = document.getElementsByName(this.childName);
			
			// 全选或全不选
			Ext.each(els, function(item, index, itemsAll) {
				item.checked = this.checked;
			}, this);
		}
		
		// 组装需要的 items 数据
		Ext.each(action.data.list, function(item, index, itemsAll) {
			// 创建一个 fieldset
			var fld  = this.createFieldset(item.title);
			
			// 添加子集合
			Ext.each(item.list, function(item, index, itemsAll) {
				// 创建一个 checkboxgroup
				var cbg = this.createCheckboxGroup(item.title, item.name);
				// 添加一个全选按钮
				cbg.items.push({boxLabel: "全选", childName: item.name, handler:  allCheckboxClick});
				
				// 添加子集合
				Ext.each(item.list, function(item, index, itemsAll) {
					// 创建一个 checkbox
					var cb = this.createCheckbox(item.title, cbg.childName, item.value);
					
					// 增加 checkbox 至 checkboxgroup
					cbg.items.push(cb);
				}, this);
				
				// 增加 checkboxgroup 至 fieldset
				fld.items.push(cbg);
			}, this);
			
			// 增加 fieldset 至 items
			items.push(fld);
		}, this);
		
		this.items = items;
		
		//alert(Ext.util.JSON.encode(items))
		/*
		this.items = [{
        xtype: 'fieldset',
        title: '系统管理员',
        autoHeight: true,
        layout: 'form',
        //collapsed: true,   // initially collapse the group
        collapsible: true,
        items: [{
            xtype: 'checkboxgroup',
            collapsible: false,
            fieldLabel: '本公司物料录入',
            columns: 9,
            items: [
                {boxLabel: '提交', name: 'cb-auto-1'},
                {boxLabel: '保存', name: 'cb-auto-1'}
            ]
        }, {
            xtype: 'checkboxgroup',
            fieldLabel: '角色管理',
            columns: 9,
            items: [
                {boxLabel: '创建角色', name: 'cb-auto-2'},
                {boxLabel: '修改角色', name: 'cb-auto-2'},
                {boxLabel: '删除角色', name: 'cb-auto-2'}
            ]
        }]
    },{
        xtype: 'fieldset',
        title: '系统管理',
        autoHeight: true,
        layout: 'form',
        //collapsed: true,   // initially collapse the group
        collapsible: true,
        items: [{
            xtype: 'checkboxgroup',
            fieldLabel: '用户管理',
            columns: 9,
            items: [
                {boxLabel: '创建用户', name: 'cb-auto-1'},
                {boxLabel: '修改用户', name: 'cb-auto-1'},
                {boxLabel: '删除用户', name: 'cb-auto-1'}
            ]
        }, {
            xtype: 'checkboxgroup',
            fieldLabel: '角色管理',
            columns: 9,
            items: [
                {boxLabel: '创建角色', name: 'cb-auto-2'},
                {boxLabel: '修改角色', name: 'cb-auto-2'},
                {boxLabel: '删除角色', name: 'cb-auto-2'}
            ]
        }]
    }]
		*/
		ExtUx.widget.CheckBoxGroupPanel.superclass.initComponent.call(this);
	},
	style         : "margin:5px 0px 0px 5px",
	autoHeight		: true,
	frame         : true,
	collapsible   : true,
	titleCollapse : true
});
Ext.reg('checkboxgrouppanel', ExtUx.widget.CheckBoxGroupPanel);
