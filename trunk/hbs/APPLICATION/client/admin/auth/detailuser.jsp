<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户查看</title>
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
				    		<listpanel frame="true" title="客户基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<label fieldLabel="帐户"                   name="vendorInfo.commCode"             labelStyle="width:150"   />
				    				<label fieldLabel="性别"                   name="vendorInfo.creditRate"           labelStyle="width:150" />	
				    				<label fieldLabel="职务"                   name="vendorInfo.allName"              labelStyle="width:150" />
				    				<label fieldLabel="手机"                   name="vendorInfo.address"              labelStyle="width:150" />
				    				<label fieldLabel="邮箱"                   name="vendorInfo.taxCode"              labelStyle="width:150" />
				    				
				    				<label fieldLabel="姓名"                   name="vendorInfo.representative"       labelStyle="width:150" />
				    				<label fieldLabel="出生日期"               name="vendorInfo.enName"               labelStyle="width:150" />
				    				<label fieldLabel="身份证号"               name="vendorInfo.enAddress"            labelStyle="width:150" />	
				    				<label fieldLabel="电话"                   name="vendorInfo.webSite"              labelStyle="width:150" />
				    			</layoutpanel>
				    		</listpanel>
				    			
			    		</items></form>
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
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
<script type="text/javascript" src="<%=contextPath %>/auth/detailuser.js"></script>