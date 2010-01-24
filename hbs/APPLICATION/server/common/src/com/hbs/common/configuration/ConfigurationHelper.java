package com.hbs.common.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.InputStream;

import java.net.URL;

import org.apache.commons.configuration.Configuration;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

/**
 * �����ļ�������.
 * 
 * 
 * 
 */
public final class ConfigurationHelper {
	/**
	 * logger.
	 */
	private static final Logger logger = Logger
			.getLogger(ConfigurationHelper.class);
	/**
	 * xml�ļ���׺��.
	 */
	private static final String XML = ".xml";
	/**
	 * properties�ļ���׺��.
	 */
	private static final String PROPERTIES = ".properties";
	/**
	 * HTTPЭ��ǰ׺.
	 */
	private static final String HTTP = "HTTP://";

	/**
	 * �����ļ���Ŀ¼.
	 */
	private static String basePath = null;
	
	private static final  String FS = System.getProperty("file.separator");

	/**
	 * ˽�й��캭������ֹ������.
	 */
	private ConfigurationHelper() {

	}

	/**
	 * ���������ļ���Ŀ¼.
	 * 
	 * @param basePath
	 *            ��Ŀ¼.
	 */
	public static void setBasePath(String basePath) {
		ConfigurationHelper.basePath = basePath;
	}

	/**
	 * �õ������ļ���Ŀ¼.
	 * 
	 * @return ��Ŀ¼.
	 */
	public static String getBasePath() {
		return basePath;
	}

	/**
	 * ���������ļ����õ������ļ���.
	 * 
	 * @param configurationFileName
	 *            �����ļ���
	 * @return �����ļ���
	 */
	public static InputStream readConfiguration(String configurationFileName) {
		if (configurationFileName == null) {
			return null;
		}
		// �õ������ļ�ȫ·���������������basePath��fileName=basePath+configurationFileName��
		String fileName = getFullFileName(configurationFileName);
		try {
			File file = new File(fileName);
			FileInputStream fileInputStream = new FileInputStream(file);
			return fileInputStream;
		} catch (FileNotFoundException e) {
			logger.error("�������ļ�ʧ�ܣ�filename=" + fileName);
		}
		return null;
	}

	/**
	 * ���������ļ����õ����ö���.
	 * 
	 * @param configurationFileName
	 *            �����ļ���
	 * @return ���ö���
	 */
	public static Configuration getConfiguration(String configurationFileName) {
		return getConfiguration(configurationFileName, 0);
	}

	/**
	 * ���������ļ����õ����ö���.
	 * 
	 * @param configurationFileName
	 *            �����ļ���
	 * @param refreshDelay
	 *            ˢ��ʱ��(΢�뵥λ)
	 * @return ���ö���
	 */
	public static Configuration getConfiguration(String configurationFileName,
			long refreshDelay) {
		if (configurationFileName == null) {
			return null;
		}

		// �õ������ļ�ȫ·���������������basePath��fileName=basePath+configurationFileName��
		String fileName = getFullFileName(configurationFileName);

		// ��������ļ�����
		boolean isXmlFile = false;
		if (configurationFileName.lastIndexOf(XML) > 0) {
			isXmlFile = true;
		} else if (configurationFileName.lastIndexOf(PROPERTIES) > 0) {
			isXmlFile = false;
		} else {
			return null;
		}

		// ��������ļ��Ƿ�URL�ļ�
		boolean isUrl = isUrlFile(fileName);

		if (isXmlFile) {
			XMLConfiguration xmlConfiguration = null;
			if (isUrl) {
				try {
					xmlConfiguration = new XMLConfiguration(new URL(fileName));
				} catch (Exception e) {
					logger.error("��URL�����ļ�ʧ�ܣ�URL=" + fileName);
				}
			} else {
				try {
					xmlConfiguration = new XMLConfiguration(fileName);
				} catch (Exception e) {
					logger.error("�������ļ�ʧ�ܣ�filename=" + fileName);
				}
			}

			// �����Ҫ��ʱˢ��,����ˢ�²���
			if (refreshDelay > 0) {
				FileChangedReloadingStrategy fileChangedReloadingStrategy = new FileChangedReloadingStrategy();
				fileChangedReloadingStrategy.setConfiguration(xmlConfiguration);
				fileChangedReloadingStrategy.setRefreshDelay(refreshDelay);
				xmlConfiguration
						.setReloadingStrategy(fileChangedReloadingStrategy);
			}
			return xmlConfiguration;
		} else {
			PropertiesConfiguration propertiesConfiguration = null;
			if (isUrl) {
				try {
					propertiesConfiguration = new PropertiesConfiguration(
							new URL(fileName));
				} catch (Exception e) {
					logger.error("��URL�����ļ�ʧ�ܣ�URL=" + fileName);
				}
			} else {
				try {
					propertiesConfiguration = new PropertiesConfiguration(
							fileName);
				} catch (Exception e) {
					logger.error("�������ļ�ʧ�ܣ�filename=" + fileName);
				}
			}

			// �����Ҫ��ʱˢ��,����ˢ�²���
			if (refreshDelay > 0) {
				FileChangedReloadingStrategy reloadingStrategy = null;
				if (isUrl) {
					reloadingStrategy = new RemoteFileChangedReloadingStrategy();
				} else {
					reloadingStrategy = new FileChangedReloadingStrategy();
				}
				reloadingStrategy.setConfiguration(propertiesConfiguration);
				reloadingStrategy.setRefreshDelay(refreshDelay);
				propertiesConfiguration.setReloadingStrategy(reloadingStrategy);

			}
			return propertiesConfiguration;
		}

	}

	/**
	 * �õ��ļ�ȫ·����.
	 * 
	 * @param fileName
	 *            �����ļ���
	 * @return ȫ·����
	 */
	public static String getFullFileName(String fileName) {
		if (basePath != null) {
			return basePath + File.separator + fileName;
		}
		return fileName;
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
	 * @param configSubDir  �����ļ���Ŀ¼
	 * @return
	 */
	public static String getFullConfigPath(String configSubDir) {
		String retValue ="";
		if(null == configSubDir || "".equals(configSubDir)){
			retValue = getBasePath() + FS;
		}else{
			retValue= getBasePath() + FS + configSubDir + FS;
		}
		return retValue;
	}
	/**
	 * �Ƿ�URL�����ļ�.
	 * 
	 * @param fileName
	 *            �����ļ���
	 * @return ȫ·����
	 */
	private static boolean isUrlFile(String fileName) {
		if (fileName.toUpperCase().startsWith(HTTP)) {
			return true;
		}
		return false;
	}
}
