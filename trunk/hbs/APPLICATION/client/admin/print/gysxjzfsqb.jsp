<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供应商现金支付申请表</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/print/css/print.css"/>
<script type="text/javascript" src="<%=contextPath %>/print/common/print.js"></script>
</head>

<body><div align="center">
<table width="978" border="0" cellpadding="0" cellspacing="0">
    <tr><td><table width="978" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="286" rowspan="2" align="center"><img src="images/logo002.jpg" width="70" height="50" /></td>
        <td width="406" class="xfont01" align="center" style="font-size:16pt">Shenzhen Glorious Electronic Co.,Ltd</td>
        <td width="286">&nbsp;</td>
      </tr>
      <tr>
        <td class="xfont01" align="center" style="font-size:14pt;">深圳市格莱尔电子有限公司</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td class="xfont01" align="center" style="font-size:14pt;">供应商现金支付申请表</td>
        <td>&nbsp;</td>
      </tr>
    </table></td></tr>
    <tr>
      <td><table width="978" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="64">&nbsp;</td>
          <td width="10">&nbsp;</td>
          <td width="356">&nbsp;</td>
          <td width="240">&nbsp;</td>
          <td width="82">&nbsp;</td>
          <td width="10">&nbsp;</td>
          <td width="216">&nbsp;</td>
        </tr>
        <tr>
          <td height="20" align="left">供应商：</td>
          <td>&nbsp;</td>
          <td align="left" id="vcname">&nbsp;</td>
          <td>&nbsp;</td>
          <td align="left">申请日期：</td>
          <td>&nbsp;</td>
          <td align="left" id="sqrq">&nbsp;</td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td><table width="978" border="0" cellspacing="0" cellpadding="0">
        <tr height="25px">
          <td width="96" class="xl10418761" align="center">送货日期</td>
          <td width="121" class="xl10418761" align="center">送货单/INVOICE</td>
          <td width="94" class="xl10418761" align="center">订单号</td>
          <td width="84" class="xl10418761" align="center">P/N</td>
          <td width="95" class="xl10418761" align="center">数量</td>
          <td width="95" class="xl10418761" align="center">税率</td>
          <td width="95" class="xl10418761" align="center">币别</td>
          <td width="95" class="xl10418761" align="center">单价</td>
          <td width="95" class="xl10418761" align="center">金额</td>
          <td width="108" class="xl10418761" align="center">备注</td>
        </tr>
        <tr height="25px" id="totaltr">
          <td colspan="8" class="xl10418762" align="right">TOTAL</td>
          <td class="xl10418762" id="totalamount" align="center">&nbsp;</td>
          <td class="xl10418762" >&nbsp;</td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td><table width="978" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="234" class="xfont01" align="right">内部资料，注意保密！</td>
          <td width="156">&nbsp;</td>
          <td width="385" align="right">申请人：</td>
          <td id="sqr">&nbsp;</td>
          </tr>
      </table></td>
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
	ExtConvertHelper.request("/common/gysdzd!list.action", null, function(response, opts) {
		var jsonData = Ext.util.JSON.decode(response.responseText);
		if(jsonData.success === false) return;
		
		var templateStr = ['<tr height="25px">'
		,'<td align="center" class="xl10418762">{chrq}</td>'
    ,'<td align="center" class="xl10418762">{shdh}</td>'
    ,'<td align="center" class="xl10418762">{gleddh}</td>'
    ,'<td align="center" class="xl10418762">{wlmc}</td>'
    ,'<td align="center" class="xl10418762">{gysxh}</td>'
    ,'<td align="center" class="xl10418762">{glexh}</td>'
    ,'<td align="center" class="xl10418762">{slpcs}</td>'
    ,'<td align="center" class="xl10418762">{sl}</td>'
    ,'<td align="center" class="xl10418762">{dj}</td>'
    ,'<td align="center" class="xl10418762">{je}</td>'
    ,'</tr>'].join("");
    var template = Ext.DomHelper.createTemplate(templateStr);
    
    // 金额统计
		var amount = 0;
		Ext.each(jsonData.data.list, function(item) {
			template.insertBefore(Ext.get("totaltr"), item);
			// 税率
			amount   += +item.je;
		});
		
		// 填充总金额
		Ext.DomQuery.select("#totalamount")[0].innerHTML = amount;
		
		

		
		var info = jsonData.data;
		// 供应商
		Ext.DomQuery.select("#vcname")[0].innerHTML = info.vcname;
		// 申请日期
		Ext.DomQuery.select("#sqrq")[0].innerHTML = info.sqrq;
		// 申请人
		Ext.DomQuery.select("#sqr")[0].innerHTML = info.sqr;
	});
</script>