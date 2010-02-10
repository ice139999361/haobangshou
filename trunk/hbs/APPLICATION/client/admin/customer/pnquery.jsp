<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>客户P/N对照查询</title>
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
			    			<layoutpanel columnNum="3">
			    				<textfield fieldLabel="客户编码"           name="custInfo.commCode"  />
			    				<textfield fieldLabel="状态"               name=""  />
			    				<textfield fieldLabel="客户P/N"            name=""  />
			    				<textfield fieldLabel="本公司P/N"          name=""  />
			    			</layoutpanel>
			    		</queryform>
				    		
				    	<complexgrid id="querygrid" title="客户P/N对照列表" frame="true" page="true" root="data.list" url="/customerInfo/customerInfo!list.action">
			    			<fields>
			    				<field name="shortName"     />
			    				<field name="commCode"      />
			    				<field name="allName"       />
			    				<field name="address"       />
			    				<field name="creditDesc"    />
			    				<field name="importantDesc" />
			    				<field name="stateDesc"     />
			    				
			    				<field name="baseSeqId"     />
			    				<field name="state"         />
			    			</fields>
			    			
			    			<columns>
			    				<column isCheck="true"          dataIndex="seqId"         />
			    				<column header="客户编码"       dataIndex="commCode"      />
			    				<column header="客户简称"       dataIndex="shortName"     id="shortName" />
			    				<column header="客户结算币种"   dataIndex="allName"       />
			    				<column header="客户P/N"        dataIndex="address"       />
			    				<column header="本公司P/N"      dataIndex="creditDesc"    />
			    				<column header="描述"           dataIndex="importantDesc" />
			    				<column header="单价"           dataIndex="stateDesc"     />
			    				<column header="税率"           dataIndex=""              />
			    				<column header="最小包装"       dataIndex=""              />
			    				<column header="最小订单量"     dataIndex=""              />
			    				<column header="样品编码"       dataIndex=""              />
			    				<column header="状态"           dataIndex="stateDesc"     />
			    				<column header="操作"           dataIndex=""              id="operator" width="250" />
			    			</columns>
			    		</complexgrid>
			    		
			    		<panel buttonAlign="center">
			    			<buttons>
			    				<button text="删除" id="deleteBtn" />
			    				<button text="恢复" id="reBtn"   />
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
<script type="text/javascript" src="<%=contextPath %>/customer/pnquery.js"></script>