<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GLE 内部备料单</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/print/css/print.css"/>
<script type="text/javascript" src="<%=contextPath %>/print/common/print.js"></script>
</head>

<body><div align="center">
<table width="978" border="0" cellpadding="0" cellspacing="0">
	<tr>
	  <td align="center" class="xfont01" style="font-size:16pt;">GLE 内部备料单</td>
	</tr>
	<tr>
	  <td>&nbsp;</td>
    </tr>
	<tr>
	  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr height="30px">
          <td width="12%" align="right">物料编号：</td>
          <td width="1%">&nbsp;</td>
          <td width="25%" id="wlbh">&nbsp;</td>
          <td width="24%">&nbsp;</td>
          <td width="22%" align="right">操作员：</td>
          <td width="1%">&nbsp;</td>
          <td width="15%" id="czy">&nbsp;</td>
        </tr>
        <tr height="30px">
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td align="right">日期：</td>
          <td>&nbsp;</td>
          <td id="czrq">&nbsp;</td>
        </tr>
      </table></td>
    </tr>
	<tr>
	  <td><table width="974" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="107" align="center" class="xl10418761">GLE型号</td>
          <td width="107" align="center" class="xl10418761">描述</td>
          <td width="107" align="center" class="xl10418761">供应商</td>
          <td width="107" align="center" class="xl10418761">数量</td>
          <td width="107" align="center" class="xl10418761">交期</td>
          <td width="107" align="center" class="xl10418761">客户</td>
          <td width="107" align="center" class="xl10418761">业务员</td>
          <td width="107" align="center" class="xl10418761">审核</td>
          <td width="118" align="center" class="xl10418761">备注</td>
        </tr>
        <tr id="totaltr">
          <td colspan="5" class="xl10418761" align="right">Total:RMB</td>
          <td colspan="4" class="xl10418762" id="totalamount">&nbsp;</td>
          </tr>
      </table></td>
    </tr>
	<tr>
	  <td class="xfont01" style="font-size:14pt;" align="left">内部资料，注意保密！</td>
    </tr>
	<tr>
	  <td>&nbsp;</td>
    </tr>
</table>
<div id="printdiv">
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=0></OBJECT> 
<input type=button name=button_print value="打印"          onclick="javascript:printit();"      /> 
<input type=button name=button_show  value="打印预览"      onclick="javascript:printpreview();" /> 
<input type=button name=button_setup value="打印页面设置"  onclick="javascript:printsetup();"   /> 
</div>
</div></body>
</html>
<script>
	ExtConvertHelper.request("/common/nbbhd-yz!list.action", null, function(response, opts) {
		var jsonData = Ext.util.JSON.decode(response.responseText);
		if(jsonData.success === false) return;

		var templateStr = ['<tr>'
		,'<td align="center" class="xl10418762">{xh}</td>'
    ,'<td align="center" class="xl10418762">{glexh}</td>'
    ,'<td align="center" class="xl10418762">{gysxh}</td>'
    ,'<td align="center" class="xl10418762">{slpcs}</td>'
    ,'<td align="center" class="xl10418762">{bb}</td>'
    ,'<td align="center" class="xl10418762">{glexh}</td>'
    ,'<td align="center" class="xl10418762">{dj}</td>'
    ,'<td align="center" class="xl10418762">{jhrq}</td>'
    ,'<td align="center" class="xl10418762">{bz}</td>'
    ,'</tr>'].join("");
    var template = Ext.DomHelper.createTemplate(templateStr);
		Ext.each(jsonData.data.list, function(item) {
			template.insertBefore(Ext.get("totaltr"), item);
		});
		
		var info = jsonData.data;
		// 填充总金额
		Ext.DomQuery.select("#totalamount")[0].innerHTML = info.amount;
		// 物料编号
		Ext.DomQuery.select("#wlbh")[0].innerHTML = info.wlbh;
		// 操作员
		Ext.DomQuery.select("#czy")[0].innerHTML = info.czy;
		// 日期
		Ext.DomQuery.select("#czrq")[0].innerHTML = info.czrq;
	});
</script>
