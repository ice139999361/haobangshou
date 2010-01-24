package com.hbs.common.utils;

import java.io.File;
import org.apache.commons.lang.StringUtils;
/**
 * <p>Title: ��ȡ·��������</p>
 * <p>Description: ��ȡ·��������</p> 
 * @version 1.0.0.0
 */
public class AppServerUtil {
	private static final String DEFAULT_RELATIVE_CONFIG_PATH = "config";

	private static final String JBOSS = "jboss";

	private static final String TOMCAT = "tomcat";

	private static final  String FS = System.getProperty("file.separator");

	private static String fullConfigPath;// ��Ҫ����������ļ�·��

	/**
	 * ���⹫����̬��������ȡ�����ļ�������·��
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
	 * ���������ļ���Ŀ¼·�� 
	 * @param configSubDir  �����ļ���Ŀ¼
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
	 * ���ݴ���·�����ļ��� �������ʱȫ·���� 
	 * @param configSubDir  String ��·��,��Ϊ��
	 * @param fileName      String �ļ���
	 * @return String 
	 */
	public static String getFullConfigPath(String configSubDir, String fileName) {
		return getFullConfigPath(configSubDir) + fileName;
	}

	/**
	 * ���������ļ���Ŀ¼·��
	 * 
	 * @return
	 */
	private static String getConPath() {
		String userDir = System.getProperty("user.dir");
		String serverName = getServerName();
		if (JBOSS.equals(serverName)) {// jboss��·��
			int index = userDir.lastIndexOf(File.separatorChar);
			String tmpPath = userDir.substring(0, index + 1);
			userDir = tmpPath + "server/default/conf";
		} else if (TOMCAT.equals(serverName)) {// user.dir�Ƿ�������binĿ¼, ����confĿ¼
			int index = userDir.lastIndexOf(File.separatorChar);			
			String tmpPath = userDir.substring(0, index + 1);
			userDir = tmpPath +  "conf";
		}
		// ���������ļ�ȫ·��
		return userDir + FS + DEFAULT_RELATIVE_CONFIG_PATH;
	}

	/**
	 * ��ȡ��ǰ������������
	 * 
	 * @return String ���ط���������,���û�У����ؿ��ַ���
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