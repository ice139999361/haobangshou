var FormatUtil = {};

Ext.apply(FormatUtil, {
	 data2string: function(value, tfomat, ffomat) {
	 		if(value)
	 			return value.slice(0, 10);
	 		else
	 			return value;
	 }
	 // �б���ʹ�ã����ڵ�ת������
  ,dateRenderer: function(value, metadata, record) {
			if(value)
				return value.slice(0, 10);
			else
				return value;
	 }
});