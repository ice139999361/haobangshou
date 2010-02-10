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
			if(partNo == null || partNo.getPartNo() == null || partNo.getPartNo().length() == 0)
			{
				setErrorReason("参数错误！");
				logger.info("参数错误！");
				return ERROR;
			}
			CompanyPartNoMgr mgr = (CompanyPartNoMgr) BeanLocator.getInstance().getBean(companyPartNoMgrName);
			partNo = mgr.getCompanyPartNo(partNo.getPartNo());
			setResult("partNo", partNo);
			return SUCCESS;
		}catch(Exception e){
			logger.error("catch Exception in doGet.", e);
			setErrorReason("内部错误");
			return ERROR;	
		}
	}
}
