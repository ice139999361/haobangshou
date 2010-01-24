Ext.namespace('ExtUx.widget.portal');
/**
 * 页面底部区域panel，用来展示版权信息。
 */

ExtUx.widget.portal.BottomPanel=Ext.extend(Ext.Panel,{
    //default setting
    border:false,
    header:false,
    frame:false,
    onRender : function(ct, position){
        ExtUx.widget.portal.BottomPanel.superclass.onRender.call(this,ct,position);   
        var tpl = new Ext.XTemplate(
        	'<div class="copyright" ><a href="{url}" target="_blank">{title}</a></div>'
        );
        tpl.append(this.body,this);    
    }
});
Ext.reg('bottompanel', ExtUx.widget.portal.BottomPanel);