/**************************************
 *
 * 添加项目中公用的校验信息
 * 作者：Ice.
 * 日期：2010.03.19
 *
 **************************************/



/**
 * 校验文本类型
 * @param val   (未知)    要校验的数据
 * @param type  (String)  比对的类型
 * @returnValue (Boolean) true：通过；false：不通过
 */
function checkDataType(val, type) {
	switch(type) {
		case "number"  : return !isNaN(val);
		case "array"   : return Ext.isArray(val);
		case "boolean" : return val == "true" || val == "false";
		case "date"    : return Ext.isDate(val);
		case "function": return Ext.isFunction(val);
		case "object"  : return Ext.isObject(val);
		case "string"  : return Ext.isString(val);
	}
	
	return true;
}

/**
 * 控件是否通过长度校验
 * @param val    (String)  要验证的文本
 * @param cmp    (Object)  要验证的组件
 * @param vText  (String)  VType的提示名字
 * @param flag   (Boolean) 提示类型  true: 字符提示；false: 文本提示
 * @param maxLen (Number)  文本的最大长度
 * @returnValue  (Boolean) true：通过；false：不通过
 */
function isPassTextLen(val, cmp, vText, flag, maxLen) {
	// 获取当前控件 maxLen 属性
	if(!maxLen) maxLen = cmp.maxLen || 0;
	// 如果没有填写 maxLen 属性，则认为不做长度边界校验
	if(!maxLen) return true;
	
	// 验证长度是否满足要求
	if(val.len() > maxLen) {
		Ext.form.VTypes[vText] = flag ? "必须小于"+ maxLen +"个字符！" : "必须小于"+ maxLen +"个英文或"+ Math.floor(maxLen/2) +"个汉字！";
		return false;
	}
	
	// 验证通过
	return true;
}

/**
 * 控件是否通过数据类型校验
 * @param val   (String)  要验证的文本
 * @param cmp   (Object)  要验证的组件
 * @param vText (String)  VType的提示名字
 * @returnValue (Boolean) true：通过；false：不通过
 */
function isPassTypeVerify(val, cmp, vText) {
	
	
	// 验证通过
	return true;
}

/**
 * 是否通过数据复合校验
 * @param val   (String)  要验证的文本
 * @param where (Object)  验证条件
 * @param cmp   (Object)  要验证的组件
 * @returnValue (Boolean) true：通过；false：不通过
 */
function isPassComplexVerify(val, where, cmp) {
	// 根据不同条件进行复合校验
	for(var i = 0, count = where.length ; i < count ; i++) {
		var item = where[i];
		
		// 如果条件不存在则进入下一轮
		if(!item) continue;
		
		// 验证代码
		switch(i) {
			case 0:
				// 如果不是指定值
				if(val != item) return false;
				break;
			case 1:
				if(!checkDataType(val, item)) return false;
				break;
			case 2:
				if(val.len() != item) return false;
				break;
		}
	}
	
	// 验证通过
	return true;
}

/**
 * 控件是否通过数据前缀校验
 * @param val   (String)  要验证的文本
 * @param cmp   (Object)  要验证的组件
 * @param vText (String)  VType的提示名字
 * @returnValue (Boolean) true：通过；false：不通过
 */
function isPassPrefixVerify(val, cmp, vText) {
	// 获取前后缀验证配置对象
	var where = cmp.__pso.prefix;
	
	// 如果不需要验证则返回 true
	if(!where) return true
	// 获取前缀文本
	if(where[2]) val = val.slice(0, where[2]);
	// 是否通过数据复合校验
	if(!isPassComplexVerify(val, where, cmp)) {
		// 错误提示
		Ext.form.VTypes[vText] = cmp.psMsg;
		// 验证不通过
		return false;
	}
	
	// 验证通过
	return true;
}

/**
 * 控件是否通过数据后缀校验
 * @param val   (String)  要验证的文本
 * @param cmp   (Object)  要验证的组件
 * @param vText (String)  VType的提示名字
 * @returnValue (Boolean) true：通过；false：不通过
 */
function isPassSuffixVerify(val, cmp, vText) {
	// 获取前后缀验证配置对象
	var where = cmp.__pso.suffix;
	
	// 如果不需要验证则返回 true
	if(!where) return true
	// 如果与前缀有缀定
	if(where[3]) val = val.slice(cmp.__pso.prefix[2]);
	else val = val.slice(-(where[2] || 1));
	// 是否通过数据复合校验
	if(!isPassComplexVerify(val, where, cmp)) {
		// 错误提示
		Ext.form.VTypes[vText] = cmp.psMsg;
		// 验证不通过
		return false;
	}
	// 验证通过
	return true;
}

/**
 * 控件是否通过数据前缀和后缀校验
 * @param val   (String)  要验证的文本
 * @param cmp   (Object)  要验证的组件
 * @param vText (String)  VType的提示名字
 * @returnValue (Boolean) true：通过；false：不通过
 */
function isPassPrefixSuffixVerify(val, cmp, vText) {
	// 获取 __pso 属性
	var pso = cmp.__pso;
	
	// 如果 pso 属性不存在，则处理添加
	if(Ext.isEmpty(pso)) {
		// 前后缀验证条件
		pso = {
			 prefix: cmp.prefix
			,suffix: cmp.suffix
		};
		
		// 前后缀条件处理
		for(var ps in pso) {
			// 如果是空的话则跳出
			if(Ext.isEmpty(pso[ps])) continue;
			
			// 获取更加细小的比较条件
			var tmp = pso[ps].split("|;");
			// 处理固定字符
			tmp[0] = Ext.isEmpty(tmp[0]) ? "" : tmp[0];
			tmp[1] = Ext.isEmpty(tmp[1]) ? "" : tmp[1];
			tmp[2] = Ext.isEmpty(tmp[2]) ? tmp[0].length : +tmp[2];
			
			// 后缀的特殊处理
			if(ps == "suffix") tmp[3] = Ext.isEmpty(tmp[3]) ? true : tmp[3] == "true";
			
			// 与 pso 对象绑定
			pso[ps] = tmp;
		}
		
		// 控件绑定 pso 对象
		cmp.__pso = pso;
		
		// 如果 maxLen 属性为空
		if(Ext.isEmpty(cmp.maxLen) && pso.suffix && pso.suffix[3]) pso.maxLen = pso.prefix[2] + pso.suffix[2];
	}
	
	// 如果有需要进行长度校验
	if(!Ext.isEmpty(pso.maxLen) && !isPassTextLen(val, cmp, vText, true, pso.maxLen)) return false;
	// 进行前缀校验
	if(!isPassPrefixVerify(val, cmp, vText)) return false;
	// 进行后缀校验
	if(!isPassSuffixVerify(val, cmp, vText)) return false;
	
	// 验证通过
	return true;
}

/**
 * 控件是否通过关联数据校验
 * @param val   (String)  要验证的文本
 * @param cmp   (Object)  要验证的组件
 * @param vText (String)  VType的提示名字
 * @returnValue (Boolean) true：通过；false：不通过
 */
function isPassRelateVerify(val, cmp, vText) {
	// 如果不需要关联校验
	if(Ext.isEmpty(cmp.relate)) return true;
	// 获取校验配置
	var vconfig = cmp.relate.split("|;");
	// 获取相关联控件
	var rcmp = Ext.getCmp(vconfig[0]);
	
	// 如果相关联控件不存在则提示
	if(!rcmp) {
		// 错误提示
		Ext.form.VTypes[vText] = "需要关联的控件不存在";
		// 验证不通过
		return false;
	}
	
	// 获取关联控件内容
	var rval = rcmp.getValue();
	// 如果关联控件没有输入则返回
	if(Ext.isEmpty(rval)) return true;
	// 存放验证结果
	var verifyResult = true;
	// 针对于符合进行判断
	switch(vconfig[1]) {
		case ">":
			verifyResult = (val > rval);
			break;
		case "<":
			verifyResult = (val < rval);
			break;
		case "==":
			verifyResult = (val == rval);
			break;
		case "<=":
			verifyResult = (val <= rval);
			break;
		case ">=":
			verifyResult = (val >= rval);
			break;
		case "===":
			verifyResult = (val === rval);
			break;
		case "&&":
			verifyResult = (val && rval);
			break;
		case "||":
			verifyResult = (val || rval);
			break;
	}
	
	// 如果不通过则错误提示
	if(!verifyResult) Ext.form.VTypes[vText] = vconfig[2];
	
	// 返回验证结果
	return verifyResult;
}

/**
 * 控件是否通过后台数据校验
 * @param val   (String)  要验证的文本
 * @param cmp   (Object)  要验证的组件
 * @param vText (String)  VType的提示名字
 * @returnValue (Boolean) true：通过；false：不通过
 */
function isPassActionVerify(val, cmp, vText) {
	// 如果不需要后台校验
	if(Ext.isEmpty(cmp.checkUrl)) return true;
	// 获取缓存对象
	var checkedVal = cmp.__checkedVal;
	// 如果缓存对象不存在则创建
	if(Ext.isEmpty(checkedVal)) cmp.__checkedVal = checkedVal = {};
	// 如果有对此数据进行缓存则直接返回
	if(Ext.isEmpty(checkedVal[val])) {
		// 同步校验
		var action = ExtConvertHelper.syncRequest(cmp.checkUrl + (cmp.checkUrl.indexOf("?") == -1 ? "?" : "&") + "value=" + val);
		// 转换 Boolean 值
		//action.success = action.success == "true";

		// 缓存验证信息
		checkedVal[val] = {
			 success  : action.success
			,errorMsg : ExtConvertHelper.getMessageInfo(action, "请求失败：服务器异常")
		};
	}

	// 验证通过
	if(checkedVal[val].success) return true;
	
	// 错误提示
	Ext.form.VTypes[vText] = checkedVal[val].errorMsg;
	// 验证不通过
	return false;
}


Ext.apply(Ext.form.VTypes, {
	 /*
	   {
	   		 dataType : "number"               // 数据类型
	   		,maxLen   : 10                     // 最大长度
	   		,charFlag : true                   // 字符还是中文
	   		,prefix   : "GV|;string|;2"        // 前缀，可选填，如：GV     (固定格式|;数据类型|;数据长度)
	   		,suffix   : "GV|;string|;2|;true"  // 后缀，可选填，如：GV     (固定格式|;数据类型|;数据长度|;是否与前缀有关系)
	   		,psMsg    : "格式错误!"            // 前/后缀校验错误后，提示信息
	   		,relate   : "cmp1;>;必须大于cmp1"  // 关联控件的验证  (关联控件ID|;两者的关系|;有错误时的提示信息)  在HTML中写时注意转意
	   }
	 */
	 commCheck: function(val, cmp) {
	 		// 定义 vText
	 		var vText = "commCheckText";
	 		// 验证控件数据类型
	 		if(!isPassTypeVerify(val, cmp, vText)) return false;
	 		// 验证控件文本长度
	 		if(!isPassTextLen(val, cmp, vText, (Ext.isEmpty(cmp.charFlag) ? true : cmp.charFlag == true))) return false;
	 		// 验证控件前缀和后缀
	 		if(!isPassPrefixSuffixVerify(val, cmp, vText)) return false;
	 		// 验证控件关联
	 		if(!isPassRelateVerify(val, cmp, vText)) return false;
	 		// 通过 Action 进行后台验证
	 		if(!isPassActionVerify(val, cmp, vText)) return false;
	 		
	 		// 验证通过
	 		return true;
	 }
});










