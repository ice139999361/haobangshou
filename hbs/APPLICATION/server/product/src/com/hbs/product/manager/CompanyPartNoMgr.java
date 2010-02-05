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
import com.hbs.domain.product.dao.CompanyPartNoDao;
import com.hbs.domain.product.dao.ProductClassDao;
import com.hbs.domain.product.pojo.CompanyPartNo;
import com.hbs.domain.product.pojo.ProductClass;
import com.hbs.product.constants.ProductConstants;

/**
 * @author Administrator
 *
 */
public class CompanyPartNoMgr {
	
	
	private static final Logger logger = Logger.getLogger(CompanyPartNoMgr.class);
	
	/**
	 * ���������Ĺ�˾������Ϣ����Ҫ�жϲ�Ʒ����Ƿ����뼰�Ƿ����
	 * @param partNo
	 * @return 0-�ɹ�   -1 �������Ʒ���  -2 ����Ĳ�Ʒ��𲻴���
	 * @throws Exception
	 */
	public int saveCompanyPartNo(CompanyPartNo partNo) throws Exception{
		int ret =0;
		logger.debug("���������Ĺ�˾������Ϣ������Ĳ���Ϊ��" + partNo.toString());
		String clsCode = partNo.getClsCode();
		if(clsCode == null){//�����ϵĲ�Ʒ���
			ret = -1;
		}else{
			ProductClass pClass= getProductClass(clsCode);
			if(pClass == null){
				ret = -2;
			}
		}
		if(ret ==0){
			CompanyPartNoDao cpDao = (CompanyPartNoDao)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_DAO);
			cpDao.insertCompanyPartNo(partNo);
		}
		return ret;
	}
	
	/**
	 * �޸Ĺ�˾������Ϣ����Ҫ�жϲ�Ʒ����Ƿ����뼰�Ƿ����
	 * @param partNo
	 * @return 0-�ɹ�   -1 �������Ʒ���  -2 ����Ĳ�Ʒ��𲻴���
	 * @throws Exception
	 */
	public int updateCompanyPartNo(CompanyPartNo partNo) throws Exception{
		int ret =0;
		logger.debug("�޸Ĺ�˾������Ϣ������Ĳ���Ϊ��" + partNo.toString());
		String clsCode = partNo.getClsCode();
		if(clsCode == null){//�����ϵĲ�Ʒ���
			ret = -1;
		}else{
			ProductClass pClass= getProductClass(clsCode);
			if(pClass == null){
				ret = -2;
			}
		}
		if(ret ==0){
			CompanyPartNoDao cpDao = (CompanyPartNoDao)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_DAO);
			cpDao.updateCompanyPartNo(partNo);
		}
		return ret;
	}
	/**
	 * ɾ����˾��������Ϣ
	 * @param partNo
	 * @throws Exception
	 */
	public void deleteComPanyPartNo(String partNo) throws Exception{
		logger.debug("ɾ����˾������Ϣ������Ĳ���Ϊ��" + partNo);
		CompanyPartNoDao cpDao = (CompanyPartNoDao)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_DAO);
		cpDao.deleteCompanyPartNo(partNo);
	}
	/**
	 * ���ݹ�˾���ϱ�ţ���ѯ��˾������Ϣ
	 * @param partNo
	 * @return
	 * @throws Exception
	 */
	public CompanyPartNo getCompanyPartNo(String partNo) throws Exception{
		logger.debug("��ѯ��˾������Ϣ������Ĳ���Ϊ��" + partNo);
		CompanyPartNoDao cpDao = (CompanyPartNoDao)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_DAO);
		return cpDao.findCompanyPartNo(partNo);
	}
	/**
	 * ���ݲ�ѯ��������ѯ��˾������Ϣ�б�
	 * @param partNo
	 * @return
	 * @throws Exception
	 */
	public List<CompanyPartNo> getCompanyPartNoList(CompanyPartNo partNo)throws Exception{
		logger.debug("��ѯ��˾������Ϣ�б�����Ĳ���Ϊ��" + partNo);
		CompanyPartNoDao cpDao = (CompanyPartNoDao)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_DAO);
		return cpDao.listCompanyPartNo(partNo);
	}
	 
	/**
	 * ���ݲ�ѯ��������ѯ��˾������Ϣ�б�����
	 * @param partNo
	 * @return
	 * @throws Exception
	 */
	public Integer getCompanyPartNoListCount(CompanyPartNo partNo)throws Exception{
		logger.debug("��ѯ��˾������Ϣ�б�����������Ĳ���Ϊ��" + partNo);
		CompanyPartNoDao cpDao = (CompanyPartNoDao)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_DAO);
		return cpDao.listCompanyPartNoCount(partNo);
	}
	
	
	private ProductClass getProductClass(String clsCode) throws Exception{		
		ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
		return pcDao.findProductClass(clsCode);
	}
}
