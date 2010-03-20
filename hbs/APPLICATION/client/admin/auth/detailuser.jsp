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
				    				<label fieldLabel="帐户"                   name="account.account"             labelStyle="width:150"   />
				    				<label fieldLabel="性别"                   name="staff.gender"           labelStyle="width:150" />	
				    				<label fieldLabel="职务"                   name="staff.duty"              labelStyle="width:150" />
				    				<label fieldLabel="手机"                   name="staff.mobile"              labelStyle="width:150" />
				    				<label fieldLabel="邮箱"                   name="staff.email"              labelStyle="width:150" />
				    				
				    				<label fieldLabel="姓名"                   name="staff.staffName"       labelStyle="width:150" />
				    				<label fieldLabel="出生日期"               name="staff.birthDate"               labelStyle="width:150" />
				    				<label fieldLabel="身份证号"               name="staff.identityNumber"            labelStyle="width:150" />	
				    				<label fieldLabel="电话"                   name="staff.phone"              labelStyle="width:150" />
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