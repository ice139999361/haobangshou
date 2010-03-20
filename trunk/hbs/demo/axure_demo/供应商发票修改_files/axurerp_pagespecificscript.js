
var PageName = '供应商发票修改';
var PageId = 'p664571c71eb84824905035f4a524c8f0'
var PageUrl = '供应商发票修改.html'
document.title = '供应商发票修改';

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

var u16 = document.getElementById('u16');
gv_vAlignTable['u16'] = 'top';
var u7 = document.getElementById('u7');

var u28 = document.getElementById('u28');
gv_vAlignTable['u28'] = 'top';
var u30 = document.getElementById('u30');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u30ann'), "<div id='u30Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u30', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u30').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u30based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u30base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u30based = document.getElementById('u30based');
            
InsertBeforeEnd(u30based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 关闭本界面<BR><BR></div>");

u30.style.cursor = 'pointer';
if (bIE) u30.attachEvent("onclick", Clicku30);
else u30.addEventListener("click", Clicku30, true);
function Clicku30(e)
{

if (true) {

	parent.window.close();

}

}

var u15 = document.getElementById('u15');

var u2 = document.getElementById('u2');
gv_vAlignTable['u2'] = 'top';
var u19 = document.getElementById('u19');

var u13 = document.getElementById('u13');

var u31 = document.getElementById('u31');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u31ann'), "<div id='u31Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u31', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u31').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u31based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u31base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u31based = document.getElementById('u31based');
            
InsertBeforeEnd(u31based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 保存修改的信息，并关闭本页面<BR><BR></div>");

u31.style.cursor = 'pointer';
if (bIE) u31.attachEvent("onclick", Clicku31);
else u31.addEventListener("click", Clicku31, true);
function Clicku31(e)
{

if (true) {

	parent.window.close();

}

}

var u22 = document.getElementById('u22');
gv_vAlignTable['u22'] = 'top';
var u12 = document.getElementById('u12');
gv_vAlignTable['u12'] = 'top';
var u5 = document.getElementById('u5');

var u8 = document.getElementById('u8');
gv_vAlignTable['u8'] = 'top';
var u10 = document.getElementById('u10');
gv_vAlignTable['u10'] = 'top';
var u0 = document.getElementById('u0');

var u26 = document.getElementById('u26');
gv_vAlignTable['u26'] = 'top';
var u25 = document.getElementById('u25');

var u21 = document.getElementById('u21');

var u17 = document.getElementById('u17');

var u3 = document.getElementById('u3');

var u29 = document.getElementById('u29');

var u23 = document.getElementById('u23');

var u14 = document.getElementById('u14');
gv_vAlignTable['u14'] = 'top';
var u6 = document.getElementById('u6');
gv_vAlignTable['u6'] = 'top';
var u9 = document.getElementById('u9');

var u20 = document.getElementById('u20');
gv_vAlignTable['u20'] = 'top';
var u1 = document.getElementById('u1');
gv_vAlignTable['u1'] = 'center';
var u11 = document.getElementById('u11');

var u18 = document.getElementById('u18');
gv_vAlignTable['u18'] = 'top';
var u24 = document.getElementById('u24');
gv_vAlignTable['u24'] = 'top';
var u4 = document.getElementById('u4');
gv_vAlignTable['u4'] = 'top';
var u27 = document.getElementById('u27');

if (window.OnLoad) OnLoad();
