var querygridUrl = "/invoice/SendInvoice!list.action?invoice.ccode=" + urlPs["invoice.ccode"] + "&invoice.poNo=" + urlPs["invoice.poNo"]+ "&&invoice.partNo=" + urlPs["invoice.partNo"];
HBSConvertHelper.init(function() {
	Ext.getCmp("backBtn").on("click", function() {
		close();
	});
});