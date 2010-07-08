<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>销售利润查询</title>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    		<queryform gridId="querygrid">
			    			<layoutpanel columnNum="3">								
								<dictcombo fieldLabel="业务员"       hiddenName="benefitDetail.staffId"     labelStyle="width:150"  url="/auth/user!listByRoleId.action?roleId=5" root="data.list"  displayField="staffName"  valueField="staffId" allowBlank="false"/>
			    				<autocomplete fieldLabel="客户简称" url="/customerInfo/customerInfoMgr!listDict.action?custInfo.state=0"  displayField="shortName"  valueField="shortName" queryParam="custInfo.shortName"     name="benefitDetail.custShortName"       labelStyle="width:150" allowBlank="true" id="acShortName" />
			    				<autocomplete fieldLabel="客户编码" url="/customerInfo/customerInfoMgr!listDict.action?custInfo.state=0"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name="benefitDetail.custCode"       labelStyle="width:150" allowBlank="true" id="acCommCode" />
			    				<datefield    fieldLabel="发货月份"       name="benefitDetail.sendMonth" 	format="Ym"                       labelStyle="width:150" allowBlank="false"/>
								
			    			</layoutpanel>
							<!--
			    			<layoutpanel columnNum="1">
			    				<hidden name="roleType" value="${param.roleType}" />
			    			</layoutpanel>
							-->
			    		</queryform>
				    		
				    	<complexgrid id="querygrid" title="销售利润信息列表" frame="true" page="true" root="data.list" autoExpandColumn="benefit" url="/invoice/Benefit!listTotal.action">
				    		<fields>
				    			<field name="custShortName" />
				    			<field name="custCode" />
								<field name="salesId" />
				    			<field name="salesName" />
								<field name="sendMonth" />
				    			<field name="custPartNo" />
				    			<field name="pnDesc" />
				    			<field name="partNo" />
				    			<field name="custPrice" />
				    			<field name="amount" />
				    			<field name="curMoney" />
				    			<field name="benefit" />
								<field name="venderShortName" />								
				    		</fields>
				    		<columns>
				    			<column header="客户简称"     dataIndex="custShortName" />
				    			<column header="客户编码"     dataIndex="custCode"  id="custCode" columnState="final"/>
								<column header="业务员"     dataIndex="salesName"   />
								<column header="月份"     dataIndex="sendMonth"   />
				    			<column header="客户物料"         dataIndex="custPartNo"      />
								<column header="物料描述"         dataIndex="pnDesc"       />
								<column header="公司物料"         dataIndex="partNo"        />
								<column header="单价"         dataIndex="custPrice"         />
								<column header="数量"         dataIndex="amount"       />
								<column header="总价"         dataIndex="curMoney"         />
								<column header="利润"         dataIndex="benefit"       id="benefit"  />
								<column header="供应商"         dataIndex="venderShortName"         />
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

<script type="text/javascript" src="<%=contextPath %>/customer/querybenefittotal.js"></script>
