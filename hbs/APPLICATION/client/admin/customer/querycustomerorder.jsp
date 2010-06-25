<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>客户结算查询</title>
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
			    				<autocomplete fieldLabel="客户简称" url="/customerInfo/customerInfoMgr!listDict.action?custInfo.state=0"  displayField="shortName"  valueField="shortName" queryParam="custInfo.shortName"     name="settlement.shortName"       labelStyle="width:150" allowBlank="true" id="acShortName" />
			    				<autocomplete fieldLabel="客户编码" url="/customerInfo/customerInfoMgr!listDict.action?custInfo.state=0"  displayField="commCode"  valueField="commCode" queryParam="custInfo.commCode"     name="settlement.commCode"       labelStyle="width:150" allowBlank="true" id="acCommCode" />
			    				
								<dictcombo fieldLabel="结算状态"           hiddenName="settlement.financeState"             paramsValue="F_STATE" emptyText="请选择" />
			    				<dictcombo fieldLabel="结算类型"           hiddenName="settlement.settlementType"             paramsValue="SETTLEMENT_TYPE" emptyText="请选择" id="vSettlementType" />
			    			</layoutpanel>
			    			<layoutpanel columnNum="1">
			    				<hidden name="roleType" value="${param.roleType}" />
			    			</layoutpanel>
			    		</queryform>
				    		
				    	<complexgrid id="querygrid" title="客户结算信息列表" frame="true" page="true" root="data.list" autoExpandColumn="zaiyao" url="/invoice/CustSettlement!list.action">
				    		<fields>
				    			<field name="shortName" />
				    			<field name="commCode" />
				    			<field name="summery" />
								<field name="settlementType" />
				    			<field name="settlementTypeDesc" />
				    			<field name="totalMoney" />
				    			<field name="needMoney" />
				    			<field name="dealMoney" />
				    			<field name="curMoney" />
				    			<field name="financeState" />
				    			<field name="financeStateDesc" />
								<field name="salesId" />
								<field name="salesName" />
								<field name="assId" />
								<field name="assName" />
				    		</fields>
				    		<columns>
				    			<column header="客户简称"     dataIndex="shortName" />
				    			<column header="客户编码"     dataIndex="commCode"  id="commCode" columnState="final"/>
								<column header="业务员"     dataIndex="salesName"   />
				    			<column header="摘要"         dataIndex="summery"      id="zaiyao"   />
				    			<column header="结算方式"     dataIndex="settlementTypeDesc"   />
				    			<column header="总金额"       dataIndex="totalMoney"    />
				    			<column header="待收款金额"   dataIndex="needMoney"    />
				    			<column header="已收款金额"   dataIndex="dealMoney" />
				    			<column header="本次收款金额" dataIndex="curMoney"    xtype="textfield" hidden="${param.roleType != 'caiwu'}" id="bcskje"/>
				    			<column header="结算状态"     dataIndex="financeStateDesc"    />
				    			<column header="操作"         dataIndex=""          id="operator"     hidden="${param.roleType != 'caiwu'}"/>
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
<script type="text/javascript" src="<%=contextPath %>/customer/querycustomerorder.js"></script>