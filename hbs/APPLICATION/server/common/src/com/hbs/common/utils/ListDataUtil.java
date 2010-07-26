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
 * 处理列表数据
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
     * 日期格式 yyyy-MM-dd
     */
	public static final String DATEFORMAT = "yyyy-MM-dd";
    /**
     * 日期格式 yyyy-MM-dd HH:mm:ss
     */
	public static final String DATEFORMAT2 = "yyyy-MM-dd HH:mm:ss";

    /**
	 * 将一组数据写入对象列表中
	 * @param itemClass	列表对象的Class
	 * @param values	一组数据。一个字符串数组，每个字符串对应一行数据，列分隔符为“,”
	 * @param fieldNames	每列数据对应的字段名
	 * @return
	 */
	@SuppressWarnings({"unchecked"})
	public static List splitIntoList(Class itemClass, String[] values, String[] fieldNames)
	{
		return splitIntoList(itemClass, values, fieldNames, SPLIT_FORMATER);
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
		ArrayList list = new ArrayList();		

		if (values == null) {
			logger.info("需要设置对象的值为null,对象为：" + itemClass.getName());
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
				logger.error("splitIntoList处理数据" + values[i] + "出错" , e);				
			}
		}
		logger.debug("处理完毕，size=" + list.size());
		return list;
		
	}

	/**
	 * 将一行的数据写到对象中
	 * @param o 待写入的对象
	 * @param values	一行数据，每列以“,”分隔
	 * @param fieldNames	每列数据对应的字段名
	 */
	@SuppressWarnings("unused")
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames) throws Exception
	{
		splitIntoFields(o, values, fieldNames, SPLIT_FORMATER);
	}
	
	/**
	 * 将一行的数据写到对象中
	 * @param o 待写入的对象
	 * @param values	一行数据
	 * @param fieldNames	每列数据对应的字段名
	 * @param spliter	values的列分隔符
	 */
	@SuppressWarnings("unchecked")
	private static void splitIntoFields(Object o, String values,
			String[] fieldNames, String spliter) throws Exception
	{
		if (o == null || values == null || fieldNames == null)
			return;
		   //为解决：||;;lwt||;;仓库||;;26718666||;;||;;||;;||;;||;;||;;||;;||;;||;;||;;
		   // 这种多个字段为空的情况修改，加解析长度
			int iLength = fieldNames.length +1;
			String[] ar = values.split(spliter,iLength);
			if(logger.isDebugEnabled()){
				StringBuilder sb = new StringBuilder();
				sb.append("解析解析的字段值为：");
				sb.append(" ");
				for(String s : ar){
					sb.append(s);
					sb.append(",");
				}
				logger.debug(sb.toString());
			}
			// 数据列数不能少于给出的字段名数
			if (ar.length < fieldNames.length){
				
				throw new Exception("传入的参数错误：数据列数不能少于给出的字段名数!");
			}else{
				int i = 0;
				for (; i < fieldNames.length; i++) {
					if (StringUtils.isNotEmpty(fieldNames[i])){
						try {
							Field fd = o.getClass().getDeclaredField(fieldNames[i]);
							if(!(Modifier.isPublic(fd.getModifiers()))){//非公共属性
								fd.setAccessible(true);
							}
							/*
							 * TODO:此处可能还需要修改，以便支持更多的格式。
							 * 目前可以处理的格式有：
							 * String
							 * Number
							 * Date(yyyy-MM-dd、yyyy-MM-dd HH:mm:ss) 
							 */
							Class typeClass = fd.getType();
							if(typeClass.equals(Date.class)){
								fd.set(o, parseDate(ar[i]));
							}else{
								// 一般类型处理方法，包括字符串、数值
								Constructor con = typeClass.getConstructor(String.class);
								fd.set(o, con.newInstance(ar[i]));
							}
						} catch (Exception e) {
							// 获取出错行号
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
				logger.debug("解析结果：" + o.toString());
			}		
		
	}

	/**
	 * 在Exception的CallStack中找到对应类的行号
	 * @param e	异常
	 * @param cls	要查找的类
	 * @return 行号，-1表示没有找到
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
	 * 分析字符串，返回Date
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
	 * 格式化日期为日期字符串
	 * @param d
	 * @return
	 * @throws Exception
	 */
	public static String formatDate(Date d) throws Exception{
		return (new DateTime(d.getTime())).toString(DateTimeFormat.forPattern(DATEFORMAT));
	}
	
	/**
	 * 格式化日期为日期时间字符串
	 * @param d
	 * @return
	 * @throws Exception
	 */
	public static String formatDateTime(Date d) throws Exception{
		return (new DateTime(d.getTime())).toString(DateTimeFormat.forPattern(DATEFORMAT2));
	}

}
