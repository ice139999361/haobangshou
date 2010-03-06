
var PageName = '代办首页';
var PageId = 'pa2310db64b534e90b878cfe5812243be'
var PageUrl = '代办首页.html'
document.title = '代办首页';

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

var u10 = document.getElementById('u10');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u10ann'), "<div id='u10Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u10', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u10').replace(/\[\[label\]\]/g, "?"));

InsertAfterBegin(document.body, "<div id='u10based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u10base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u10based = document.getElementById('u10based');
            
InsertBeforeEnd(u10based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 提醒事项，可以收缩，提醒列表能够滚动<BR><BR></div>");

var u5 = document.getElementById('u5');
gv_vAlignTable['u5'] = 'center';
var u0 = document.getElementById('u0');

var u13 = document.getElementById('u13');
gv_vAlignTable['u13'] = 'top';
var u3 = document.getElementById('u3');
gv_vAlignTable['u3'] = 'center';
var u9 = document.getElementById('u9');
gv_vAlignTable['u9'] = 'center';
var u6 = document.getElementById('u6');
gv_vAlignTable['u6'] = 'top';
var u1 = document.getElementById('u1');
gv_vAlignTable['u1'] = 'center';
var u12 = document.getElementById('u12');
gv_vAlignTable['u12'] = 'top';
var u4 = document.getElementById('u4');

x = 0;
y = 0;
InsertAfterBegin(document.getElementById('u4ann'), "<div id='u4Note' class='annnoteimage' style='left:" + x + ";top:" + y + "' onclick=\"ToggleWorkflow(event, 'u4', 300, 300, false)\"></div>");

eval(annwindow.replace(/\[\[id\]\]/g, 'u4').replace(/\[\[label\]\]/g, "这个条目可以收缩"));

InsertAfterBegin(document.body, "<div id='u4based' style='z-index:1; visibility:hidden; position:absolute'></div><div id='u4base' style='z-index:1; visibility:hidden; position:absolute'></div>");
var u4based = document.getElementById('u4based');
            
InsertBeforeEnd(u4based, "<div class='anncontent'><span class='annfieldname'>功能说明:</span> 需要处理的代办列表，可以收缩，处理的代办列表能够滚动<BR><BR></div>");

var u11 = document.getElementById('u11');
gv_vAlignTable['u11'] = 'center';
var u7 = document.getElementById('u7');
gv_vAlignTable['u7'] = 'top';
var u2 = document.getElementById('u2');

var u8 = document.getElementById('u8');

if (window.OnLoad) OnLoad();
