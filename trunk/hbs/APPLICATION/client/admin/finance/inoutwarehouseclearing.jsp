<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>出入库结算</title>
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
			    				<textfield fieldLabel="入库单"     name=""  />
			    				<textfield fieldLabel="出库单"     name=""  />
			    				<dictcombo fieldLabel="查询"       hiddenName="" paramsValue="IMPORTANT_CODE" showText="全部" />
			    			</layoutpanel>
			    			<layoutpanel columnNum="5:.11,.05,.15,.05,.3">									
									<label     fieldLabel="录入时间" />
									<label     fieldLabel="从"       labelSeparator=""/>
									<datefield fieldLabel="起始时间" hideLabel="true"  name="" format="Y-m-d" />
									<label     fieldLabel="到"       labelSeparator=""/>
									<datefield fieldLabel="结束时间" hideLabel="true"  name="" format="Y-m-d" />	
			    			</layoutpanel>
			    		</queryform>
				    		
				    	<complexgrid id="querygrid" title="待结算信息列表" frame="true" page="true" root="data.list" url="/adffds.action">
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
				    			//       
				    			<column header="类型"       dataIndex="l1" />
				    			<column header="单号"       dataIndex="l2" />
				    			<column header="币种"       dataIndex="l3" />
				    			<column header="总金额"     dataIndex="l4" />
				    			<column header="待结算金额" dataIndex="l5" />
				    			<column header="银行"       dataIndex="l6" />
				    			<column header="账号"       dataIndex="l7" />
				    			<column header="操作"       dataIndex="" id="operator" />
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
<script type="text/javascript" src="<%=contextPath %>/finance/inoutwarehouseclearing.js"></script>