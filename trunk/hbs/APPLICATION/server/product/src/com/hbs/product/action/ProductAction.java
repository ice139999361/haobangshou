/**
 * 
 */
package com.hbs.product.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.customerinfo.action.CustPartNoInfoNormalAction;
import com.hbs.customerinfo.manager.CustPartNoInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.product.pojo.CompanyPartNo;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.product.manager.CompanyPartNoMgr;
import com.hbs.vendorinfo.action.VendorPartNoInfoNormalAction;
import com.hbs.vendorinfo.manager.VendorPartNoInfoMgr;

/**
 * 本公司物料Action
 * @author xyf
 * @actions doList doGet
 */
@SuppressWarnings("serial")
public class ProductAction extends BaseAction {
	/**
	 * Manager名
	 */
	public static final String companyPartNoMgrName = "companyPartNoMgr";

	/**
	 * logger.
	 */
	private static final Logger logger = Logger
			.getLogger(ProductAction.class);
	
	private CompanyPartNo partNo;

	public CompanyPartNo getPartNo() {
		return partNo;
	}

	public void setPartNo(CompanyPartNo partNo) {
		this.partNo = partNo;
	}
	
	/**
	 * 查询
	 * @action.input partNo.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList()
	{
		try
		{
			if(partNo == null)
				partNo = new CompanyPartNo();
			setPagination(partNo);
			CompanyPartNoMgr mgr = (CompanyPartNoMgr) getBean(companyPartNoMgrName);
			List<CompanyPartNo> list = mgr.getCompanyPartNoList(partNo);
			setResult("list", list);
			setTotalCount(mgr.getCompanyPartNoListCount(partNo));
			setResult("count", getTotalCount());
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doList.", e);
			setErrorReason("内部错误");
			return ERROR;			
		}
	}
	
	/**
	 * 获取物料详细信息
	 * @action.input partNo.partNo
	 * @action.result partNo.*
	 * @return
	 */
	public String doGet()
	{
		try{
			if(partNo == null || StringUtils.isEmpty(partNo.getPartNo()))
			{
				setErrorReason("参数错误！");
				logger.info("参数错误！");
				return ERROR;
			}
			CompanyPartNoMgr mgr = (CompanyPartNoMgr) getBean(companyPartNoMgrName);
			partNo = mgr.getCompanyPartNo(partNo.getPartNo());
			setResult("partNo", partNo);
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doGet.", e);
			setErrorReason("内部错误");
			return ERROR;	
		}
	}
	
    /**
     * 保存物料信息
     * @action.input	partNo.*
     * @return
     */
	public String doSave() {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doSave " + partNo);

			List<FieldErr> errs = CompanyPartNoUtil.checkInputFields(partNo);
			if(!errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}

			CompanyPartNoMgr mgr = (CompanyPartNoMgr)getBean(companyPartNoMgrName);
			CompanyPartNo partNo2 = mgr.getCompanyPartNo(partNo.getPartNo());
			int i;
			if(partNo2 == null)
				i = mgr.saveCompanyPartNo(partNo);
			else
				i = mgr.updateCompanyPartNo(partNo);
			if(i != 0)
			{
				logger.info("保存出错！");
				setErrorReason("保存出错！");
				return ERROR;
			}
			this.setAlertMsg("提交成功！");
			if (logger.isDebugEnabled())    logger.debug("end doSave");
    		return SUCCESS;
    	}
    	catch(Exception e)
    	{
    		logger.error("catch Exception in doSave.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}

	}
	
	/**
	 * 检查编码
	 * @action.input value
	 * @return
	 */
	public String doCheckPartNo() {
		try{
			String partNoStr = this.getHttpServletRequest().getParameter("value");
			if(StringUtils.isEmpty(partNoStr))
			{
				setErrorReason("参数错误！");
				logger.info("参数错误！");
				return ERROR;
			}
			CompanyPartNoMgr mgr = (CompanyPartNoMgr) getBean(companyPartNoMgrName);
			partNo = mgr.getCompanyPartNo(partNoStr);
			if(partNo == null)
				return SUCCESS;
			else{
				logger.debug("编码重复！");
				setErrorReason("编码重复！");
				return ERROR;
			}
		}catch(Exception e){
			logger.error("catch Exception in doCheckCommCode.", e);
			setErrorReason("内部错误");
			return ERROR;	
		}		
	}
	
	/**
	 * 删除本公司物料信息
	 * @action.input partNo.partNo
	 * @return
	 */
	public String doDelete(){
		try{
			if(partNo == null || StringUtils.isEmpty(partNo.getPartNo())){
				setErrorReason("参数错误！");
				logger.info("参数错误！");
				return ERROR;
			}
			CustPartNoInfoMgr custMgr = (CustPartNoInfoMgr)getBean(CustPartNoInfoNormalAction.custPartNoInfoMgrName);
			CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
			custPartNoInfo.setPartNo(partNo.getPartNo());
			if(custMgr.listCustPartNoInfoCount(custPartNoInfo) > 0){
				String s = "存在客户物料关联，不能删除！";
				setErrorReason(s);
				logger.info(s);
				return ERROR;
			}
			VendorPartNoInfoMgr vendorMgr = (VendorPartNoInfoMgr)getBean(VendorPartNoInfoNormalAction.vendorPartNoInfoMgrName);
			VendorPartNoInfo vendorPartNoInfo = new VendorPartNoInfo();
			vendorPartNoInfo.setPartNo(partNo.getPartNo());
			if(vendorMgr.listVendorPartNoInfoCount(vendorPartNoInfo) > 0){
				String s = "存在供应商物料关联，不能删除！";
				setErrorReason(s);
				logger.info(s);
				return ERROR;
			}
			CompanyPartNoMgr mgr = (CompanyPartNoMgr) getBean(companyPartNoMgrName);
			mgr.deleteComPanyPartNo(partNo.getPartNo());
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doDelete.", e);
			setErrorReason("内部错误");
			return ERROR;	
		}		
	}
}
