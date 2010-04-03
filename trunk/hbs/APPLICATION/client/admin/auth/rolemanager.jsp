<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色管理</title>
	<script type="text/javascript" src="<%=contextPath %>/customer/common/CommonPro.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/customer/component/querycustomer.js"></script>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    		<panel>
			    			<buttons>
			    				<button text="新增" id="addBtn" />
			    			</buttons>
			    		</panel>
			    		
			    		<queryform gridId="querygrid">
			    			<layoutpanel columnNum="2:.33,.33">
			    				<textfield fieldLabel="角色名称"                   name="vendorInfo.commCode"    />
			    				<textfield fieldLabel="角色描述"                   name="vendorInfo.commCode"    />
			    			</layoutpanel>
			    		</queryform>
			    		
				    	<complexgrid id="querygrid" title="角色列表" frame="true" page="true" root="data.list" url="/auth/role!list.action">
				    		<fields>
				    			<field name="roleId" />
			    				<field name="roleName"	/>
			    				<field name="memo"	/>
			    			</fields>
			    			
			    			<columns>
			    				<column header="角色名称"       dataIndex="roleName"  id="roleName"  width="150" />
			    				<column header="角色描述"       dataIndex="memo"                     width="500" />
			    				<column header="操作"			      dataIndex=""          id="operator"  width="150" />
			    			</columns>
				    	</complexgrid>
			    		
			    	<!-- service ext ui.  end. -->
			    	</items>
			    </panel>
			  </items>
			</viewport>
		</application>
	</xmp>
</body>
</html>
<script type="text/javascript" src="<%=contextPath %>/auth/rolemanager.js"></script>