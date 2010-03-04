Ext.namespace('ExtUx.widget');

ExtUx.widget.AuditPanel = function(config) {
	config.itemsFun = function() {
		var cph = new ColumnPanelHelper;
			
		var p1 = cph.createLayoutPanel(1);
		p1.push({xtype: "dictcombo", fieldLabel: "审批结果"       , hiddenName: "audit"           , paramsValue: "AUDIT", emptyText: "请选择"});
		p1.push({xtype: "textarea" , fieldLabel: "审批意见"       , name: "auditDesc", width: 600 ,height: 80});
		
	
	
		//var p2 = cph.createLayoutPanel("5:.10,.05,.15,.05,.3");
		//ExtConvertHelper.copyArrayToArray(cmpobj["khlrsj"], p2);
	
		return cph;
	}
	ExtUx.widget.AuditPanel.superclass.constructor.call(this, config);
};

Ext.extend(ExtUx.widget.AuditPanel, ExtUx.widget.ListPanel, {
	 title		     : "审批信息"
	,frame         : true
	,collapsible   : true
	,titleCollapse : true
});
Ext.reg('auditpanel', ExtUx.widget.AuditPanel);
