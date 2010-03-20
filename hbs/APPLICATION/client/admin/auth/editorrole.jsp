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
				    				<textfield fieldLabel="帐户"                   name="vendorInfo.commCode"           labelStyle="width:150" />
				    				<dictcombo fieldLabel="性别"                   hiddenName="vendorInfo.creditRate"     labelStyle="width:150"  paramsValue="CREDIT_RATE"     emptyText="请选择" />	
				    				<textfield fieldLabel="职务"                   name="vendorInfo.allName"              labelStyle="width:150" />
				    				<textfield fieldLabel="密码"                   name="vendorInfo.shortName"            labelStyle="width:150"  inputType="password" />
				    				<textfield fieldLabel="手机"                   name="vendorInfo.address"              labelStyle="width:150" />
				    				<textfield fieldLabel="邮箱"                   name="vendorInfo.taxCode"              labelStyle="width:150" />
				    				
				    				<textfield fieldLabel="姓名"                   name="vendorInfo.representative"       labelStyle="width:150" />
				    				<datefield fieldLabel="出生日期"               name="vendorInfo.enName"               labelStyle="width:150"  width="123"  format="Y-m-d"/>
				    				<textfield fieldLabel="身份证号"               name="vendorInfo.enAddress"            labelStyle="width:150" />	
				    				<textfield fieldLabel="确认密码"               name="vendorInfo.enShortName"          labelStyle="width:150"  inputType="password"/>
				    				<textfield fieldLabel="电话"                   name="vendorInfo.webSite"              labelStyle="width:150" />
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<hidden name="vendorInfo.baseSeqId" />
				    				<hidden name="vendorInfo.state" />
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
<script type="text/javascript" src="<%=contextPath %>/auth/editorrole.js"></script>