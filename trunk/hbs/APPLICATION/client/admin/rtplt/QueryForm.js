Ext.namespace('ExtUx.widget');

ExtUx.widget.QueryForm = function(config) {
	// 处理 config 信息
	ColumnPanelHelper.processConfig(config);
	ExtUx.widget.QueryForm.superclass.constructor.call(this, config);
};

Ext.extend(ExtUx.widget.QueryForm, Ext.form.FormPanel, {
	initComponent : function(){
		ExtUx.widget.QueryForm.superclass.initComponent.call(this);
		urlPs.__exportFormId = this.id;
		Ext.getCmp("query_btn").form = this;
		Ext.getCmp("clean_btn").form = this;
	},
	buttons: [
		 {
		 		 id: "query_btn"
		 		,text: "查询"
		 		,listeners: {
		 				click: function() {
		 					// 如果 BaseForm 的 Ext Element 对象不存在
		 					if(!this.form.getForm().getEl()) return;
		 					// 获取关联表格的 id
			 				var _gridId = this.form.gridId;
			 				// 如果没有进行关联则跳出
			 				if(!_gridId) return;
			 				// 获取关联的表格
			 				var _grid = Ext.getCmp(_gridId);
			 				// 获取 grid 中的 store
			 				var _store = _grid.store;
			 				
			 				// 用于存放查询的参数
			 				var params = this.form.getForm().getValues();
			 				// 组装分页信息参数
			 				if(_grid.getBottomToolbar() && _grid.getBottomToolbar().getPageParams) Ext.apply(params, _grid.getBottomToolbar().getPageParams());
			 				// 设置查询的基本条件
			 				_store.baseParams = params;
			 				// 更改表格状态为刷新状态
			 				_grid.refreshState = 'refresh';
			 				// 加载数据
			 				_store.load();
			 				// 备份当前参数
			 				this.form.currParams = params;
		 				}
		 		 }
		 }
		,{
				 id: "clean_btn"
				,text: "清除"
				,handler: function() {
						// 获取关联表格的 id
						this.form.getForm().reset();
				 }
		 }
	],
	title         : "查询",
	style         : "margin:5px 0px 0px 5px",
	frame         : true,
	collapsible   : true,
	titleCollapse : true,
	autoHeight		: true
});
Ext.reg('queryform', ExtUx.widget.QueryForm);
