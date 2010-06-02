
var PageName = '财务';
var PageId = 'paaa7643efa064fe39ca35761c6c40a96'
var PageUrl = '财务.html'
document.title = '财务';

if (top.location != self.location)
{
	if (parent.HandleMainFrameChanged) {
		parent.HandleMainFrameChanged();
	}
}

if (window.OnLoad) OnLoad();
