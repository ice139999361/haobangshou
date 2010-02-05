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
	 * 保存新增的产品类别，如果是非一级类别，则需要填写父类别
	 * @param pClass
	 * @return >0 成功，插入的classCode, -1 格式不正确
	 * @throws Exception
	 */
	public int saveProductClass(ProductClass pClass) throws Exception{
		int ret =0;
		logger.debug("保存公司产品类别：传入的参数为：" + pClass.toString());
		String classLevel = pClass.getClsLevel();
		if(!classLevel.endsWith(ProductConstants.PRODUCT_CLASS_LEVEL_1)){//非一级，需要填写父类别
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
	 * 修改公司的产品类别
	 * @param pClass
	 * @return 0 成功 -1 格式不正确
	 * @throws Exception
	 */
	public int updateProductClass(ProductClass pClass) throws Exception{
		int ret =0;
		logger.debug("修改公司产品类别：传入的参数为：" + pClass.toString());
		String classLevel = pClass.getClsLevel();
		if(!classLevel.endsWith(ProductConstants.PRODUCT_CLASS_LEVEL_1)){//非一级，需要填写父类别
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
	 * 删除产品类别，需要判断是否该产品类别下是否有物料信息，有则不能删除
	 * @param id  类别编码
	 * @return  0--成功， -1 有物料信息，无法删除
	 * @throws Exception
	 */
	public int deleteProductClass(String id) throws Exception{
		int ret =0;
		CompanyPartNoMgr cpMgr = (CompanyPartNoMgr)BeanLocator.getInstance().getBean(ProductConstants.COMPANY_PART_NO_MGR);
		CompanyPartNo cPartNo = new CompanyPartNo();
		cPartNo.setClsCode(id);
		Integer i = cpMgr.getCompanyPartNoListCount(cPartNo);
		if(i != null && i== 0){//存在该产品类型的物料，无法删除
			ret =-1;
		}
		if(ret == 0) {
			ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
			pcDao.deleteProductClass(id);
		}
		
		return ret;
	}
	/**
	 * 根据产品编码，查询产品分类信息
	 * @param clsCode
	 * @return
	 * @throws Exception
	 */
	public ProductClass getProductClass(String clsCode) throws Exception{
		logger.debug("查询公司产品类别：传入的参数为：" + clsCode);
		ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
		return pcDao.findProductClass(clsCode);
	}
	/**
	 * 根据查询条件，查询产品类别列表信息
	 * @param pClass
	 * @return
	 * @throws Exception
	 */
	public List<ProductClass> getProductClassList(ProductClass pClass) throws Exception{
		logger.debug("查询公司产品类别列表：传入的参数为：" + pClass.toString());
		ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
		return pcDao.listProductClass(pClass);
	}
	
	public Integer getProductClassListCount(ProductClass pClass) throws Exception{
		logger.debug("查询公司产品类别列表数目：传入的参数为：" + pClass.toString());
		ProductClassDao pcDao = (ProductClassDao)BeanLocator.getInstance().getBean(ProductConstants.PRODUCT_CLASS_DAO);
		return pcDao.listProductClassCount(pClass);
	}
}
