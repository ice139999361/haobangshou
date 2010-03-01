/**
 * system ��hbs
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
	public static final String DETAIL_DATEFORMAT ="yyyy��MM��dd��";
	
	/**
	 * ˽�й��캯��
	 */
	private DateUtils(){
		
	}
	/**
	 * ������λ�ȡ����,
	 * @param curDate  ������Ҫ�������ڵ�����
	 * @param startDate ������ʼ��,��ʽyyyymmdd
	 * @param internal  ���ڵĿ��,1 ��ʾһ����,2 ��ʾ2����,��������
	 * @return  ��������,��ʽΪyyyymm
	 */
	public static String getDatePeriod(Date curDate , String startDate , String internal){
		DateTime dtStart = getStartPeriodDateTime(curDate,startDate,internal);
		return dtStart.toString(MONTHFORMAT);
	}
	
	/**
	 * ���ݵ�ǰ���ڻ�ȡǰһ������
	 * @param curPeriod ��ǰ����
	 * @param internal  ���ڼ��
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
	 * ������λ�ȡ������ʼ��,
	 * @param curDate  ������Ҫ�������ڵ�����
	 * @param startDate ������ʼ��,��ʽyyyymmdd
	 * @param internal  ���ڵĿ��,1 ��ʾһ����,2 ��ʾ2����,��������
	 * @return
	 */
	public static Date getStartPeriod(Date curDate ,String startDate , String internal){
				
		DateTime dtStart = getStartPeriodDateTime(curDate,startDate,internal);		
		
		return dtStart.toDate();
	}
	/**
	 * ��ȡ������/�����յ��������� �����ݵ�ǰ���������ǰ�������ڵ���ʼ���ڣ�
	 * ��ʼ����-1 ��Ϊ�ϸ����ڵĽ����գ�������+ ���� ��Ϊ������/������
	 * ������ - �ύ��������  = ��������
	 * @param curDate ��ǰ����
	 * @param startDate  ������ʼ�գ���ʽΪyyyymmdd
	 * @param internal  ���ڵĿ��,1 ��ʾһ����,2 ��ʾ2����,��������
	 * @param accountDay ���ڽ�����ĵڼ���Ϊ������/������
	 * @return  �ַ�������
	 */
	public static String getReminderDay(Date curDate ,String startDate , String internal,String accountDay,String reminderDay){
		DateTime dtStart = getStartPeriodDateTime(curDate,startDate,internal);
		//�ϸ����ڵĽ�����
		dtStart = dtStart.plusDays(-1);
		//�����ڽ�������Ϊ������/������
		dtStart = dtStart.plusDays(new Integer(accountDay));
		//��ǰ��������֪ͨ
		dtStart = dtStart.plusDays( - (new Integer(reminderDay)));
		
		return dtStart.toString(DATEFORMAT);
	}
	/**
	 * ��ȡ���ڵĶ�����/������
	 * @param curDate ��ǰ����
	 * @param startDate  ������ʼ�գ���ʽΪyyyymmdd
	 * @param internal  ���ڵĿ��,1 ��ʾһ����,2 ��ʾ2����,��������
	 * @param accountDay ���ڽ�����ĵڼ���Ϊ������/������
	 * @return �ַ�������
	 */
	public static String getAccountDay(Date curDate ,String startDate , String internal,String accountDay){
		DateTime dtStart = getStartPeriodDateTime(curDate,startDate,internal);
		//�ϸ����ڵĽ�����
		dtStart = dtStart.plusDays(-1);
		//�����ڽ�������Ϊ������/������
		dtStart = dtStart.plusDays(new Integer(accountDay));
		return dtStart.toString(DETAIL_DATEFORMAT);
	}
	/**
	 * ������λ�ȡ������ʼ��,˽�о�̬����
	 * @param curDate  ������Ҫ�������ڵ�����
	 * @param startDate ������ʼ��,��ʽyyyymmdd
	 * @param internal  ���ڵĿ��,1 ��ʾһ����,2 ��ʾ2����,��������
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
			if(curTime.isAfter(dtEnd)){  //�������ڴ����������
				dtStart = dtStart.plusMonths(iInternal);
				dtEnd = dtEnd.plusMonths(iInternal);				
			}else{//��������С���������
				if(curTime.isBefore(dtStart)){//��������С����ʼ����					
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
	 * ���ݴ�������ڸ�ʽ�����ص�ǰʱ��ĸ�ʽ���ַ���
	 * @param format
	 * @return
	 */
	public static String getCurFormatDate(String format){
		DateTime curTime = new DateTime(new Date());
		return curTime.toString(format);
	}
	
	/**
	 * ���ݴ�������ں���Ҫ�ĸ�ʽ�����ظ�ʽ���ַ���
	 * �������ĸ�ʽΪnull��ʹ��ϵͳȱʡ�ĸ�ʽ
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
	 * ���ݴ�������ں���Ҫ�ĸ�ʽ�����ظ�ʽ���ַ���
	 * �������ĸ�ʽΪnull��ʹ��ϵͳȱʡ�ĸ�ʽ 
	 * @param date 
	 * @param internal ��Ҫ��ȥ�ļ��
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
