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
				list.add(new FieldErr("CCode","CCodeû����д"));
			if(StringUtils.isEmpty(invoice.getShortName()))
				list.add(new FieldErr("ShortName","ShortNameû����д"));
			if(StringUtils.isEmpty(invoice.getPoNo()))
				list.add(new FieldErr("PoNo","PoNoû����д"));
			if(StringUtils.isEmpty(invoice.getCpartNo()))
				list.add(new FieldErr("CpartNo","CpartNoû����д"));
			if(StringUtils.isEmpty(invoice.getPartNo()))
				list.add(new FieldErr("PartNo","PartNoû����д"));
			if(invoice.getCurMoney() == null)
				list.add(new FieldErr("CurMoney", "CurMoneyû����д"));
			if(StringUtils.isEmpty(invoice.getInvoiceDesc()))
				list.add(new FieldErr("InvoiceDesc","InvoiceDescû����д"));
			if(invoice.getCreateTime() == null)
				list.add(new FieldErr("CreateTime","CreateTimeû����д"));
		}
		return list;
	}
}
