ExtConvertHelper.init(function() {
	// -------------------------------------- ��ȡ��Ҫ�־��õ��Ķ���
	
	// ��ȡ�ύ��ť
	var submitBtn 	= Ext.getCmp("submitBtn");
	// ��ȡ���水ť
	var saveBtn 		= Ext.getCmp("saveBtn");
	// ��ȡ���ذ�ť
	var backBtn 		= Ext.getCmp("backBtn");
	// ��ȡ�ͻ���ϵ����Ϣ���
	var contactgrid = Ext.getCmp("contactgrid");
	// ��ȡ�ͻ��ջ�����Ϣ���
	var consigneegrid = Ext.getCmp("consigneegrid");
	// ��ȡ�ͻ�������Ϣ���
	var custbankgrid = Ext.getCmp("custbankgrid");
	
	
	
	// -------------------------------------- Ӧ���߼�����
	
	/**
	 * �ύ����
	 * @param url  (String) �ύ��url
	 */
	function submitData(url) {
		// ��֤ form �����Ƿ�����Ҫ��
		//if(!ExtConvertHelper.isFormValid("form")) return;
		
		// ��ȡ���ͻ���ϵ����Ϣ���ͻ��ջ�����Ϣ���ͻ�������Ϣ������е��ύ����
		var girdData = HBSConvertHelper.getGridSubmitData("contactgrid,consigneegrid,custbankgrid", "contactlist,consigneelist,custbanklist");
		
		alert(girdData)
		
		ExtConvertHelper.submitForm("form", url);
	}
	
	// ���ύ��ť������ʱ
	submitBtn.on("click", function() {
		submitData("aaa.action");
	});
	
	// �����水ť������ʱ
	saveBtn.on("click", function() {
		submitData("aaa.action");
	});
	
	// ������ȡ����ťʱ������Ĭ�ϵĹرմ��ڷ���
	backBtn.on("click", ExtConvertHelper.defaultCloseTab);
});