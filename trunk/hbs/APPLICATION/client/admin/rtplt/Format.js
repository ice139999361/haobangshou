var FormatUtil = {};

Ext.apply(FormatUtil, {
	 data2string: function(value, tfomat, ffomat) {
	 		if(value)
	 			return value.slice(0, 10);
	 		else
	 			return value;
	 }
	 // 列表中使用，日期的转换方法
  ,dateRenderer: function(value, metadata, record) {
			if(value)
				return value.slice(0, 10);
			else
				return value;
	 }
});