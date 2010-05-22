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
	    ,loadingText   : "正在请求数据"
	    ,minChars      : 3
	    ,queryDelay    : 300
	    ,editable      : true
	    ,hideTrigger   : true
    });

    ExtUx.widget.AutoComplete.superclass.constructor.call(this, config);  
}

Ext.extend(ExtUx.widget.AutoComplete, ExtUx.widget.DictCombo);

Ext.reg("autocomplete", ExtUx.widget.AutoComplete);