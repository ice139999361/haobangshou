package com.hbs.common.springhelper;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 加载spring配置文件，提供getBean接口. 
 *
 */
public final class BeanLocator {
	private static final Logger logger = Logger.getLogger(BeanLocator.class);
    /**
     * 单实例.
     */
    private static BeanLocator instance = null;

    
    /**
     * 加载配置文件名.
     */
    private static String configurationFileName = null;
    
    /**
     * spring环境.
     */
    private static ServletContext webServletContext = null;
    private ApplicationContext applicationContext;


    /**
     * 单例模式.
     * @return 接口
     */
    public static BeanLocator getInstance() {
        if (instance == null) {
            // 同步控制代码, 防止初始化多次.
            synchronized(logger) {
                if (instance == null) {
                    instance = new BeanLocator();
                }
            }
        }
        return instance;
    }

    /**
     * 设置配置文件名.
     * @param fileName 配置文件名
     */
    public static void setConfigurationFileName(String fileName) {
        configurationFileName = fileName;
    }

    public static void setServletContext(ServletContext servletContext)
    {
        webServletContext = servletContext;
    }

    /**
     * 私有构造.
     */
    private BeanLocator() {
        
        // 得到spring框架bean环境
        applicationContext = null;

        try
        {
            if(configurationFileName == null || configurationFileName.length() == 0)
                applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(webServletContext);
            else
                applicationContext = new ClassPathXmlApplicationContext(configurationFileName);
        

        }catch(Exception e){
        	logger.error("初始化spring配置文件时发生异常:" + e.getMessage(), e);
        	throw new RuntimeException("初始化spring配置文件时发生异常:" + e.getMessage(), e);
        }
    }

    /**
     * spring getBean 接口.
     * @param beanName 接口名称
     * @return 接口
     */
    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
