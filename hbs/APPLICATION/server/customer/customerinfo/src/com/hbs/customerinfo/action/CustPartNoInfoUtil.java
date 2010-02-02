/**
 * 
 */
package com.hbs.customerinfo.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * Action�ж�CustPartNoInfo��һЩͨ�ô�������
 * @author xyf
 *
 */
public class CustPartNoInfoUtil {
	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustPartNoInfoUtil.class);
	
    /**
	 * �ж��Ƿ���д��key�ֶΡ�custPNInfo.SeqId �� (custPNInfo.commCode + custPNInfo.state)
	 * @param custPNInfo
	 * @return
	 */
	public static boolean checkKeyFields(CustPartNoInfo custPNInfo)
	{
		try
		{
			if(custPNInfo == null)
				return false;
			if(custPNInfo.getSeqId() != 0)
				return true;
			String s = custPNInfo.getCommCode();
			if(s == null || s.length() == 0)
				return false;
			s = custPNInfo.getState();
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
	 * ������Ŀͻ���Ϣ����У�飬�ڲ�����checkSelectFields��
	 * @param custPNInfo	�ͻ���Ϣ
	 * @return ������Ϣ����ʽ��Map<�����ֶ�,������Ϣ>
	 */
	public static List<FieldErr> checkInputFields(CustPartNoInfo custPNInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(custPNInfo == null)
			return list;
		
		String s;
		// DONE:���checkInputFields��������Ŀͻ���Ϣ����У��
		s = custPNInfo.getVendorCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("venderCode","venderCodeû����д"));
		}
		s = custPNInfo.getCommCode();
		if(s == null || s.length() == 0)
		{
			list.add(new FieldErr("CommCode","CommCodeû����д"));
		}
		
		List<FieldErr> list2 = checkSelectFields(custPNInfo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
	}
	
	/**
	 * ���ѡ�����ݡ����ѡ��ֵ����дѡ���˵���ֶ�
	 * @param custPNInfo
	 */
	public static List<FieldErr> checkSelectFields(CustPartNoInfo custPNInfo)
	{
		if(custPNInfo == null)
			return null;
		List<FieldErr> list = new ArrayList<FieldErr>();
		
		try
		{
			String s;
			ConfigEncode ce = new ConfigEncode();
			ConfigEncode ce2;
			
			int i;
			s = custPNInfo.getStaffId();
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
		
}
