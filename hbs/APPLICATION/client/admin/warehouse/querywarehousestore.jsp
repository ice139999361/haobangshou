<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>已有发票记录查看</title>
	<script type="text/javascript" src="<%=contextPath %>/invoice/component/customerinvoicecommonpro.js"></script>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    		<queryform gridId="querygrid" exportId="exportBtn">
			    			<layoutpanel columnNum="3">
			    				<dictcombo fieldLabel="仓库类型"         hiddenName="custInfo.companyBranch"  paramsValue="COMPANY_BRANCH"  showText="请选择" />
			    				<dictcombo fieldLabel="用途"             hiddenName="custInfo.companyBranch"  paramsValue="COMPANY_BRANCH"  showText="请选择" />
			    					
			    				<textfield fieldLabel="公司物料编码"     name="custOrder.commCode"  />
			    				<datefield fieldLabel="供应商编码"       name="" format="Y-m"/>
			    				
			    				<textfield fieldLabel="供应商物料编码"   name="custOrder.poNo"  />	
			    				<datefield fieldLabel="客户编码"         name="" format="Y-m"/>
			    			</layoutpanel>
			    		</queryform>
			    	
				    	<complexgrid id="querygrid" title="库存列表" frame="true" page="true" root="data.list">
				    		<fields>
				    			<field name="l1" />
				    			<field name="l2" />
				    			<field name="l3" />
				    			<field name="l4" />
				    			<field name="l5" />
				    			<field name="l6" />
				    			<field name="l7" />
				    			<field name="l8" />
				    			<field name="l9" />
				    		</fields>
				    		<columns>
				    			<column header="仓库类型"   dataIndex="l1" />
				    			<column header="用途"       dataIndex="l2" />
				    			<column header="GLE P/N"    dataIndex="l3" />
				    			<column header="供应商编码" dataIndex="l4" />
				    			<column header="供应商 P/N" dataIndex="l5" />
				    			<column header="客户编码"   dataIndex="l6" />
				    			<column header="物料描述"   dataIndex="l7" />
				    			<column header="库存数量"   dataIndex="l8" />
				    			<column header="可用数量"   dataIndex="l9" />
				    		</columns>
				    	</complexgrid>

							<panel buttonAlign="center">
								<buttons>
				    			<button text="导出" id="exportBtn" />
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
<script type="text/javascript" src="<%=contextPath %>/warehouse/querywarehousestore.js"></script>