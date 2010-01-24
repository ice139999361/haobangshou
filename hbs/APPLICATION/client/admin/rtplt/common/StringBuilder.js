/**
 * StringBuilder 构造方法
 * 说明：
 * Ice.
 */
function StringBuilder(){
	this._aStr_ = new Array;
}

/**
 * 进行优化，私有方法
 * 减少重复的字符串构建
 */
StringBuilder.prototype._optStr_ = function() {
	if(this._aStr_.length < 2) return;
	var sTempStr = this._aStr_.join("");
	this._aStr_.splice(0, this._aStr_.length);
	this.append(sTempStr);
};

/**
 * 追加一个字符串、字符串数组、多个字符串、多个字符串数组、字符串数组与字符串混合传入
 * 例：append("apple")、 append("apple", "orange", "banana")、applend(new Array("apple", "orange"))、 
 * applend(new Array("apple", "banana"), ["orange", "peach"])、 applend(new Array("apple", "banana"), "orange")
 */
StringBuilder.prototype.append = function() {
	for(var i = 0 ; i < arguments.length ; i++) {
		this._aStr_.push(arguments[i] instanceof Array ? arguments[i].join("") : arguments[i]);
	}
	return this;
};

// *-----------------------------------删除字符串区域 开始

/**
 * @return String 返回字符串原型
 */
StringBuilder.prototype.toString = function() {
	this._optStr_();
	if(this._aStr_.length == 0) return "";
	return this._aStr_[0];
};

/**
 * 删除一个字符或一段字符串
 * 例：del(1)、del(2, 5)
 */
StringBuilder.prototype.del = function() {
	if(this.toString() == "") return;
	if(arguments.length == 1) this._delAchar_(arguments[0]);
	else if(arguments.length > 1) this._delStrs_((arguments[0] > arguments[1] ? arguments[1] : arguments[0]), (arguments[0] > arguments[1] ? arguments[0] : arguments[1]));
};

// 删除一个字符

StringBuilder.prototype._delAchar_ = function(index) {
	if(isNaN(index)) return;
	if(index < 0) return;
	if(this.toString().length <= index) return;
	var tmpStr = this.toString();
	this.append(tmpStr.slice(0, index));
	if((tmpStr.length - 1) != index) 
		this.append(tmpStr.slice((index+1)));
	this._aStr_.shift();
};

// 删除一段字符串
StringBuilder.prototype._delStrs_ = function(begin, end) {
	begin = begin < 0 ? 0 : begin;
	end = end < 0 ? 0 : end;
	if(begin == end) return;
	var tmpStr = this.toString();
	if(tmpStr.length < begin) return;
	this.append(tmpStr.slice(0, begin));
	if((tmpStr.length - 1) > end)
		this.append(tmpStr.slice(end));
	this._aStr_.shift();
};
// *-----------------------------------删除字符串区域 结束