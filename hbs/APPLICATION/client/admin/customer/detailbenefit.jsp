<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>销售客户利润明细</title>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
				    	<complexgrid id="querygrid" height="500" title="销售客户利润明细列表" storeAutoLoad="true" frame="true" root="data.list" url="/invoice/Benefit!listDetail.action?benefitDetail.custCode=${param.custCode}&amp;benefitDetail.dynamicFields.sendMonth=${param.sendMonth}&amp;benefitDetail.salesId=${param.salesId}&amp;benefitDetail.custPartNo=${param.custPartNo}">
				    		<fields>
				    			<field name="custShortName" />
				    			<field name="custCode" />
								<field name="salesId" />
				    			<field name="salesName" />
								<field name="sendPoNo" />
								<field name="createTime" />
				    			<field name="custPartNo" />
				    			<field name="pnDesc" />
				    			<field name="partNo" />
				    			<field name="custPrice" />
								<field name="custTaxRate" />
				    			<field name="amount" />
				    			<field name="curMoney" />
				    			<field name="benefit" />
								<field name="venderShortName" />								
				    		</fields>
				    		<columns>
				    			<column header="客户简称"     dataIndex="custShortName" />
				    			<column header="客户编码"     dataIndex="custCode"  id="commCode" columnState="final"/>
								<column header="业务员"     dataIndex="salesName"   />
								<column header="发货单号"     dataIndex="sendPoNo"   />
								<column header="发货日期"     dataIndex="createTime"   />
				    			<column header="客户物料"         dataIndex="custPartNo"      />
								<column header="物料描述"         dataIndex="pnDesc"       />
								<column header="公司物料"         dataIndex="partNo"        />
								<column header="单价"         dataIndex="custPrice"         />
								<column header="税率"         dataIndex="custTaxRate"         />
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
<script type="text/javascript" src="<%=contextPath %>/customer/detailbenefit.js"></script>