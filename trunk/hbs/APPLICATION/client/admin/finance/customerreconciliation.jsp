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
			    				<label        fieldLabel="结算方式"            name="" id="offperiod" />
			    			</layoutpanel>
			    			<layoutpanel columnNum="1">
			    				<label        fieldLabel=" 对帐单注意事项" name="" id="cinfo" />
			    			</layoutpanel>
			    		</listpanel>
			    		
			    		<complexgrid id="financegrid" title="联系人员信息" itemsFun="contactFun" itemsAlign="after" frame="true" height="150" url="1" editorFlag="false">
			    			
			    		</complexgrid>
			    	
			    	
			    		<queryform gridId="settlementgrid" exportId="exportBtn">
			    			<layoutpanel columnNum="6:.11,.05,.15,.05,.2,">									
									<label     fieldLabel="日期范围" />
									<label     fieldLabel="从"       labelSeparator=""/>
									<datefield fieldLabel="起始时间" hideLabel="true"  name="warehouseSendDetail.dynamicFields.beginPoNoDate" format="Y-m-d" />
									<label     fieldLabel="到"       labelSeparator=""/>
									<datefield fieldLabel="结束时间" hideLabel="true"  name="warehouseSendDetail.dynamicFields.endPoNoDate" format="Y-m-d" />	
									<textfield fieldLabel="帐期"     name="warehouseSendDetail.financePeriod" id="wsdfinancePeriod" />
			    			</layoutpanel>
			    			<layoutpanel columnNum ="1">
			    				<dictcombo    fieldLabel="财务状态"           hiddenName="warehouseSendDetail.financeState"    paramsValue="WAREHOUSE_FINANCE_STATE"     showText="请选择" />
			    			</layoutpanel>
			    			<layoutpanel columnNum ="1">
			    				<hidden name="warehouseSendDetail.custCode" id="wsdcustCode" />
			    			</layoutpanel>
			    		</queryform>
				    		
				    	<complexgrid id="settlementgrid" title="结算信息列表" frame="true" page="false" root="data.list" url="/warehouseSend/warehouseSend!listFinanceDetail.action">
				    		<fields>
				    			<field name="sendSeqId" />
				    			<field name="createTime" />
				    			<field name="sendPoNo" />
				    			<field name="rltPoNo" />
				    			<field name="partNo" />
				    			<field name="custPartNo" />
				    			<field name="pnDesc" />
				    			<field name="financeStateDesc" />
				    			<field name="financeState" />
				    			<field name="financeStateDesc" />
				    			<field name="financePeriod" />
				    			<field name="amount" />				    								
				    			<field name="curMoney" />
				    			<field name="settlementType" />
				    		</fields>
				    		<columns>       
				    			<column isCheck="true"      dataIndex="sendSeqId" />
				    			<column header="出库日期"   dataIndex="createTime" />
				    			<column header="出库单号"   dataIndex="sendPoNo" />
				    			<column header="客户订单号"   dataIndex="rltPoNo" />
				    			<column header="GLE P/N"    dataIndex="partNo" />
				    			<column header="客户 P/N"   dataIndex="custPartNo" />
				    			<column header="描述"       dataIndex="pnDesc" />				    			
				    			<column header="是否对帐"   dataIndex="financeStateDesc"  />				    				
				    			<column header="财务账期"   dataIndex="financePeriod"  />	
				    			<column header="数量"       dataIndex="amount"  />				    			
				    			<column header="金额"       dataIndex="curMoney"  />
				    		</columns>
				    		
				    		<submitFields value="sendSeqId,settlementType" />
				    	</complexgrid>
				    	
				    	<panel buttonAlign="center">
			    			<buttons>
			    				<button text="确认对帐" id="submitBtn" url="/warehouseSend/warehouseSend!confirmFinancePeriod.action" />
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