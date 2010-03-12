/**************************************
 *
 * 作者：Ice.
 * 日期：2010.01.03
 *
 **************************************/

/**
 * @class Ice.util.JSON
 * JSON相关工具类
 * @singleton
 */
Ice.util.JSON = {
	
	/**
	 * 将一个 DOM 转换为 JSON 对象.
	 * @param 	{Object} oDom 		需要转换的节点对象
	 * @param 	{Object} extFun 	可在解析中用此方法对元素节点及顶级文档节点进行处理
	 * @return 	{Object} returns obj
	 */
	parseDOM2JSON : function(oDom, extFun) {
		// 如果不是元素节点或等级节点则跳出
		if(oDom.nodeType != Node.ELEMENT_NODE && oDom.nodeType != Node.DOCUMENT_NODE) return;
		
		// 创建填充的 JSON 对象
		var json = {};
		
		// 调用扩展方法
		if(extFun) extFun(oDom, json);
		
		// 加载 JSON 对象属性
		if(oDom.attributes && oDom.attributes.length) {
			for(var i = 0, attrs = oDom.attributes, attrCount = attrs.length ; i < attrCount ; i++) {
				// 获取当前属性
				var attr = attrs[i];
				json[attr.nodeName] = attr.nodeValue;
			}
		}

		// 如果有子节点则继续构建属性
		if(oDom.hasChildNodes()) {
			// 临时存放子节点解析后的对象
			var tmpJson = {};
			// 将每一个子节点都放入数组，已被后用
			var tmpChildren = [];
			
			for(var i = 0, nodes = oDom.childNodes, nodeCount = nodes.length ; i < nodeCount ; i++) {
				var node = nodes[i];

				// 如果不是元素节点则返回值
				if(node.nodeType != Node.ELEMENT_NODE) continue;
				
				// 对当前节点递归封装
				var nodeJson = this.parseDOM2JSON(node, extFun);
				// 将每一个 nodeJson 都加入数组
				tmpChildren.push(nodeJson);
				
				// 组装 JSON 对象
				var tmp = tmpJson[node.nodeName];
				// 填充属性值，动态组装数组或对象
				tmpJson[node.nodeName] =  node.nodeValue || (tmp instanceof Array ? (tmp.push(nodeJson), tmp) : (tmp ? [tmp, nodeJson] : nodeJson));
				
				// 因为IE与其他浏览器区别，在属性为 null，并且只有一个子节点时进行重新组装
				if(!tmpJson[node.nodeName] && node.childNodes.length == 1) {
					// 获取唯一的子节点
					var tmpNode = node.childNodes[0];
					// 如果是文本节点的则重新填充
					if(tmpNode.nodeType == Node.TEXT_NODE) tmpJson[node.nodeName] = tmpNode.nodeValue;
				}
			}
			
			// 根据不同的解析类型做出不同的处理
			switch(this.__getParseType__(oDom.nodeName)) {
				case this.__NOATTRIBUTEPARSE2ARRAY:
					return tmpChildren;
				case this.__ATTRIBUTEPARSE2ARRAY:
					json.__children = tmpChildren;
					break;
				default:
					Ice.apply(json, tmpJson);
					break;
			}
		}
		
		// 要返回的 JSON 对象
		var rJson = null;
		// 如果有属性则与 json 变量保持一致
		for(var p in json) {
			rJson = json;
			break;
		}
		
		return rJson;
	},
	__getParseType__:  function(nodeName) {
		// 组装名称，便于查询
		nodeName = ":" + nodeName + ":";
		// 如果需要直接解析
		if(this.__noAttributeParse2Array.indexOf(nodeName) != -1) return this.__NOATTRIBUTEPARSE2ARRAY;
		// 如果需要加入 __children 属性
		if(this.__attributeParse2Array.indexOf(nodeName) != -1)   return this.__ATTRIBUTEPARSE2ARRAY;
		
		// 默认返回空串
		return "";
	},
	// 属性部分
	
	// 需要直接解析为 array 的节点，用于没有属性
	__noAttributeParse2Array : ":items:,:fields:,:columns:,:buttons:,:multiselects:",
	// 需要加入 __children 属性的 array 节点，有属性的情况下
	__attributeParse2Array   : ":layoutpanel:",
	
	// 常量：需要直接解析为 array 节点
	__NOATTRIBUTEPARSE2ARRAY : 1,
	// 常量：需要加入 _children 属性解析为 array 节点
	__ATTRIBUTEPARSE2ARRAY   : 2
}
	