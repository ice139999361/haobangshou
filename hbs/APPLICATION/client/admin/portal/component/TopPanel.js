Ext.namespace('ExtUx.widget.portal');
/**
 * Portal Top面板
 * 初始化参数：
 *  logoImage:String 可选 log图片地址
 *  tools:Array 可选 右边放置的图标数组，元素为
 *      {image:'',handler:''} 
 */

ExtUx.widget.portal.TopPanel=Ext.extend(Ext.Panel,{
    border:false,
    header:false,
    frame:false,
    bodyStyle:'background-color:transparent',
    onRender : function(ct, position){
        ExtUx.widget.portal.TopPanel.superclass.onRender.call(this,ct,position);   
        var tpl = new Ext.XTemplate(
            '<div style="float:left;">',
            '<img src="{logoImage}"/>',
            '</div>',
            '<ul style="float:right;margin:20px 10px 0px 0px">',
                '<tpl for="tool">',
                    '<li style="float:left;margin-left:10px"><a href="javascript:void(0)" onclick="javascript:{handler}()"><img id="{id}" src="{image}"/></a></li>',    
                '</tpl>',
            '</ul>'
        );
        tpl.append(this.body,this);    
    }
});

Ext.reg('toppanel', ExtUx.widget.portal.TopPanel);