/**
 * 
 */
package com.hbs.customerinfo.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.customerinfo.manager.CustPartNoInfoMgr;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * 客户物料关系普通用户Action
 * @author xyf
 * @actions doList doSave
 */
@SuppressWarnings("serial")
public class CustPartNoInfoNormalAction extends BaseAction {

	/**
	 * Manager名
	 */
	public static final String custPartNoInfoMgrName = "custPartNoInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustPartNoInfoNormalAction.class);

    CustPartNoInfo custPartNoInfo;
    public CustPartNoInfo getCustPartNoInfo() { return custPartNoInfo; }
    public void setCustPartNoInfo(CustPartNoInfo custPartNoInfo) { this.custPartNoInfo = custPartNoInfo; }
    
    CustomerInfo custInfo;
    
    /**
     * 客户物料编号
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
     * 查询客户物料关系，判断了用户是否可以查看。
 	 * @action.input custPartNoInfo.commCode + custPartNoInfo.查询条件
	 * @action.result list：列表 count：总数
     * @return
     */
    public String doList()
    {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doList");

			if(custPartNoInfo == null)
				custPartNoInfo = new CustPartNoInfo();
			
			if(!checkCommonFields())
				return ERROR;

			setPagination(custPartNoInfo);
			CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
			setResult("list", mgr.listCustPartNoInfo(custPartNoInfo));
			setTotalCount(mgr.listCustPartNoInfoCount(custPartNoInfo));
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
     * 保存客户物料关系
     * @action.input	custPartNoInfo.*
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

			//custPartNoInfo.setVendorCode(custInfo.getVendorCode());

			List<FieldErr> errs = CustPartNoInfoUtil.checkInputFields(custPartNoInfo);
			if(!errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			if(CustPartNoInfoUtil.checkSetStaffId(custPartNoInfo))
				setMyId(true);
			//custPartNoInfo.setCreateDate(new Date());
			
			CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
			int i = mgr.commitCustPartNoInfo(custPartNoInfo);
			if(i == -1)
			{
				logger.info("已经存在修改待审批数据！");
				setErrorReason("已经存在修改待审批数据！");
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
     * 获取客户物料关系
     * @action.input	custPartNoInfo.seqId
     * @action.result	custPartNoInfo.*
     * @return
     */
   public String doGetInfo() {
    	try {
    		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
    		if(custPartNoInfo == null || custPartNoInfo.getSeqId() == null) {
    			logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
    		}
    		custPartNoInfo = mgr.getCustPartNoInfoByID(custPartNoInfo.getSeqId().toString());
//    		if(!checkCommonFields())
//    			return ERROR;;
    		if(null != custPartNoInfo){
	    		//获取客户信息
	    		CustomerInfoMgr custmgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
	    		CustomerInfo custInfo = new CustomerInfo();
				custInfo.setCommCode(custPartNoInfo.getCommCode());
				custInfo.setState("0");
				custInfo = custmgr.getCustomerInfo(custInfo, false);
				setResult("custInfo", custInfo);
				
    		}else{
    			logger.info("无对应的客户物料信息！");
				setErrorReason("无对应的客户物料信息！");
				return ERROR;
    		}
    		setResult("custPartNoInfo", custPartNoInfo);
    		return SUCCESS;
    	} catch(Exception e) {
    		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}
    }
   
   /**
    * 获取客户物料关系 add by yangzj
    * @action.input	  客户物料编号或本公司物料编号
    * @action.result	custPartNoInfo.*
    * @return
    */
  public String doGetInfoDict() {
   	try {
   		
   		if(cpartNo == null && partNo == null) {
   			logger.info("doGetInfoDict() ，无输入参数无法获取！");
				setErrorReason("无法获取客户物料关系对照！");
				return ERROR;
   		}else{
   			logger.debug("doGetInfoDict(),输入的参数为：partno=" + partNo +"cpartno=" + cpartNo);
   		}
   		CustPartNoInfo cPartNoInfo = new CustPartNoInfo();
   		cPartNoInfo.setCustPartNo(cpartNo);
   		cPartNoInfo.setPartNo(partNo);
   		cPartNoInfo.setState("0");
   		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
   		custPartNoInfo = mgr.getCustPartNoInfoByBizKey(cPartNoInfo);   		
   		setResult("custPartNoInfo", custPartNoInfo);
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
	   			logger.info("doGetInfoDict() ，无输入参数无法获取！");
					setErrorReason("无法获取客户物料关系对照！");
					return ERROR;
	   		}else{
	   			logger.debug("doGetInfoDict(),输入的参数为：partno=" + partNo +"cpartno=" + cpartNo);
	   		}
	   		CustPartNoInfo cPartNoInfo = new CustPartNoInfo();
	   		cPartNoInfo.setCustPartNo(cpartNo);
	   		cPartNoInfo.setPartNo(partNo);
	   		cPartNoInfo.setState("0");
	   		
	   		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
			setResult("list", mgr.listCustPartNoInfo(cPartNoInfo));
			//setTotalCount(mgr.listCustPartNoInfoCount(cPartNoInfo));
			//setResult("count", getTotalCount());
	   		return SUCCESS;
	   	} catch(Exception e) {
	   		logger.error("catch Exception in doGetInfo.", e);
				setErrorReason("内部错误");
				return ERROR;
	   	}
	   }
   /**
    * 获取客户物料关系
    * @action.input	custPartNoInfo.commCode + custPartNoInfo.custPartNo + custPartNoInfo.state，如果state没有填写，则默认state=0
    * @action.result	custPartNoInfo.*
    * @return
    */
  public String doGetInfoByBizKey() {
   	try {
   		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
   		if(custPartNoInfo == null || custPartNoInfo.getSeqId() == null || StringUtils.isEmpty(custPartNoInfo.getCustPartNo())) {
   			logger.info("参数错误！");
				setErrorReason("参数错误！");
				return ERROR;
   		}
   		if(StringUtils.isEmpty(custPartNoInfo.getState()))
   			custPartNoInfo.setState("0");
   		
   		custPartNoInfo = mgr.getCustPartNoInfoByBizKey(custPartNoInfo);
   		
   		if(!checkCommonFields())
   			return ERROR;;
   				
   		setResult("custPartNoInfo", custPartNoInfo);
   		return SUCCESS;
   	} catch(Exception e) {
   		logger.error("catch Exception in doGetInfo.", e);
			setErrorReason("内部错误");
			return ERROR;
   	}
   }
  

  	/**
  	 * 获取物料的历史价格变动，变通做法，获取操作历史记录
  	 * @action.input custPartNoInfo.* partNo custPartNo commCode
  	 * @action.result list	List<OperLog>
  	 * @return
  	 */
  	public String doGetPriceChange(){
  		try {
	   		CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)getBean(custPartNoInfoMgrName);
	   		if(custPartNoInfo == null || custPartNoInfo.getPartNo() == null || custPartNoInfo.getCommCode() == null || StringUtils.isEmpty(custPartNoInfo.getCustPartNo())) {
	   			logger.info("参数错误！");
					setErrorReason("参数错误！");
					return ERROR;
	   		}
	   		if(StringUtils.isEmpty(custPartNoInfo.getState()))
	   			custPartNoInfo.setState("0");
	   		
	   		setResult("list", mgr.getPartNoChange(custPartNoInfo));
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
		custPartNoInfo.setStaffId(getLoginStaff().getStaffId().toString());
		custPartNoInfo.setStaffName(setName ? getLoginStaff().getStaffName() : null);
	}
	
	/**
	 * 检查客户编码是否填写，并判断是否有权限操作。如果出现问题，本函数内设置了ErrorReaseon。
	 * @return
	 */
	protected boolean checkCommonFields()
	{
		try{
			if(custPartNoInfo == null)
			{
				logger.info("参数错误！");
				setErrorReason("参数错误！");
				return false;
			}
			
			String commCode = custPartNoInfo.getCommCode();
			if(StringUtils.isEmpty(commCode))
			{
				logger.info("客户编码没有填写！");
				setErrorReason("客户编码没有填写！");
				return false;
			}
			
			//DONE：限制范围
			CustomerInfoMgr custmgr = (CustomerInfoMgr)getBean(CustomerInfoNormalAction.custInfoMgrName);
			custInfo = new CustomerInfo();
			custInfo.setCommCode(commCode);
			custInfo.setState("0");
			custInfo = custmgr.getCustomerInfo(custInfo, false);
			if(custInfo == null) {
				logger.info("客户编码错误！");
				setErrorReason("客户编码错误！");
				return false;
			}
			String id = getLoginStaff().getStaffId().toString();
			if(custInfo == null || (!id.equals(custInfo.getStaffId()) && !id.equals(custInfo.getAssStaffId())))
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
			if(custPartNoInfo == null)
				return;
			if(StringUtils.isEmpty(custPartNoInfo.getCommCode()))
			{
				String s = this.getHttpServletRequest().getParameter("custInfo.commCode");
				if(StringUtils.isNotEmpty(s))
				{
					custPartNoInfo.setCommCode(s);
				}
			}
		} catch (Exception e) {
			logger.error("catch Exception in fixCommCode", e);
		}
	}
}
