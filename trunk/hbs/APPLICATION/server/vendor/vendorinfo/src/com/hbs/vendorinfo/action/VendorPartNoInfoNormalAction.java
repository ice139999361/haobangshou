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
	public static final String vendorPartNoInfoMgrName = "vendorPartNoInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorPartNoInfoNormalAction.class);

    VendorPartNoInfo vendorPartNoInfo;
    public VendorPartNoInfo getVendorPartNoInfo() { return vendorPartNoInfo; }
    public void setVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) { this.vendorPartNoInfo = vendorPartNoInfo; }
    
    //VendorInfo vendorInfo;
    
    /**
     * 供应商物料编号
     */
    private String cpartNo;
    /**
     * 本公司物料编号
     */
    private String partNo;
    
    public String getCpartNo() {
		return cpartNo;
	}
	public void setCpartNo(String cpartNo) {
		this.cpartNo = cpartNo;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

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
			
			if(!checkListFields())
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
			if(i == -1)
			{
				logger.info("您提交物料信息正在等待审批！请审批结束后再修改！");
				setErrorReason("您提交物料信息正在等待审批！请审批结束后再修改！");
				return ERROR;
			}else if( i == 0 ){
				this.setAlertMsg("提交成功！");
				if (logger.isDebugEnabled())    logger.debug("end doSave");
	    		return SUCCESS;
			}else{
				logger.info("保存失败！");
				setErrorReason("保存失败！");
				return ERROR;
			}
    	}
    	catch(Exception e)
    	{
    		logger.error("catch Exception in doSave.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}
    }
    
    /**
     * 获取供应商物料关系
     * @action.input	vendorPartNoInfo.seqId
     * @action.result	vendorPartNoInfo.*
     * @return
     */
   public String doGetInfo() {
    	try {
    		if (logger.isDebugEnabled())    logger.debug("begin doGetInfo()");
    		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
    		if(vendorPartNoInfo == null || vendorPartNoInfo.getSeqId() == null) {
    			logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
    		}else{
    			logger.debug("doGetInfo(),输入的参数为：" + vendorPartNoInfo.toString());
    		}
    		vendorPartNoInfo = mgr.getVendorPartNoInfoByID(vendorPartNoInfo.getSeqId().toString());
    		//这里不需要检查了吧
    		//getVendorPartNoInfoByID返回null时也可以返回失败。
//    		if(!checkCommonFields())
//    			return ERROR;;
    		if(null != vendorPartNoInfo){
    			VendorInfoMgr vendormgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
    			VendorInfo vendorInfo = new VendorInfo();
    			vendorInfo.setCommCode(vendorPartNoInfo.getCommCode());
    			vendorInfo.setState("0");
    			vendorInfo = vendormgr.getVendorInfo(vendorInfo, false);
				setResult("vendorInfo", vendorInfo);
				
    		}else{
    			logger.info("无对应的供应商物料信息！");
				setErrorReason("无对应的供应商物料信息！");
				return ERROR;
    		}		
    		setResult("vendorPartNoInfo", vendorPartNoInfo);
    		return SUCCESS;
    	} catch(Exception e) {
    		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}
    }
 
   /**
    * 获取供应商物料关系 add by xyf
    * @action.input	  供应商物料编号或本公司物料编号
    * @action.result	vendorPartNoInfo.*
    * @return
    */
  public String doGetInfoDict() {
   	try {
   		
   		if(cpartNo == null && partNo == null) {
   			logger.info("doGetInfoDict() ，无输入参数无法获取！");
				setErrorReason("无法获取供应商物料关系对照！");
				return ERROR;
   		}else{
   			logger.debug("doGetInfoDict(),输入的参数为：partno=" + partNo +" cpartno=" + cpartNo);
   		}
   		VendorPartNoInfo cPartNoInfo = new VendorPartNoInfo();
   		cPartNoInfo.setCustPartNo(cpartNo);
   		cPartNoInfo.setPartNo(partNo);
   		cPartNoInfo.setState("0");
   		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
   		vendorPartNoInfo = mgr.getVendorPartNoInfoByBizKey(cPartNoInfo);   		
   		setResult("vendorPartNoInfo", vendorPartNoInfo);
   		return SUCCESS;
   	} catch(Exception e) {
   		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("内部错误");
			return ERROR;
   	}
   }
    
  public String doListDict() {
	   	try {
	   		
	   		if(cpartNo == null && partNo == null) {
	   			logger.info("doListDict() ，无输入参数无法获取！");
					setErrorReason("无法获取供应商物料关系对照！");
					return ERROR;
	   		}else{
	   			logger.debug("doListDict(),输入的参数为：partno=" + partNo +"cpartno=" + cpartNo);
	   		}
	   		VendorPartNoInfo cPartNoInfo = new VendorPartNoInfo();
	   		cPartNoInfo.setCustPartNo(cpartNo);
	   		cPartNoInfo.setPartNo(partNo);
	   		cPartNoInfo.setState("0");
	   		
	   		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
			setResult("list", mgr.listVendorPartNoInfo(cPartNoInfo));
			//setTotalCount(mgr.listCustPartNoInfoCount(cPartNoInfo));
			//setResult("count", getTotalCount());
	   		return SUCCESS;
	   	} catch(Exception e) {
	   		logger.error("catch Exception in doListDict.", e);
				setErrorReason("内部错误");
				return ERROR;
	   	}
	   }
   /**
    * 获取供应商物料关系
    * @action.input	vendorPartNoInfo.commCode + vendorPartNoInfo.custPartNo + vendorPartNoInfo.state，如果state没有填写，则默认state=0
    * @action.result	vendorPartNoInfo.*
    * @return
    */
  public String doGetInfoByBizKey() {
   	try {
   		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
   		if(vendorPartNoInfo == null || vendorPartNoInfo.getSeqId() == null || StringUtils.isEmpty(vendorPartNoInfo.getCustPartNo())) {
   			logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
   		}
   		if(StringUtils.isEmpty(vendorPartNoInfo.getState()))
   			vendorPartNoInfo.setState("0");
   		
   		vendorPartNoInfo = mgr.getVendorPartNoInfoByBizKey(vendorPartNoInfo);
   		
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
 	 * 获取物料的历史价格变动，变通做法，获取操作历史记录
 	 * @action.input vendorPartNoInfo.* partNo custPartNo commCode
 	 * @action.result list	List<OperLog>
 	 * @return
 	 */
 	public String doGetPriceChange(){
 		try {
	   		VendorPartNoInfoMgr mgr = (VendorPartNoInfoMgr)getBean(vendorPartNoInfoMgrName);
	   		if(vendorPartNoInfo == null || vendorPartNoInfo.getPartNo() == null || vendorPartNoInfo.getCommCode() == null || StringUtils.isEmpty(vendorPartNoInfo.getCustPartNo())) {
	   			logger.info("参数错误！");
					setErrorReason("参数错误！");
					return ERROR;
	   		}
	   		if(StringUtils.isEmpty(vendorPartNoInfo.getState()))
	   			vendorPartNoInfo.setState("0");
	   		
	   		setResult("list", mgr.getPartNoChange(vendorPartNoInfo));
	   		return SUCCESS;
 		} catch(Exception e) {
 			logger.error("catch Exception in doGetPriceChange.", e);
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
				logger.info("参数错误！请输入完整的供应商物料信息！");
				setErrorReason("参数错误！请输入完整的供应商物料信息！");
				return false;
			}
			
			String commCode = vendorPartNoInfo.getCommCode();
			String shortName = vendorPartNoInfo.getShortName();
			if(StringUtils.isEmpty(commCode) && StringUtils.isEmpty(shortName))
			{
				logger.info("供应商编码或简称没有填写！");
				setErrorReason("供应商编码或简称没有填写！");
				return false;
			}
			
			//DONE：限制范围
			VendorInfoMgr vendormgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
			VendorInfo vendorInfo = new VendorInfo();
			vendorInfo.setCommCode(commCode);
			vendorInfo.setShortName(shortName);
			vendorInfo.setState("0");
			vendorInfo = vendormgr.getVendorInfo(vendorInfo, false);
			if(vendorInfo == null) {
				logger.info("供应商编码错误！");
				setErrorReason("无对应的供应商信息！");
				return false;
			}
			String id = getLoginStaff().getStaffId().toString();
			
			if( !id.equals(vendorInfo.getStaffId()))
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
	protected boolean checkListFields()
	{
		try{
			if(vendorPartNoInfo == null)
			{
				vendorPartNoInfo = new VendorPartNoInfo();
				String id = getLoginStaff().getStaffId().toString();
				vendorPartNoInfo.setField("operId", id);
				return true;
			}else{
				String commCode = vendorPartNoInfo.getCommCode();
				String shortName = vendorPartNoInfo.getShortName();
				if(StringUtils.isEmpty(commCode) && StringUtils.isEmpty(shortName))
				{
					String id = getLoginStaff().getStaffId().toString();
					vendorPartNoInfo.setField("operId", id);
					return true;
				}else{
					VendorInfoMgr vendormgr = (VendorInfoMgr)getBean(VendorInfoNormalAction.vendorInfoMgrName);
					VendorInfo vendorInfo = new VendorInfo();
					vendorInfo.setCommCode(commCode);
					vendorInfo.setShortName(shortName);
					vendorInfo.setState("0");
					vendorInfo = vendormgr.getVendorInfo(vendorInfo, false);
					if(vendorInfo == null) {
						logger.info("您填写的供应商编码不存在，请先录入供应商信息！");
						setErrorReason("您填写的供应商编码不存在，请先录入供应商信息！");
						return false;
					}
					String id = getLoginStaff().getStaffId().toString();
					
					if( !id.equals(vendorInfo.getStaffId()))
					{
						logger.info("您查询的供应商不属于您，您没有权限访问！");
						setErrorReason("您查询的供应商不属于您，您没有权限访问！");
						return false;
					}
				}
			}
			
			return true;
		}catch(Exception e){
			logger.error("catch Exception in checkListFields", e);
			setErrorReason("内部错误");
			return false;
		}
	}
	protected void fixCommCode()
	{
//		try {
//			if(vendorPartNoInfo == null)
//				return;
//			if(StringUtils.isEmpty(vendorPartNoInfo.getCommCode()))
//			{
//				String s = this.getHttpServletRequest().getParameter("vendorInfo.commCode");
//				if(StringUtils.isNotEmpty(s))
//				{
//					vendorPartNoInfo.setCommCode(s);
//				}
//			}
//		} catch (Exception e) {
//			logger.error("catch Exception in fixCommCode", e);
//		}
	}
}
