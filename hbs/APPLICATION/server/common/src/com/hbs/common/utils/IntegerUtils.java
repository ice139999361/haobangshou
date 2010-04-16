/**
 * 
 */
package com.hbs.common.utils;

/**
 * @author xyf
 *
 */
public class IntegerUtils {
	
	/**
	 * 返回int值，如果为null，返回0
	 * @param i
	 * @return
	 */
	public static int intValue(Integer i) {
		if(i == null)
			return 0;
		else
			return i.intValue();
	}
	
	/**
	 * 返回int值，如果为null，返回v
	 * @param i
	 * @param v
	 * @return
	 */
	public static int isNull(Integer i, int v) {
		if(i == null)
			return v;
		else
			return i.intValue();
	}
}
