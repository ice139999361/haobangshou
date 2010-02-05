/**
 * system ��hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.product.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.product.dao.ProductClassDao;
import com.hbs.domain.product.pojo.CompanyPartNo;
import com.hbs.domain.product.pojo.ProductClass;
import com.hbs.product.constants.ProductConstants;

/**
 * @author Administrator
 *
 */
public class ProductClassMgr {
	
	private static final Logger logger = Logger.getLogger(ProductClassMgr.class);
	/**
	 * ���������Ĳ�Ʒ�������Ƿ�һ���������Ҫ��д�����
	 * @param pClass
	 * @return >0 �ɹ��������classCode, -1 ��ʽ����ȷ
	 * @throws Exception
	 */
	public int saveProductClass(ProductClass pClass) throws Exception{
		int ret =0;
		logger.debug("���湫˾��Ʒ��𣺴���Ĳ���Ϊ��" + pClass.toString());
		String classLevel = pClass.getClsLevel();
		if(!classLevel.endsWith(ProductConstants.PRODUCT_CLASS_LEVEL_1)){//��һ������Ҫ��д�����
			Integer pCode = pClass.getParentClsCode();
			if(pCode == null){
				ret = -1;
			}
		}
		if(ret == 0){
			ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
			ret = pcDao.insertProductClass(pClass);
			pClass.setClsCode(ret);
		}
		return ret;
	}
	/**
	 * �޸Ĺ�˾�Ĳ�Ʒ���
	 * @param pClass
	 * @return 0 �ɹ� -1 ��ʽ����ȷ
	 * @throws Exception
	 */
	public int updateProductClass(ProductClass pClass) throws Exception{
		int ret =0;
		logger.debug("�޸Ĺ�˾��Ʒ��𣺴���Ĳ���Ϊ��" + pClass.toString());
		String classLevel = pClass.getClsLevel();
		if(!classLevel.endsWith(ProductConstants.PRODUCT_CLASS_LEVEL_1)){//��һ������Ҫ��д�����
			Integer pCode = pClass.getParentClsCode();
			if(pCode == null){
				ret = -1;
			}
		}
		if(ret == 0){
			ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
			pcDao.updateProductClass(pClass);
			
		}
		return ret;
	}
	
	/**
	 * ɾ����Ʒ�����Ҫ�ж��Ƿ�ò�Ʒ������Ƿ���������Ϣ��������ɾ��
	 * @param id  ������
	 * @return  0--�ɹ��� -1 ��������Ϣ���޷�ɾ��
	 * @throws Exception
	 */
	public int deleteProductClass(String id) throws Exception{
		int ret =0;
		CompanyPartNoMgr cpMgr = (CompanyPartNoMgr)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_MGR);
		CompanyPartNo cPartNo = new CompanyPartNo();
		cPartNo.setClsCode(id);
		Integer i = cpMgr.getCompanyPartNoListCount(cPartNo);
		if(i != null && i== 0){//���ڸò�Ʒ���͵����ϣ��޷�ɾ��
			ret =-1;
		}
		if(ret == 0) {
			ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
			pcDao.deleteProductClass(id);
		}
		
		return ret;
	}
	/**
	 * ���ݲ�Ʒ���룬��ѯ��Ʒ������Ϣ
	 * @param clsCode
	 * @return
	 * @throws Exception
	 */
	public ProductClass getProductClass(String clsCode) throws Exception{
		logger.debug("��ѯ��˾��Ʒ��𣺴���Ĳ���Ϊ��" + clsCode);
		ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
		return pcDao.findProductClass(clsCode);
	}
	/**
	 * ���ݲ�ѯ��������ѯ��Ʒ����б���Ϣ
	 * @param pClass
	 * @return
	 * @throws Exception
	 */
	public List<ProductClass> getProductClassList(ProductClass pClass) throws Exception{
		logger.debug("��ѯ��˾��Ʒ����б�����Ĳ���Ϊ��" + pClass.toString());
		ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
		return pcDao.listProductClass(pClass);
	}
	
	public Integer getProductClassListCount(ProductClass pClass) throws Exception{
		logger.debug("��ѯ��˾��Ʒ����б���Ŀ������Ĳ���Ϊ��" + pClass.toString());
		ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
		return pcDao.listProductClassCount(pClass);
	}
}
