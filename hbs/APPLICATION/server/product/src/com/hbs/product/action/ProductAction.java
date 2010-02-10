/**
 * 
 */
package com.hbs.product.action;

import java.util.List;

import org.apache.log4j.Logger;

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
			if(partNo == null || partNo.getPartNo() == null || partNo.getPartNo().length() == 0)
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
}
