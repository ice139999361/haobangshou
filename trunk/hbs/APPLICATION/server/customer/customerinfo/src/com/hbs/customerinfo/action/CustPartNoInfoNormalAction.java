/**
 * 
 */
package com.hbs.customerinfo.action;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;

import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.customerinfo.manager.CustPartNoInfoMgr;

/**
 * 客户物料关系普通用户Action
 * @author xyf
 * @actions doList
 */
@SuppressWarnings("serial")
public class CustPartNoInfoNormalAction extends BaseAction {

	/**
	 * Manager名
	 */
	static final String custPartNoInfoMgrName = "custPartNoInfoMgr";
		
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustPartNoInfoNormalAction.class);

    CustPartNoInfo custPNInfo;	
    public CustPartNoInfo getCustPNInfo() { return custPNInfo; }
    public void setCustPNInfo(CustPartNoInfo custPNInfo) { this.custPNInfo = custPNInfo; }
    
    /**
     * 查询客户物料关系
 	 * @action.input custPNInfo.查询条件
	 * @action.result list：列表 count：总数
     * @return
     */
    public String doList()
    {
    	try
    	{
			logger.debug("begin doList");
			if(custPNInfo == null)
			{
				custPNInfo = new CustPartNoInfo();
			}
			setPagination(custPNInfo);
			//TODO：限制范围
			//setMyId(false);
			CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean(custPartNoInfoMgrName);
			setResult("list", mgr.listCustPartNoInfo(custPNInfo));
			setTotalCount(mgr.listCustPartNoInfoCount(custPNInfo));
			setResult("count", getTotalCount());
			logger.debug("end doList");
    		return SUCCESS;
    	}
    	catch(Exception e)
    	{
    		logger.error("catch Exception in doList.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}
    }
    
    public String doSave()
    {
    	try
    	{
			logger.debug("begin doSave");
			
			logger.debug("end doSave");
    		return SUCCESS;
    	}   
    	catch(Exception e)
    	{
    		logger.error("catch Exception in doSave.", e);
			setErrorReason("内部错误");
			return ERROR;
    	}
    }
}
