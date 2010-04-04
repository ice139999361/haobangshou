/**
 * 
 */
package com.hbs.product.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.action.base.BaseAction;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.product.pojo.CompanyPartNo;
import com.hbs.product.manager.CompanyPartNoMgr;

/**
 * ����˾����Action
 * @author xyf
 * @actions doList doGet
 */
@SuppressWarnings("serial")
public class ProductAction extends BaseAction {
	/**
	 * Manager��
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
	 * ��ѯ
	 * @action.input partNo.��ѯ����
	 * @action.result list���б� count������
	 * @return
	 */
	public String doList()
	{
		try
		{
			if(partNo == null)
				partNo = new CompanyPartNo();
			setPagination(partNo);
			CompanyPartNoMgr mgr = (CompanyPartNoMgr) BeanLocator.getInstance().getBean(companyPartNoMgrName);
			List<CompanyPartNo> list = mgr.getCompanyPartNoList(partNo);
			setResult("list", list);
			setTotalCount(mgr.getCompanyPartNoListCount(partNo));
			setResult("count", getTotalCount());
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
	 * ��ȡ������ϸ��Ϣ
	 * @action.input partNo.partNo
	 * @action.result partNo.*
	 * @return
	 */
	public String doGet()
	{
		try{
			if(partNo == null || StringUtils.isEmpty(partNo.getPartNo()))
			{
				setErrorReason("��������");
				logger.info("��������");
				return ERROR;
			}
			CompanyPartNoMgr mgr = (CompanyPartNoMgr) BeanLocator.getInstance().getBean(companyPartNoMgrName);
			partNo = mgr.getCompanyPartNo(partNo.getPartNo());
			setResult("partNo", partNo);
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doGet.", e);
			setErrorReason("�ڲ�����");
			return ERROR;	
		}
	}
	
    /**
     * ����������Ϣ
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

			CompanyPartNoMgr mgr = (CompanyPartNoMgr)BeanLocator.getInstance().getBean(companyPartNoMgrName);
			CompanyPartNo partNo2 = mgr.getCompanyPartNo(partNo.getPartNo());
			int i;
			if(partNo2 == null)
				i = mgr.saveCompanyPartNo(partNo);
			else
				i = mgr.updateCompanyPartNo(partNo);
			if(i != 0)
			{
				logger.info("�������");
				setErrorReason("�������");
				return ERROR;
			}
			this.setAlertMsg("�ύ�ɹ���");
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
	 * ������
	 * @action.input value
	 * @return
	 */
	public String doCheckPartNo() {
		try{
			String partNoStr = this.getHttpServletRequest().getParameter("value");
			if(StringUtils.isEmpty(partNoStr))
			{
				setErrorReason("��������");
				logger.info("��������");
				return ERROR;
			}
			CompanyPartNoMgr mgr = (CompanyPartNoMgr) getBean(companyPartNoMgrName);
			partNo = mgr.getCompanyPartNo(partNoStr);
			if(partNo == null)
				return SUCCESS;
			else{
				logger.debug("�����ظ���");
				setErrorReason("�����ظ���");
				return ERROR;
			}
		}catch(Exception e){
			logger.error("catch Exception in doCheckCommCode.", e);
			setErrorReason("�ڲ�����");
			return ERROR;	
		}		
	}
}
