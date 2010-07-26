/**
 * 
 */
package com.hbs.common.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * �����б�����
 * @author xyf
 * modified by yangzj  20100317
 *
 */
public class ListDataUtil {
	
	private static final String SPLIT_FORMATER = ",";
	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(ListDataUtil.class);
    /**
     * ���ڸ�ʽ yyyy-MM-dd
     */
	public static final String DATEFORMAT = "yyyy-MM-dd";
    /**
     * ���ڸ�ʽ yyyy-MM-dd HH:mm:ss
     */
	public static final String DATEFORMAT2 = "yyyy-MM-dd HH:mm:ss";

    /**
	 * ��һ������д������б���
	 * @param itemClass	�б�����Class
	 * @param values	һ�����ݡ�һ���ַ������飬ÿ���ַ�����Ӧһ�����ݣ��зָ���Ϊ��,��
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 * @return
	 */
	@SuppressWarnings({"unchecked"})
	public static List splitIntoList(Class itemClass, String[] values, String[] fieldNames)
	{
		return splitIntoList(itemClass, values, fieldNames, SPLIT_FORMATER);
	}
	
	/**
	 * ��һ������д������б���
	 * @param itemClass	�б�����Class
	 * @param values	һ�����ݡ�һ���ַ������飬ÿ���ַ�����Ӧһ������
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 * @param spliter	values�ķָ���
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List splitIntoList(Class itemClass, String[] values, String[] fieldNames, String spliter)
	{
		ArrayList list = new ArrayList();		

		if (values == null) {
			logger.info("��Ҫ���ö����ֵΪnull,����Ϊ��" + itemClass.getName());
		} else {
			int i = 0;
			try {
				for (; i < values.length; i++) {
					Object o = itemClass.newInstance();
					splitIntoFields(o, values[i], fieldNames, spliter);
					list.add(o);

				}

			} catch (Exception e) {
				list = new ArrayList();
				logger.error("splitIntoList��������" + values[i] + "����" , e);				
			}
		}
		logger.debug("������ϣ�size=" + list.size());
		return list;
		
	}

	/**
	 * ��һ�е�����д��������
	 * @param o ��д��Ķ���
	 * @param values	һ�����ݣ�ÿ���ԡ�,���ָ�
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 */
	@SuppressWarnings("unused")
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames) throws Exception
	{
		splitIntoFields(o, values, fieldNames, SPLIT_FORMATER);
	}
	
	/**
	 * ��һ�е�����д��������
	 * @param o ��д��Ķ���
	 * @param values	һ������
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 * @param spliter	values���зָ���
	 */
	@SuppressWarnings("unchecked")
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames, String spliter) throws Exception
	{
		if (o == null || values == null || fieldNames == null)
			return;
		   //Ϊ�����||;;lwt||;;�ֿ�||;;26718666||;;||;;||;;||;;||;;||;;||;;||;;||;;||;;
		   // ���ֶ���ֶ�Ϊ�յ�����޸ģ��ӽ�������
			int iLength = fieldNames.length +1;
			String[] ar = values.split(spliter,iLength);
			if(logger.isDebugEnabled()){
				StringBuilder sb = new StringBuilder();
				sb.append("�����������ֶ�ֵΪ��");
				sb.append(" ");
				for(String s : ar){
					sb.append(s);
					sb.append(",");
				}
				logger.debug(sb.toString());
			}
			// ���������������ڸ������ֶ�����
			if (ar.length < fieldNames.length){
				
				throw new Exception("����Ĳ����������������������ڸ������ֶ�����!");
			}else{
				int i = 0;
				for (; i < fieldNames.length; i++) {
					if (StringUtils.isNotEmpty(fieldNames[i])){
						try {
							Field fd = o.getClass().getDeclaredField(fieldNames[i]);
							if(!(Modifier.isPublic(fd.getModifiers()))){//�ǹ�������
								fd.setAccessible(true);
							}
							/*
							 * TODO:�˴����ܻ���Ҫ�޸ģ��Ա�֧�ָ���ĸ�ʽ��
							 * Ŀǰ���Դ���ĸ�ʽ�У�
							 * String
							 * Number
							 * Date(yyyy-MM-dd��yyyy-MM-dd HH:mm:ss) 
							 */
							Class typeClass = fd.getType();
							if(typeClass.equals(Date.class)){
								fd.set(o, parseDate(ar[i]));
							}else{
								// һ�����ʹ������������ַ�������ֵ
								Constructor con = typeClass.getConstructor(String.class);
								fd.set(o, con.newInstance(ar[i]));
							}
						} catch (Exception e) {
							// ��ȡ�����к�
							int line = findExceptionLinenumber(e, ListDataUtil.class);
							StringBuffer sb = new StringBuffer();
							sb.append("splitIntoFields line ").append(line)
							.append(" ").append(o.getClass().getSimpleName()).append(".").append(fieldNames[i])
							.append("=\"").append(ar[i]).append("\"")
							.append(" ").append(e.toString());
							logger.debug(sb);
						}
					}
				}
				logger.debug("���������" + o.toString());
			}		
		
	}

	/**
	 * ��Exception��CallStack���ҵ���Ӧ����к�
	 * @param e	�쳣
	 * @param cls	Ҫ���ҵ���
	 * @return �кţ�-1��ʾû���ҵ�
	 */
	@SuppressWarnings("unchecked")
	public static int findExceptionLinenumber(Exception e, Class cls) {
		int line = -1;
		final String className = cls.getName();  
		for(StackTraceElement s : e.getStackTrace()){
			if(className.equals(s.getClassName())){
				line = s.getLineNumber();
				break;
			}
		}
		return line;
	}

	/**
	 * �����ַ���������Date
	 * @param datestr
	 * @return	
	 */
	public static Date parseDate(String datestr) throws Exception{
		DateTimeFormatter fmt;
		long dt;
		try {
			fmt = DateTimeFormat.forPattern(DATEFORMAT);
			dt = fmt.parseMillis(datestr);
		} catch (IllegalArgumentException e) {
			fmt = DateTimeFormat.forPattern(DATEFORMAT2);
			dt = fmt.parseMillis(datestr);
		}
		return new Date(dt);
	}
	
	/**
	 * ��ʽ������Ϊ�����ַ���
	 * @param d
	 * @return
	 * @throws Exception
	 */
	public static String formatDate(Date d) throws Exception{
		return (new DateTime(d.getTime())).toString(DateTimeFormat.forPattern(DATEFORMAT));
	}
	
	/**
	 * ��ʽ������Ϊ����ʱ���ַ���
	 * @param d
	 * @return
	 * @throws Exception
	 */
	public static String formatDateTime(Date d) throws Exception{
		return (new DateTime(d.getTime())).toString(DateTimeFormat.forPattern(DATEFORMAT2));
	}

}
