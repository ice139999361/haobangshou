
var PageName = '调货信息（经理使用）';
var PageId = 'p4cb5dbc1b7444462bc97722fc27cb0d7'
var PageUrl = '调货信息（经理使用）.html'
document.title = '调货信息（经理使用）';

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

var u109 = document.getElementById('u109');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u109ann'), "<div id='u109Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u109', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u109').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u109based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u109base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u109based = document.getElementById('u109based');
            
InsertBeforeEnd(u109based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 不做任何动作，关闭页面<BR><BR></div>");

u109.style.cursor = 'pointer';
if (bIE) u109.attachEvent("onclick", Clicku109);
else u109.addEventListener("click", Clicku109, true);
function Clicku109(e)
{

if (true) {

	parent.window.close();

}

}

var u86 = document.getElementById('u86');
gv_vAlignTable['u86'] = 'top';
var u54 = document.getElementById('u54');
gv_vAlignTable['u54'] = 'top';
var u60 = document.getElementById('u60');
gv_vAlignTable['u60'] = 'top';
var u29 = document.getElementById('u29');

var u115 = document.getElementById('u115');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u115ann'), "<div id='u115Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u115', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u115').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u115based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u115base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u115based = document.getElementById('u115based');
            
InsertBeforeEnd(u115based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 用户输入，可不填<BR><BR></div>");

var u102 = document.getElementById('u102');
gv_vAlignTable['u102'] = 'top';
var u106 = document.getElementById('u106');
gv_vAlignTable['u106'] = 'top';
var u45 = document.getElementById('u45');

var u83 = document.getElementById('u83');

var u51 = document.getElementById('u51');

var u96 = document.getElementById('u96');
gv_vAlignTable['u96'] = 'top';
var u79 = document.getElementById('u79');

var u42 = document.getElementById('u42');
gv_vAlignTable['u42'] = 'top';
var u80 = document.getElementById('u80');
gv_vAlignTable['u80'] = 'top';
var u12 = document.getElementById('u12');
gv_vAlignTable['u12'] = 'top';
var u99 = document.getElementById('u99');

var u17 = document.getElementById('u17');

var u23 = document.getElementById('u23');

var u76 = document.getElementById('u76');
gv_vAlignTable['u76'] = 'top';
var u107 = document.getElementById('u107');

var u14 = document.getElementById('u14');
gv_vAlignTable['u14'] = 'top';
var u67 = document.getElementById('u67');

var u20 = document.getElementById('u20');
gv_vAlignTable['u20'] = 'top';
var u73 = document.getElementById('u73');

var u48 = document.getElementById('u48');
gv_vAlignTable['u48'] = 'top';
var u4 = document.getElementById('u4');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u4ann'), "<div id='u4Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u4', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u4').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u4based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u4base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u4based = document.getElementById('u4based');
            
InsertBeforeEnd(u4based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 用户输入，可不填<BR><BR></div>");

var u11 = document.getElementById('u11');

var u64 = document.getElementById('u64');
gv_vAlignTable['u64'] = 'top';
var u70 = document.getElementById('u70');
gv_vAlignTable['u70'] = 'top';
var u39 = document.getElementById('u39');

var u119 = document.getElementById('u119');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u119ann'), "<div id='u119Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u119', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u119').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u119based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u119base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u119based = document.getElementById('u119based');
            
InsertBeforeEnd(u119based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 用户输入，可不填<BR><BR></div>");

var u87 = document.getElementById('u87');

var u55 = document.getElementById('u55');

var u93 = document.getElementById('u93');

var u61 = document.getElementById('u61');

var u116 = document.getElementById('u116');
gv_vAlignTable['u116'] = 'top';
var u103 = document.getElementById('u103');

var u104 = document.getElementById('u104');
gv_vAlignTable['u104'] = 'top';
var u84 = document.getElementById('u84');
gv_vAlignTable['u84'] = 'top';
var u52 = document.getElementById('u52');
gv_vAlignTable['u52'] = 'top';
var u90 = document.getElementById('u90');
gv_vAlignTable['u90'] = 'top';
var u36 = document.getElementById('u36');
gv_vAlignTable['u36'] = 'top';
var u89 = document.getElementById('u89');

var u15 = document.getElementById('u15');

var u81 = document.getElementById('u81');

var u110 = document.getElementById('u110');
gv_vAlignTable['u110'] = 'top';
var u27 = document.getElementById('u27');

var u33 = document.getElementById('u33');

var u0 = document.getElementById('u0');

var u24 = document.getElementById('u24');
gv_vAlignTable['u24'] = 'top';
var u77 = document.getElementById('u77');

var u30 = document.getElementById('u30');
gv_vAlignTable['u30'] = 'top';
var u58 = document.getElementById('u58');
gv_vAlignTable['u58'] = 'top';
var u5 = document.getElementById('u5');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u5ann'), "<div id='u5Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u5', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u5').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u5based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u5base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u5based = document.getElementById('u5based');
            
InsertBeforeEnd(u5based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 查询满足条件的调货申请<BR><BR></div>");

var u21 = document.getElementById('u21');

var u74 = document.getElementById('u74');
gv_vAlignTable['u74'] = 'top';
var u49 = document.getElementById('u49');

var u97 = document.getElementById('u97');

var u65 = document.getElementById('u65');

var u71 = document.getElementById('u71');

var u121 = document.getElementById('u121');

u121.style.cursor = 'pointer';
if (bIE) u121.attachEvent("onclick", Clicku121);
else u121.addEventListener("click", Clicku121, true);
function Clicku121(e)
{

if (true) {

	NewWindow("调货申请审批.html" + GetQuerystring(), "", "directories=1, height=500, location=1, menubar=1, resizable=1, scrollbars=1, status=1, toolbar=1, width=500", true, 500, 500);

}

}

var u94 = document.getElementById('u94');
gv_vAlignTable['u94'] = 'top';
var u62 = document.getElementById('u62');
gv_vAlignTable['u62'] = 'top';
var u28 = document.getElementById('u28');
gv_vAlignTable['u28'] = 'top';
var u47 = document.getElementById('u47');

var u46 = document.getElementById('u46');
gv_vAlignTable['u46'] = 'top';
var u117 = document.getElementById('u117');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u117ann'), "<div id='u117Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u117', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u117').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u117based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u117base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u117based = document.getElementById('u117based');
            
InsertBeforeEnd(u117based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 用户输入，可不填<BR><BR></div>");

var u85 = document.getElementById('u85');

var u120 = document.getElementById('u120');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u120ann'), "<div id='u120Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u120', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u120').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u120based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u120base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u120based = document.getElementById('u120based');
            
InsertBeforeEnd(u120based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 审批人为空的情况下，操作列才会显示审批 ，其他不显示<BR><BR></div>");

u120.style.cursor = 'pointer';
if (bIE) u120.attachEvent("onclick", Clicku120);
else u120.addEventListener("click", Clicku120, true);
function Clicku120(e)
{

if (true) {

	NewWindow("调货申请审批.html" + GetQuerystring(), "", "directories=1, height=500, location=1, menubar=1, resizable=1, scrollbars=1, status=1, toolbar=1, width=500", true, 500, 500);

}

}

var u91 = document.getElementById('u91');

var u37 = document.getElementById('u37');

var u43 = document.getElementById('u43');

var u18 = document.getElementById('u18');
gv_vAlignTable['u18'] = 'top';
var u82 = document.getElementById('u82');
gv_vAlignTable['u82'] = 'top';
var u1 = document.getElementById('u1');
gv_vAlignTable['u1'] = 'center';
var u92 = document.getElementById('u92');
gv_vAlignTable['u92'] = 'top';
var u34 = document.getElementById('u34');
gv_vAlignTable['u34'] = 'top';
var u40 = document.getElementById('u40');
gv_vAlignTable['u40'] = 'top';
var u68 = document.getElementById('u68');
gv_vAlignTable['u68'] = 'top';
var u25 = document.getElementById('u25');

var u31 = document.getElementById('u31');

var u59 = document.getElementById('u59');

var u22 = document.getElementById('u22');
gv_vAlignTable['u22'] = 'top';
var u75 = document.getElementById('u75');

var u26 = document.getElementById('u26');
gv_vAlignTable['u26'] = 'top';
var u88 = document.getElementById('u88');
gv_vAlignTable['u88'] = 'top';
var u8 = document.getElementById('u8');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u8ann'), "<div id='u8Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u8', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u8').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u8based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u8base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u8based = document.getElementById('u8based');
            
InsertBeforeEnd(u8based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 支持分页<BR><BR></div>");

var u112 = document.getElementById('u112');
gv_vAlignTable['u112'] = 'top';
var u72 = document.getElementById('u72');
gv_vAlignTable['u72'] = 'top';
var u56 = document.getElementById('u56');
gv_vAlignTable['u56'] = 'top';
var u95 = document.getElementById('u95');

var u122 = document.getElementById('u122');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u122ann'), "<div id='u122Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u122', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u122').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u122based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u122base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u122based = document.getElementById('u122based');
            
InsertBeforeEnd(u122based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 审批过的，只能查看<BR><BR></div>");

u122.style.cursor = 'pointer';
if (bIE) u122.attachEvent("onclick", Clicku122);
else u122.addEventListener("click", Clicku122, true);
function Clicku122(e)
{

if (true) {

	NewWindow("查看详情_1.html" + GetQuerystring(), "", "directories=1, height=500, location=1, menubar=1, resizable=1, scrollbars=1, status=1, toolbar=1, width=500", true, 500, 500);

}

}

var u108 = document.getElementById('u108');
gv_vAlignTable['u108'] = 'top';
var u113 = document.getElementById('u113');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u113ann'), "<div id='u113Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u113', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u113').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u113based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u113base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u113based = document.getElementById('u113based');
            
InsertBeforeEnd(u113based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 日期控件，用户选择<BR><BR></div>");

var u100 = document.getElementById('u100');
gv_vAlignTable['u100'] = 'top';
var u53 = document.getElementById('u53');

var u114 = document.getElementById('u114');
gv_vAlignTable['u114'] = 'top';
var u101 = document.getElementById('u101');

var u105 = document.getElementById('u105');

var u2 = document.getElementById('u2');
gv_vAlignTable['u2'] = 'top';
var u44 = document.getElementById('u44');
gv_vAlignTable['u44'] = 'top';
var u50 = document.getElementById('u50');
gv_vAlignTable['u50'] = 'top';
var u19 = document.getElementById('u19');

var u78 = document.getElementById('u78');
gv_vAlignTable['u78'] = 'top';
var u7 = document.getElementById('u7');
gv_vAlignTable['u7'] = 'center';
var u41 = document.getElementById('u41');

var u111 = document.getElementById('u111');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u111ann'), "<div id='u111Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u111', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u111').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u111based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u111base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u111based = document.getElementById('u111based');
            
InsertBeforeEnd(u111based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 日期控件，用户选择<BR><BR></div>");

var u69 = document.getElementById('u69');

var u123 = document.getElementById('u123');

u123.style.cursor = 'pointer';
if (bIE) u123.attachEvent("onclick", Clicku123);
else u123.addEventListener("click", Clicku123, true);
function Clicku123(e)
{

if (true) {

	NewWindow("查看详情_1.html" + GetQuerystring(), "", "directories=1, height=500, location=1, menubar=1, resizable=1, scrollbars=1, status=1, toolbar=1, width=500", true, 500, 500);

}

}

var u32 = document.getElementById('u32');
gv_vAlignTable['u32'] = 'top';
var u16 = document.getElementById('u16');
gv_vAlignTable['u16'] = 'top';
var u98 = document.getElementById('u98');
gv_vAlignTable['u98'] = 'top';
var u9 = document.getElementById('u9');

var u13 = document.getElementById('u13');

var u66 = document.getElementById('u66');
gv_vAlignTable['u66'] = 'top';
var u6 = document.getElementById('u6');

var u35 = document.getElementById('u35');

var u57 = document.getElementById('u57');

var u10 = document.getElementById('u10');
gv_vAlignTable['u10'] = 'top';
var u63 = document.getElementById('u63');

var u38 = document.getElementById('u38');
gv_vAlignTable['u38'] = 'top';
var u118 = document.getElementById('u118');
gv_vAlignTable['u118'] = 'top';
var u3 = document.getElementById('u3');
gv_vAlignTable['u3'] = 'top';
if (window.OnLoad) OnLoad();
