Ext.namespace('ExtUx.widget');

ExtUx.widget.AutoComplete = function(config){
    // 初始化属性信息
    Ext.applyIf(config, {
	     url           : "/common/encodeDict!list1.action"
	    ,valueField    : "value"
	    ,displayField  : "value"
	    ,root					 : "data.list"
	    ,paramsName    : ""
	    ,paramsValue   : ""
	    ,width         : 123
	    ,loadingText   : "正在请求数据"
	    ,minChars      : 3
	    ,queryDelay    : 300
	    ,editable      : true
	    ,hideTrigger   : true
    });

    ExtUx.widget.AutoComplete.superclass.constructor.call(this, config);  
    
    
    /*
    this.addEvents("aabbccddee");
    
    this.on("select", function() {
    	ExtConvertHelper.request("/success.action", null, function(response, options) {
    		
    	}, null, this);
    });
    */
}

Ext.extend(ExtUx.widget.AutoComplete, ExtUx.widget.DictCombo, {
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
		
		
		this.on("select", _process);
		new Ext.KeyNav(this.getEl(), { enter: _process, scope: this });
	}
});

Ext.reg("autocomplete", ExtUx.widget.AutoComplete);