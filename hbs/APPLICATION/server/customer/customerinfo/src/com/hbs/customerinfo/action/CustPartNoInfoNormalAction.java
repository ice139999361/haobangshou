/**
 * 
 */
package com.hbs.customerinfo.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.manager.CustPartNoInfoMgr;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * �ͻ����Ϲ�ϵ��ͨ�û�Action
 * @author xyf
 * @actions doList doSave
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

    CustPartNoInfo custPartNoInfo;
    public CustPartNoInfo getCustPartNoInfo() { return custPartNoInfo; }
    public void setCustPartNoInfo(CustPartNoInfo custPartNoInfo) { this.custPartNoInfo = custPartNoInfo; }
    
    /**
     * ��ѯ�ͻ����Ϲ�ϵ���ж����û��Ƿ���Բ鿴��
 	 * @action.input custPartNoInfo.commCode + custPartNoInfo.��ѯ����
	 * @action.result list���б� count������
     * @return
     */
    public String doList()
    {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doList");

			if(!checkCommonFields())
				return ERROR;
			
			setPagination(custPartNoInfo);
			CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean(custPartNoInfoMgrName);
			setResult("list", mgr.listCustPartNoInfo(custPartNoInfo));
			setTotalCount(mgr.listCustPartNoInfoCount(custPartNoInfo));
			setResult("count", getTotalCount());
			if (logger.isDebugEnabled())    logger.debug("end doList");
    		return SUCCESS;
    	}
    	catch(Exception e)
    	{
    		logger.error("catch Exception in doList.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
    
    /**
     * ����ͻ����Ϲ�ϵ
     * @action.input	custPartNoInfo.*
     * @return
     */
    public String doSave()
    {
    	try
    	{
			if (logger.isDebugEnabled())    logger.debug("begin doSave");

			if(!checkCommonFields())
				return ERROR;

			List<FieldErr> errs = CustPartNoInfoUtil.checkInputFields(custPartNoInfo);
			if(errs.isEmpty())
			{
				String s = FieldErr.formFieldsErrString(errs);
				logger.info(s);
				setErrorReason(s);
				return ERROR;
			}
			if(CustPartNoInfoUtil.checkSetStaffId(custPartNoInfo))
				setMyId(true);
			
			CustPartNoInfoMgr mgr = (CustPartNoInfoMgr)BeanLocator.getInstance().getBean(custPartNoInfoMgrName);
			int i = mgr.commitCustPartNoInfo(custPartNoInfo);
			if(i != 0)
			{
				logger.info("�������");
				setErrorReason("�������");
				return ERROR;
			}
			if (logger.isDebugEnabled())    logger.debug("end doSave");
    		return SUCCESS;
    	}
    	catch(Exception e)
    	{
    		logger.error("catch Exception in doSave.", e);
			setErrorReason("�ڲ�����");
			return ERROR;
    	}
    }
    
	/**
	 * ����STAFF��ϢΪ��ǰ�û���Ϣ
	 * @param setName �Ƿ������û�����Ϊtrueʱ����staffNameΪ��ǰ�û���staffName��Ϊfalseʱ����staffNameΪnull��
	 * �ڲ�ѯʱ��Ϊfalse����������ʱ��Ϊtrue��
	 * @throws Exception
	 */
	private void setMyId(boolean setName) throws Exception {
		custPartNoInfo.setStaffId(getLoginStaff().getStaffId());
		custPartNoInfo.setStaffName(setName ? getLoginStaff().getStaffName() : null);
	}
	
	/**
	 * ���ͻ������Ƿ���д�����ж��Ƿ���Ȩ�޲���������������⣬��������������ErrorReaseon��
	 * @return
	 */
	protected boolean checkCommonFields()
	{
		try{
			if(custPartNoInfo == null)
			{
				logger.info("��������");
				setErrorReason("��������");
				return false;
			}
			
			String commCode = custPartNoInfo.getCommCode();
			if(commCode == null || commCode.length() == 0)
			{
				logger.info("�ͻ�����û����д��");
				setErrorReason("�ͻ�����û����д��");
				return false;
			}
			
			//DONE�����Ʒ�Χ
			CustomerInfoMgr custmgr = (CustomerInfoMgr)BeanLocator.getInstance().getBean(CustomerInfoNormalAction.custInfoMgrName);
			CustomerInfo custInfo = new CustomerInfo();
			custInfo = custmgr.getCustomerInfo(custInfo, false);
			String id = getLoginStaff().getStaffId();
			if(custInfo == null || (custInfo.getStaffId() != id && custInfo.getAssStaffId() != id))
			{
				logger.info("��û��Ȩ�޷��ʣ�");
				setErrorReason("��û��Ȩ�޷��ʣ�");
				return false;
			}
			return true;
		}catch(Exception e){
			logger.error("catch Exception in checkCommonFields", e);
			setErrorReason("�ڲ�����");
			return false;
		}
	}
}
