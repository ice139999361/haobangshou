var FormatUtil = {};

Ext.apply(FormatUtil, {
	 data2string: function(value, tfomat, ffomat) {
	 		return value.slice(0, 10);
	 }
	 // 列表中使用，日期的转换方法
  ,dateRenderer: function(value, metadata, record) {
			return value.slice(0, 10);
	 }
});