<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户对账单</title>
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
        <td class="xfont01" align="center" style="font-size:12pt;" id="cutitle">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td></tr>
    <tr><td>&nbsp;</td></tr>
	<tr>
	  <td><table width="978" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="xfont02" align="right" height="25px">&nbsp;</td>
          <td>&nbsp;</td>
          <td class="xfont02" align="left">&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td class="xfont01" align="right"><span class="xfont01">GLE深圳总部</span></td>
          <td>&nbsp;</td>
          <td id="gleszzb">&nbsp;</td>
        </tr>
        <tr>
          <td width="127" class="xfont02" align="right" height="25px">客户：</td>
          <td width="9">&nbsp;</td>
          <td width="117" class="xfont02" align="left" id="cuname">&nbsp;</td>
          <td width="49">&nbsp;</td>
          <td width="245">&nbsp;</td>
          <td width="201" class="xfont02" align="right">TEL：</td>
          <td width="10">&nbsp;</td>
          <td width="226" align="left" class="xfont02" id="gletel">&nbsp;</td>
        </tr>
        <tr>
          <td class="xfont02" align="right" height="25px">联系人：</td>
          <td>&nbsp;</td>
          <td class="xfont02" align="left" id="khlxr">&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td class="xfont02" align="right">FAX：</td>
          <td>&nbsp;</td>
          <td align="left" class="xfont02" id="glefax">&nbsp;</td>
        </tr>
        <tr>
          <td class="xfont02" align="right" height="25px">TEL：</td>
          <td>&nbsp;</td>
          <td class="xfont02" align="left" id="khtel">&nbsp;</td>
          <td class="xfont02" align="left">FAX：</td>
          <td class="xfont02" align="left" id="khfax">&nbsp;</td>
          <td class="xfont02" align="right">对帐负责人：</td>
          <td>&nbsp;</td>
          <td id="gledzfzr">&nbsp;</td>
        </tr>
        <tr>
          <td class="xfont02" align="right" height="25px">付款方式：</td>
          <td>&nbsp;</td>
          <td class="xfont02" align="left" id="khfkfs">&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td class="xfont02" align="right">对帐日期：</td>
          <td>&nbsp;</td>
          <td id="dzrq">&nbsp;</td>
        </tr>
      </table></td>
    </tr>
	<tr>
	  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="9%">&nbsp;</td>
          <td width="9%">&nbsp;</td>
          <td width="9%">&nbsp;</td>
          <td width="9%">&nbsp;</td>
          <td width="9%">&nbsp;</td>
          <td width="9%">&nbsp;</td>
          <td width="9%">&nbsp;</td>
          <td width="9%">&nbsp;</td>
          <td width="9%">&nbsp;</td>
          <td width="9%">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td class="xl10418761" align="center">出货日期</td>
          <td class="xl10418761" align="center">送货单号</td>
          <td class="xl10418761" align="center">客户订单号</td>
          <td class="xl10418761" align="center">物料名称</td>
          <td class="xl10418761" align="center">客户料号</td>
          <td class="xl10418761" align="center">GLE料号</td>
          <td class="xl10418761" align="center">数量PCS</td>
          <td class="xl10418761" align="center">税率</td>
          <td class="xl10418761" align="center">单价</td>
          <td class="xl10418761" align="center">金額</td>
          <td class="xl10418761" align="center">备注</td>
        </tr>
        <tr id="totaltr">
          <td class="xl10418761">合       計：</td>
          <td class="xl10418761">&nbsp;</td>
          <td class="xl10418761">&nbsp;</td>
          <td class="xl10418761">&nbsp;</td>
          <td class="xl10418761">&nbsp;</td>
          <td class="xl10418761">&nbsp;</td>
          <td class="xl10418761" id="totalcount" align="center">&nbsp;</td>
          <td class="xl10418761">&nbsp;</td>
          <td class="xl10418761">&nbsp;</td>
          <td class="xl10418761" id="totalamount" align="center">&nbsp;</td>
          <td class="xl10418761">&nbsp;</td>
        </tr>
      </table></td>
    </tr>
	<tr>
	  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="18%">&nbsp;</td>
          <td width="9%">&nbsp;</td>
          <td width="73%">&nbsp;</td>
          </tr>
        <tr>
          <td class="xl10418761" align="right">前期未付货款金额:</td>
          <td class="xl10418761" align="right" id="qqwfkje">&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td class="xl10418761" align="right">当月货款金额:</td>
          <td class="xl10418761" align="right" id="dyhkje">&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td class="xl10418761" align="right">总货款金额:</td>
          <td class="xl10418761" align="right" id="zhkje">&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
      </table></td>
    </tr>
	<tr>
	  <td>&nbsp;</td>
    </tr>
	<tr>
	  <td>&nbsp;</td>
    </tr>
	<tr>
	  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="14%">&nbsp;</td>
          <td width="14%" align="right">客戶簽章：</td>
          <td width="15%" style="border-bottom:1pt solid windowtext;">&nbsp;</td>
          <td width="24%" align="right">GLE签章：</td>
          <td width="15%" style="border-bottom:1pt solid windowtext;">&nbsp;</td>
          <td width="17%">&nbsp;</td>
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
		
		var templateStr = ['<tr>'
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
    ,'<td align="center" class="xl10418762">{bz}</td>'
    ,'</tr>'].join("");
    var template = Ext.DomHelper.createTemplate(templateStr);
    
    // 数量统计 金额统计
		var quantity = amount = 0;
		Ext.each(jsonData.data.list, function(item) {
			template.insertBefore(Ext.get("totaltr"), item);
			// 数量
			quantity += +item.slpcs;
			// 税率
			amount   += +item.je;
		});
		
		// 填充数量/PCS
		Ext.DomQuery.select("#totalcount")[0].innerHTML  = quantity;
		// 填充总金额
		Ext.DomQuery.select("#totalamount")[0].innerHTML = amount;
		

		var info = jsonData.data;
		// 对账单标题
		Ext.DomQuery.select("#cutitle")[0].innerHTML = info.vctitle;
		// 客户名称
		Ext.DomQuery.select("#cuname")[0].innerHTML = info.cuname;
		// 联系人
		Ext.DomQuery.select("#khlxr")[0].innerHTML = info.khlxr;
		// 客户TEL
		Ext.DomQuery.select("#khtel")[0].innerHTML = info.khtel;
		// 客户FAX
		Ext.DomQuery.select("#khfax")[0].innerHTML = info.khfax;
		// 客户付款方式
		Ext.DomQuery.select("#khfkfs")[0].innerHTML = info.khfkfs;
		
		// GLE深圳总部
		Ext.DomQuery.select("#gleszzb")[0].innerHTML = info.gleszzb;
		// GLE TEL
		Ext.DomQuery.select("#gletel")[0].innerHTML = info.gletel;
		// GLE FAX
		Ext.DomQuery.select("#glefax")[0].innerHTML = info.glefax;
		// 对账负责人
		Ext.DomQuery.select("#gledzfzr")[0].innerHTML = info.gledzfzr;
		// 对账日期
		Ext.DomQuery.select("#dzrq")[0].innerHTML = info.dzrq;
		
		// 前期未付货款金额
		Ext.DomQuery.select("#qqwfkje")[0].innerHTML = info.qqwfkje;
		// 当月货款金额
		Ext.DomQuery.select("#dyhkje")[0].innerHTML = info.dyhkje;
		// 总货款金额
		Ext.DomQuery.select("#zhkje")[0].innerHTML = info.zhkje;
	});
</script>