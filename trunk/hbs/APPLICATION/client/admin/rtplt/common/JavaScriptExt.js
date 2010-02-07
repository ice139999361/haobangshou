// 获取该串真正的字符数
String.prototype.len = function(){ 
	return this.replace(/[^\x00-\xff]/g, "**").length;
}

// 清除空字符
String.prototype.trim = function() {
	return this.replace(/(^[\s\t\xa0\u3000]+)|([\u3000\xa0\s\t]+$)/g, "");
};


if(typeof HTMLElement != "undefined") {
	HTMLElement.prototype.__defineGetter__("innerText", function() {
		return this.textContent;
	});
	
	HTMLElement.prototype.__defineSetter__("innerText", function(sText) { 
		this.textContent = sText; 
	}); 
}