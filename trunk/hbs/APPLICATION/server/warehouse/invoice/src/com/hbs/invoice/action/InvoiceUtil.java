/**
 * 
 */
package com.hbs.invoice.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;

/**
 * @author xyf
 *
 */
public class InvoiceUtil {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(InvoiceUtil.class);
	
	public static boolean checkKeyFields(InvoiceInfo invoice) {
		if(invoice == null)
			return false;
		if(invoice.getInvoiceSeqId() != null)
			return true;
//		if(StringUtils.isEmpty(invoice.getCcode())
//				|| StringUtils.isEmpty(invoice.getPoNo())
//				|| StringUtils.isEmpty(invoice.getCpartNo())){
//			return false;
//		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static List<FieldErr> checkInputFields(
			InvoiceInfo invoice, Map otherData) {
		List<FieldErr> list = new ArrayList<FieldErr>();
		if(null == invoice.getInvoiceSeqId()){
			if(StringUtils.isEmpty(invoice.getCcode()))
				list.add(new FieldErr("CCode","CCode没有填写"));
			if(StringUtils.isEmpty(invoice.getShortName()))
				list.add(new FieldErr("ShortName","ShortName没有填写"));
			if(StringUtils.isEmpty(invoice.getPoNo()))
				list.add(new FieldErr("PoNo","PoNo没有填写"));
			if(StringUtils.isEmpty(invoice.getCpartNo()))
				list.add(new FieldErr("CpartNo","CpartNo没有填写"));
			if(StringUtils.isEmpty(invoice.getPartNo()))
				list.add(new FieldErr("PartNo","PartNo没有填写"));
			if(invoice.getCurMoney() == null)
				list.add(new FieldErr("CurMoney", "CurMoney没有填写"));
			if(StringUtils.isEmpty(invoice.getInvoiceDesc()))
				list.add(new FieldErr("InvoiceDesc","InvoiceDesc没有填写"));
			if(invoice.getCreateTime() == null)
				list.add(new FieldErr("CreateTime","CreateTime没有填写"));
		}
		return list;
	}
}
