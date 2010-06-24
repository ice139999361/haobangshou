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
			    				<textfield fieldLabel="客户简称"           name="corderDetail.shortName"  />
			    				<textfield fieldLabel="客户编码"           name="corderDetail.commCode"   />
			    				<textfield fieldLabel="结算状态"           name="corderDetail.poNo"       />
			    				<dictcombo fieldLabel="结算类型"           hiddenName="corderDetail.settlementType"             paramsValue="SETTLEMENT_TYPE" emptyText="请选择" id="vSettlementType" />
			    			</layoutpanel>
			    			<layoutpanel columnNum="1">
			    				<hidden name="roleType" value="${param.roleType}" />
			    			</layoutpanel>
			    		</queryform>
				    		
				    	<complexgrid id="querygrid" title="客户结算信息列表" frame="true" page="true" root="data.list" autoExpandColumn="zaiyao" url="/test1.action">
				    		<fields>
				    			<field name="shortName" />
				    			<field name="commCode" />
				    			<field name="poNo" />
				    			<field name="cpartNo" />
				    			<field name="partNo" />
				    			<field name="pnDesc" />
				    			<field name="verDeliveryDate" />
				    			<field name="amount" />
				    			<field name="jszt" />
				    			<field name="deliveryAmount" />
				    			<field name="specDesc" />
				    		</fields>
				    		<columns>
				    			<column header="客户简称"     dataIndex="shortName" />
				    			<column header="客户编码"     dataIndex="commCode"  id="commCode" columnState="final"/>
				    			<column header="摘要"         dataIndex="poNo"      id="zaiyao"   />
				    			<column header="结算方式"     dataIndex="cpartNo"   />
				    			<column header="总金额"       dataIndex="partNo"    />
				    			<column header="待收款金额"   dataIndex="pnDesc"    />
				    			<column header="已收款金额"   dataIndex="verDeliveryDate" />
				    			<column header="本次收款金额" dataIndex="amount"    xtype="textfield" hidden="${param.roleType != 'caiwu'}" id="bcskje"/>
				    			<column header="结算状态"     dataIndex="jszt"    />
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