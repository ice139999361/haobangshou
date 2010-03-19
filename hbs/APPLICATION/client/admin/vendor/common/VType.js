/**************************************
 *
 * 供应商部分通用校验
 * 作者：Ice.
 * 日期：2010.03.19
 *
 **************************************/


/**
 * 控件是否通过长度校验
 * @param val   (String)  要验证的文本
 * @param cmp   (Object)  要验证的组件
 * @param vText (String)  VType的提示名字
 * @param flag  (Boolean) 提示类型  true: 字符提示；false: 文本提示
 */
function isPassTextLen(val, cmp, vText, flag) {
	// 获取当前控件 maxLen 属性
	var maxLen = cmp.maxLen;
	// 如果没有填写 maxLen 属性，则认为不做长度边界校验
	if(typeof cmp.maxLen == "undefined") return true;
	
	// 验证长度是否满足要求
	if(val.len() > maxLen) {
		Ext.form.VTypes[vText] = flag ? "不能超过"+ maxLen +"个字符！" : "不能超过"+ maxLen +"个英文或"+ Math.floor(maxLen/2) +"个汉字！"
		return false;
	}
	
	// 验证通过
	return true;
}

Ext.apply(Ext.form.VTypes, {
	
});










