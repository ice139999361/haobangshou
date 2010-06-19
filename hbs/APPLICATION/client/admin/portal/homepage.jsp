<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=contextPath %>/portal/css/theme.css"/>
	<title>待处理事项</title>
</head>
         
<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    
			    <panel frame="true" id="mypanel" layout="fit">
			    	<items>
			    		<portal margins="35 5 5 0">
			    			<items>
			    				
			    				
			    				<panel columnWidth=".9" style="padding:10px 0 10px 10px">
			    					<items>
			    						<complexgrid frame="true" collapsible="true" titleCollapse="true" title="待处理代办" id="waitmustgrid" autoExpandColumn="comments" root="data.waitMust" itemsFun="waitgridFun" url="1" height="230"/>
			    							
			    						<complexgrid frame="true" collapsible="true" titleCollapse="true" title="提醒待办" id="waitremindergrid" autoExpandColumn="comments" root="data.waitReminder" itemsFun="waitgridFun" url="1" height="230"/>
			    					</items>
			    				</panel>
			    				
			    				
			    			</items>
			    		</portal>
			    	</items>
			    </panel>
			  </items>
			</viewport>
		</application>
	</xmp>
</body>
</html>
<script type="text/javascript">
	
var waitgridFun = function() {
	var cgh = new ComplexGridHelper;
	
	cgh.appendField("businessType");
	cgh.appendField("comments");
	cgh.appendField("url");
	cgh.appendField("createTime");
			    							
	cgh.appendColumn({header: "代办类型"      , dataIndex: "businessType"});
	cgh.appendColumn({header: "代办时间"      , dataIndex: "createTime"   , width: 130});
	cgh.appendColumn({header: "代办描述"      , dataIndex: "comments"     , id: "comments"});
	return cgh;
};

	
HBSConvertHelper.init(function() {
	Ext.getCmp("waitmustgrid").getView().on("refresh", function(view) {
		for(var i = 0 ; i < view.ds.getCount() ; i++) {
			// 获取代办描述所在的列
			var comments_cell = view.getCell(i, view.grid.getColumnIndexById("comments"));
			// 将需要的链接渲染到此列
			HBSConvertHelper.renderATag2Cell(comments_cell.innerText, view.ds.getAt(i).get("url"), "open", comments_cell);
		}
	});
	
	// 加载数据
	function fillingData() {
		//ExtConvertHelper.loadForm(null, "/common/waitaction.action", null, function(form, action) {
		ExtConvertHelper.request("/common/waitTask!listWaitTaskInfo.action", null, function(response, opts) {
			var jsonData = Ext.util.JSON.decode(response.responseText);
			if(jsonData.success === false) return;			
			Ext.getCmp("waitmustgrid").store.removeAll();
			Ext.getCmp("waitremindergrid").store.removeAll();
			Ext.getCmp("waitmustgrid").addData(jsonData.data.waitMust);
			Ext.getCmp("waitremindergrid").addData(jsonData.data.waitReminder);
			
		});
		
		setTimeout(fillingData, 30000);
	}
	
	
	fillingData();
});
</script>