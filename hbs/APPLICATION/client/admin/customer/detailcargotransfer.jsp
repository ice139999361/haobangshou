<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>调货信息查看</title>
	<script type="text/javascript" src="<%=contextPath %>/customer/common/CommonPro.js"></script>
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
				    				<label    fieldLabel="GLE物料编码"	      name="adjustInfo.partNo"       labelStyle="width:150" />
				    				<label    fieldLabel="从 客户编码"	      name="adjustInfo.fromCustCode"       labelStyle="width:150" />
				    				<label    fieldLabel="到 客户编码"        name="adjustInfo.toCustCode"       labelStyle="width:150" />
				    				<label    fieldLabel="申请调货数量"       name="adjustInfo.applyAmount"	    labelStyle="width:150"/>
				    				
				    				<label    fieldLabel="物料描述"           name="adjustInfo.pnDesc"	    labelStyle="width:150"/>
				    				<label    fieldLabel="客户简称"           name="adjustInfo.fromCustName"	    labelStyle="width:150"/>
				    				<label    fieldLabel="客户简称"           name="adjustInfo.toCustName"	    labelStyle="width:150"/>
				    				<label    fieldLabel="物料提供供应商"     name="adjustInfo.shortName"	    labelStyle="width:150"/>
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<label     fieldLabel="申请调货原因"      name="adjustInfo.applyContent"        labelStyle="width:150" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<hidden    name="adjustInfo.applySeqId" />
				    			</layoutpanel>
				    		</listpanel>				    		
				    		
				    		
				    		<auditpanel id="auditPanel" />
				    			
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
<script type="text/javascript" src="<%=contextPath %>/customer/detailcargotransfer.js"></script>