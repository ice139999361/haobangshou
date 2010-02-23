// 好帮手引用框架作用域
var HBS = Ice;

var ExtConvertHelper = { 
	 /**
	  * 获取 location.href 中所传递过来的参数
	  * 例：xxx.jsp?id=5&name=aaa  |  var urlPs = DisnHelper.getUrlPs()  urlPs.id  urlPs.name
	  */
	 getUrlPs: function() {
			if(!this.__urlPs) {
				this.__urlPs = Ext.urlDecode((location.search || "?").slice(1));
			}
			
			return this.__urlPs;
	 }
	,submitForm: function(formId, url, params, success, failure) {
		  formId = formId || this.getHiddenForm().id;
			Ext.getCmp(formId).getForm().submit({
				url: SERVER_PATH + url,
				params: this._processParams(params),
			  waitTitle: '提示',
			  clientValidation: false,
				waitMsg: '请等待：正在提交请求',
				success: success ? success : function(){},
				failure: failure ? failure : function(form, action){
																			 var message = ExtConvertHelper.getMessageInfo(action, "请求失败：服务器异常");
																			 Ext.Msg.alert("提示", message);
																		 }
			});
	 }
	,loadForm: function(formId, url, params, success, failure) {

			Ext.getCmp(formId).getForm().load({
				url: SERVER_PATH + url,
				params: this._processParams(params),
			  waitTitle: '提示',
			  clientValidation: false,
				waitMsg: '请等待：正在提交请求',
				success: success ? success : function(){},
				failure: failure ? failure : function(form, action){
																			 var message = ExtConvertHelper.getMessageInfo(action, "请求失败：服务器异常");
																			 Ext.Msg.alert("提示", message);
																		 }
			});
	 }
	 /**
 		* 对指定 form 进行 vtype 校验
 		* @param formId 要进行验证的 form 的 id 属性
 		* @return Boolean
 		*/
	,isFormValid: function(formId) {
			// 获取 form 对象
			var tmpForm = Ext.getCmp(formId);
			if(!tmpForm.getForm().isValid()) {
				Ext.Msg.alert("提示", "数据格式错误：请参考提示进行修改。", function(){
					// 第一个验证没通过的组件获得焦点
					if(tmpForm.getForm().invalidField) tmpForm.getForm().invalidField.focus(true,true);
				});
				return false;
			}
			
			// 通过
			return true;
	 }
	,convertDOMJSON: function(obj, supPro, supObj) {
			// 为每个属性进行量身定制的改革
	 		for(var pro in obj) {
	 			// 获取一个属性
	 			var val = obj[pro];
	 			
	 			// 获取特定改革方法
	 	 		var extFun = this.extProConvertMap[pro];
	 	 		// 如果有定制改革方法则调用
	 	 		if(extFun) val = extFun(val, pro, obj);
	 	 		// 如果是对象则递归
	 	 		else if(typeof val == "object") val = this.convertDOMJSON(val, pro, obj);
	 	 		// 字符串处理
	 	 		else {
	 	 		}
	 	 		
	 	 		// 将更改后的对象放置在指定位置(有可能更换了对象地址，所以必须做此步)
	 	 		obj[pro] = val;
	 		}
	 		
	 		return obj;
	 }
	 // 获取当前毫秒数
	,getCurrentTimeMillis: function() {
			return new Date().getTime();
	 }
	 // 获取唯一的序列串
	,getUniqueStr: function(preStr) {
			return preStr + this.__sequence++;
	 }
	 // 把一个对象转换为 Array
	,convertObj2Array: function(obj) {
			// 如果对象不存在，则返回 null 值
			if(!obj) return null;
			
			// 返回需要的 Array 对象
			return obj instanceof Array ? obj : [obj];
	 }
	 // 删除属性中没用的值
	,deletePro: function(pros, obj) {
			// 如果是字符串则进行分解
			if(typeof pros == "string") pros = pros.split(",");
			// 删除对象中的对应属性
			Ext.each(pros, function(item, index, itemsAll) {
				delete obj[item];
			});
			
			return obj;
	 }
	 /**
		* 创建一个ExtUI控件并返回
		* @param config 控件的配置参数，必须有xtype属性
		*/
	,createComponent: function(config) {
			// 获取对应控件对象
			var cmptype = this.componentMap[config.xtype];
			// 删除没用的参数
			delete config.xtype
			
			if(typeof cmptype == "string") {				
				// 获取控件对象
				Ext.each(cmptype.split("."), function(item, index, itemsAll) {
					if(!index) {
						cmptype = eval(item);	
						return;
					}
					// 获取控件定义
					cmptype = cmptype[item];	
				});
			}
			
			return new cmptype(config);
	 }
	 // 获取需要的提示
	,getMessageInfo: function(action, msg) {
		 	return action.result && action.result.data ? action.result.data.msg : msg;
	 }
	,getATagString: function(text, url) {
			var sb = new StringBuilder;
			sb.append('<a style="color:blue;white-space:nowrap;text-overflow:ellipsis;word-break:keep-all;overflow:hidden;"')
				.append(' href="').append(url).append('">')
				.append(text).append("</a>");;
				
			return sb.toString();
	 }
	 /**
	 * 导出文件
	 * 业务描述：分三种方法导出文件，1、通过 form。2、通过 url。 3、通过 fileId
	 */
	,exportFile: function() {
			// 获取导出的 url
			var _exportUrl = arguments[0];
			
			
			// 获取用于导出的 form 对象
			var exportForm = arguments[1];
			// 获取参数
			var params = arguments[2];
			
			// 导出文件
			exportForm.getForm().exportData({
				url     : SERVER_PATH + _exportUrl,
				params  : this._processParams(params),
				failure : function(form, action){
					Ext.Msg.alert('提示', action.result.data.msg);   
				}
			});
	 }
	 /**
	  * 隐藏不需要的页面元素
	  */
	,hideItems: function(items) {
			Ext.each(items.split(","), function(item, index, itemsAll) {
					var cmp = Ext.getCmp(item);
					cmp.hide();
					cmp.disable();
			});
	 }
	 // 从对象中获取指定 key 的值
	,getDataForObject: function(key, obj) {
			Ext.each(key.split("."), function(item, index, itemsAll) {
					if(!obj) return;
					obj = obj[item];
			})
			return obj;
	 }
	 // 拷贝第一个 Array 中的子对象到第二个 Array
	,copyArrayToArray: function(ar1, ar2) {
			Ext.each(ar1, function(item, index, itemsAll) { ar2.push(item) });
	 }
   // 获取一个隐藏的FormPanel
	,getHiddenForm: function() {
			if(!this.__hiddenForm) {
				this.__hiddenForm = new Ext.form.FormPanel({
					 renderTo : document.body
					,hidden  : true
					,items	 : {xtype: 'label'}
				})
			}
		
			return this.__hiddenForm;
	 }
	 // 格式化要提交的参数
	,_processParams: function(params) {
			// 获取当前毫秒数
			var currentTimeMillis = this.getCurrentTimeMillis();
			
			// 如果参数不存在
			if (!params) {
				return "_t=" + currentTimeMillis;
			} 
			// 如果是 string 格式的参数
			else if (typeof params == "string") {
				return encodeURI(params + "&_t=" + currentTimeMillis);
			}
			// 其他格式，认为是 object
			else {
				params._t = currentTimeMillis;
				return params;
			}
			
			return params;
	 }
	 ,__convertExtItem: function(obj, supPro, supObj) {
		  var aItem = [];
		  for(var pro in obj) {
		  	var val = obj[pro];
		  	if(val instanceof Array) {
		  		for(var i = 0 ; i < val.length ; i++) {
		  			aItem.push(ExtConvertHelper.convertDOMJSON(val[i], pro, obj));
		  		}
		  	} else if(typeof val == "object") {
		  		aItem.push(ExtConvertHelper.convertDOMJSON(val, pro, obj));
		  	}
		  }
		  
		  return aItem;
	 }
	 
	 /*--------------- 属性及常量 ---------------*/
	 // 序列值
	,__sequence: 0
	 // 映射xtype对应的控件
	,componentMap: {									
		  "textfield" 		: Ext.form.TextField
		 ,"timefield" 		: Ext.form.TimeField
		 ,"datefield" 		: Ext.form.DateField
		 ,"textarea"			: Ext.form.TextArea
		 ,"numberfield"		: Ext.form.NumberField
		 ,"dictcombo"     : "ExtUx.widget.DictCombo"
	 }
};
//alert(ExtConvertHelper.convertDOMJSON)
Ext.apply(ExtConvertHelper, {
	extProConvertMap: {
	 		 // 将会做出自动转换，属性名之间用 "," 分隔
	 		 "boolean" : "fileUpload,allowBlank,deftbar,sortable,page,enableTabScroll,closable,frame,displayIcon,split,collapsible,preloadChildren,expanded,singleClickExpand"
	 		,"number"	 : "activeTab,width,height,labelWidth"
	 		 // 添加自己的转换方法
			//,"items" 	 : ExtConvertHelper.__convertExtItem
			//,"fields"  : ExtConvertHelper.__convertExtItem
			//,"columns" : ExtConvertHelper.__convertExtItem
			//,"layoutpanel" : ExtConvertHelper.__convertExtItem
	 }
	,initExtProConvertMap : function() {
		var extProConvertMap = this.extProConvertMap;
		var epctm = {};
		
		for(var proType in extProConvertMap) {
			// 获取需要转换的属性
			var pros = extProConvertMap[proType];
			var prosFun = null;
			
			switch(proType) {
				// 针对于 boolean 的转换
				case "boolean":
					prosFun = function (obj, supPro, supObj) {
						return obj == "true";
					}
					
					break;
				// 针对于 number 的转换
				case "number":
					prosFun = function (obj, supPro, supObj) {
						return isNaN(obj) ? obj : +obj;
					}
					break;
			}
			
			// 如果需要处理
			if(prosFun) {
				for(var i = 0, pros = pros.split(","), count = pros.length ; i < count ; i++) {
					epctm[pros[i]] = prosFun;
				}
			}
		}
		Ext.apply(extProConvertMap, epctm);
		//for(var proType in extProConvertMap) alert(proType + ": " + extProConvertMap[proType])
	 }
});


// 初始化需要转换的属性方法对照表
ExtConvertHelper.initExtProConvertMap();

// 获取当前页面 url 传递过来的参数
var urlPs = ExtConvertHelper.getUrlPs();