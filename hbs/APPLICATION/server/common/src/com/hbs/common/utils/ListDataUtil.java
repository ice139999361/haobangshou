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
 * 处理列表数据
 * @author xyf
 *
 */
public class ListDataUtil {
	/**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(ListDataUtil.class);

    /**
	 * 将一组数据写入对象列表中
	 * @param itemClass	列表对象的Class
	 * @param values	一组数据。一个字符串数组，每个字符串对应一行数据，列分隔符为“,”
	 * @param fieldNames	每列数据对应的字段名
	 * @return
	 */
	@SuppressWarnings({"unused", "unchecked"})
	public static List splitIntoList(Class itemClass, String[] values, String[] fieldNames)
	{
		return splitIntoList(itemClass, values, fieldNames, ",");
	}
	
	/**
	 * 将一组数据写入对象列表中
	 * @param itemClass	列表对象的Class
	 * @param values	一组数据。一个字符串数组，每个字符串对应一行数据
	 * @param fieldNames	每列数据对应的字段名
	 * @param spliter	values的分隔符
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List splitIntoList(Class itemClass, String[] values, String[] fieldNames, String spliter)
	{
		//DONE:完成splitIntoList
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
					logger.info("splitIntoList处理数据"+values[i]+"出错", e);
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
	 * 将一行的数据写到对象中
	 * @param o 待写入的对象
	 * @param values	一行数据，每列以“,”分隔
	 * @param fieldNames	每列数据对应的字段名
	 */
	@SuppressWarnings("unused")
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames)
	{
		splitIntoFields(o, values, fieldNames, ",");
	}
	
	/**
	 * 将一行的数据写到对象中
	 * @param o 待写入的对象
	 * @param values	一行数据
	 * @param fieldNames	每列数据对应的字段名
	 * @param spliter	values的列分隔符
	 */
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames, String spliter)
	{
		if(o == null || values == null || fieldNames == null)
			return;
		try
		{
			String[] ar = values.split(spliter);
			// 数据列数不能少于给出的字段名数
			if(ar.length < fieldNames.length)
				return;
			
			// 将数据接入对应字段
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
					if(logger.isDebugEnabled())	logger.debug("splitIntoFields处理字段"+fieldNames[i]+"出错", e);
					String setName = "set" + fieldNames[i].substring(0, 1).toUpperCase() + fieldNames[i].substring(1);
					try{
						o.getClass().getDeclaredMethod(setName, String.class).invoke(o, ar[i]);
					}catch(Exception e1){
						if(logger.isDebugEnabled())	logger.debug("splitIntoFields处理"+setName+"(String)出错", e1);
						try{
							o.getClass().getDeclaredMethod(setName, Integer.class).invoke(o, Integer.parseInt(ar[i]));
						}catch(Exception e2){
							if(logger.isDebugEnabled())	logger.debug("splitIntoFields处理"+setName+"(Integer)出错", e2);
							try {
								o.getClass().getDeclaredMethod(setName, Date.class).invoke(o, DateFormat.getDateInstance().parse(ar[i]));
							} catch (Exception e3) {
								if(logger.isDebugEnabled())	logger.debug("splitIntoFields处理"+setName+"(Date)出错", e3);
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			logger.info("splitIntoFields出错", e);
		}
	}


}
