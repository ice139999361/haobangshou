package com.hbs.common.springhelper;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * ����spring�����ļ����ṩgetBean�ӿ�. 
 *
 */
public final class BeanLocator {
	private static final Logger logger = Logger.getLogger(BeanLocator.class);
    /**
     * ��ʵ��.
     */
    private static BeanLocator instance = null;

    
    /**
     * ���������ļ���.
     */
    private static String configurationFileName = null;
    
    /**
     * spring����.
     */
    private static ServletContext webServletContext = null;
    private ApplicationContext applicationContext;


    /**
     * ����ģʽ.
     * @return �ӿ�
     */
    public static BeanLocator getInstance() {
        if (instance == null) {
            // ͬ�����ƴ���, ��ֹ��ʼ�����.
            synchronized(logger) {
                if (instance == null) {
                    instance = new BeanLocator();
                }
            }
        }
        return instance;
    }

    /**
     * ���������ļ���.
     * @param fileName �����ļ���
     */
    public static void setConfigurationFileName(String fileName) {
        configurationFileName = fileName;
    }

    public static void setServletContext(ServletContext servletContext)
    {
        webServletContext = servletContext;
    }

    /**
     * ˽�й���.
     */
    private BeanLocator() {
        
        // �õ�spring���bean����
        applicationContext = null;

        try
        {
            if(configurationFileName == null || configurationFileName.length() == 0)
                applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(webServletContext);
            else
                applicationContext = new ClassPathXmlApplicationContext(configurationFileName);
        

        }catch(Exception e){
        	logger.error("��ʼ��spring�����ļ�ʱ�����쳣:" + e.getMessage(), e);
        	throw new RuntimeException("��ʼ��spring�����ļ�ʱ�����쳣:" + e.getMessage(), e);
        }
    }

    /**
     * spring getBean �ӿ�.
     * @param beanName �ӿ�����
     * @return �ӿ�
     */
    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
