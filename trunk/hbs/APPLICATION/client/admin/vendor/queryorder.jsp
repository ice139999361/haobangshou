<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>客户订单查询</title>
	<script type="text/javascript" src="<%=contextPath %>/vendor/component/queryorder.js"></script>
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
			    				<textfield fieldLabel="客户编码"           name="custOrder.commCode"  />
			    				<textfield fieldLabel="客户订单号"         name="custOrder.poNo"  />
			    				<datefield fieldLabel="创建年月"           name="" format="Y-m"/>
			    			</layoutpanel>
			    		</queryform>
				    		
				    	<complexgrid id="querygrid" title="客户订单列表" frame="true" page="true" root="data.list" itemsFun="complexgridFun" />
			    	<!-- service ext ui.  end. -->
			    	</items>
			    </panel>
			  </items>
			</viewport>
		</application>
	</xmp>
</body>
</html>
<script type="text/javascript" src="<%=contextPath %>/vendor/queryorder.js"></script>