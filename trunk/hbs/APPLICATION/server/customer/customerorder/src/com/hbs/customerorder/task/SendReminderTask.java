/**
 * system ：hbs
 * desc:  发货提醒的接口类，子类实现
 *        分为预付费发货提醒和账期发货提醒  
 * version: 1.0
 * author : yangzj
 */
package com.hbs.customerorder.task;

public interface SendReminderTask {
	
	public void reminder();
}
