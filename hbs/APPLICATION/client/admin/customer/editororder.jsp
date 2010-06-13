<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="<%=contextPath %>/customer/component/ordercommonpro.js"></script>
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
				    		<listpanel frame="true" title="订单基本信息" collapsible="true" titleCollapse="true">
				    			<layoutpanel columnNum="2">
				    				<autocomplete fieldLabel="客户编码"	url="/customerInfo/customerInfo!list.action?custInfo.state=0"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"           name="custOrder.commCode"       labelStyle="width:150" id="acCommCode" />
				    				<textfield    fieldLabel="客户订单号"         name="custOrder.poNo"                        labelStyle="width:150"/>
				    				<label        fieldLabel="对应分公司"         id="acCompanyBranch"                        labelStyle="width:150"/>


				    				<autocomplete fieldLabel="客户简称" url="/customerInfo/customerInfo!list.action?custInfo.state=0"  displayField="shortName"  valueField="shortName" queryParam="custInfo.shortName"          name="custOrder.shortName"	id="acShortName"                       labelStyle="width:150"/>
				    				<datefield    fieldLabel="客户订单日期"       name="custOrder.oderTime" 	format="Y-m-d"                       labelStyle="width:150" id="acOderTime"/>
				    				<label        fieldLabel="结算类型"           id="acSettlementType"                        labelStyle="width:150"/>
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<hidden name="custOrder.conTel"	id="acTelHidden" />
				    				<hidden name="custOrder.conFax"	id="acFaxHidden" />
				    				<hidden name="custOrder.receiveAddress"	id="acAddressHidden" />
				    				<hidden name="custOrder.receiveZip"	id="acZipHidden" />
				    				<hidden name="custOrder.period" />
				    				<hidden name="custOrder.activeState" />
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<dictcombo    fieldLabel="选择联系人"	id="acContactList"	url="/customerInfo/customerInfo!getContactList.action" record="seqId,conName,conTel,conFax,isPrimary"	root="data.list"	valueField="conName"	displayField="conName"	name="custOrder.conName"                        labelStyle="width:150"/>
				    			</layoutpanel>
				    			<layoutpanel columnNum="2">
				    				<label        fieldLabel="电话"               name="custOrder.conTel"	id="acTel"                        labelStyle="width:150"/>
				    				<label        fieldLabel="传真"               name="custOrder.conFax"	id="acFax"                       labelStyle="width:150"/>
				    			</layoutpanel>
				    			<layoutpanel columnNum="1">
				    				<dictcombo    fieldLabel="选择收货人"	id="acConsigneeList"	url="/customerInfo/customerInfo!getConsigneeList.action" record="seqId,conName,conAddress,conZip,isPrimary"	root="data.list"	valueField="conName"	displayField="conName"	name="custOrder.receiveName"                        labelStyle="width:150"/>
				    			</layoutpanel>
				    			<layoutpanel columnNum="2">
				    				<label        fieldLabel="收货地址"           name="custOrder.receiveAddress"	id="acAddress"                        labelStyle="width:150"/>
				    				<label        fieldLabel="邮编"               name="custOrder.receiveZip"	id="acZip"                        labelStyle="width:150"/>
				    			</layoutpanel>
				    		</listpanel>
			    		</items></form>

			    		<complexgrid id="ordergrid" frame="true" height="200" deftbar="true" url="1" title="订单详情" itemsFun="ordergridFun" />

			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="提交" id="submitBtn" />
			    				<button text="暂存" id="saveBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/customer/editororder.js"></script>