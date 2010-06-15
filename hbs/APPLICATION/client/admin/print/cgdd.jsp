<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/includejs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购订单</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/print/css/print.css"/>
<script type="text/javascript" src="<%=contextPath %>/print/common/print.js"></script>
<script type="text/javascript" src="<%=contextPath %>/print/common/moneyToUpper.js"></script>
</head>

<body><div align="center">
<table width="978" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td  width="100%"><table width="978" border="0" cellpadding="0" cellspacing="0">
	  <tr>
        <td width="105" rowspan="2"><div align="right"><img src="images/logo002.jpg" alt="GLE" width="70" /></div></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr height="50">
        <td width="9">&nbsp;</td>
        <td width="406" rowspan="2"><table width="406" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="406"><span class="STYLE2">SHENZHEN GOORIOUS ELECTRONIC CO.,LTD </span></td>
          </tr>
          <tr>
            <td align="left"><span class="STYLE4">深圳市格莱尔电子有限公司</span></td>
          </tr>
          <tr>
            <td align="left"><span class="STYLE6">Room 15-16,15/F,Nobl Plaza 1st Qianjin Road Baoan District Shenzhen</span></td>
          </tr>
          <tr>
            <td align="left"><span class="STYLE8">深圳市前进一路诺铂广场1515-1516室</span></td>
          </tr>
          <tr>
            <td align="left"><span class="STYLE8">Tel:0755-27815501-03  Fax:0755-27815500</span></td>
          </tr>
          <tr>
            <td align="left"><span style='color:windowtext;text-decoration:none'><a href="http://www.glecorp.com" target="_blank">http://www.glecorp.com</a></span></td>
          </tr>
        </table></td>
        <td width="62">&nbsp;</td>
        <td width="384" rowspan="2"><table width="384" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="224" class="xl10418761" align="right" height="30px"><span>Purchase Order No.订单编号：</span></td>
            <td width="160" class="xl10418761" align="left"  height="30px"><span id="poNo">&nbsp;</span></td>
          </tr>
          <tr>
            <td class="xl10418761" align="right"  height="30px"><span>Vendor Code供应商编码：</span></td>
            <td class="xl10418761" align="left"   height="30px"><span id="vccommCode">&nbsp;</span></td>
          </tr>
          <tr>
            <td class="xl10418761" align="right"  height="30px"><span>Payment Term付款方式：</span></td>
            <td class="xl10418761" align="left"   height="30px"><span id="paymentterm">&nbsp;</span></td>
          </tr>
          <tr>
            <td class="xl10418761" align="right"  height="30px"><span>Date of lssue日期：</span></td>
            <td class="xl10418761" align="left"   height="30px"><span id="dateoflssue">&nbsp;</span></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="center" style="text-decoration: underline;"><span class="STYLE2">PURCHASE ORDER</span></td>
      </tr>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0" id="datatable">
          <tr>
            <td height="68" colspan="6" class="xl10418761"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="24%" rowspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td align="right">SUPPLIER：</td>
                  </tr>
                  <tr>
                    <td align="right">供应商：</td>
                  </tr>
                </table></td>
                <td width="76%" align="left" id="vcaddress">&nbsp;</td>
              </tr>
              <tr>
                <td align="left" id="vcphone">&nbsp;</td>
              </tr>
              <tr>
                <td align="left" id="vccontact">&nbsp;</td>
              </tr>
            </table></td>
            <td colspan="5" class="xl10418761"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="22%" align="right">SHIP TO:</td>
                <td width="2%">&nbsp;</td>
                <td width="76%" rowspan="2" align="left" id="shipto">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">送货地址:</td>
                <td>&nbsp;</td>
                </tr>
            </table></td>
            </tr>
          <tr>
            <td width="7%" align="center" class="xl10418762">ITEM<br />
              序号</td>
            <td width="11%" align="center" class="xl10418762">GLE PART NO<br />
              GLE型号</td>
            <td width="11%" align="center" class="xl10418762">VENDOR PART NO<br />
              供应商型号</td>
            <td width="9%" align="center" class="xl10418762">DESCRIPTION<br />描述</td>
            <td width="8%" align="center" class="xl10418762">QUANTITY<br />
              数量/PCS</td>
            <td width="9%" align="center" class="xl10418762">CURRENCY<br />
              币别</td>
            <td width="11%" align="center" class="xl10418762">UNIT PRICE<br />
              单价</td>
            <td width="8%" align="center" class="xl10418762">TAX RATE<br />
              税率</td>
            <td width="8%" align="center" class="xl10418762">AMOUNT<br />
              金额</td>
            <td width="10%" align="center" class="xl10418762">DELIVERY DATE<br />
              交货日期</td>
            <td width="8%" align="center" class="xl10418762">REMARK<br />
              备注</td>
          </tr>
          <tr id="totaltr">
            <td class="xl10418761" colspan="4" align="center">TOTAL/合计</td>
            <td class="xl10418761" align="center" id="totalcount">1000</td>
            <td class="xl10418761" align="center">&nbsp;</td>
            <td class="xl10418761" align="center">&nbsp;</td>
            <td class="xl10418761" align="center">&nbsp;</td>
            <td class="xl10418761" align="center" id="totalamount">2500</td>
            <td class="xl10418761" align="center">&nbsp;</td>
            <td class="xl10418761" align="center">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="11" class="xl10418762"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="18%" align="right" style="font-size:12pt">TOTAL AMOUNT：</td>
                <td width="1%">&nbsp;</td>
                <td width="81%" align="left" style="font-size:12pt" id="moneyUpper"></td>
              </tr>
            </table></td>
            </tr>
          <tr>
            <td colspan="11" class="xl10418762"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="18%" align="right" style="font-size:12pt">REMARKS：</td>
                <td width="1%">&nbsp;</td>
                <td width="81%" align="left" style="font-size:12pt">1、所供产品必须符合RoHS标准 2、按指定时间交货</td>
              </tr>
            </table></td>
            </tr>
          <tr>
            <td colspan="5" class="xl10418762"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="left" class="xfont01">SPECIAL INSTRUCTIONS:</td>
              </tr>
              <tr>
                <td align="left">1.供应商收到订单后请于8小时内回传,否则视为接受.</td>
              </tr>
              <tr>
                <td align="left">2.供应商所提供的货品必须和所送样品一致并确保包装完好数量无误.</td>
              </tr>
              <tr>
                <td align="left">3.供应商承担一切因其产品的品质因素所造成的任何损失.</td>
              </tr>
              <tr>
                <td align="left">4.供应商不能按期准时交货或延误交期的,采购商有权取消合同并由供应商承担所有因此造成的损失.</td>
              </tr>
              <tr>
                <td align="left">5.包装及运输费用:由供应商承担.</td>
              </tr>
              <tr>
                <td align="left">6.请随货提供0.5%的备品及出货检验报告.</td>
              </tr>
            </table></td>
            <td colspan="3" class="xl10418762"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="xfont01" align="left">Order Confirmed</td>
              </tr>
              <tr>
                <td height="95px">&nbsp;</td>
              </tr>
              <tr>
                <td align="left" style="border-top:.5pt solid windowtext;">Sign with Company Chop</td>
              </tr>
            </table></td>
            <td colspan="3" class="xl10418762"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td colspan="2" align="left" class="xfont01">For and on behalf of</td>
              </tr>
              <tr>
                <td colspan="2" align="left" class="xfont02">Shenzhen Glorious Electronic Co.,Ltd</td>
              </tr>
              <tr>
                <td width="33%" align="right" height="40px" class="xfont01">采购员：</td>
                <td width="67%">&nbsp;</td>
              </tr>
              <tr>
                <td class="xfont01" align="right" height="39px">审核：</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2" align="left" style="border-top:.5pt solid windowtext;">Purchaser</td>
              </tr>
            </table></td>
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="16%">&nbsp;</td>
            <td width="68%" align="center">（本订单为打印件，手写无效）</td>
            <td width="9%"><div align="right">操作人：</div></td>
            <td width="7%" id="operatorpe"></td>
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
</div>
</body>
</html>
<script>
	var params = ["vendorOrder.commCode=" , urlPs["vendorOrder.commCode"],"&vendorOrder.poNo=" , urlPs["vendorOrder.poNo"]].join("");
	ExtConvertHelper.request("/vendorOrder/vendorOrder!print.action", params, function(response, opts) {
		var jsonData = Ext.util.JSON.decode(response.responseText);
		if(jsonData.success === false) return;
		var info = jsonData.data.vendorOrder;
		var templateStr = ['<tr>'
		,'<td align="center" class="xl10418763">{operSeqId}</td>'
    ,'<td align="center" class="xl10418763">{partNo}</td>'
    ,'<td align="center" class="xl10418763">{cpartNo}</td>'
    ,'<td align="center" class="xl10418763">{pnDesc}</td>'
    ,'<td align="center" class="xl10418763">{amount}</td>'
    ,'<td align="center" class="xl10418763">{bb}</td>'
    ,'<td align="center" class="xl10418763">{cprice}</td>'
    ,'<td align="center" class="xl10418763">{cpriceTax}</td>'
    ,'<td align="center" class="xl10418763">{money}</td>'
    ,'<td align="center" class="xl10418763">{orgDeliveryDate}</td>'
    ,'<td align="center" class="xl10418763">{specDesc}</td>'
    ,'</tr>'].join("");
    var template = Ext.DomHelper.createTemplate(templateStr);

    // 数量统计 金额统计
		var quantity = amount = 0;
		Ext.each(jsonData.data.vendorOrder.vendorOrderDetailList, function(item) {
			item.bb = jsonData.data.vendorInfo.currencyDesc;
			if(item.orgDeliveryDate instanceof Date)
			{
				item.orgDeliveryDate = Ext.util.Format.date(item.orgDeliveryDate, 'Y-m-d');
			}else{
				item.orgDeliveryDate = item.orgDeliveryDate.split(" ")[0];
			}
			if(!item.specDesc)
				item.specDesc = "&nbsp;"

			template.insertBefore(Ext.get("totaltr"), item);
			// 数量
			quantity += +item.amount;
			// 税率
			amount   += +item.money;
		});

		// 填充数量/PCS
		Ext.DomQuery.select("#totalcount")[0].innerHTML  = quantity;
		// 填充总金额
		Ext.DomQuery.select("#totalamount")[0].innerHTML = amount;
		Ext.DomQuery.select("#moneyUpper")[0].innerHTML  = moneyToUpper(amount);

		// Purchase Order No.订单编号
		Ext.DomQuery.select("#poNo")[0].innerHTML = info.poNo;
		// Vendor Code供应商编码
		Ext.DomQuery.select("#vccommCode")[0].innerHTML = info.commCode;
		// Payment Term付款方式
		Ext.DomQuery.select("#paymentterm")[0].innerHTML = jsonData.data.vendorInfo.settlementDesc2;
		// Date of lssue日期
		Ext.DomQuery.select("#dateoflssue")[0].innerHTML = info.orderTime;

		// 供应商地址
		Ext.DomQuery.select("#vcaddress")[0].innerHTML = jsonData.data.vendorInfo.allName;
		// 供应商联系电话
		Ext.DomQuery.select("#vcphone")[0].innerHTML = "TEL：" + info.conTel +"        FAX：" + info.conFax;
		// 供应商联系人
		Ext.DomQuery.select("#vccontact")[0].innerHTML = "联系人: " + info.conName;
		// 送货地址
		Ext.DomQuery.select("#shipto")[0].innerHTML = info.receiveAddress;

		// 操作人
		Ext.DomQuery.select("#operatorpe")[0].innerHTML = info.staffName;
	});
</script>