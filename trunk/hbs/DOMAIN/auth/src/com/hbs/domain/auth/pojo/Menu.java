/**
 * system £ºhbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.domain.auth.pojo;



import java.util.ArrayList;
import java.util.List;

public class Menu {
	Integer id;
	String text;
	String menuOrder;
	String iconCls ="nav";
	boolean leaf;
	String icon ="\\portal\\images\\title_icon02.gif";
	String url;
	List<Menu> children;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
	public void addChildren(Menu menu){
		if (null == children){
			children = new ArrayList<Menu>();			
		}
		children.add(menu);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
			sb.append("\"id\":").append("\"").append(this.id.toString()).append("\",");
			sb.append("\"text\":").append("\"").append(this.text).append("\",");
			sb.append("\"menuOrder\":").append("\"").append(this.menuOrder.toString()).append("\",");
			sb.append("\"iconCls\":").append("\"").append(this.iconCls).append("\",");
			sb.append("\"leaf\":").append(this.leaf).append(",");
			
			if(leaf){
				sb.append("\"url\":").append("\"").append(this.url).append("\"");
			}
			if(null!= children && children.size()>0){
				int iSize = children.size();
				sb.append("\"children\": [");
				for(int i =0 ;i< iSize;i++){
					Menu mm = children.get(i);
					sb.append(mm.toString());
					if(i != iSize -1){
						sb.append(",");
					}
				}
				sb.append("]");
			}
		sb.append("}");
		return sb.toString();
	}
	
	
	
}
