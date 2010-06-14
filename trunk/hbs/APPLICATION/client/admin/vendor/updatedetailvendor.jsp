<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改供应商采购员信息</title>	
</head>

<body>
	<xmp id="config"  style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    	
			    		<form id="form"><items>
				    		<listpanel frame="true" title="供应商基本信息" collapsible="true" titleCollapse="true">
								<layoutpanel columnNum="2">
									<autocomplete fieldLabel="供应商简称" url="/vendorInfo/vendorInfo!listDict.action?vendorInfo.state=0"  displayField="shortName"  valueField="commCode" queryParam="vendorInfo.shortName"     name="vendorInfo.shortName"       labelStyle="width:150" allowBlank="false" id="shortName" minChars="1" />				    				
				    				<label fieldLabel="法人代表"               name=""   id="representative"    labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="2">
									<autocomplete fieldLabel="供应商编码" url="/vendorInfo/vendorInfo!listDict.action?vendorInfo.state=0"  displayField="commCode"  valueField="commCode" queryParam="vendorInfo.commCode"     name="vendorInfo.commCode"       labelStyle="width:150" allowBlank="false" id="commCode"/>				    				
									<label fieldLabel="英文简称"               name=""     id="enShortName"     labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="2">	
				    				<label fieldLabel="信用度"                 name=""    id="creditDesc" labelStyle="width:150"  />
									<label fieldLabel="重要程度"               name="" id="importantDesc" labelStyle="width:150"  />
								</layoutpanel>
								<layoutpanel columnNum="2">	
				    				<label fieldLabel="企业性质"                   name=""      id="commType"       labelStyle="width:150" />
									<label fieldLabel="企业规模"                   name=""     id="commScale"       labelStyle="width:150" />
									<label fieldLabel="企业类型"                   name=""     id="saleType"       labelStyle="width:150" />
								</layoutpanel>
								<layoutpanel columnNum="1">
									<label fieldLabel="中文名称"               name=""       id="allName"       labelStyle="width:150"   width="600" />
									<label fieldLabel="公司地址"               name=""        id="address"      labelStyle="width:150"   width="600" />
									<label fieldLabel="英文名称"               name=""         id="enName"      labelStyle="width:150"   width="600" />
				    				<label fieldLabel="英文地址"               name=""    id="enAddress"        labelStyle="width:150"   width="600" />		
									<label fieldLabel="纳税人识别号"           name=""      id="taxCode"        labelStyle="width:150"   width="600" />
									<label fieldLabel="公司网址"               name=""      id="webSite"        labelStyle="width:150"   width="600" />
								</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<label fieldLabel="对应的分公司或分支机构" name="" id="companyBranchDesc" labelStyle="width:150"  />
				    				
				    			</layoutpanel>
				    			
				    			<layoutpanel columnNum="1">
				    				<label fieldLabel="企业备注/企业经营产品"                    name=""     id="commDesc"        labelStyle="width:150"/>
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">				    				
				    				<label fieldLabel="原采购员"           name=""    id="staffName"        labelStyle="width:150" />
									<dictcombo fieldLabel="新采购员"       hiddenName="vendorInfo.staffId"     labelStyle="width:150;color:red;"  url="/auth/user!listByRoleId.action?roleId=9" root="data.list"  displayField="staffName"  valueField="staffId" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<hidden name="vendorInfo.baseSeqId"  id="baseSeqId" />				    				
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
<script type="text/javascript" src="<%=contextPath %>/vendor/updatedetailvendor.js"></script>