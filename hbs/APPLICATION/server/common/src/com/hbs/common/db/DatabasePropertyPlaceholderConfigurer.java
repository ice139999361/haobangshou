package com.hbs.common.db;

/**
 * DatabasePropertyPlaceholderConfigurer.java 
 * <p>Title: SIMS</p>
 * <p> Description: ������ݿ������ļ�,�����ⲿ�����ļ���ʽ,�̳�
 *                  PropertyPlaceholderConfigurer����</p>

 * @author yangzj
 * @version 1.0.0
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import com.hbs.common.configuration.ConfigurationHelper;

public class DatabasePropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	/**
	 * logger.
	 */
	private static final Logger logger = Logger
			.getLogger(DatabasePropertyPlaceholderConfigurer.class);

	/**
	 * ���ػ��෽��,�������Ե��ڴ�
	 */
	public void loadProperties(Properties props) throws IOException {

		String propertyFileName = ConfigurationHelper.getFullConfigPath("db",
				"jdbc.properties");

		PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

		logger.info("�������Դ�����ļ�Ϊ:" + propertyFileName);
		InputStream is = null;
		try {
			is = new FileInputStream(propertyFileName);
			propertiesPersister.load(props, is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
}
