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
 * Action中对VendorPartNoInfo的一些通用处理函数集
 * @author xyf
 *
 */
public class VendorPartNoInfoUtil {
	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorPartNoInfoUtil.class);
	
    /**
	 * 判断是否填写了key字段。vendorPartNoInfo.SeqId 或 (vendorPartNoInfo.commCode + vendorPartNoInfo.state)
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
	 * 对输入的供应商物料信息进行校验，内部调用checkSelectFields。
	 * @param vendorPartNoInfo	供应商物料关系信息
	 * @return 出错信息，格式：Map<出错字段,出错信息>
	 */
	public static List<FieldErr> checkInputFields(VendorPartNoInfo vendorPartNoInfo)
	{
		ArrayList<FieldErr> list = new ArrayList<FieldErr>();
		if(vendorPartNoInfo == null)
		{
			list.add(new FieldErr("", "参数错误"));
			return list;
		}
		
		if(vendorPartNoInfo.getCreateDate() == null)
			vendorPartNoInfo.setCreateDate(new Date());
		
		String s;
		// DONE:完成checkInputFields，对输入的供应商信息进行校验
		
		s = vendorPartNoInfo.getSampleCode();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("SampleCode","SampleCode没有填写"));
		}
		
		s = vendorPartNoInfo.getCommCode();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("CommCode","CommCode没有填写"));
		}
		s = vendorPartNoInfo.getCustPartNo();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("CustPartNo","CustPartNo没有填写"));
		}
		BigDecimal num = vendorPartNoInfo.getPrice();
		if(num.abs().movePointRight(3).compareTo(BigDecimal.ONE) <= 0)
		{
			list.add(new FieldErr("Price","Price没有填写"));
		}
		s = vendorPartNoInfo.getPartNo();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("PartNo","PartNo没有填写"));
		}
		else
		{
			try {
				CompanyPartNoMgr mgr = (CompanyPartNoMgr)BeanLocator.getInstance().getBean("companyPartNoMgr");
				CompanyPartNo pnInfo = mgr.getCompanyPartNo(s);
				if(pnInfo == null)
				{
					list.add(new FieldErr("PartNo", "PartNo错误"));
				}
				else
				{
					vendorPartNoInfo.setPnDesc(pnInfo.getPnDesc());
					vendorPartNoInfo.setClsName(pnInfo.getClsCode());
				}
			} catch (Exception e) {
				logger.error("检查PartNo出错", e);
				list.add(new FieldErr("PartNo", "PartNo错误"));
			}
		}
		s = vendorPartNoInfo.getPnDesc();
		if(StringUtils.isEmpty(s))
		{
			list.add(new FieldErr("PnDesc","PnDesc没有填写"));
		}
		
		List<FieldErr> list2 = checkSelectFields(vendorPartNoInfo);
		if(list2 != null && list2.size() > 0)
			list.addAll(list2);
		
		return list;
	}
	
	/**
	 * 检查选择数据。检查选项值，填写选项的说明字段
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
				//DONE：用户信息需要处理	
				Staff u = StaffUtil.getStaffById(s);
				if(u == null)
					list.add(new FieldErr("StaffId", "StaffId错误"));
				else
					vendorPartNoInfo.setStaffName(u.getStaffName());
				
			}
		}
		catch(Exception e)
		{
			logger.info("checkSelectFields出错", e);
		}
		
		return list;
	}

	/**
	 * 判断是否需要设置staffId
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
