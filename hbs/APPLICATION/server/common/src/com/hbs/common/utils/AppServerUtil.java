package com.hbs.common.utils;

import java.io.File;
import org.apache.commons.lang.StringUtils;
/**
 * <p>Title: 获取路径公共类</p>
 * <p>Description: 获取路径公共类</p> 
 * @version 1.0.0.0
 */
public class AppServerUtil {
	private static final String DEFAULT_RELATIVE_CONFIG_PATH = "config";

	private static final String JBOSS = "jboss";

	private static final String TOMCAT = "tomcat";

	private static final  String FS = System.getProperty("file.separator");

	private static String fullConfigPath;// 需要保存的配置文件路径

	/**
	 * 对外公开静态方法，获取配置文件的配置路径
	 * 
	 * @return
	 */
	public static String getConfigPath() {
		if (null == fullConfigPath) {
			fullConfigPath = getConPath();
		}
		return fullConfigPath;
	}

	/**
	 * 返回配置文件的目录路径 
	 * @param configSubDir  配置文件子目录
	 * @return
	 */
	public static String getFullConfigPath(String configSubDir) {
		String retValue ="";
		if(null == configSubDir || "".equals(configSubDir)){
			retValue = getConfigPath() + FS;
		}else{
			retValue= getConfigPath() + FS + configSubDir + FS;
		}
		return retValue;
	}

	/**
	 * 根据传入路径和文件名 获得运行时全路径名 
	 * @param configSubDir  String 子路径,可为空
	 * @param fileName      String 文件名
	 * @return String 
	 */
	public static String getFullConfigPath(String configSubDir, String fileName) {
		return getFullConfigPath(configSubDir) + fileName;
	}

	/**
	 * 返回配置文件的目录路径
	 * 
	 * @return
	 */
	private static String getConPath() {
		String userDir = System.getProperty("user.dir");
		String serverName = getServerName();
		if (JBOSS.equals(serverName)) {// jboss的路径
			int index = userDir.lastIndexOf(File.separatorChar);
			String tmpPath = userDir.substring(0, index + 1);
			userDir = tmpPath + "server/default/conf";
		} else if (TOMCAT.equals(serverName)) {// user.dir是服务器的bin目录, 换成conf目录
			int index = userDir.lastIndexOf(File.separatorChar);			
			String tmpPath = userDir.substring(0, index + 1);
			userDir = tmpPath +  "conf";
		}
		// 返回配置文件全路径
		return userDir + FS + DEFAULT_RELATIVE_CONFIG_PATH;
	}

	/**
	 * 获取当前服务器的名称
	 * 
	 * @return String 返回服务器名字,如果没有，返回空字符串
	 */
	private static String getServerName() {
		String retStrValue = "";
		if (StringUtils.isNotEmpty(System.getProperty("jboss.server.name"))) {
			retStrValue = JBOSS;
		} else if (StringUtils.isNotEmpty(System.getProperty("catalina.home"))) {
			retStrValue = TOMCAT;
		}

		return retStrValue;
	}
}