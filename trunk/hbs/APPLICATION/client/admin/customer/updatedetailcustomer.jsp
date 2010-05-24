<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>销售人员修改</title>	
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
								 <autocomplete fieldLabel="客户简称" url="/customerInfo/customerInfoMgr!listDict.action?custInfo.state=0"  displayField="shortName"  valueField="shortName" queryParam="custInfo.shortName"     name="custInfo.shortName"       labelStyle="width:150" allowBlank="false" id="acShortName" />
				    				<label fieldLabel="客户简称"               name="custInfo.shortName"          labelStyle="width:150" />				    				
				    				<label fieldLabel="客户法人代表"           name="custInfo.representative"     labelStyle="width:150" />
								</layoutpanel>


								<layoutpanel columnNum="2">	
								    <label fieldLabel="客户编码"               name="custInfo.commCode"           labelStyle="width:150" id="abcde"/>
				    				
									<label fieldLabel="英文简称"               name="custInfo.enShortName"        labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="2">	
				    				<label fieldLabel="客户信用度"             name="custInfo.creditDesc"         labelStyle="width:150" />
									<label fieldLabel="客户的重要程度"         name="custInfo.importantDesc"      labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="2">	
				    				<label fieldLabel="性质"                   name="custInfo.commType"           labelStyle="width:150" />
									<label fieldLabel="规模"                   name="custInfo.commScale"          labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="1">
									<label fieldLabel="公司中文名称"           name="custInfo.allName"            labelStyle="width:150" width="600" />
									<label fieldLabel="客户公司地址"           name="custInfo.address"            labelStyle="width:150" width="600"/>
									<label fieldLabel="公司英文名称"           name="custInfo.enName"             labelStyle="width:150" width="600"/>
				    				<label fieldLabel="公司英文地址"           name="custInfo.enAddress"          labelStyle="width:150" width="600"/>	
				    				<label fieldLabel="客户纳税人识别号"       name="custInfo.taxCode"            labelStyle="width:150" width="600"/>				    				
				    				<label fieldLabel="客户公司网址"           name="custInfo.webSite"            labelStyle="width:150" width="600"/>
								</layoutpanel>					
				    			<layoutpanel columnNum="2">
				    				<label fieldLabel="对应的分公司或分支机构" name="custInfo.companyBranchDesc"  labelStyle="width:150" />
				    				<label fieldLabel="销售人员名字"           name="custInfo.staffName"          labelStyle="width:150" />
				    				<label fieldLabel="对应的业务部助理姓名"   name="custInfo.assStaffName"       labelStyle="width:150" />
				    				<label fieldLabel="发货单是否显示单价"     name="custInfo.isShowPriceDesc"    labelStyle="width:150" />
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<label fieldLabel="客户备注"               name="custInfo.commDesc"       labelStyle="width:150" />
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<hidden name="custInfo.baseSeqId" />
				    				<hidden name="custInfo.state" />									
									<hidden name="custInfo.assStaffId" />
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
<script type="text/javascript" src="<%=contextPath %>/customer/detailcustomer.js"></script>