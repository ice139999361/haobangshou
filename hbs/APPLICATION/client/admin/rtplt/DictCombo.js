Ext.namespace('ExtUx.widget');

ExtUx.widget.DictCombo = function(config){
    // 初始化属性信息
    Ext.applyIf(config, {
	     valueField    : "encodeKey"
	    ,displayField  : "encodeValue"
	    ,triggerAction : "all"
	    ,editable      : false
			,resizable     : true
	    ,invalidText   : "无效的值"
	    ,url           : "/common/encodeDict!list.action"
	    ,root					 : "data.encodeDict"
	    ,paramsName    : "encodeDict.encodeType"
	    ,paramsValue   : ""
	    ,width         : 123
    });

    ExtUx.widget.DictCombo.superclass.constructor.call(this, config);  
}

Ext.extend(ExtUx.widget.DictCombo, Ext.form.ComboBox, {
		initComponent : function(){
			// 定义数据结构
			var RecordType = new Ext.data.Record.create([this.valueField, this.displayField])
			// 创建数据容器
			this.store = new Ext.data.Store({
				proxy: new Ext.data.HttpProxy({url: SERVER_PATH + this.url}),
				reader: new Ext.data.JsonReader({
					root: this.root
				}, RecordType)
			});
			// 设置参数
			for(var i = 0, _paramsName = this.paramsName.split(","), _paramsValue = this.paramsValue.split(","), count = _paramsName.length ; i < count ; i++) {
				this.store.baseParams[_paramsName[i]] = _paramsValue[i];
			}
			
			ExtUx.widget.DictCombo.superclass.initComponent.call(this);
			this.store.load();
		}
});

Ext.reg("dictcombo", ExtUx.widget.DictCombo);