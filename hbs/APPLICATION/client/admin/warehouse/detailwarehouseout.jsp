<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>出库单查看</title>
	<script type="text/javascript" src="<%=contextPath %>/warehouse/component/warehouseoutcommonpro.js"></script>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    	
							<form id="form"><items>
				    		<listpanel frame="true" title="出库基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<label    fieldLabel="出库单号"       name=""                   labelStyle="width:150" />
				    				<label    fieldLabel="对应分公司"     name=""                   labelStyle="width:150" />
				    				<label    fieldLabel="客户"           name="custInfo.commCode"  labelStyle="width:150" />
				    				<label    fieldLabel="联系电话"       name=""                   labelStyle="width:150" />
				    				<label    fieldLabel="邮编"           name=""                   labelStyle="width:150"   />
				    					
				    				<label    fieldLabel="出库日期"  	    name=""                   labelStyle="width:150"  />
				    				<label    fieldLabel="出库仓库"       name=""                   labelStyle="width:150"    />
				    				<label    fieldLabel="收货人"         name="custInfo.commCode"  labelStyle="width:150" />
				    				<label    fieldLabel="传真"           name=""                   labelStyle="width:150"  />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<label     fieldLabel="备注"          name=""                   labelStyle="width:150"  />
				    			</layoutpanel>	
				    		</listpanel>
			    		</items></form>
			    		
			    		<complexgrid id="warehousegrid" frame="true" height="200" deftbar="false" url="1" title="出库详情" itemsFun="warehousegridFun" editorFlag="false"/>
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="取消"         id="backBtn"   />
			    			</buttons>
			    		</panel>
			    		
			    	<!-- service ext ui.  end. -->
			    	</items>
			    </panel>
			  </items>
			</viewport>
		</application>
	</xmp>
</body>
</html>
<script type="text/javascript" src="<%=contextPath %>/warehouse/editorwarehouseout.js"></script>