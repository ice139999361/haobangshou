/**
 * 
 */
package com.hbs.invoice.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;
import com.hbs.invoice.manager.SendInvoiceMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class InvoiceSendAction extends InvoiceBaseAction {
	private static final Logger logger = Logger.getLogger(InvoiceSendAction.class);

	private SendInvoiceMgr mgr = (SendInvoiceMgr)getBean("sendInvoiceMgr");
	
	/* (non-Javadoc)
	 * @see com.hbs.invoice.action.InvoiceBaseAction#getInvoiceByIdAbstract()
	 */
	@Override
	protected InvoiceInfo getInvoiceByIdAbstract() throws Exception {
		if(invoice.getInvoiceSeqId() == null){
			logger.info("参数错误");
			setErrorReason("参数错误");
			return null;
		}
		return mgr.getSendInvoice(invoice.getInvoiceSeqId().toString());
	}

	/* (non-Javadoc)
	 * @see com.hbs.invoice.action.InvoiceBaseAction#getInvoiceByKeyAbstract()
	 */
	@Override
	protected InvoiceInfo getInvoiceByKeyAbstract() throws Exception {
		List<InvoiceInfo> list = mgr.getSendInvoiceList(invoice);
		if(list == null || list.size() <= 1){
			logger.info("没有找到");
			setErrorReason("没有找到");
			return null;
		}
		return mgr.getSendInvoice(list.get(0).getInvoiceSeqId().toString());
	}

	/* (non-Javadoc)
	 * @see com.hbs.invoice.action.InvoiceBaseAction#getIsManager()
	 */
	@Override
	protected boolean getIsManager() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hbs.invoice.action.InvoiceBaseAction#getRoleName()
	 */
	@Override
	public String getRoleName() {
		return "cwnormal";
	}

	/* (non-Javadoc)
	 * @see com.hbs.invoice.action.InvoiceBaseAction#listInvoiceAbstract()
	 */
	@Override
	protected List<InvoiceInfo> listInvoiceAbstract() throws Exception {
		return mgr.getSendInvoiceList(invoice);
	}

	/* (non-Javadoc)
	 * @see com.hbs.invoice.action.InvoiceBaseAction#listInvoiceCountAbstract()
	 */
	@Override
	protected int listInvoiceCountAbstract() throws Exception {
		return mgr.getSendInvoiceListCount(invoice);
	}

	@Override
	protected int saveInvoiceAbstract() throws Exception {
		return mgr.saveSendInvoice(invoice);
	}

	@Override
	protected int deleteInvoiceAbstract() throws Exception {
		return mgr.deleteSendInvoice(invoice.getInvoiceSeqId().toString());
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected int updateInvoiceAbstract() throws Exception {
		return mgr.updateSendInvoice(invoice);
	}

}
