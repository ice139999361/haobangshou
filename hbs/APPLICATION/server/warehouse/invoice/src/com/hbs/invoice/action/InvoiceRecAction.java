/**
 * 
 */
package com.hbs.invoice.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;
import com.hbs.invoice.manager.RecInvoiceMgr;

/**
 * @author xyf
 *
 */
@SuppressWarnings("serial")
public class InvoiceRecAction extends InvoiceBaseAction {
	private static final Logger logger = Logger.getLogger(InvoiceRecAction.class);

	private RecInvoiceMgr mgr = (RecInvoiceMgr)getBean("recInvoiceMgr");
	
	/* (non-Javadoc)
	 * @see com.hbs.invoice.action.InvoiceBaseAction#getInvoiceByIdAbstract()
	 */
	@Override
	protected InvoiceInfo getInvoiceByIdAbstract() throws Exception {
		if(invoice.getInvoiceSeqId() == null){
			logger.info("��������");
			setErrorReason("��������");
			return null;
		}
		return mgr.getRecInvoice(invoice.getInvoiceSeqId().toString());
	}

	/* (non-Javadoc)
	 * @see com.hbs.invoice.action.InvoiceBaseAction#getInvoiceByKeyAbstract()
	 */
	@Override
	protected InvoiceInfo getInvoiceByKeyAbstract() throws Exception {
		List<InvoiceInfo> list = mgr.getRecInvoiceList(invoice);
		if(list == null || list.size() <= 1){
			logger.info("û���ҵ�");
			setErrorReason("û���ҵ�");
			return null;
		}
		return mgr.getRecInvoice(list.get(0).getInvoiceSeqId().toString());
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
		return mgr.getRecInvoiceList(invoice);
	}

	/* (non-Javadoc)
	 * @see com.hbs.invoice.action.InvoiceBaseAction#listInvoiceCountAbstract()
	 */
	@Override
	protected int listInvoiceCountAbstract() throws Exception {
		return mgr.getRecInvoiceListCount(invoice);
	}

	@Override
	protected int saveInvoiceAbstract() throws Exception {
		return mgr.saveRecInvoice(invoice);
	}

	@Override
	protected int deleteInvoiceAbstract() throws Exception {
		return mgr.deleteRecInvoice(invoice.getInvoiceSeqId().toString());
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected int updateInvoiceAbstract() throws Exception {
		return mgr.updateRecInvoice(invoice);
	}

}
