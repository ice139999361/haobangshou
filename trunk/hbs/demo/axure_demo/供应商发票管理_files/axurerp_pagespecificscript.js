
var PageName = '供应商发票管理';
var PageId = 'pf214b7f3411742e0bec6d0410313f6ed'
var PageUrl = '供应商发票管理.html'
document.title = '供应商发票管理';

if (top.location != self.location)
{
	if (parent.HandleMainFrameChanged) {
		parent.HandleMainFrameChanged();
	}
}

var $OnLoadVariable = '';

var $CSUM;

var hasQuery = false;
var query = window.location.hash.substring(1);
if (query.length > 0) hasQuery = true;
var vars = query.split("&");
for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split("=");
    if (pair[0].length > 0) eval("$" + pair[0] + " = decodeURIComponent(pair[1]);");
} 

if (hasQuery && $CSUM != 1) {
alert('Prototype Warning: The variable values were too long to pass to this page.\nIf you are using IE, using Firefox will support more data.');
}

function GetQuerystring() {
    return '#OnLoadVariable=' + encodeURIComponent($OnLoadVariable) + '&CSUM=1';
}

function PopulateVariables(value) {
  value = value.replace(/\[\[OnLoadVariable\]\]/g, $OnLoadVariable);
  value = value.replace(/\[\[PageName\]\]/g, PageName);
  return value;
}

function OnLoad(e) {

}

var u20 = document.getElementById('u20');

var u64 = document.getElementById('u64');
gv_vAlignTable['u64'] = 'top';
var u51 = document.getElementById('u51');

var u36 = document.getElementById('u36');
gv_vAlignTable['u36'] = 'top';
var u31 = document.getElementById('u31');

var u57 = document.getElementById('u57');

var u11 = document.getElementById('u11');
gv_vAlignTable['u11'] = 'top';
var u27 = document.getElementById('u27');

var u6 = document.getElementById('u6');

var u4 = document.getElementById('u4');
gv_vAlignTable['u4'] = 'top';
var u2 = document.getElementById('u2');

var u10 = document.getElementById('u10');

var u0 = document.getElementById('u0');

var u26 = document.getElementById('u26');
gv_vAlignTable['u26'] = 'top';
var u49 = document.getElementById('u49');

var u63 = document.getElementById('u63');

var u35 = document.getElementById('u35');

var u29 = document.getElementById('u29');

var u54 = document.getElementById('u54');
gv_vAlignTable['u54'] = 'top';
var u8 = document.getElementById('u8');

var u34 = document.getElementById('u34');
gv_vAlignTable['u34'] = 'top';
var u33 = document.getElementById('u33');

var u14 = document.getElementById('u14');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u14ann'), "<div id='u14Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u14', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u14').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u14based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u14base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u14based = document.getElementById('u14based');
            
InsertBeforeEnd(u14based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 日期控件 格式为YYYY-MM-DD<BR><BR></div>");

var u32 = document.getElementById('u32');
gv_vAlignTable['u32'] = 'top';
var u28 = document.getElementById('u28');
gv_vAlignTable['u28'] = 'top';
var u44 = document.getElementById('u44');
gv_vAlignTable['u44'] = 'top';
var u53 = document.getElementById('u53');

var u50 = document.getElementById('u50');
gv_vAlignTable['u50'] = 'top';
var u45 = document.getElementById('u45');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u45ann'), "<div id='u45Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u45', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u45').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u45based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u45base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u45based = document.getElementById('u45based');
            
InsertBeforeEnd(u45based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 精确到2位小数<BR><BR></div>");

var u22 = document.getElementById('u22');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u22ann'), "<div id='u22Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u22', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u22').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u22based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u22base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u22based = document.getElementById('u22based');
            
InsertBeforeEnd(u22based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 下拉列表框支持分页，可按供应商编码和日期排序<BR><BR></div>");

var u52 = document.getElementById('u52');
gv_vAlignTable['u52'] = 'top';
var u66 = document.getElementById('u66');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u66ann'), "<div id='u66Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u66', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u66').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u66based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u66base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u66based = document.getElementById('u66based');
            
InsertBeforeEnd(u66based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 删除需要提示用户确实需要删除吗？<BR><BR></div>");

u66.style.cursor = 'pointer';
if (bIE) u66.attachEvent("onclick", Clicku66);
else u66.addEventListener("click", Clicku66, true);
function Clicku66(e)
{

if (true) {

	NewWindow("删除提示_3.html" + GetQuerystring(), "", "directories=1, height=500, location=1, menubar=1, resizable=1, scrollbars=1, status=1, toolbar=1, width=500", true, 500, 500);

}

}

var u13 = document.getElementById('u13');
gv_vAlignTable['u13'] = 'top';
var u46 = document.getElementById('u46');
gv_vAlignTable['u46'] = 'top';
var u47 = document.getElementById('u47');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u47ann'), "<div id='u47Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u47', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u47').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u47based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u47base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u47based = document.getElementById('u47based');
            
InsertBeforeEnd(u47based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 客户输入的发票号备注<BR><BR></div>");

var u12 = document.getElementById('u12');

var u41 = document.getElementById('u41');

var u21 = document.getElementById('u21');
gv_vAlignTable['u21'] = 'center';
var u37 = document.getElementById('u37');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u37ann'), "<div id='u37Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u37', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u37').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u37based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u37base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u37based = document.getElementById('u37based');
            
InsertBeforeEnd(u37based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 链接到这条记录的详情界面<BR><BR></div>");

u37.style.cursor = 'pointer';
if (bIE) u37.attachEvent("onclick", Clicku37);
else u37.addEventListener("click", Clicku37, true);
function Clicku37(e)
{

if (true) {

	NewWindow("供应商发票详情查看.html" + GetQuerystring(), "", "directories=1, height=500, location=1, menubar=1, resizable=1, scrollbars=1, status=1, toolbar=1, width=500", true, 500, 500);

}

}

var u7 = document.getElementById('u7');
gv_vAlignTable['u7'] = 'top';
var u40 = document.getElementById('u40');
gv_vAlignTable['u40'] = 'top';
var u17 = document.getElementById('u17');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u17ann'), "<div id='u17Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u17', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u17').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u17based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u17base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u17based = document.getElementById('u17based');
            
InsertBeforeEnd(u17based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 查询，送到后台查询供应商发票<br>查询后的结果显示在下方列表<BR><BR></div>");

var u5 = document.getElementById('u5');
gv_vAlignTable['u5'] = 'top';
var u15 = document.getElementById('u15');
gv_vAlignTable['u15'] = 'top';
var u56 = document.getElementById('u56');
gv_vAlignTable['u56'] = 'top';
var u3 = document.getElementById('u3');
gv_vAlignTable['u3'] = 'center';
var u65 = document.getElementById('u65');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u65ann'), "<div id='u65Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u65', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u65').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u65based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u65base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u65based = document.getElementById('u65based');
            
InsertBeforeEnd(u65based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 修改的功能只能够修改发票号一栏和已开票金额一栏，其他不允许修改<BR><BR></div>");

u65.style.cursor = 'pointer';
if (bIE) u65.attachEvent("onclick", Clicku65);
else u65.addEventListener("click", Clicku65, true);
function Clicku65(e)
{

if (true) {

	NewWindow("供应商发票修改.html" + GetQuerystring(), "", "directories=1, height=500, location=1, menubar=1, resizable=1, scrollbars=1, status=1, toolbar=1, width=500", true, 500, 500);

}

}

var u1 = document.getElementById('u1');
gv_vAlignTable['u1'] = 'center';
var u25 = document.getElementById('u25');

var u59 = document.getElementById('u59');

var u16 = document.getElementById('u16');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u16ann'), "<div id='u16Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u16', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u16').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u16based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u16base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u16based = document.getElementById('u16based');
            
InsertBeforeEnd(u16based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 日期控件，格式为YYYY-MM-DD 不能小于起始日期<BR><BR></div>");

var u39 = document.getElementById('u39');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u39ann'), "<div id='u39Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u39', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u39').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u39based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u39base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u39based = document.getElementById('u39based');
            
InsertBeforeEnd(u39based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 格式YYYY-MM-DD<BR><BR></div>");

var u48 = document.getElementById('u48');
gv_vAlignTable['u48'] = 'top';
var u19 = document.getElementById('u19');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u19ann'), "<div id='u19Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u19', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u19').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u19based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u19base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u19based = document.getElementById('u19based');
            
InsertBeforeEnd(u19based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 新增供应商发票，弹出新增页面<BR><BR></div>");

u19.style.cursor = 'pointer';
if (bIE) u19.attachEvent("onclick", Clicku19);
else u19.addEventListener("click", Clicku19, true);
function Clicku19(e)
{

if (true) {

	NewWindow("供应商发票新增.html" + GetQuerystring(), "", "directories=1, height=500, location=1, menubar=1, resizable=1, scrollbars=1, status=1, toolbar=1, width=500", true, 500, 500);

}

}

var u9 = document.getElementById('u9');
gv_vAlignTable['u9'] = 'top';
var u30 = document.getElementById('u30');
gv_vAlignTable['u30'] = 'top';
var u60 = document.getElementById('u60');
gv_vAlignTable['u60'] = 'top';
var u24 = document.getElementById('u24');
gv_vAlignTable['u24'] = 'top';
var u58 = document.getElementById('u58');
gv_vAlignTable['u58'] = 'top';
var u55 = document.getElementById('u55');

var u38 = document.getElementById('u38');
gv_vAlignTable['u38'] = 'top';
var u61 = document.getElementById('u61');

var u18 = document.getElementById('u18');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u18ann'), "<div id='u18Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u18', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u18').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u18based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u18base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u18based = document.getElementById('u18based');
            
InsertBeforeEnd(u18based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 清除查询条件<BR><BR></div>");

var u62 = document.getElementById('u62');
gv_vAlignTable['u62'] = 'top';
var u43 = document.getElementById('u43');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u43ann'), "<div id='u43Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u43', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u43').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u43based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u43base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u43based = document.getElementById('u43based');
            
InsertBeforeEnd(u43based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 本公司的物料编码<BR><BR></div>");

var u42 = document.getElementById('u42');
gv_vAlignTable['u42'] = 'top';
var u23 = document.getElementById('u23');

if (window.OnLoad) OnLoad();
