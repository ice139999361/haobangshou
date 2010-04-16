<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>送货单/Delivery</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/print/css/print.css"/>
<script type="text/javascript" src="<%=contextPath %>/print/common/print.js"></script>
</head>

<body><div align="center">
<table width="650" border="0" cellpadding="0" cellspacing="0">
	<tr>
	  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="646" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="145" rowspan="3" valign="top" align="center"><img src="images/logo002.jpg" width="70" height="50" /></td>
              <td width="501" class="xfont01" style="font-size:16pt" align="left">Shenzhen Glorious Electronic Co.,Ltd</td>
            </tr>
            <tr>
              <td class="xfont01" style="font-size:14pt" align="left">深圳市格莱尔电子有限公司</td>
            </tr>
            <tr>
              <td style="font-size:10pt" align="left">Room 15-16,15/F,Nobl Plaza 1st Qianjin Road Baoan District Shenzhen,Guangdong,China</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td align="left">深圳市前进一路诺铂广场1515-1516室</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td align="left">Tel:0755-27815501-03  Fax:0755-27815500</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td align="left"><a href="http://www.glecorp.com" target="_blank">http://www.glecorp.com</a></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td></td>
        </tr>
        <tr>
          <td><table width="646" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="646" align="center" class="xfont01" style="font-size:14pt;">送货单/Delivery</td>
            </tr>
            <tr>
              <td style="border-top:3pt solid windowtext;border-bottom:.5pt solid windowtext;font-size:2px;">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="15%" align="right" class="xfont01">送货日期：</td>
              <td width="1%">&nbsp;</td>
              <td width="18%" align="left" class="xfont01" id="shrq">&nbsp;</td>
              <td width="11%">&nbsp;</td>
              <td width="1%">&nbsp;</td>
              <td width="15%">&nbsp;</td>
              <td width="15%" class="xfont01" align="right">送货单号：</td>
              <td width="1%">&nbsp;</td>
              <td width="23%" class="xfont01" align="left" id="shdh">&nbsp;</td>
            </tr>
            <tr>
              <td class="xfont01" align="right">客户名称：</td>
              <td>&nbsp;</td>
              <td colspan="4" class="xfont01" align="left" id="cuname">&nbsp;</td>
              <td align="right" class="xfont01">发票号：</td>
              <td>&nbsp;</td>
              <td class="xfont01" align="left" id="fph">&nbsp;</td>
            </tr>
            <tr>
              <td class="xfont01" align="right">联系电话：</td>
              <td>&nbsp;</td>
              <td class="xfont01" align="left" id="culxdh">&nbsp;</td>
              <td class="xfont01" align="right">联络人:</td>
              <td>&nbsp;</td>
              <td class="xfont01" align="left" id="culxr">&nbsp;</td>
              <td class="xfont01" align="right">业务员：</td>
              <td>&nbsp;</td>
              <td class="xfont01" align="left" id="ywy">&nbsp;</td>
            </tr>
            <tr>
              <td class="xfont01" align="right">送货地址1：</td>
              <td>&nbsp;</td>
              <td colspan="4" class="xfont01" align="left" id="cuaddress1">&nbsp;</td>
              <td class="xfont01" align="right">销售部门：</td>
              <td>&nbsp;</td>
              <td class="xfont01" align="left" id="xsbm">市场部</td>
            </tr>
            <tr>
              <td class="xfont01" align="right">送货地址2：</td>
              <td>&nbsp;</td>
              <td colspan="4" align="left" class="xfont01" id="cuaddress2">&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td><table width="650" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="65" class="xl10418761" style="font-size:12px" align="center">序号<br />Item</td>
              <td width="78" class="xl10418761" style="font-size:12px" align="center">订单编号<br />PO NO</td>
              <td width="71" class="xl10418761" style="font-size:12px" align="center">GLE型号<br />GLE P/N</td>
              <td width="67" class="xl10418761" style="font-size:12px" align="center">客户型号<br />Cust P/N</td>
              <td width="68" class="xl10418761" style="font-size:12px" align="center">数量PCS<br />Quantitie</td>
              <td width="62" class="xl10418761" style="font-size:12px" align="center">币别<br />Currency</td>
              <td width="61" class="xl10418761" style="font-size:12px" align="center">单价<br />Unit Price</td>
              <td width="65" class="xl10418761" style="font-size:12px" align="center">税率<br />Tax Rate</td>
              <td width="56" class="xl10418761" style="font-size:12px" align="center">总金额<br />Amount</td>
              <td width="57" class="xl10418761" style="font-size:12px" align="center">备注<br />Remark</td>
            </tr>
            <tr id="totaltr">
              <td colspan="4" class="xl10418762" align="center">合计/TOTAL</td>
              <td class="xl10418762" align="center" id="totalcount">&nbsp;</td>
              <td class="xl10418762">&nbsp;</td>
              <td class="xl10418762">&nbsp;</td>
              <td class="xl10418762">&nbsp;</td>
              <td class="xl10418762" align="center" id="totalamount">&nbsp;</td>
              <td class="xl10418762">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="21%" align="right" style="border-bottom:2pt solid windowtext;">收款提示：</td>
              <td width="79%" align="left" style="border-bottom:2pt solid windowtext;">收到货物时请当面点清后签收，如对质量、数量、价格有异议,请在送货单签收日期之日起5天内书面提出，否则视为接受</td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr height="23px">
              <td width="25%" align="right">送货单位(签字\盖章)：</td>
              <td width="1%">&nbsp;</td>
              <td width="25%">&nbsp;</td>
              <td width="2%">&nbsp;</td>
              <td width="25%" align="right">收货单位(签字\盖章)：</td>
              <td width="1%">&nbsp;</td>
              <td width="21%">&nbsp;</td>
            </tr>
            <tr height="23px">
              <td align="right">Sent BY：</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td align="right">Received BY：</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr height="23px">
              <td align="right">仓管员：</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td align="right">收货人：</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr height="23px">
              <td align="right">运输方式：</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td align="right">收货日期：</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr height="23px">
              <td align="right">收款方式：</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table></td>
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
	var params = ["warehouseSend.sendPoNo=", urlPs["warehouseSend.sendPoNo"],"&warehouseSend.custCode=" , urlPs["warehouseSend.custCode"],"&warehouseSend.poNoType=" , urlPs["warehouseSend.poNoType"]].join("");

	ExtConvertHelper.request("/warehouseSend/warehouseSend!print.action", params, function(response, opts) {
		var jsonData = Ext.util.JSON.decode(response.responseText);
		if(jsonData.success === false) return;
		var currency = jsonData.data.currency;
		alert(currency);
		var templateStr = ['<tr>'
		,'<td align="center" class="xl10418762" style="font-size:10px">{sendSeqId}</td>'
    ,'<td align="center" class="xl10418762" style="font-size:10px">{rltPoNo}</td>'
    ,'<td align="center" class="xl10418762" style="font-size:10px">{partNo}</td>'
    ,'<td align="center" class="xl10418762" style="font-size:10px">{custPartNo}</td>'
    ,'<td align="center" class="xl10418762" style="font-size:10px">{amount}</td>'
    ,'<td align="center" class="xl10418762" style="font-size:10px">{currency}</td>'
    ,'<td align="center" class="xl10418762" style="font-size:10px">{price}</td>'
    ,'<td align="center" class="xl10418762" style="font-size:10px">{taxRate}</td>'
    ,'<td align="center" class="xl10418762" style="font-size:10px">{curMoney}</td>'
    ,'<td align="center" class="xl10418762" style="font-size:10px">{specDesc}</td>'
    ,'</tr>'].join("");
    var template = Ext.DomHelper.createTemplate(templateStr);
    
    // 数量统计 金额统计
		var quantity = amount = 0;
		Ext.each(jsonData.data.warehouseSend.detailList, function(item) {			
			if(jsonData.data.isShowPrice == 0) {
				item.price = "&nbsp;";
				item.taxRate = "&nbsp;";
      }
      if(item.specDesc == null){
      	item.specDesc = "&nbsp;";
      }
      item.currency=currency;
			template.insertBefore(Ext.get("totaltr"), item);
			// 数量
			quantity += +item.amount;
			// 税率
			amount   += +item.curMoney;
			
		});
		
		// 填充数量/PCS
		Ext.DomQuery.select("#totalcount")[0].innerHTML  = quantity;
		// 填充总金额
		Ext.DomQuery.select("#totalamount")[0].innerHTML = amount;
		
		
		var info = jsonData.data;
		// 送货日期
		var dataformat = FormatUtil.data2string(info.warehouseSend.createDate);
		Ext.DomQuery.select("#shrq")[0].innerHTML = dataformat;
		// 客户名称
		Ext.DomQuery.select("#cuname")[0].innerHTML = info.custName;
		// 联系电话
		Ext.DomQuery.select("#culxdh")[0].innerHTML = info.warehouseSend.conTel;
		// 联系人
		Ext.DomQuery.select("#culxr")[0].innerHTML = info.warehouseSend.receiveName;
		// 送货地址1
		Ext.DomQuery.select("#cuaddress1")[0].innerHTML = info.warehouseSend.receiveAddress;
		// 送货地址2
		//Ext.DomQuery.select("#cuaddress2")[0].innerHTML = info.vcaddress;
		
		// 送货单号
		Ext.DomQuery.select("#shdh")[0].innerHTML = info.warehouseSend.sendPoNo;
		// 发票号
		//Ext.DomQuery.select("#fph")[0].innerHTML = info.warehouseSend.;
		// 业务员
		Ext.DomQuery.select("#ywy")[0].innerHTML = info.sales;
		// 销售部门
		Ext.DomQuery.select("#xsbm")[0].innerHTML = info.department;
	});
</script>