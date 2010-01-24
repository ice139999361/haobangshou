/**************************************
 *
 * 作者：Ice.
 * 日期：2010.01.02
 *
 **************************************/

/**
 * @class Ice
 * 框架的核心工具类
 * @singleton
 */
var Ice = {
	 /**
	  * 此框架的版本号
	  * @type String
	  */
	 version : '1.0'
};


/**
 * 拷贝所有的属性到一个对象.
 * @param 	{Object} obj 			特性将要归属的对象
 * @param 	{Object} config 	要拷贝的属性
 * @param 	{Object} defaults 要继承的对象，此对象的所有属性也将拷贝至 obj 对象
 * @return 	{Object} returns obj
 */
Ice.apply = function(obj, config, defaults){
    // 如果需要继承的对象，首先将其对象的特性加载至 obj 对象
    if(defaults){
        Ice.apply(obj, defaults);
    }
    
    // 加载或覆盖自身扩展的特性
    if(obj && config && typeof config == 'object'){
        for(var pro in config){
            obj[pro] = config[pro];
        }
    }
    
    return obj;
};


//
Ice.apply(Ice, {
	 /**
	  * 创建命名空间
	  * @param {String} namespace...  例：Ice.namespace("Ice.util", "Ice.")
    */
   namespace : function(){
   		// 定义存放命名对象
   		var oNs = window;
   		
   		for(var i = 0, count = arguments.length ; i < count ; i++) {
   			// 通过成员变量分隔命名地址
   		 	var aNs = arguments[i].split(".");
   		 	
   		 	// 构建命名对象
   		 	for(var j = 0, aNsCount = aNs.length ; j < aNsCount ; j++) {
   		 	 	oNs = oNs[aNs[j]] = oNs[aNs[j]] || {};
   		 	}
   		}
   		
      return oNs;
   }
});