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
				    			<layoutpanel columnNum="2">
				    				<textfield fieldLabel="帐户"                   name="account.account"        labelStyle="width:150"  allowBlank="false" />
				    				<dictcombo fieldLabel="性别"                   hiddenName="staff.gender"     labelStyle="width:150"  paramsValue="GENDER"     emptyText="请选择" />	
				    				<textfield fieldLabel="职务"                   name="staff.duty"             labelStyle="width:150" />
				    				<textfield fieldLabel="密码"                   name="account.password"       labelStyle="width:150"  allowBlank="false" inputType="password" id="paw1" />
				    				<textfield fieldLabel="手机"                   name="staff.mobile"           labelStyle="width:150" />
				    				<textfield fieldLabel="邮箱"                   name="staff.email"            labelStyle="width:150" />
				    				
				    				<textfield fieldLabel="姓名"                   name="staff.staffName"        labelStyle="width:150" />
				    				<datefield fieldLabel="出生日期"               name="staff.birthDate"        labelStyle="width:150"  width="123"  format="Y-m-d"/>
				    				<textfield fieldLabel="身份证号"               name="staff.identityNumber"   labelStyle="width:150" />	
				    				<textfield fieldLabel="确认密码"               name="account.repassword"     labelStyle="width:150"  allowBlank="false"  inputType="password" id="paw2" relate="paw1|;==|;两次密码输入不一致。" vtype="commCheck"/>
				    				<textfield fieldLabel="电话"                   name="staff.phone"            labelStyle="width:150" />
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<hidden name="staff.staffId" />
				    				<hidden name="account.staffId" />
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
<script type="text/javascript" src="<%=contextPath %>/auth/editoruser.js"></script>