/**
 * 
 */
package com.hbs.common.utils;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * �����б�����
 * @author xyf
 *
 */
public class ListDataUtil {
	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(ListDataUtil.class);

    /**
	 * ��һ������д������б���
	 * @param itemClass	�б�����Class
	 * @param values	һ�����ݡ�һ���ַ������飬ÿ���ַ�����Ӧһ�����ݣ��зָ���Ϊ��,��
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 * @return
	 */
	@SuppressWarnings({"unused", "unchecked"})
	public static List splitIntoList(Class itemClass, String[] values, String[] fieldNames)
	{
		return splitIntoList(itemClass, values, fieldNames, ",");
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
		//DONE:���splitIntoList
		try
		{
			ArrayList list = new ArrayList();
			if(values == null)
				return list;
			
			for(int i = 0; i < values.length; i++)
			{
				try
				{
					Object o = itemClass.newInstance();
					splitIntoFields(o, values[i], fieldNames, spliter);
					list.add(o);
				}
				catch(Exception e)
				{
					logger.info("splitIntoList��������"+values[i]+"����", e);
				}
			}
			return list;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	/**
	 * ��һ�е�����д��������
	 * @param o ��д��Ķ���
	 * @param values	һ�����ݣ�ÿ���ԡ�,���ָ�
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 */
	@SuppressWarnings("unused")
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames)
	{
		splitIntoFields(o, values, fieldNames, ",");
	}
	
	/**
	 * ��һ�е�����д��������
	 * @param o ��д��Ķ���
	 * @param values	һ������
	 * @param fieldNames	ÿ�����ݶ�Ӧ���ֶ���
	 * @param spliter	values���зָ���
	 */
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames, String spliter)
	{
		if(o == null || values == null || fieldNames == null)
			return;
		try
		{
			String[] ar = values.split(spliter);
			// ���������������ڸ������ֶ�����
			if(ar.length < fieldNames.length)
				return;
			
			// �����ݽ����Ӧ�ֶ�
			for(int i = 0; i < fieldNames.length; i++)
			{
				if(StringUtils.isEmpty(fieldNames[i]))
					continue;
				try
				{
					o.getClass().getDeclaredField(fieldNames[i]).set(o, ar[i]);
				}
				catch(Exception e)
				{
					if(logger.isDebugEnabled())	logger.debug("splitIntoFields�����ֶ�"+fieldNames[i]+"����", e);
					String setName = "set" + fieldNames[i].substring(0, 1).toUpperCase() + fieldNames[i].substring(1);
					try{
						o.getClass().getDeclaredMethod(setName, String.class).invoke(o, ar[i]);
					}catch(Exception e1){
						if(logger.isDebugEnabled())	logger.debug("splitIntoFields����"+setName+"(String)����", e1);
						try{
							o.getClass().getDeclaredMethod(setName, Integer.class).invoke(o, Integer.parseInt(ar[i]));
						}catch(Exception e2){
							if(logger.isDebugEnabled())	logger.debug("splitIntoFields����"+setName+"(Integer)����", e2);
							try {
								o.getClass().getDeclaredMethod(setName, Date.class).invoke(o, DateFormat.getDateInstance().parse(ar[i]));
							} catch (Exception e3) {
								if(logger.isDebugEnabled())	logger.debug("splitIntoFields����"+setName+"(Date)����", e3);
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			logger.info("splitIntoFields����", e);
		}
	}


}
