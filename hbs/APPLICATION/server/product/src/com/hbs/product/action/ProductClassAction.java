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
 * ����˾�������Action
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class ProductClassAction extends BaseAction {
	/**
	 * Manager��
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
	 * ��ѯ
	 * @action.input pClass.��ѯ����
	 * @action.result list���б� count������
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
			setErrorReason("�ڲ�����");
			return ERROR;	
		}
	}
	
	/**
	 * ��ȡ���Ϸ�����ϸ��Ϣ
	 * @action.input pClass.clsCode
	 * @action.result pClass.*
	 * @return
	 */
	public String doGet()
	{
		try{
			if(pClass == null || pClass.getClsCode() == null)
			{
				setErrorReason("��������");
				logger.info("��������");
				return ERROR;
			}
			ProductClassMgr mgr = (ProductClassMgr) BeanLocator.getInstance().getBean(productClassMgrName);
			pClass = mgr.getProductClass(pClass.getClsCode().toString());
			setResult("pClass", pClass);
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doGet.", e);
			setErrorReason("�ڲ�����");
			return ERROR;	
		}
	}

	/**
	 * �г�ָ��id������
	 * @action.input id
	 * @action.result list���б� count������
	 * @return
	 */
	public String doGetList() {
		if(id == null)
		{
			setErrorReason("��������");
			logger.info("��������");
			return ERROR;
		}
		pClass = new ProductClass();
		pClass.setParentClsCode(id);
		return doList();
	}
	
	/**
	 * ��ȡָ��id�����������ϸ��Ϣ
	 * @action.input id
	 * @action.result pClass.*
	 * @return
	 */
	public String doGetInfo() {
		if(id == null)
		{
			setErrorReason("��������");
			logger.info("��������");
			return ERROR;
		}
		pClass = new ProductClass();
		pClass.setClsCode(id);
		return doGet();
	}
}
