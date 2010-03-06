/**
 * 
 */
package com.hbs.vendorinfo.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.StaffUtil;
import com.hbs.domain.auth.pojo.Staff;
import com.hbs.domain.product.pojo.CompanyPartNo;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.product.manager.CompanyPartNoMgr;
import com.hbs.vendorinfo.manager.VendorPartNoInfoMgr;

/**
 * Action�ж�VendorPartNoInfo��һЩͨ�ô�������
 * @author xyf
 *
 */
public class VendorPartNoInfoUtil {
	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorPartNoInfoUtil.class);
	
    /**
	 * �ж��Ƿ���д��key�ֶΡ�vendorPartNoInfo.SeqId �� (vendorPartNoInfo.commCode + vendorPartNoInfo.state)
	 * @param vendorPartNoInfo
	 * @return
	 */
	public static boolean checkKeyFields(VendorPartNoInfo vendorPartNoInfo)
	{
		try
		{
			if(vendorPartNoInfo == null)
				return false;
			if(vendorPartNoInfo.getSeqId() != 0)
				return true;
			String s = vendorPartNoInfo.getCommCode();
			if(s == null || s.length() == 0)
				return false;
			s = vendorPartNoInfo.getState();
			try
			{
				Integer.parseInt(s);
			}
			catch(Exception e)
			{
				return false;
			}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * ������Ĺ�Ӧ��������Ϣ����У�飬�ڲ�����checkSelectFields��
	 * @param vendorPartNoInfo	��Ӧ�����Ϲ�ϵ��Ϣ
	 * @return ������Ϣ����ʽ��Map<�����ֶ�,������Ϣ>
	 */
	public static List<FieldErr> checkInputFields(VendorPartNoInfo vendorPartNoInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(vendorPartNoInfo == null)
		{
			list.add(new FieldErr("", "��������"));
			return list;
		}
		
		if(vendorPartNoInfo.getCreateDate() == null)
			vendorPartNoInfo.setCreateDate(new Date());
		
		String s;
		// DONE:���checkInputFields��������Ĺ�Ӧ����Ϣ����У��
		
		s = vendorPartNoInfo.getSampleCode();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("SampleCode","SampleCodeû����д"));
		}
		
		s = vendorPartNoInfo.getCommCode();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("CommCode","CommCodeû����д"));
		}
		s = vendorPartNoInfo.getCustPartNo();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("CustPartNo","CustPartNoû����д"));
		}
		BigDecimal num = vendorPartNoInfo.getPrice();
		if(num.abs().movePointRight(3).compareTo(BigDecimal.ONE) <= 0)
		{
			list.add(new FieldErr("Price","Priceû����д"));
		}
		s = vendorPartNoInfo.getPartNo();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("PartNo","PartNoû����д"));
		}
		else
		{
			try {
				CompanyPartNoMgr mgr = (CompanyPartNoMgr)BeanLocator.getInstance().getBean("companyPartNoMgr");
				CompanyPartNo pnInfo = mgr.getCompanyPartNo(s);
				if(pnInfo == null)
				{
					list.add(new FieldErr("PartNo", "PartNo����"));
				}
				else
				{
					vendorPartNoInfo.setPnDesc(pnInfo.getPnDesc());
					vendorPartNoInfo.setClsName(pnInfo.getClsCode());
				}
			} catch (Exception e) {
				logger.error("���PartNo����", e);
				list.add(new FieldErr("PartNo", "PartNo����"));
			}
		}
		s = vendorPartNoInfo.getPnDesc();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("PnDesc","PnDescû����д"));
		}
		
		List<FieldErr> list2 = checkSelectFields(vendorPartNoInfo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
	}
	
	/**
	 * ���ѡ�����ݡ����ѡ��ֵ����дѡ���˵���ֶ�
	 * @param vendorPartNoInfo
	 */
	public static List<FieldErr> checkSelectFields(VendorPartNoInfo vendorPartNoInfo)
	{
		if(vendorPartNoInfo == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		try
		{
			String s;
			
			int i;
			s = vendorPartNoInfo.getStaffId();
			if(StringUtils.isNotEmpty(s))
			{
				//DONE���û���Ϣ��Ҫ����	
				Staff u = StaffUtil.getStaffById(s);
				if(u == null)
					list.add(new FieldErr("StaffId", "StaffId����"));
				else
					vendorPartNoInfo.setStaffName(u.getStaffName());
				
			}
		}
		catch(Exception e)
		{
			logger.info("checkSelectFields����", e);
		}
		
		return list;
	}

	/**
	 * �ж��Ƿ���Ҫ����staffId
	 * @param vendorPartNoInfo
	 * @return
	 */
	public static boolean checkSetStaffId(VendorPartNoInfo vendorPartNoInfo) {
		int userid = 0;
		try
		{
			String s = vendorPartNoInfo.getStaffId();
			userid = Integer.parseInt(s);
		}
		catch(NumberFormatException e)
		{
			userid = 0;
		}
		return (userid == 0);
	}
}
