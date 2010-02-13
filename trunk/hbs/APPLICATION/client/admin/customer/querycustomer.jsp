<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>客户信息查询</title>
	<script type="text/javascript" src="<%=contextPath %>/customer/common/CommonPro.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/customer/component/querycustomer.js"></script>
</head>

<body>
	<xmp id="config" style="display:none">
		<application>
			<viewport layout="fit">
			  <items>
			    <panel frame="true" autoScroll="true">
			    	<items>
			    	<!-- service ext ui.  begin. -->
			    		
			    		<queryform gridId="querygrid" exportId="exportBtn" itemsFun="queryformFun" />
			
				    	<complexgrid id="querygrid" title="客户信息列表" frame="true" page="true" root="data.list" itemsFun="complexgridFun" />
			    		
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