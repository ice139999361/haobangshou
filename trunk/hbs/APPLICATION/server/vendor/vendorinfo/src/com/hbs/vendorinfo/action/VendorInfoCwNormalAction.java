/**
 * 
 */
package com.hbs.vendorinfo.action;

import org.apache.log4j.Logger;

import com.hbs.common.action.JianQuanUtil;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.vendorinfo.manager.VendorInfoMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class VendorInfoCwNormalAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(VendorInfoCwNormalAction.class);
	
	public static final String roleName = "cwnormal";
	
	VendorInfo vendorInfo;
	
	public VendorInfo getVendorInfo() {
		return vendorInfo;
	}
	
	public void setVendorInfo(VendorInfo a) {
		this.vendorInfo = a;
	}
	
	/**
	 * 查询
	 * @action.input vendorInfo.查询条件
	 * @action.result list：列表 count：总数
	 * @return
	 */
	public String doList() {
		try {
			if (logger.isDebugEnabled())
				logger.debug("begin doList");
			if (vendorInfo == null) {
				vendorInfo = new VendorInfo();
			}
			setPagination(vendorInfo);
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			vendorInfo.setState("0");
			setResult("list", mgr.getVendorInfoList(vendorInfo));
			setTotalCount(mgr.getCustomerInfoCount(vendorInfo));
			setResult("count", getTotalCount());
			setResult("jq", JianQuanUtil.getJQ(JianQuanUtil.TypeCustState, roleName));
			if (logger.isDebugEnabled())
				logger.debug("end doList");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("catch Exception in doList.", e);
			setErrorReason("内部错误");
			return ERROR;
		}
	}

	/**
	 * 获取供应商详细信息
	 * @action.input 
	 *	vendorInfo.baseSeqId 或 vendorInfo.commCode
	 * @action.result vendorInfo.*
	 * @return
	 */
	public String doGetInfo()
	{
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doGetInfo");
			if(!VendorInfoUtil.checkKeyFields2(vendorInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			vendorInfo.setState("0");
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			getVendorInfoValue(mgr);
			if(!"0".equals(vendorInfo.getState())){
				logger.info("状态错误！");
				setErrorReason("状态错误！");
				return ERROR;
			}
			this.setResult("vendorInfo", vendorInfo);
			if (logger.isDebugEnabled())    logger.debug("end doGetInfo");
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doGetInfo", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}

	/**
	 * 财务锁定
	 * @action.input 
	 *	vendorInfo.baseSeqId 或 vendorInfo.commCode
	 * @action.input memo 说明
	 * @return
	 */
	public String doLock(){
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doLock");
			if(!VendorInfoUtil.checkKeyFields2(vendorInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			vendorInfo.setState("0");
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			int i = mgr.lockVendorInfo(vendorInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				logger.error("提交错误！ ret = " + i);
				setErrorReason("提交错误！");
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doLock", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}

	/**
	 * 财务解锁
	 * @action.input 
	 *	vendorInfo.baseSeqId 或 vendorInfo.commCode
	 * @action.input memo 说明
	 * @return
	 */
	public String doUnlock(){
		try
		{
			if (logger.isDebugEnabled())    logger.debug("begin doUnlock");
			if(!VendorInfoUtil.checkKeyFields2(vendorInfo))
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
			}
			vendorInfo.setState("0");
			VendorInfoMgr mgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			int i = mgr.unlockVendorInfo(vendorInfo, getLoginStaff().getStaffId().toString(), getLoginStaff().getStaffName(), getMemo());
			if(i != 0) {
				logger.error("提交错误！ ret = " + i);
				setErrorReason("提交错误！");
				return ERROR;
			}
			return SUCCESS;
		}
		catch(Exception e)
		{
			logger.error("catch Exception in doUnlock", e);
			setErrorReason("内部错误");
            return ERROR;
		}
	}

	protected String getMemo() {
		return this.getHttpServletRequest().getParameter("memo");
	}
	
	/**
	 * 根据vendorInfo的部分信息获取全部信息
	 * @param mgr
	 * @throws Exception
	 */
	protected void getVendorInfoValue(VendorInfoMgr mgr) throws Exception
	{
		vendorInfo = VendorInfoUtil.getVendorInfo(mgr, vendorInfo);
	}
}
