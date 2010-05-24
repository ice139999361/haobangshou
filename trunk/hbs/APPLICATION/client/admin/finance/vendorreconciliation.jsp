<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>供应商对帐</title>
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
			    		<listpanel frame="true" title="供应商基本信息" collapsible="true" titleCollapse="true">
			    			<layoutpanel columnNum="3">
			    				<autocomplete fieldLabel="供应商编码" url="/vendorInfo/vendorInfo!list.action"  displayField="commCode"  valueField="commCode" queryParam="vendorInfo.commCode"     name="vendorInfo.commCode"       labelStyle="width:150" id="commcode" />
			    				<label        fieldLabel="供应商名称"      name="" id="allname" />
			    				<label        fieldLabel="帐期"            name="" id="offperiod" />
			    			</layoutpanel>
			    			<layoutpanel columnNum="1">
			    				<label        fieldLabel=" 对帐单注意事项" name="" id="cinfo" />
			    			</layoutpanel>
			    		</listpanel>
			    		
			    		<complexgrid id="financegrid" title="财务人员信息" itemsFun="contactFun" itemsAlign="after" frame="true" height="150" url="1" editorFlag="false">
			    			
			    		</complexgrid>
			    	
			    	
			    		<queryform gridId="settlementgrid">
			    			<layoutpanel columnNum="6:.11,.05,.15,.05,.2,">									
									<label     fieldLabel="日期范围" />
									<label     fieldLabel="从"       labelSeparator=""/>
									<datefield fieldLabel="起始时间" hideLabel="true"  name="warehouseRecDetail.dynamicFields.beginPoNoDate" format="Y-m-d" />
									<label     fieldLabel="到"       labelSeparator=""/>
									<datefield fieldLabel="结束时间" hideLabel="true"  name="warehouseRecDetail.dynamicFields.endPoNoDate" format="Y-m-d" />	
									<textfield fieldLabel="帐期"     name="warehouseRecDetail.financePeriod"  id="wsdfinancePeriod"/>
			    			</layoutpanel>
			    			<layoutpanel columnNum ="1">
			    				<dictcombo    fieldLabel="财务状态"           hiddenName="warehouseRecDetail.financeState"    paramsValue="WAREHOUSE_FINANCE_STATE"     showText="请选择" />
			    			</layoutpanel>
			    			<layoutpanel columnNum ="1">
			    				<hidden name="warehouseRecDetail.vendorCode" id="wsdvendorCode" />
			    			</layoutpanel>
			    		</queryform>
				    		
				    	<complexgrid id="settlementgrid" title="结算信息列表" frame="true" page="false" root="data.list" url="/warehouseRec/warehouseRec!listFinanceDetail.action">
				    		<fields>
				    			<field name="recDetailSeqId" />
				    			<field name="applyDate" />
				    			<field name="recPoNo" />
				    			<field name="rltPoNo" />
				    			<field name="partNo" />
				    			<field name="cpartNo" />
				    			<field name="pnDesc" />
				    			<field name="financePeriod" />
				    			<field name="financeStateDesc" />
				    			<field name="financeState" />				    			
				    			<field name="amount" />				    								
				    			<field name="curMoney" />
				    			<field name="settlementType" />
				    			
				    		</fields>
				    		<columns>       
				    			<column isCheck="true"      dataIndex="recDetailSeqId" />
				    			<column header="入库日期"   dataIndex="applyDate" />
				    			<column header="入库单号"   dataIndex="recPoNo" />
				    			<column header="采购单号"   dataIndex="rltPoNo" />
				    			<column header="GLE P/N"    dataIndex="partNo" />
				    			<column header="供应商 P/N" dataIndex="cpartNo" />
				    			<column header="描述"       dataIndex="pnDesc" />
				    			
				    			<column header="财务账期"   dataIndex="financePeriod"  />
				    			<column header="是否对帐"   dataIndex="financeStateDesc"  />
				    			<column header="数量"       dataIndex="amount"  />
				    			
				    			<column header="金额"       dataIndex="curMoney"  />
				    		</columns>
				    		<submitFields value="recDetailSeqId,settlementType,financeState" />
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
<script type="text/javascript" src="<%=contextPath %>/finance/vendorreconciliation.js"></script>