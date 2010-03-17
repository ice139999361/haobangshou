/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.domain;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * @author Administrator
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = new Integer(5);
		Integer j = -i;
		
		System.out.println(i + j);
		System.out.println(getNeedDate(new Date(), "1",true));
	}

	
	public static Date getNeedDate(Date date , String internal,boolean isAdd){
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

		return dt.toDate();
	}
}
