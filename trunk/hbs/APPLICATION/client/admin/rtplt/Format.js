var FormatUtil = {};

Ext.apply(FormatUtil, {
	 data2string: function(value, tfomat, ffomat) {
	 		return value.slice(0, 10);
	 }
	 // �б���ʹ�ã����ڵ�ת������
  ,dateRenderer: function(value, metadata, record) {
			return value.slice(0, 10);
	 }
});