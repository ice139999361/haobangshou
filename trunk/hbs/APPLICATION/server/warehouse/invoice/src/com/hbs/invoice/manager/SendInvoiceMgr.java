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
import com.hbs.domain.common.dao.baseinfo.InvoiceInfoDao;
import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;
import com.hbs.invoice.constants.InvoiceConstants;
import com.hbs.warehouse.common.utils.WareHouseLogUtils;

public class SendInvoiceMgr {
	private static final Logger logger = Logger.getLogger(SendInvoiceMgr.class);
	
	/**
	 * 保存客户发票记录，保存的依据是客户的送货单
	 * @param invoiceInfo
	 * @return >0 保存成功
	 * @throws Exception
	 */
	public int saveSendInvoice(InvoiceInfo invoiceInfo) throws Exception{
		int ret =0;
		logger.debug("保存出货财务发票记录信息！输入的参数为：" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		ret = dao.insertInvoiceInfo(invoiceInfo);
		//操作日志
		WareHouseLogUtils.operLog(invoiceInfo.getStaffId(), invoiceInfo.getStaffName(), "新增", "客户发票", invoiceInfo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * 修改客户发票记录
	 * @param invoiceInfo
	 * @return 0  成功
	 * @throws Exception
	 */
	public int updateSendInvoice(InvoiceInfo invoiceInfo) throws Exception{
		int ret =0;
		logger.debug("修改出货财务发票记录信息！输入的参数为：" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		dao.updateInvoiceInfo(invoiceInfo);
		//操作日志
		WareHouseLogUtils.operLog(invoiceInfo.getStaffId(), invoiceInfo.getStaffName(), "修改", "客户发票", invoiceInfo.getLogKey(), null, null);
		return ret;
	}
	/**
	 * 删除客户发票记录，先查找，不存在，抛出异常
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public int deleteSendInvoice(String seqId) throws Exception{
		int ret =0;
		logger.debug("删除出货财务发票记录信息！输入的参数为：" + seqId);
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		InvoiceInfo existInfo = dao.findInvoiceInfo(seqId);
		if(existInfo != null){
			dao.deleteInvoiceInfo(seqId);
			//操作日志
			WareHouseLogUtils.operLog(existInfo.getStaffId(), existInfo.getStaffName(), "删除", "客户发票", existInfo.getLogKey(), existInfo.toString(), null);
		}else{
			throw new Exception("需要删除的客户发票信息不存在！输入的参数为：" + seqId);
		}
		return ret;
	}
	
	/**
	 * 根据seqid查找单条客户发票记录
	 * @param seqId
	 * @return
	 * @throws Exception
	 */
	public InvoiceInfo getSendInvoice(String seqId) throws Exception{
		InvoiceInfo ret = null;
		logger.debug("查找单条出货财务发票记录信息！输入的参数为：" + seqId);
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		ret = dao.findInvoiceInfo(seqId);		
		return ret;
	}
	/**
	 * 根据输入的条件，查询客户发票列表记录
	 * @param invoiceInfo
	 * @return
	 * @throws Exception
	 */
	public List<InvoiceInfo> getSendInvoiceList(InvoiceInfo invoiceInfo) throws Exception{
		List<InvoiceInfo> ret = null;
		logger.debug("查找条出货财务发票记录信息！输入的参数为：" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		ret = dao.listInvoiceInfo(invoiceInfo);
		return ret;
	}
	/**
	 * 根据输入的条件，查询客户发票列表记录数
	 * @param invoiceInfo
	 * @return
	 * @throws Exception
	 */
	public int getSendInvoiceListCount(InvoiceInfo invoiceInfo) throws Exception{
		int ret =0;
		logger.debug("查找条出货财务发票记录信息记录数！输入的参数为：" + invoiceInfo.toString());
		InvoiceInfoDao dao = (InvoiceInfoDao)BeanLocator.getInstance().getBean(InvoiceConstants.CUST_SEND_INVOICE_DAO);
		ret = dao.listInvoiceInfoCount(invoiceInfo);
		return ret;
	}
}
