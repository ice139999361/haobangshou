<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改客户销售人员信息</title>	
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
								 <autocomplete fieldLabel="客户简称" url="/customerInfo/customerInfoMgr!listDict.action?custInfo.state=0"  displayField="shortName"  valueField="shortName" queryParam="custInfo.shortName"     name="custInfo.shortName"       labelStyle="width:150" allowBlank="false" id="shortName" />				    						    				
				    				<label fieldLabel="客户法人代表"           name="custInfo.representative"   id="representative"  labelStyle="width:150" />
								</layoutpanel>


								<layoutpanel columnNum="2">	
								<autocomplete fieldLabel="客户编码" url="/customerInfo/customerInfoMgr!listDict.action?custInfo.state=0"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name="custInfo.commCode"       labelStyle="width:150" allowBlank="false" id="commCode" />
								   
				    				
									<label fieldLabel="英文简称"               name=""    id="enShortName"    labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="2">	
				    				<label fieldLabel="客户信用度"             name=""      id="creditDesc"   labelStyle="width:150" />
									<label fieldLabel="客户的重要程度"         name=""   id="importantDesc"   labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="2">	
				    				<label fieldLabel="性质"                   name=""        id="commType"   labelStyle="width:150" />
									<label fieldLabel="规模"                   name=""       id="commScale"   labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="1">
									<label fieldLabel="公司中文名称"           name=""         id="allName"   labelStyle="width:150" width="600" />
									<label fieldLabel="客户公司地址"           name=""         id="address"   labelStyle="width:150" width="600"/>
									<label fieldLabel="公司英文名称"           name=""          id="enName"   labelStyle="width:150" width="600"/>
				    				<label fieldLabel="公司英文地址"           name=""       id="enAddress"   labelStyle="width:150" width="600"/>	
				    				<label fieldLabel="客户纳税人识别号"       name=""         id="taxCode"   labelStyle="width:150" width="600"/>				    				
				    				<label fieldLabel="客户公司网址"           name=""         id="webSite"   labelStyle="width:150" width="600"/>
								</layoutpanel>					
				    			<layoutpanel columnNum="2">
				    				<label fieldLabel="对应的分公司或分支机构" name="" id="companyBranchDesc" labelStyle="width:150" />
				    				<label fieldLabel="发货单是否显示单价"     name=""   id="isShowPriceDesc" labelStyle="width:150" />
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<label fieldLabel="客户备注"               name=""      id="commDesc" labelStyle="width:150" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="2">
				    				<label fieldLabel="原销售人员"           name=""         id="staffName" labelStyle="width:150" />
									<dictcombo fieldLabel="新销售人员"       hiddenName="custInfo.staffId"     labelStyle="width:150"  url="/auth/user!listByRoleId.action?roleId=5" root="data.list"  displayField="staffName"  valueField="staffId" />
				    			</layoutpanel>
								<layoutpanel columnNum="2">
				    				<label fieldLabel="原业务部助理"   name=""      id="assStaffName" labelStyle="width:150" />
									<dictcombo fieldLabel="对应的业务部助理"       hiddenName="custInfo.assStaffId"     labelStyle="width:150"  url="/auth/user!listByRoleId.action?roleId=7" root="data.list"  displayField="staffName"  valueField="staffId" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<hidden name="custInfo.baseSeqId" id="baseSeqId" />
				    				
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
<script type="text/javascript" src="<%=contextPath %>/customer/updatedetailcustomer.js"></script>