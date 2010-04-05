var FormatUtil = {};

Ext.apply(FormatUtil, {
	dateRenderer: function(value, metadata, record) {
		return value.slice(0, 10);
	}
});