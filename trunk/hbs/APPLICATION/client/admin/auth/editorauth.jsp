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
				    		<listpanel frame="true" title="用户信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="1">
				    				<dictcombo fieldLabel="请选择用户" hiddenName="userRole.staffId"     labelStyle="width:150"  url="/auth/user!list.action" valueField="staffId" displayField="staffName" root="data.list"     emptyText="请选择" id="staffId" />	
				    			</layoutpanel>
				    		</listpanel>
				    	
				    		<listpanel frame="true" title="角色信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="1">
				    				<itemselector fieldLabel="请选择需要的角色"                   name="roleId"     labelStyle="width:150" id="selectrolepanel">
				    					<multiselects>
				    						<multiselect width="250" height="200" legend="可选的角色" url="/auth/role!list.action" valueField="roleId" displayField="roleName" root="data.list" />
				    						<multiselect width="250" height="200" legend="己选的角色" store="[]" valueField="roleId" displayField="roleName" />
				    					</multiselects>
				    				</itemselector>
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
<script type="text/javascript" src="<%=contextPath %>/auth/editorauth.js"></script>