package com.hbs.invoice.action;

import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.invoice.pojo.BenefitDetail;
import com.hbs.invoice.manager.BenefitMgr;


@SuppressWarnings("serial")
public class BenefitAction extends BaseAction {
	protected static final Logger logger = Logger.getLogger(BenefitAction.class);
	
	private BenefitDetail benefitDetail;

	public BenefitDetail getBenefitDetail() {
		return benefitDetail;
	}

	public void setBenefitDetail(BenefitDetail benefitDetail) {
		this.benefitDetail = benefitDetail;
	}
	
	/**
	 * list the sales benefit
	 * @return
	 */
	public String doList(){
		try {
			logger.debug("begin list the sales benefit list the function is doList()");
			if(benefitDetail == null){
				logger.debug("系统无输入参数，不能执行查询操作!");
				setErrorReason("系统无输入参数，不能执行查询操作!");
				return ERROR;
			}else{
				if(StringUtils.isEmpty(benefitDetail.getSalesId()) && StringUtils.isEmpty(benefitDetail.getSendMonth())){
					logger.debug("请至少输入销售人员或年月!");
					setErrorReason("请至少输入销售人员或年月!");
					return ERROR;
				}
			}
				
			setPagination(benefitDetail);
			setResult("list", listBenefit(benefitDetail));
			setTotalCount(listBenefitCount(benefitDetail));
			
			
			logger.debug("end list the sales benefit list the function is doList()");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in sales benefit list doList", e);
			setErrorReason(e.getMessage());
			return ERROR;
		}
	}
	
	public String doListDetail(){
		try {
			logger.debug("begin list the sales benefit list the function is doListDetail()");
			if(benefitDetail == null){
				logger.debug("系统无输入参数，不能执行查询操作!");
				setErrorReason("系统无输入参数，不能执行查询操作!");
				return ERROR;
			}
				
			setPagination(benefitDetail);
			setResult("list", listBenefitDetail(benefitDetail));
			setTotalCount(listBenefitDetailCount(benefitDetail));
			
			
			logger.debug("end list the sales benefit list the function is doListDetail()");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in sales benefit list doList", e);
			setErrorReason(e.getMessage());
			return ERROR;
		}
	}
	private List<BenefitDetail> listBenefit(BenefitDetail benefitDetail) throws Exception{
		logger.debug("get the sales benefit info ,the input parameteris :" + benefitDetail);
		BenefitMgr mgr = (BenefitMgr)BeanLocator.getInstance().getBean("benefitMgr");
		List<BenefitDetail> listBenefit = mgr.getBenefitTotalList(benefitDetail);
		return listBenefit;
	}
	
	private Integer listBenefitCount(BenefitDetail benefitDetail) throws Exception{
		logger.debug("get the sales benefit settlement count ,the input parameteris :" + benefitDetail);
		BenefitMgr mgr = (BenefitMgr)BeanLocator.getInstance().getBean("benefitMgr");
		Integer iCount = mgr.getBenefitTotalListCount(benefitDetail);
		return iCount;
	}
	
	private List<BenefitDetail> listBenefitDetail(BenefitDetail benefitDetail) throws Exception{
		logger.debug("get the sales benefit info ,the input parameteris :" + benefitDetail);
		BenefitMgr mgr = (BenefitMgr)BeanLocator.getInstance().getBean("benefitMgr");
		List<BenefitDetail> listBenefit = mgr.getBenefitDetailList(benefitDetail);
		return listBenefit;
	}
	
	private Integer listBenefitDetailCount(BenefitDetail benefitDetail) throws Exception{
		logger.debug("get the sales benefit settlement count ,the input parameteris :" + benefitDetail);
		BenefitMgr mgr = (BenefitMgr)BeanLocator.getInstance().getBean("benefitMgr");
		Integer iCount = mgr.getBenefitDetailListCount(benefitDetail);
		return iCount;
	}
}
