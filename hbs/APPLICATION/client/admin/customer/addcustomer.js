ExtConvertHelper.init(function() {
	// -------------------------------------- ��ȡ��Ҫ�־��õ��Ķ���
	
	// ��ȡ�ύ��ť
	var submitBtn = Ext.getCmp("submitBtn");
	// ��ȡ���水ť
	var saveBtn 	= Ext.getCmp("saveBtn");
	// ��ȡ���ذ�ť
	var backBtn 	= Ext.getCmp("backBtn");
	
	
	
	// -------------------------------------- Ӧ���߼�����
	
	// ���ύ��ť������ʱ
	submitBtn.on("click", function() {
		// ��֤ form �����Ƿ�����Ҫ��
		if(!ExtConvertHelper.isFormValid("form")) return;
		
		ExtConvertHelper.submitForm("form", "/aaa.action");
	});
	
	// �����水ť������ʱ
	saveBtn.on("click", function() {
		ExtConvertHelper.submitForm("form", "/aaa.action");
	});
	
	// ������ȡ����ťʱ������Ĭ�ϵĹرմ��ڷ���
	backBtn.on("click", ExtConvertHelper.defaultCloseTab);
});