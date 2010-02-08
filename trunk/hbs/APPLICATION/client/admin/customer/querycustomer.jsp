<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>客户信息查询</title>
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
			    	
			    		<queryform gridId="querygrid" exportId="exportBtn">
			    			<layoutpanel columnNum="6:.3,.11,.05,.15,.05,.3">
			    				<textfield fieldLabel="客户简称"               name="custInfo.shortName" />
			    				<label     fieldLabel="客户录入时间"          />
			    				<label     fieldLabel="从"  labelSeparator="" />
			    				<datefield hideLabel="true" format="Y-m-d"    width="120" />
			    				<label     fieldLabel="到"  labelSeparator="" />
			    				<datefield hideLabel="true" format="Y-m-d"    width="120" />
			    			</layoutpanel>
			    			<layoutpanel columnNum="2:.3,.3">
			    				<checkbox  labelSeparator=""                   name=""  boxLabel="精确查找" />
			    				<textfield fieldLabel="客户编码"               name="custInfo.commCode"  />
			    			</layoutpanel>
			    		</queryform>
				    		
				    		
				    	
			
				    	<complexgrid id="querygrid" title="客户信息列表" frame="true" page="true" root="data.list" url="/customerInfo/customerInfo!list.action">
			    			<fields>
			    				<field name="baseSeqId"     />
			    				<field name="shortName"     />
			    				<field name="commCode"      />
			    				<field name="allName"       />
			    				<field name="address"       />
			    				<field name="creditDesc"    />
			    				<field name="importantDesc" />
			    			</fields>
			    			
			    			<columns>
			    				<column header="客户简称"       dataIndex="shortName"     id="shortName" />
			    				<column header="客户编码"       dataIndex="commCode"      />
			    				<column header="公司中文名称"   dataIndex="allName"       />
			    				<column header="客户公司地址"   dataIndex="address"       />
			    				<column header="客户信用度"     dataIndex="creditDesc"    />
			    				<column header="客户的重要程度" dataIndex="importantDesc" />
			    				<column header="操作"           dataIndex=""              id="operator" />
			    			</columns>
			    		</complexgrid>
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<exportbutton text="导出" url="/test2.action" />
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
<script type="text/javascript" src="<%=contextPath %>/customer/querycustomer.js"></script>