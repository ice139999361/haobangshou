/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.utils;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Administrator
 *
 */
public class DateUtils {
	
	private static final String DATEFORMAT ="yyyyMMdd";
	private static final String MONTHFORMAT ="yyyyMM";
	
	/**
	 * 私有构造函数
	 */
	private DateUtils(){
		
	}
	/**
	 * 根据入参获取账期,
	 * @param curDate  传入需要计算账期的日期
	 * @param startDate 账期起始日,格式yyyymmdd
	 * @param internal  账期的跨度,1 表示一个月,2 表示2个月,依次类推
	 * @return  返回账期,格式为yyyymm
	 */
	public static String getDatePeriod(Date curDate , String startDate , String internal){
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATEFORMAT);
		DateTime curTime = new DateTime(curDate);		
		DateTime dtStart = new DateTime(fmt.parseDateTime(startDate));		
		int iInternal = Integer.parseInt(internal);
		DateTime dtEnd = dtStart.plusMonths(iInternal);		
		boolean isTrue = true;
		while(isTrue){
			if(curTime.isAfter(dtEnd)){  //当期日期大于最后日期
				dtStart = dtStart.plusMonths(iInternal);
				dtEnd = dtEnd.plusMonths(iInternal);				
			}else{//当期日期小于最后日期
				if(curTime.isBefore(dtStart)){//当期日期小于起始日期					
					dtStart = dtStart.plusMonths(-iInternal);
					dtEnd = dtEnd.plusMonths(-iInternal);
				}else{
					isTrue = false;
				}
			}
		}
		return dtStart.toString(MONTHFORMAT);
	}
	
	/**
	 * 根据传入的日期格式，返回当前时间的格式化字符串
	 * @param format
	 * @return
	 */
	public static String getCurFormatDate(String format){
		DateTime curTime = new DateTime(new Date());
		return curTime.toString(format);
	}
	
	/**
	 * 根据传入的日期和需要的格式，返回格式化字符串
	 * 如果传入的格式为null，使用系统缺省的格式
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getFormatDate(Date date,String format){
		String ft = null;
		if(null == format){
			ft = DATEFORMAT;
		}else{
			ft = format;
		}
		return (new DateTime(date)).toString(ft);
	}
}
