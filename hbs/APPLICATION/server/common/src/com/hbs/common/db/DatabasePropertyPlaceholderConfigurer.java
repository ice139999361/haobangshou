package com.hbs.common.db;

/**
 * DatabasePropertyPlaceholderConfigurer.java 
 * <p>Title: SIMS</p>
 * <p> Description: 获得数据库配置文件,采用外部部署文件方式,继承
 *                  PropertyPlaceholderConfigurer基类</p>

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
	 * 重载基类方法,加载属性到内存
	 */
	public void loadProperties(Properties props) throws IOException {

		String propertyFileName = ConfigurationHelper.getFullConfigPath("db",
				"jdbc.properties");

		PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

		logger.info("获得数据源配置文件为:" + propertyFileName);
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
