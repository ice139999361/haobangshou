Ext.namespace('ExtUx.widget');

ExtUx.widget.AutoComplete = function(config){
    // 初始化属性信息
    Ext.applyIf(config, {
	     url           : "/common/encodeDict!list.action"
	    ,valueField    : "encodeValue"
	    ,root					 : "data.encodeDict"
	    ,paramsName    : "encodeDict.encodeType"
	    ,paramsValue   : ""
	    ,width         : 123
	    ,loadingText   : "正在请求数据"
	    ,minChars      : 3
	    ,queryDelay    : 300
	    ,editable      : true
    });

    ExtUx.widget.AutoComplete.superclass.constructor.call(this, config);  
}

Ext.extend(ExtUx.widget.AutoComplete, ExtUx.widget.DictCombo);

Ext.reg("autocomplete", ExtUx.widget.AutoComplete);