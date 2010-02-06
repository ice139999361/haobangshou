HBSConvertHelper.init(function() {
	// -------------------------------------- ��ȡ��Ҫ�־��õ��Ķ���
	
	// ��ȡ�ύ��ť
	var submitBtn 	= Ext.getCmp("submitBtn");
	// ��ȡ���水ť
	var saveBtn 		= Ext.getCmp("saveBtn");
	// ��ȡ���ذ�ť
	var backBtn 		= Ext.getCmp("backBtn");
	
	
	
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
		
		// �ύ����
		ExtConvertHelper.submitForm("form", url, girdData, function(form, action) {
			// ��ȡ�ɹ������ʾ��Ϣ
			var msg = ExtConvertHelper.getMessageInfo(action, "�����ɹ���");
			
			// ������ʾ����û�
			Ext.Msg.alert("��ʾ", msg, function() {
				// �û����������ش�ҳ��
				location.reload();
			});
		});
	}
	
	// ���ύ��ť������ʱ
	submitBtn.on("click", function() {
		submitData("/customerInfo/customerInfo!save.action");
	});
	
	// �����水ť������ʱ
	saveBtn.on("click", function() {
		submitData("/customerInfo/customerInfo!saveTemp.action");
	});
	
	// ������ȡ����ťʱ������Ĭ�ϵĹرմ��ڷ���
	backBtn.on("click", ExtConvertHelper.defaultCloseTab);
});