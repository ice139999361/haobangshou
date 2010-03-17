/**
 * system ：hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.invoice.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;


import com.hbs.domain.invoice.dao.PeriodSpecMemoDao;
import com.hbs.domain.invoice.pojo.PeriodSpecMemo;
import com.hbs.invoice.constants.InvoiceConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;

public class PeriodSpecMemoMgr {
	private static final Logger logger = Logger.getLogger(PeriodSpecMemoMgr.class);
	
	/**
	 * 保存财务账期结算备注信息
	 * @param specMemo
	 * @return =0 保存成功
	 * @throws Exception
	 */
	public int saveSepcMemo(PeriodSpecMemo specMemo) throws Exception{
		int ret =0;
		logger.debug("保存财务账期结算备注信息！输入的参数为：" + specMemo.toString());
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		dao.insertPeriodSpecMemo(specMemo);
		//操作日志
		WareHouseLogUtils.operLog(specMemo.getStaffId(), specMemo.getStaffName(), "新增", "结算备注", specMemo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * 修改财务账期结算备注信息
	 * @param specMemo
	 * @return 0  成功
	 * @throws Exception
	 */
	public int updateSepcMemo(PeriodSpecMemo specMemo) throws Exception{
		int ret =0;
		logger.debug("修改出货财务发票记录信息！输入的参数为：" + specMemo.toString());
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		dao.updatePeriodSpecMemo(specMemo);
		//操作日志
		WareHouseLogUtils.operLog(specMemo.getStaffId(), specMemo.getStaffName(), "修改", "结算备注", specMemo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * 删除财务账期结算备注信息，先查找，不存在，抛出异常
	 * @param commCode
	 * @return
	 * @throws Exception
	 */
	public int deleteSepcMemo(String commCode) throws Exception{
		int ret =0;
		logger.debug("删除财务账期结算备注信息！输入的参数为：" + commCode);
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		PeriodSpecMemo specMemo = dao.findPeriodSpecMemo(commCode);
		if(specMemo != null){
			dao.deletePeriodSpecMemo(commCode);
			//操作日志
			WareHouseLogUtils.operLog(specMemo.getStaffId(), specMemo.getStaffName(), "删除", "结算备注", specMemo.getLogKey(), specMemo.toString(), null);
		}else{
			throw new Exception("需要删除的财务账期结算备注信息不存在！输入的参数为：" + commCode);
		}
		return ret;
	}
	
	/**
	 * 根据commCode查找单条财务账期结算备注信息
	 * @param commCode
	 * @return
	 * @throws Exception
	 */
	public PeriodSpecMemo getSepcMemo(String commCode) throws Exception{
		PeriodSpecMemo ret = null;
		logger.debug("查找单条财务账期结算备注信息！输入的参数为：" + commCode);
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		ret = dao.findPeriodSpecMemo(commCode);		
		return ret;
	}
	/**
	 * 根据输入的条件，查询财务账期结算备注信息
	 * @param specMemo
	 * @return
	 * @throws Exception
	 */
	public List<PeriodSpecMemo> getSepcMemoList(PeriodSpecMemo specMemo) throws Exception{
		List<PeriodSpecMemo> ret = null;
		logger.debug("查找条出货财务发票记录信息！输入的参数为：" + specMemo.toString());
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		ret = dao.listPeriodSpecMemo(specMemo);
		return ret;
	}
	/**
	 * 根据输入的条件，查询财务账期结算备注信息记录数
	 * @param specMemo
	 * @return
	 * @throws Exception
	 */
	public int getSepcMemoListCount(PeriodSpecMemo specMemo) throws Exception{
		int ret =0;
		logger.debug("查找财务账期结算备注信息记录数！输入的参数为：" + specMemo.toString());
		PeriodSpecMemoDao dao = (PeriodSpecMemoDao)BeanLocator.getInstance().getBean(InvoiceConstants.PERIOD_SPEC_MEMO_DAO);
		ret = dao.listPeriodSpecMemoCount(specMemo);
		return ret;
	}
}
