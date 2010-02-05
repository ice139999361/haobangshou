package com.hbs.domain.product.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;

/**
 * ProductClass对象.
 * 
 * @author hbs
 * 
 */
public class ProductClass extends BaseDomain {

	/**
	 * 产品类别编码.
	 */
	private Integer clsCode;

	/**
	 * 产品类别名称.
	 */
	private String clsName;

	/**
	 * 产品类别特征描述.
	 */
	private String clsDesc;

	/**
	 * 本类别所属父类别 ；为0空说明没有父类别，是最大类别
	 */
	private Integer parentClsCode = 0;
	/**
	 * 产品分层级别，一级的所属父类为0 级别可为：1---一级 /2---二级/3---三级/4---四级 等需定义到字典表中
	 */
	private String clsLevel;

	/**
	 * @return the clsCode
	 */
	public Integer getClsCode() {
		return clsCode;
	}

	/**
	 * @param clsCode
	 *            the clsCode to set
	 */
	public void setClsCode(Integer clsCode) {
		this.clsCode = clsCode;
	}

	/**
	 * @return the clsName
	 */
	public String getClsName() {
		return clsName;
	}

	/**
	 * @param clsName
	 *            the clsName to set
	 */
	public void setClsName(String clsName) {
		this.clsName = clsName;
	}

	/**
	 * @return the clsDesc
	 */
	public String getClsDesc() {
		return clsDesc;
	}

	/**
	 * @param clsDesc
	 *            the clsDesc to set
	 */
	public void setClsDesc(String clsDesc) {
		this.clsDesc = clsDesc;
	}

	/**
	 * @return the parentClsCode
	 */
	public Integer getParentClsCode() {
		return parentClsCode;
	}

	/**
	 * @param parentClsCode
	 *            the parentClsCode to set
	 */
	public void setParentClsCode(Integer parentClsCode) {
		this.parentClsCode = parentClsCode;
	}

	/**
	 * @return the clsLevel
	 */
	public String getClsLevel() {
		return clsLevel;
	}

	/**
	 * @param clsLevel
	 *            the clsLevel to set
	 */
	public void setClsLevel(String clsLevel) {
		this.clsLevel = clsLevel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.clsCode != null){
			sb.append("clsCode=").append(this.clsCode).append(" ");
		}
		if(this.clsName != null){
			sb.append("clsName=").append(this.clsName).append(" ");
		}
		if(this.clsDesc != null){
			sb.append("clsDesc=").append(this.clsDesc).append(" ");
		}
		if(this.parentClsCode != null){
			sb.append("parentClsCode=").append(this.parentClsCode).append(" ");
		}
		if(this.clsLevel != null){
			sb.append("clsLevel=").append(this.clsLevel).append(" ");
		}
		return sb.toString();
	}
	
}
