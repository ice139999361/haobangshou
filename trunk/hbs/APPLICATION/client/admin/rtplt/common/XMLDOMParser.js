/**************************************
 *
 * 作者：Ice.
 * 日期：2010.01.01
 *
 **************************************/
 

function XMLDOMParser() {
	// 如果是IE浏览器
	if (window.ActiveXObject) {
		// 定义标识名称集
		var arrSignatures = [ "MSXML2.DOMDocument.5.0", "MSXML2.DOMDocument.4.0"
												 ,"MSXML2.DOMDocument.3.0", "MSXML2.DOMDocument"
												 ,"Microsoft.XmlDom" ];
												 
		// 创建解析器
		for (var i = 0 ; i < arrSignatures.length ; i++) {
			try {
				var oXmlDom = new ActiveXObject(arrSignatures[i]);
				return oXmlDom;
			} catch (oError) { }
		} 
		
		// 抛出错误信息
		throw new Error("您的系统没有安装 MSXML。");
	}
	// 如果是实现了 DOM 标准的浏览器
	else if (document.implementation && document.implementation.createDocument) {
		
		// 创建 XMLDOM 对象
		var oXmlDom = document.implementation.createDocument("", "", null);
		
		// 初始化错误对象
		oXmlDom.parseError = {
			 valueOf  : function() { return this.errorCode; }
			,toString : function() { return this.errorCode.toString(); }
		};
		
		// 初始化错误对象
		oXmlDom.__initError__();
		
		// 添加加载时的捕获事件
		oXmlDom.addEventListener("load", function() {
			// 填充错误对象信息
			this.__checkForErrors__();
			// 更新加载状态
			this.__changeReadyState__(4);
		}, false);
		
		return oXmlDom;
	}
}


// 如果是支持 DOM 标准的浏览器，则会调用此初始化方法
if(document.implementation && document.implementation.createDocument) {
	
	// 设置初始状态
	Document.prototype.readyState = 0;
	// 当状态改变时触发的方法
	Document.prototype.onreadystatechange = null;
	
	// 更新状态及触发异步方法
	Document.prototype.__changeReadyState__ = function(iReadyState) {
		this.readyState = iReadyState;
		
		if(this.onreadystatechange) this.onreadystatechange();
	}
	
	/**
	 * 初始化错误对象
	 */
	Document.prototype.__initError__ = function() {
		// 错误代码
		this.parseError.errorCode =  0;
		// 错误发生文件中位置
		this.parseError.filepos   = -1;
		// 遇到错误的行号
		this.parseError.line			= -1;
		// 在遇到错误行上的字符位置
		this.parseError.linepos	  = -1;
		// 对错误的一个解释
		this.parseError.reason    = null;
		// 造成错误的代码
		this.parseError.srcText   = null;
		// 造成错误的文件URL
		this.parseError.url       = null;		
	}
	
	/**
	 * 填充错误对象信息
	 */
	Document.prototype.__checkForErrors__ = function() {
		// 如果没有错误则跳出
		if(this.documentElement.tagName != "parsererror") return;
		
		// 获取错误信息的正则
		var reError = />([\s\S]*?)Location:([\s\S]*?)Line Number (\d+), Column(\d+):<sourcetext>([\s\S]*?)(?:\-*\^)/;
		// 获取需要信息
		reError.test(this.xml);
		// 设置错误代码
		this.parseError.errorCode = -999999;
		// 设置对错误的一个解释
		this.parseError.reason    = RegExp.$1;
		// 设置造成错误的文件URL
		this.parseError.url 			= RegExp.$2;
		// 设置遇到错误的行号
		this.parseError.line 			= parseInt(RegExp.$3);
		// 设置在遇到错误行上的字符位置
		this.parseError.linepos   = parseInt(RegExp.$4);
		// 设置造成错误的代码
		this.parseError.srcText	  = RegExp.$5;
	}
	
	/**
	 * 加载解析指定的 XML 字符串
	 *@param sXML String XML格式的字符串
	 */
	Document.prototype.loadXML = function(sXml) {
		// 初始化错误对象
		this.__initError__();
		
		// 更新加载状态
		this.__changeReadyState__(1);
		
		// 创建解析对象
		var oParser = new DOMParser();
		// 解析 XML 字符串
		var oXmlDom = oParser.parseFromString(sXml, "text/xml");
		
		// 删除元素中所有的节点
		while (this.firstChild) this.removeChild(this.firstChild);
		
		// 加入解析字符串后获取的节点
		for(var i = 0 ; i < oXmlDom.childNodes.length ; i++) {
			var oNewNode = this.importNode(oXmlDom.childNodes[i], true);
			this.appendChild(oNewNode);
		}
		
		// 填充错误对象信息
		this.__checkForErrors__();
		
		// 更新加载状态
		this.__changeReadyState__(4);
	}
	
	// 给予原有load方法新的别名
	Document.prototype.__load__ = Document.prototype.load;
	
	/**
	 * 封装新的load方法，加入了状态变更
	 */
	Document.prototype.load = function(sURL) {
		this.__initErrors__();
		this.__changeReadyState__(1);
		this.__load__(sURL);
	}
	
	// 给节点添加只读 xml 属性
	Node.prototype.__defineGetter__("xml", function() {
		var oSerializer = new XMLSerializer();
		return oSerializer.serializeToString(this, "text/xml");
	});

}
