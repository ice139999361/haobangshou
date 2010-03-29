
var PageName = '仓库出库单';
var PageId = 'p5fb8d19c4bf5439398c11f2bc26a602b'
var PageUrl = '仓库出库单.html'
document.title = '仓库出库单';

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
            
InsertBeforeEnd(u109based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 提交出库单及明细，提交后的数据不能修改，成功后，打印按钮可用<BR><BR></div>");

var u86 = document.getElementById('u86');

var u54 = document.getElementById('u54');

var u60 = document.getElementById('u60');

var u29 = document.getElementById('u29');
gv_vAlignTable['u29'] = 'top';
var u115 = document.getElementById('u115');
gv_vAlignTable['u115'] = 'top';
var u102 = document.getElementById('u102');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u102ann'), "<div id='u102Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u102', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u102').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u102based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u102base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u102based = document.getElementById('u102based');
            
InsertBeforeEnd(u102based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 编辑框，用户填写<BR><BR></div>");

var u104 = document.getElementById('u104');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u104ann'), "<div id='u104Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u104', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u104').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u104based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u104base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u104based = document.getElementById('u104based');
            
InsertBeforeEnd(u104based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 编辑框，用户填写<BR><BR></div>");

var u45 = document.getElementById('u45');
gv_vAlignTable['u45'] = 'top';
var u83 = document.getElementById('u83');
gv_vAlignTable['u83'] = 'top';
var u51 = document.getElementById('u51');
gv_vAlignTable['u51'] = 'top';
var u96 = document.getElementById('u96');

var u79 = document.getElementById('u79');
gv_vAlignTable['u79'] = 'top';
var u42 = document.getElementById('u42');

var u80 = document.getElementById('u80');

var u26 = document.getElementById('u26');

var u106 = document.getElementById('u106');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u106ann'), "<div id='u106Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u106', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u106').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u106based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u106base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u106based = document.getElementById('u106based');
            
InsertBeforeEnd(u106based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 编辑框，用户填写<BR><BR></div>");

var u99 = document.getElementById('u99');
gv_vAlignTable['u99'] = 'top';
var u17 = document.getElementById('u17');

var u23 = document.getElementById('u23');
gv_vAlignTable['u23'] = 'top';
var u76 = document.getElementById('u76');

var u110 = document.getElementById('u110');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u110ann'), "<div id='u110Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u110', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u110').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u110based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u110base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u110based = document.getElementById('u110based');
            
InsertBeforeEnd(u110based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 删除本行记录<BR><BR></div>");

var u14 = document.getElementById('u14');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u14ann'), "<div id='u14Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u14', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u14').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u14based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u14base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u14based = document.getElementById('u14based');
            
InsertBeforeEnd(u14based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 根据用户选择的客户自动填充<BR><BR></div>");

var u67 = document.getElementById('u67');
gv_vAlignTable['u67'] = 'top';
var u20 = document.getElementById('u20');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u20ann'), "<div id='u20Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u20', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u20').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u20based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u20base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u20based = document.getElementById('u20based');
            
InsertBeforeEnd(u20based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 添加出库单明细，把用户选择的放入列表，允许多次添加<BR><BR></div>");

u20.style.cursor = 'pointer';
if (bIE) u20.attachEvent("onclick", Clicku20);
else u20.addEventListener("click", Clicku20, true);
function Clicku20(e)
{

if (true) {

	NewWindow("选择客户订单.html" + GetQuerystring(), "", "directories=1, height=400, location=1, menubar=1, resizable=1, scrollbars=1, status=1, toolbar=1, width=600", true, 600, 400);

}

}

var u73 = document.getElementById('u73');
gv_vAlignTable['u73'] = 'top';
var u48 = document.getElementById('u48');

var u4 = document.getElementById('u4');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u4ann'), "<div id='u4Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u4', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u4').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u4based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u4base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u4based = document.getElementById('u4based');
            
InsertBeforeEnd(u4based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 填单日期缺省值为当天日期，用户可以修改，格式为YYYY-MM-DD<BR><BR></div>");

var u11 = document.getElementById('u11');
gv_vAlignTable['u11'] = 'top';
var u64 = document.getElementById('u64');

var u70 = document.getElementById('u70');

var u39 = document.getElementById('u39');
gv_vAlignTable['u39'] = 'top';
var u119 = document.getElementById('u119');
gv_vAlignTable['u119'] = 'top';
var u87 = document.getElementById('u87');
gv_vAlignTable['u87'] = 'top';
var u55 = document.getElementById('u55');
gv_vAlignTable['u55'] = 'top';
var u93 = document.getElementById('u93');
gv_vAlignTable['u93'] = 'top';
var u61 = document.getElementById('u61');
gv_vAlignTable['u61'] = 'top';
var u116 = document.getElementById('u116');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u116ann'), "<div id='u116Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u116', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u116').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u116based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u116base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u116based = document.getElementById('u116based');
            
InsertBeforeEnd(u116based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 缺省值为：根据用户选择的联系人自动填充<br>允许用户修改<BR><BR></div>");

var u103 = document.getElementById('u103');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u103ann'), "<div id='u103Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u103', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u103').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u103based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u103base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u103based = document.getElementById('u103based');
            
InsertBeforeEnd(u103based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 编辑框，用户填写，这列可能要大一点，便于输入<BR><BR></div>");

var u107 = document.getElementById('u107');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u107ann'), "<div id='u107Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u107', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u107').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u107based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u107base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u107based = document.getElementById('u107based');
            
InsertBeforeEnd(u107based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 编辑框，用户填写，这列可能要大一点，便于输入<BR><BR></div>");

var u84 = document.getElementById('u84');

var u52 = document.getElementById('u52');

var u90 = document.getElementById('u90');

var u36 = document.getElementById('u36');

var u89 = document.getElementById('u89');
gv_vAlignTable['u89'] = 'top';
var u15 = document.getElementById('u15');
gv_vAlignTable['u15'] = 'top';
var u81 = document.getElementById('u81');
gv_vAlignTable['u81'] = 'top';
var u27 = document.getElementById('u27');
gv_vAlignTable['u27'] = 'top';
var u33 = document.getElementById('u33');
gv_vAlignTable['u33'] = 'top';
var u101 = document.getElementById('u101');
gv_vAlignTable['u101'] = 'top';
var u0 = document.getElementById('u0');

var u24 = document.getElementById('u24');

var u77 = document.getElementById('u77');
gv_vAlignTable['u77'] = 'top';
var u30 = document.getElementById('u30');

var u100 = document.getElementById('u100');

var u58 = document.getElementById('u58');

var u5 = document.getElementById('u5');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u5ann'), "<div id='u5Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u5', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u5').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u5based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u5base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u5based = document.getElementById('u5based');
            
InsertBeforeEnd(u5based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 操作人不能输入，是根据session中的用户姓名自动填充<BR><BR></div>");

var u21 = document.getElementById('u21');

var u74 = document.getElementById('u74');

var u49 = document.getElementById('u49');
gv_vAlignTable['u49'] = 'top';
var u12 = document.getElementById('u12');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u12ann'), "<div id='u12Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u12', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u12').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u12based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u12base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u12based = document.getElementById('u12based');
            
InsertBeforeEnd(u12based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 用户选择仓库，必填<BR><BR></div>");

var u65 = document.getElementById('u65');
gv_vAlignTable['u65'] = 'top';
var u71 = document.getElementById('u71');
gv_vAlignTable['u71'] = 'top';
var u121 = document.getElementById('u121');
gv_vAlignTable['u121'] = 'top';
var u94 = document.getElementById('u94');

var u62 = document.getElementById('u62');

var u46 = document.getElementById('u46');

var u117 = document.getElementById('u117');
gv_vAlignTable['u117'] = 'top';
var u85 = document.getElementById('u85');
gv_vAlignTable['u85'] = 'top';
var u120 = document.getElementById('u120');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u120ann'), "<div id='u120Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u120', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u120').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u120based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u120base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u120based = document.getElementById('u120based');
            
InsertBeforeEnd(u120based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 缺省值为：根据用户选择的联系人自动填充<br>允许用户修改<BR><BR></div>");

var u91 = document.getElementById('u91');
gv_vAlignTable['u91'] = 'top';
var u37 = document.getElementById('u37');
gv_vAlignTable['u37'] = 'top';
var u43 = document.getElementById('u43');
gv_vAlignTable['u43'] = 'top';
var u18 = document.getElementById('u18');
gv_vAlignTable['u18'] = 'center';
var u82 = document.getElementById('u82');

var u1 = document.getElementById('u1');
gv_vAlignTable['u1'] = 'center';
var u34 = document.getElementById('u34');

var u40 = document.getElementById('u40');

var u68 = document.getElementById('u68');

var u25 = document.getElementById('u25');
gv_vAlignTable['u25'] = 'top';
var u31 = document.getElementById('u31');
gv_vAlignTable['u31'] = 'top';
var u97 = document.getElementById('u97');
gv_vAlignTable['u97'] = 'top';
var u59 = document.getElementById('u59');
gv_vAlignTable['u59'] = 'top';
var u22 = document.getElementById('u22');

var u75 = document.getElementById('u75');
gv_vAlignTable['u75'] = 'top';
var u88 = document.getElementById('u88');

var u8 = document.getElementById('u8');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u8ann'), "<div id='u8Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u8', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u8').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u8based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u8base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u8based = document.getElementById('u8based');
            
InsertBeforeEnd(u8based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 用户不能填写，在用户临时保存或提交后，后台保存完数据后，返回给前台填充进去<BR><BR></div>");

var u112 = document.getElementById('u112');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u112ann'), "<div id='u112Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u112', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u112').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u112based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u112base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u112based = document.getElementById('u112based');
            
InsertBeforeEnd(u112based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 删除本行记录<BR><BR></div>");

var u72 = document.getElementById('u72');

var u56 = document.getElementById('u56');

var u95 = document.getElementById('u95');
gv_vAlignTable['u95'] = 'top';
var u122 = document.getElementById('u122');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u122ann'), "<div id='u122Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u122', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u122').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u122based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u122base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u122based = document.getElementById('u122based');
            
InsertBeforeEnd(u122based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 缺省值为：根据用户选择的联系人自动填充<br>允许用户修改<BR><BR></div>");

var u108 = document.getElementById('u108');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u108ann'), "<div id='u108Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u108', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u108').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u108based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u108base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u108based = document.getElementById('u108based');
            
InsertBeforeEnd(u108based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 临时保存，临时保存的数据可以做修改操作，成功后，清空<BR><BR></div>");

var u113 = document.getElementById('u113');
gv_vAlignTable['u113'] = 'top';
var u47 = document.getElementById('u47');
gv_vAlignTable['u47'] = 'top';
var u53 = document.getElementById('u53');
gv_vAlignTable['u53'] = 'top';
var u114 = document.getElementById('u114');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u114ann'), "<div id='u114Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u114', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u114').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u114based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u114base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u114based = document.getElementById('u114based');
            
InsertBeforeEnd(u114based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 用户选择选择收货人，必填<br>收货人的下拉列表框的初始化，是在用户选择了客户后，查询联系人信息表初始化的<BR><BR></div>");

var u28 = document.getElementById('u28');

var u92 = document.getElementById('u92');

var u2 = document.getElementById('u2');
gv_vAlignTable['u2'] = 'top';
var u44 = document.getElementById('u44');

var u50 = document.getElementById('u50');

var u19 = document.getElementById('u19');
gv_vAlignTable['u19'] = 'top';
var u78 = document.getElementById('u78');

var u7 = document.getElementById('u7');
gv_vAlignTable['u7'] = 'top';
var u41 = document.getElementById('u41');
gv_vAlignTable['u41'] = 'top';
var u111 = document.getElementById('u111');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u111ann'), "<div id='u111Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u111', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u111').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u111based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u111base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u111based = document.getElementById('u111based');
            
InsertBeforeEnd(u111based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 删除本行记录<BR><BR></div>");

var u69 = document.getElementById('u69');
gv_vAlignTable['u69'] = 'top';
var u123 = document.getElementById('u123');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u123ann'), "<div id='u123Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u123', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u123').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u123based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u123base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u123based = document.getElementById('u123based');
            
InsertBeforeEnd(u123based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 提交后方能打印<BR><BR></div>");

var u32 = document.getElementById('u32');

var u16 = document.getElementById('u16');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u16ann'), "<div id='u16Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u16', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u16').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u16based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u16base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u16based = document.getElementById('u16based');
            
InsertBeforeEnd(u16based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 备注，可不填，要大一点<BR><BR></div>");

var u98 = document.getElementById('u98');

var u9 = document.getElementById('u9');
gv_vAlignTable['u9'] = 'top';
var u13 = document.getElementById('u13');
gv_vAlignTable['u13'] = 'top';
var u66 = document.getElementById('u66');

var u105 = document.getElementById('u105');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u105ann'), "<div id='u105Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u105', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u105').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u105based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u105base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u105based = document.getElementById('u105based');
            
InsertBeforeEnd(u105based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 编辑框，用户填写，这列可能要大一点，便于输入<BR><BR></div>");

var u6 = document.getElementById('u6');
gv_vAlignTable['u6'] = 'top';
var u35 = document.getElementById('u35');
gv_vAlignTable['u35'] = 'top';
var u57 = document.getElementById('u57');
gv_vAlignTable['u57'] = 'top';
var u10 = document.getElementById('u10');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u10ann'), "<div id='u10Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u10', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u10').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u10based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u10base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u10based = document.getElementById('u10based');
            
InsertBeforeEnd(u10based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 用户选择选择客户，必填<BR><BR></div>");

var u63 = document.getElementById('u63');
gv_vAlignTable['u63'] = 'top';
var u38 = document.getElementById('u38');

var u118 = document.getElementById('u118');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u118ann'), "<div id='u118Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u118', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u118').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u118based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u118base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u118based = document.getElementById('u118based');
            
InsertBeforeEnd(u118based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 缺省值为：根据用户选择的联系人自动填充<br>允许用户修改<BR><BR></div>");

var u3 = document.getElementById('u3');
gv_vAlignTable['u3'] = 'top';
if (window.OnLoad) OnLoad();
