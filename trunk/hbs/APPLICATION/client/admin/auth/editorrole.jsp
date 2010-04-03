<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
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
				    		<listpanel frame="true" title="基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="1">
				    				<textfield fieldLabel="角色名称"               name="role.roleName"           labelStyle="width:150" />
				    				<textarea  fieldLabel="角色描述"               name="role.memo"           labelStyle="width:150" width="600" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<hidden name="role.roleId" />
				    			</layoutpanel>
				    		</listpanel>
				    		
				    		<checkboxgrouppanel title="角色对应的资源信息" id="checkboxgrouppanel" url="/auth/resource!list2.action" panelTitle="title" />
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
<script type="text/javascript" src="<%=contextPath %>/auth/editorrole.js"></script>