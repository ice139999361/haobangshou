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
	    ,record        : null
	    ,width         : 123
    });

    ExtUx.widget.DictCombo.superclass.constructor.call(this, config);  
}

Ext.extend(ExtUx.widget.DictCombo, Ext.form.ComboBox, {
		initComponent : function(){
			// 定义数据结构
			var RecordType = new Ext.data.Record.create((this.record ? this.record.split(",") : [this.valueField, this.displayField]));
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
		},
	setParam : function(key, value){
		this.store.baseParams[key] = value;
	},
	setProcessConfig: function(url, paramName, params, processFun) {
		this.__processConfig = {
			 "url"        : url
			,"paramName"  : paramName || "query"
			,"params"     : params
			,"processFun" : processFun
		};
		
		var _process = function() {
			// 如果是用回车键,会处发两次
			if(this.triggerFlag) return;
			// 事件开始触发
			this.triggerFlag = true;
			// 获取处理配置
			var __processConfig = this. __processConfig;
			// 设置访问的 url
			var _url = __processConfig.url + (__processConfig.url.indexOf("?") == -1 ? "?" : "&") + __processConfig.paramName + "=" + (this.getValue() || this.getEl().dom.value);
			
			ExtConvertHelper.request(_url, __processConfig.params, function(response, options) {
				var action = Ext.util.JSON.decode(response.responseText);
				// 调用应用的处理方法
				this.__processConfig.processFun.call(this, action, response, options);
				
				// 事件结束触发
    		this.triggerFlag = false;
    	}, null, this);
		}
		
		try{
			this.on("select", _process);
			new Ext.KeyNav(this.getEl(), { enter: _process, scope: this });
		} catch(e) {}
	}
});

Ext.reg("dictcombo", ExtUx.widget.DictCombo);