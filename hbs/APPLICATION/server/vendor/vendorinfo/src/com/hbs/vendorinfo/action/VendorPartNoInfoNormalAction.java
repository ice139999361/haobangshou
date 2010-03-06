/**
 * 
 */
package com.hbs.vendorinfo.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.vendorinfo.manager.VendorInfoMgr;
import com.hbs.vendorinfo.manager.VendorPartNoInfoMgr;

/**
 * 供应商物料关系普通用户Action
 * @author xyf
 * @actions doList doSave
 */
@SuppressWarnings("serial")
public class VendorPartNoInfoNormalAction extends BaseAction {

	/**
	 * Manager名
	 */
	static final String vendorPartNoInfoMgrName = "vendorPartNoInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorPartNoInfoNormalAction.class);

    VendorPartNoInfo vendorPartNoInfo;
    public VendorPartNoInfo getVendorPartNoInfo() { return vendorPartNoInfo; }
    public void setVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) { this.vendorPartNoInfo = vendorPartNoInfo; }
    
    /**
     * 查询供应商物料关系，判断了用户是否可以查看。
 	 * @action.input vendorPartNoInfo.commCode + vendorPartNoInfo.查询条件
	 * @action.result list：列表 count：总数
     * @return
     */
    public String doList()
    {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doList");

			if(vendorPartNoInfo == null)
				vendorPartNoInfo = new VendorPartNoInfo();
			
			if(!checkCommonFields())
				return ERROR;
			
			setPagination(vendorPartNoInfo);
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
			setResult("list", mgr.listVendorPartNoInfo(vendorPartNoInfo));
			setTotalCount(mgr.listVendorPartNoInfoCount(vendorPartNoInfo));
			setResult("count", getTotalCount());
			if (logger.isDebugEnabled())    logger.debug("end doList");
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
     * 保存供应商物料关系
     * @action.input	vendorPartNoInfo.*
     * @return
     */
    public String doSave()
    {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doSave");

			fixCommCode();

			if(!checkCommonFields())
				return ERROR;

			List<FieldErr> errs = VendorPartNoInfoUtil.checkInputFields(vendorPartNoInfo);
			if(!errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			if(VendorPartNoInfoUtil.checkSetStaffId(vendorPartNoInfo))
				setMyId(true);
			
			VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
			int i = mgr.commitVendorPartNoInfo(vendorPartNoInfo);
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
     * 获取客户物料关系
     * @action.input	vendorPartNoInfo.seqId
     * @action.result	vendorPartNoInfo.*
     * @return
     */
   public String doGetInfo() {
    	try {
    		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
    		if(vendorPartNoInfo == null || vendorPartNoInfo.getSeqId() == null) {
    			logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
    		}
    		vendorPartNoInfo = mgr.getVendorPartNoInfoByID(vendorPartNoInfo.getSeqId().toString());
    		if(!checkCommonFields())
    			return ERROR;;
    				
    		setResult("vendorPartNoInfo", vendorPartNoInfo);
    		return SUCCESS;
    	} catch(Exception e) {
    		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}
    }
    
	/**
	 * 设置STAFF信息为当前用户信息
	 * @param setName 是否设置用户名。为true时设置staffName为当前用户的staffName；为false时设置staffName为null。
	 * 在查询时，为false；在增、改时，为true。
	 * @throws Exception
	 */
	private void setMyId(boolean setName) throws Exception {
		vendorPartNoInfo.setStaffId(getLoginStaff().getStaffId().toString());
		vendorPartNoInfo.setStaffName(setName ? getLoginStaff().getStaffName() : null);
	}
	
	/**
	 * 检查供应商编码是否填写，并判断是否有权限操作。如果出现问题，本函数内设置了ErrorReaseon。
	 * @return
	 */
	protected boolean checkCommonFields()
	{
		try{
			if(vendorPartNoInfo == null)
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return false;
			}
			
			String commCode = vendorPartNoInfo.getCommCode();
			if(StringUtils.isEmpty(commCode))
			{
				logger.info("供应商编码没有填写！");
				setErrorReason("供应商编码没有填写！");
				return false;
			}
			
			//DONE：限制范围
			VendorInfoMgr vendormgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			VendorInfo vendorInfo = new VendorInfo();
			vendorInfo.setCommCode(commCode);
			vendorInfo.setState("0");
			vendorInfo = vendormgr.getVendorInfo(vendorInfo, false);
			String id = getLoginStaff().getStaffId().toString();
			if(vendorInfo == null || !id.equals(vendorInfo.getStaffId()))
			{
				logger.info("您没有权限访问！");
				setErrorReason("您没有权限访问！");
				return false;
			}
			return true;
		}catch(Exception e){
			logger.error("catch Exception in checkCommonFields", e);
			setErrorReason("内部错误");
			return false;
		}
	}
	
	protected void fixCommCode()
	{
		try {
			if(vendorPartNoInfo == null)
				return;
			if(StringUtils.isEmpty(vendorPartNoInfo.getCommCode()))
			{
				String s = this.getHttpServletRequest().getParameter("vendorInfo.commCode");
				if(StringUtils.isNotEmpty(s))
				{
					vendorPartNoInfo.setCommCode(s);
				}
			}
		} catch (Exception e) {
			logger.error("catch Exception in fixCommCode", e);
		}
	}
}
