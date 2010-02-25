/**
 * 
 */
package com.hbs.vendorinfo.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.action.FieldErr;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;

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
			return list;
		
		String s;
		// DONE:���checkInputFields��������Ĺ�Ӧ����Ϣ����У��
		
		s = vendorPartNoInfo.getSampleCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("SampleCode","SampleCodeû����д"));
		}
		
		s = vendorPartNoInfo.getCommCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CommCode","CommCodeû����д"));
		}
		s = vendorPartNoInfo.getCustPartNo();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CustPartNo","CustPartNoû����д"));
		}
		s = vendorPartNoInfo.getPnDesc();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PnDesc","PnDescû����д"));
		}
		BigDecimal num = vendorPartNoInfo.getPrice();
		if(num.abs().movePointRight(3).compareTo(BigDecimal.ONE) <= 0)
		{
			list.add(new FieldErr("Price","Priceû����д"));
		}
		s = vendorPartNoInfo.getPartNo();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("PartNo","PartNoû����д"));
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
			if(s != null && s.length() != 0)
			{
				try{
					i = Integer.parseInt(s);
				}catch(Exception e){
					i = 0;
				}
				if(i != 0)
				{
					//TODO���û���Ϣ��Ҫ����					
					//custInfo.setStaffName(s);
				}
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
