var querygridUrl = "/warehouse/warehouse!list.action";
HBSConvertHelper.init(function() {
// ��ȡ���ذ�ť
	var backBtn 		= Ext.getCmp("backBtn");
	(function() {
		
		// ������ȡ����ťʱ������Ĭ�ϵĹرմ��ڷ���
		backBtn.on("click", HBSConvertHelper.defaultCloseTab);
	});
});