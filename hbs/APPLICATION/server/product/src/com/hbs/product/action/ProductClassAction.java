/**
 * 
 */
package com.hbs.product.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.product.pojo.ProductClass;
import com.hbs.product.manager.ProductClassMgr;

/**
 * 本公司物料类别Action
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class ProductClassAction extends BaseAction {
	/**
	 * Manager名
	 */
	public static final String productClassMgrName = "productClassMgr";

	/**
	 * logger.
	 */
	private static final Logger logger = Logger
			.getLogger(ProductClassAction.class);
	
	private ProductClass pClass;
	
	/**
	 * @return the pClass
	 */
	public ProductClass getPClass() {
		return pClass;
	}

	/**
	 * @param pClass the pClass to set
	 */
	public void setPClass(ProductClass pClass) {
		this.pClass = pClass;
	}

	private Integer id;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 查询
	 * @action.input pClass.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList()
	{
		try {
			if(pClass == null)
				pClass = new ProductClass();
			setPagination(pClass);
			ProductClassMgr mgr = (ProductClassMgr) BeanLocator.getInstance().getBean(productClassMgrName);
			List<ProductClass> list = mgr.getProductClassList(pClass);
			setResult("list", list);
			setTotalCount(mgr.getProductClassListCount(pClass));
			setResult("count", getTotalCount());
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doGet.", e);
			setErrorReason("内部错误");
			return ERROR;	
		}
	}
	
	/**
	 * 获取物料分类详细信息
	 * @action.input pClass.clsCode
	 * @action.result pClass.*
	 * @return
	 */
	public String doGet()
	{
		try{
			if(pClass == null || pClass.getClsCode() == null)
			{
				setErrorReason("参数错误！");
				logger.info("参数错误！");
				return ERROR;
			}
			ProductClassMgr mgr = (ProductClassMgr) BeanLocator.getInstance().getBean(productClassMgrName);
			pClass = mgr.getProductClass(pClass.getClsCode().toString());
			setResult("pClass", pClass);
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doGet.", e);
			setErrorReason("内部错误");
			return ERROR;	
		}
	}

	/**
	 * 列出指定id的子类
	 * @action.input id
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doGetList() {
		if(id == null)
		{
			setErrorReason("参数错误！");
			logger.info("参数错误！");
			return ERROR;
		}
		pClass = new ProductClass();
		pClass.setParentClsCode(id);
		return doList();
	}
	
	/**
	 * 获取指定id的物料类别详细信息
	 * @action.input id
	 * @action.result pClass.*
	 * @return
	 */
	public String doGetInfo() {
		if(id == null)
		{
			setErrorReason("参数错误！");
			logger.info("参数错误！");
			return ERROR;
		}
		pClass = new ProductClass();
		pClass.setClsCode(id);
		return doGet();
	}
}
