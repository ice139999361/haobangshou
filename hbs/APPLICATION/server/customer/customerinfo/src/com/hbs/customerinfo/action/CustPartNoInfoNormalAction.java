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
 * �ͻ����Ϲ�ϵ��ͨ�û�Action
 * @author xyf
 * @actions doList
 */
@SuppressWarnings("serial")
public class CustPartNoInfoNormalAction extends BaseAction {

	/**
	 * Manager��
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
     * ��ѯ�ͻ����Ϲ�ϵ
 	 * @action.input custPNInfo.��ѯ����
	 * @action.result list���б� count������
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
			//TODO�����Ʒ�Χ
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
			setErrorReason("�ڲ�����");
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
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
}
