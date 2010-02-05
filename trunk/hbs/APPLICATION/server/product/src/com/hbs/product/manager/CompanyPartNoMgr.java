/**
 * system ：hbs
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
	 * 保存新增的公司物料信息，需要判断产品类别是否输入及是否存在
	 * @param partNo
	 * @return 0-成功   -1 无输入产品类别  -2 输入的产品类别不存在
	 * @throws Exception
	 */
	public int saveCompanyPartNo(CompanyPartNo partNo) throws Exception{
		int ret =0;
		logger.debug("保存新增的公司物料信息，传入的参数为：" + partNo.toString());
		String clsCode = partNo.getClsCode();
		if(clsCode == null){//无物料的产品类别
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
	 * 修改公司物料信息，需要判断产品类别是否输入及是否存在
	 * @param partNo
	 * @return 0-成功   -1 无输入产品类别  -2 输入的产品类别不存在
	 * @throws Exception
	 */
	public int updateCompanyPartNo(CompanyPartNo partNo) throws Exception{
		int ret =0;
		logger.debug("修改公司物料信息，传入的参数为：" + partNo.toString());
		String clsCode = partNo.getClsCode();
		if(clsCode == null){//无物料的产品类别
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
	 * 删除公司的物料信息
	 * @param partNo
	 * @throws Exception
	 */
	public void deleteComPanyPartNo(String partNo) throws Exception{
		logger.debug("删除公司物料信息，传入的参数为：" + partNo);
		CompanyPartNoDao cpDao = (CompanyPartNoDao)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_DAO);
		cpDao.deleteCompanyPartNo(partNo);
	}
	/**
	 * 根据公司物料编号，查询公司物料信息
	 * @param partNo
	 * @return
	 * @throws Exception
	 */
	public CompanyPartNo getCompanyPartNo(String partNo) throws Exception{
		logger.debug("查询公司物料信息，传入的参数为：" + partNo);
		CompanyPartNoDao cpDao = (CompanyPartNoDao)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_DAO);
		return cpDao.findCompanyPartNo(partNo);
	}
	/**
	 * 根据查询条件，查询公司物料信息列表
	 * @param partNo
	 * @return
	 * @throws Exception
	 */
	public List<CompanyPartNo> getCompanyPartNoList(CompanyPartNo partNo)throws Exception{
		logger.debug("查询公司物料信息列表，传入的参数为：" + partNo);
		CompanyPartNoDao cpDao = (CompanyPartNoDao)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_DAO);
		return cpDao.listCompanyPartNo(partNo);
	}
	 
	/**
	 * 根据查询条件，查询公司物料信息列表总数
	 * @param partNo
	 * @return
	 * @throws Exception
	 */
	public Integer getCompanyPartNoListCount(CompanyPartNo partNo)throws Exception{
		logger.debug("查询公司物料信息列表总数，传入的参数为：" + partNo);
		CompanyPartNoDao cpDao = (CompanyPartNoDao)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_DAO);
		return cpDao.listCompanyPartNoCount(partNo);
	}
	
	
	private ProductClass getProductClass(String clsCode) throws Exception{		
		ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
		return pcDao.findProductClass(clsCode);
	}
}
