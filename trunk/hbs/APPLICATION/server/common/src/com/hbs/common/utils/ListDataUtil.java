/**
 * 
 */
package com.hbs.common.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
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
		ArrayList list = null;		

		if (values == null) {
			logger.info("需要设置对象的值为null,对象为：" + itemClass.getName());
		} else {
			list = new ArrayList();
			int i = 0;
			try {
				for (; i < values.length; i++) {
					Object o = itemClass.newInstance();
					splitIntoFields(o, values[i], fieldNames, spliter);
					list.add(o);

				}

			} catch (Exception e) {
				list = null;
				logger.error("splitIntoList处理数据" + values[i] + "出错" , e);				
			}
		}
		
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
			logger.debug("解析解析的字段值为：" + ar.toString());
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
							//TODO:此处可能还需要修改，目前可以处理的格式有String、Number、Date
							Class typeClass = fd.getType();
							if(typeClass.equals(Date.class)){
								DateTimeFormatter fmt = DateTimeFormat.forPattern(DATEFORMAT);
								DateTime dt = fmt.parseDateTime(ar[i]);
								Calendar c = Calendar.getInstance();
								c.set(dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
								fd.set(o, c.getTime());
							}else{
								// 一般类型处理方法，包括字符串、数值
								Constructor con = typeClass.getConstructor(String.class);
								fd.set(o, con.newInstance(ar[i]));
							}
						} catch (Exception e) {
							// 获取出错行号
							int line = -1;
							final String className = ListDataUtil.class.getName();
							for(StackTraceElement s : e.getStackTrace()){
								if(className.equals(s.getClassName())){
									line = s.getLineNumber();
									break;
								}
							}
							StringBuffer sb = new StringBuffer();
							sb.append("splitIntoFields line ").append(line)
							.append(" ").append(o.getClass().getSimpleName()).append(".").append(fieldNames[i])
							.append("=\"").append(ar[i]).append("\"")
							.append(" ").append(e.toString());
							logger.debug(sb);
						}
					}
				}
			}		
		
	}


}
