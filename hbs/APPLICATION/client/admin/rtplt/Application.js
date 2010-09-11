Ext.namespace("ExtUx.widget");

/**
 * config参数包括
 *  xmpid:配置xmp的节点id.【可选】缺省为"config"
 *  resKey:当前页面的resource key.【可选】如果当前页面有鉴权的组件，并且省略了resKey。这里必填  
 *  permProxyUrl:组件鉴权的url【可选】,如果不填，使用portal中的值
 */
ExtUx.widget.Application = function(config){
	// 如果 config 对象不存在
	if(!config) config = {};
	// 获取 xmp 控件的 id
	var xmpid = config.xmpid || "config";
	// 获取 xmp 控件
	var xmpDom = Ext.getDom(xmpid);
	// 删除不需要的节点
	document.body.removeChild(xmpDom);
	
	// -
	
	// 获取通过 XMP 标签中文本转换得到的 JSON 对象
	this.extUiJson = this.__getExtUIJson__(xmpDom);
	

	// -
	this.addEvents({
		'ready' : true
  });
  // 设置为未渲染
  this.isReady = false;
	// 渲染页面
	Ext.onReady(this.__initReady__, this);
}
Ext.extend(ExtUx.widget.Application, Ext.util.Observable, {
		 __getExtUIJson__: function(xmpDom) {
			  // 获取 XML DOM 字符串
			  var domStr = xmpDom.innerHTML;
			  
			  // 创建 XML DOM 解析器
			  var xmlDom = new XMLDOMParser();
			  // 加载 XML DOM 字符串
				xmlDom.loadXML(domStr);
				
				// 转换 XML DOM 字符串为 XML DOM 对象
				var json = Ice.util.JSON.parseDOM2JSON(xmlDom, function(node, json) {
					// 如果不是元素节点则跳出
					if(node.nodeType != Node.ELEMENT_NODE) return;
					
					// 如果不存在 xtype 则加入至 json 对象中
					if(!node.getAttribute("xtype")) json.xtype = node.nodeName;
				});
				
				// 返回通过 XML DOM 转换得到的 Ext UI JSON
				return ExtConvertHelper.convertDOMJSON(json);
		 }
    //private
    ,getPortal:function(){
        for(var _parent = window ; ; _parent = _parent.parent) {
        	//alert(_parent)
        	if(_parent.portal || _parent.top == _parent.self) return _parent.portal;
        }
    },
    switchContent:function(src,createNew,tabTip){
    	var pt = this.getPortal();
      if(pt != undefined) pt.contentMgr.switchContent(src,createNew,tabTip);
    },
    navigate:function(result){
        var pt=this.getPortal();
        if(pt!=undefined){
            pt.navMgr.navigate(SERVLET_PATH,result);
        }
    },
    /**
     * 在创建此对象时的初始渲染方法
     */
    __initReady__ : function(){
       //初始化QuickTip
       Ext.QuickTips.init();
       
       // 获取 JSON 数据中的 application 节点
       var alt = this.extUiJson.application;
       
       // 如果 viewport 对象存在则创建
       if(alt && alt.viewport) new Ext.Viewport(alt.viewport);
       
       // 如果 window 对象存在则创建
       if(alt && alt.window) {
       	 // 获取 window 对象, 并将其封装为数组
       	 var __tmp__ = (alt.window instanceof Array) ? alt.window : [alt.window];
       	 
       	 // 创建所有的 window 对象
       	 for(var i = 0 ; i < __tmp__.length ; i++) {
       	 		new Ext.Window(__tmp__[i]);
       	 }
       }
       
       // 触发自定义 ready 事件
       this.fireEvent('ready', this);
       // 将状态更改为已执行
       this.isReady = true;
    }
    /**
     * 渲染方法
     */
   ,onReady : function(fn, scope){
   		 if(this.isReady) fn.apply(scope || this);
   		 else this.on('ready', fn, scope);
    }
});