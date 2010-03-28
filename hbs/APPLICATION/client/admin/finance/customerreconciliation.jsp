<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>客户对帐</title>
	<script type="text/javascript" src="<%=contextPath %>/customer/common/CommonPro.js"></script>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    		<listpanel frame="true" title="客户基本信息" collapsible="true" titleCollapse="true">
			    			<layoutpanel columnNum="3">
			    				<autocomplete fieldLabel="客户编码" url="/customerInfo/customerInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name="custInfo.commCode"       labelStyle="width:150" id="commcode" />
			    				<label        fieldLabel="客户名称"      name="" id="allname" />
			    				<label        fieldLabel="帐期"            name="" id="offperiod" />
			    			</layoutpanel>
			    			<layoutpanel columnNum="1">
			    				<label        fieldLabel=" 对帐单注意事项" name="" id="cinfo" />
			    			</layoutpanel>
			    		</listpanel>
			    		
			    		<complexgrid id="financegrid" title="财务人员信息" itemsFun="contactFun" itemsAlign="after" frame="true" height="150" url="1" editorFlag="false">
			    			<fields>
			    				<field name="conAddress" />
			    				<field name="conZip" />
			    				<field name="isPrimary" />
			    			</fields>
			    			
			    			<columns>
			    				<column header="收货地址" 			dataIndex="conAddress" xtype="textfield" />
			    				<column header="收货邮编" 			dataIndex="conZip"     xtype="textfield" />
			    			</columns>
			    		</complexgrid>
			    	
			    	
			    		<queryform gridId="settlementgrid" exportId="exportBtn">
			    			<layoutpanel columnNum="6:.11,.05,.15,.05,.2,">									
									<label     fieldLabel="日期范围" />
									<label     fieldLabel="从"       labelSeparator=""/>
									<datefield fieldLabel="起始时间" hideLabel="true"  name="" format="Y-m-d" />
									<label     fieldLabel="到"       labelSeparator=""/>
									<datefield fieldLabel="结束时间" hideLabel="true"  name="" format="Y-m-d" />	
									<textfield fieldLabel="帐期"     name=""  />
			    			</layoutpanel>
			    		</queryform>
				    		
				    	<complexgrid id="settlementgrid" title="待结算信息列表" frame="true" page="false" root="data.list" url="/adffds.action">
				    		<fields>
				    			<field name="l1" />
				    			<field name="l2" />
				    			<field name="l3" />
				    			<field name="l4" />
				    			<field name="l5" />
				    			<field name="l6" />
				    			<field name="l7" />
				    			<field name="l8" />
				    		</fields>
				    		<columns>       
				    			<column isCheck="true"      dataIndex="l8" />
				    			<column header="入库日期"   dataIndex="l1" />
				    			<column header="入库单号"   dataIndex="l2" />
				    			<column header="采购单号"   dataIndex="l3" />
				    			<column header="GLE P/N"    dataIndex="l4" />
				    			<column header="客户 P/N" dataIndex="l5" />
				    			<column header="描述"       dataIndex="l6" />
				    			<column header="类型"       dataIndex="l7" />
				    			<column header="是否对帐"   dataIndex="l7"  />
				    			<column header="数量"       dataIndex="l7"  />
				    			<column header="单价"       dataIndex="l7"  />
				    			<column header="金额"       dataIndex="l7"  />
				    		</columns>
				    	</complexgrid>
				    	
				    	<panel buttonAlign="center">
			    			<buttons>
			    				<button text="确认对帐" id="submitBtn" />
			    				<button text="取消"     id="backBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/finance/customerreconciliation.js"></script>