package com.hbs.invoice.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;

import com.hbs.domain.invoice.dao.BenefitDetailDao;
import com.hbs.domain.invoice.pojo.BenefitDetail;


public class BenefitMgr {

	private static final Logger logger = Logger.getLogger(BenefitMgr.class);
	
	
	/**
	 * 
	 * @param BenefitDetail
	 * @return
	 * @throws Exception
	 */
	public List<BenefitDetail> getBenefitDetailList(BenefitDetail benefitDetail) throws Exception{
		List<BenefitDetail> ret = null;
		logger.debug("查找利差详细信息！输入的参数为：" + benefitDetail.toString());
		BenefitDetailDao dao = (BenefitDetailDao)BeanLocator.getInstance().getBean("benefitDetailDaoImpl");
		ret = dao.listBenefitDetail(benefitDetail);
		return ret;
	}
	/**
	 * 
	 * @param BenefitDetail
	 * @return
	 * @throws Exception
	 */
	public int getBenefitDetailListCount(BenefitDetail benefitDetail) throws Exception{
		int ret =0;
		logger.debug("查找利差详细记录信息记录数！输入的参数为：" + benefitDetail.toString());
		BenefitDetailDao dao = (BenefitDetailDao)BeanLocator.getInstance().getBean("benefitDetailDaoImpl");
		ret = dao.listBenefitDetailCount(benefitDetail);
		return ret;
	}
	
	
	/**
	 * 
	 * @param BenefitDetail
	 * @return
	 * @throws Exception
	 */
	public List<BenefitDetail> getBenefitTotalList(BenefitDetail benefitDetail) throws Exception{
		List<BenefitDetail> ret = null;
		logger.debug("查找利差汇总信息！输入的参数为：" + benefitDetail.toString());
		BenefitDetailDao dao = (BenefitDetailDao)BeanLocator.getInstance().getBean("benefitDetailDaoImpl");
		ret = dao.listBenefitTotal(benefitDetail);
		return ret;
	}
	/**
	 * 
	 * @param BenefitDetail
	 * @return
	 * @throws Exception
	 */
	public int getBenefitTotalListCount(BenefitDetail benefitDetail) throws Exception{
		int ret =0;
		logger.debug("查找利差汇总记录信息记录数！输入的参数为：" + benefitDetail.toString());
		BenefitDetailDao dao = (BenefitDetailDao)BeanLocator.getInstance().getBean("benefitDetailDaoImpl");
		ret = dao.listBenefitTotalCount(benefitDetail);
		return ret;
	}
}
