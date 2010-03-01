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
	
	public static final String DATEFORMAT ="yyyyMMdd";
	public static final String MONTHFORMAT ="yyyyMM";
	public static final String DETAIL_DATEFORMAT ="yyyy年MM月dd日";
	
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
		DateTime dtStart = getStartPeriodDateTime(curDate,startDate,internal);
		return dtStart.toString(MONTHFORMAT);
	}
	
	/**
	 * 根据当前账期获取前一个账期
	 * @param curPeriod 当前账期
	 * @param internal  账期间隔
	 * @return
	 */
	public static String getPrePeriod(String curPeriod , String internal){
		DateTimeFormatter fmt = DateTimeFormat.forPattern(MONTHFORMAT);				
		DateTime dtStart = new DateTime(fmt.parseDateTime(curPeriod));	
		int iInternal = Integer.parseInt(internal);
		dtStart = dtStart.plusMonths(-iInternal);
		return dtStart.toString(MONTHFORMAT);
	}
	
	/**
	 * 根据入参获取账期起始日,
	 * @param curDate  传入需要计算账期的日期
	 * @param startDate 账期起始日,格式yyyymmdd
	 * @param internal  账期的跨度,1 表示一个月,2 表示2个月,依次类推
	 * @return
	 */
	public static Date getStartPeriod(Date curDate ,String startDate , String internal){
				
		DateTime dtStart = getStartPeriodDateTime(curDate,startDate,internal);		
		
		return dtStart.toDate();
	}
	/**
	 * 获取对账日/结算日的提醒日期 ，根据当前日期算出当前账期日期的起始日期，
	 * 起始日期-1 就为上个账期的结束日，结束日+ 天数 就为对账日/结算日
	 * 对账日 - 提交提醒天数  = 提醒日期
	 * @param curDate 当前日期
	 * @param startDate  账期起始日，格式为yyyymmdd
	 * @param internal  账期的跨度,1 表示一个月,2 表示2个月,依次类推
	 * @param accountDay 账期结束后的第几日为对账日/结算日
	 * @return  字符串日期
	 */
	public static String getReminderDay(Date curDate ,String startDate , String internal,String accountDay,String reminderDay){
		DateTime dtStart = getStartPeriodDateTime(curDate,startDate,internal);
		//上个账期的结束日
		dtStart = dtStart.plusDays(-1);
		//加账期结束后几天为对账日/结算日
		dtStart = dtStart.plusDays(new Integer(accountDay));
		//提前几天提醒通知
		dtStart = dtStart.plusDays( - (new Integer(reminderDay)));
		
		return dtStart.toString(DATEFORMAT);
	}
	/**
	 * 获取账期的对账日/结算日
	 * @param curDate 当前日期
	 * @param startDate  账期起始日，格式为yyyymmdd
	 * @param internal  账期的跨度,1 表示一个月,2 表示2个月,依次类推
	 * @param accountDay 账期结束后的第几日为对账日/结算日
	 * @return 字符串日期
	 */
	public static String getAccountDay(Date curDate ,String startDate , String internal,String accountDay){
		DateTime dtStart = getStartPeriodDateTime(curDate,startDate,internal);
		//上个账期的结束日
		dtStart = dtStart.plusDays(-1);
		//加账期结束后几天为对账日/结算日
		dtStart = dtStart.plusDays(new Integer(accountDay));
		return dtStart.toString(DETAIL_DATEFORMAT);
	}
	/**
	 * 根据入参获取账期起始日,私有静态函数
	 * @param curDate  传入需要计算账期的日期
	 * @param startDate 账期起始日,格式yyyymmdd
	 * @param internal  账期的跨度,1 表示一个月,2 表示2个月,依次类推
	 * @return
	 */
	private static DateTime getStartPeriodDateTime(Date curDate ,String startDate , String internal){
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
		return dtStart;
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
	/**
	 * 根据传入的日期和需要的格式，返回格式化字符串
	 * 如果传入的格式为null，使用系统缺省的格式 
	 * @param date 
	 * @param internal 需要减去的间隔
	 * @param format
	 * @return
	 */
	public static String getFormatDate(Date date , String internal , String format,boolean isAdd){
		String ft = null;
		if(null == format){
			ft = DATEFORMAT;
		}else{
			ft = format;
		}
		DateTime dt = new DateTime(date);
		if(!isAdd){
			if(internal != null){
				dt = dt.plusDays(-(new Integer(internal)));
			}else{
				dt = dt.plusDays(1);
			}
		}else{
			if(internal != null){
				dt = dt.plusDays((new Integer(internal)));
			}
		}
		return dt.toString(ft);
	}
	
	
}
