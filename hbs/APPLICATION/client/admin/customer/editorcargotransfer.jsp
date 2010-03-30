<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新增调货申请</title>
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
				    		<listpanel frame="true" title="调货信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<autocomplete fieldLabel="GLE物料编码"	url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"           name="custOrder.commCode"       labelStyle="width:150" />
				    				<autocomplete fieldLabel="从 客户编码"	url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"           name="custOrder.commCode"       labelStyle="width:150" />
				    				<autocomplete fieldLabel="到 客户编码"	url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"           name="custOrder.commCode"       labelStyle="width:150" />
				    				<textfield    fieldLabel="申请调货数量"           name="custOrder.shortName"	               labelStyle="width:150"/>
				    				
				    				<textfield    fieldLabel="物料描述"           name="custOrder.shortName"	      readOnly="true"              labelStyle="width:150"/>
				    				<textfield    fieldLabel="客户简称"           name="custOrder.shortName"	      readOnly="true"              labelStyle="width:150"/>
				    				<textfield    fieldLabel="客户简称"           name="custOrder.shortName"	      readOnly="true"              labelStyle="width:150"/>
				    				<textfield    fieldLabel="物料提供供应商"     name="custOrder.shortName"	                 labelStyle="width:150"/>
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<textarea     fieldLabel="申请调货原因"               name="custInfo.commDesc"              labelStyle="width:150" width="600" height="80" />
				    			</layoutpanel>
				    		</listpanel>
			    		</items></form>
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="提交" id="submitBtn" />
			    				<button text="取消" id="backBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/customer/editorcargotransfer.js"></script>