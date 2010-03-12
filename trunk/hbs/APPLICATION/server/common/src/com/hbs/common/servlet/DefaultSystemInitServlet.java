package com.hbs.common.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.lang.StringUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;


import com.hbs.common.springhelper.BeanLocator;
import com.hbs.common.utils.AppServerUtil;
import com.hbs.common.appender.TimeSizeRollingFileAppender;
import com.hbs.common.configuration.ConfigurationHelper;
/**
 * ϵͳȱʡ����servlet(�����ϵͳ�ж���ĳ�ʼ��Ҫ��,����web.xml�����µ���ϵͳ��ʼ��servlet).
  
 */
@SuppressWarnings("serial")
public class DefaultSystemInitServlet extends HttpServlet {
    

    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(DefaultSystemInitServlet.class);
    
    /**
     * quartz������.
     */
    private static Scheduler scheduler = null;
    /**
     * ��ʼ��.
     */
    @Override
    public void init() throws ServletException{
    	System.out.println("�����ʼ��");
        // BeanUtilsʹ��Convertת���ֶΣ�����ע��ת���� add by lhm
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        
        

        // ��ʼ�������ļ���Ŀ¼
        initRootConfigPath();
        BeanLocator.setServletContext(this.getServletContext());

        // ��ʼ��log4j����(��־���Ŀ¼��)
        initLog4j();
        
     // ��ʼ��quartz����(�����ϵͳ����quartz).
        initQuartz();
    }

    /**
     * �ر�ʱ��Դ����.
     */
    public void destroy() {
    	 if (scheduler != null) {
             try {
                 scheduler.shutdown();
             } catch (SchedulerException e) {
                 logger.error("�ر�quartz�������ʧ�ܣ�ʧ��ԭ��:",e);
                 return;
             }

             logger.info("�ر�quartz������ȳɹ���");
         }
    }

    /**
     * ��ʼ�������ļ���Ŀ¼.
     */
    private void initRootConfigPath() {
        // ��ʼ�������ļ���Ŀ¼
        /***************************************************************************************************************
         * ���÷�����ȱʡ��������ļ���Ŀ¼�� TOMCAT��$TOMCAT_HOME/bin/config OC4J: $OC4J_HOME/j2ee/home/sims/config JBOSS
         * $JOBSS_HOME/config
         **************************************************************************************************************/
        String userDir = AppServerUtil.getConfigPath();
        String configRootPath = userDir ;
        ConfigurationHelper.setBasePath(configRootPath);
        
        logger.info("����ϵͳ�����ļ���Ŀ¼�ɹ�, �����ļ���Ŀ¼:" + configRootPath);
       

       
    }

   

    /**
     * ��ʼ��log4j����(��־���Ŀ¼��).
     */
    public void initLog4j() {
        // ��ʼ��log�ļ���Ŀ¼
        /***************************************************************************************************************
         * ���÷�����ȱʡ���log��Ŀ¼�� TOMCAT��$TOMCAT_HOME/bin/log OC4J: $OC4J_HOME/j2ee/home/sims/log $JBOSS_HOME/log
         **************************************************************************************************************/
        String userDir = AppServerUtil.getConfigPath();        
        String strLogPath =userDir.substring(0,userDir.lastIndexOf("conf" + File.separator)) + "logs";
        System.out.println("Ŀ¼·����" + strLogPath);
        TimeSizeRollingFileAppender.setLogRootPath(strLogPath);

        // ��init-param�еõ�log4j.xml�����ļ����·��.
        String log4jConfig = this.getInitParameter("log4jConfig");
        if (log4jConfig == null || log4jConfig.length() == 0) {
            logger.error("û��ָ��log4j�����ļ����·����");
            System.out.println("û��ָ��log4j�����ļ����·����");
            return;
        }

        // ��ʼ��log4j����(��־���Ŀ¼��).
        String configFileName = ConfigurationHelper.getFullFileName(log4jConfig);
        System.out.println("configFileName " + configFileName);
        // ����XML�����ļ�(ÿ10����ɨ��һ�������ļ�,���log4j.xml�и���,�Զ�����)
        DOMConfigurator.configureAndWatch(configFileName, 6000);

        logger.info("����log4j�����ļ��ɹ���");
        System.out.println("����log4j�����ļ��ɹ���");
    }

   

    /**
     * ��ʼ��quartz����(quartz�����ļ�Ŀ¼��).
     */
    public void initQuartz() {
        // ��ʼ��quartz����(quartz�����ļ�Ŀ¼��).
        // ��init-param�еõ�quartz.properties�����ļ����·��.
        String quartzConfig = this.getInitParameter("quartzConfig");
        if (quartzConfig == null || quartzConfig.length() == 0) {
            logger.info("û��ָ��quartz�����ļ����·����");
            return;
        }

        // �õ�quartz�����ļ�ȫ·����
        String quartzConfigFileName = ConfigurationHelper.getFullFileName(quartzConfig);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(quartzConfigFileName);
        } catch (FileNotFoundException e) {
            logger.error("�Ҳ���quartz�����ļ�,�ļ���:" + quartzConfigFileName);
            return;
        }

        // �õ�quartz�����ļ���Ŀ¼
        String quartzConfigPath = StringUtils.substringBeforeLast(quartzConfigFileName, "/");

        // ���������ļ�
        Properties properties = new Properties();
        try {
            properties.load(fileInputStream);

            // ����quartz_jobs.xml·��
            properties.setProperty("org.quartz.plugin.jobInitializer.fileName", quartzConfigPath + File.separator
                    + "quartz_jobs.xml");

        } catch (IOException e) {
            logger.error("����quartz�����ļ�ʧ��,�ļ���:" + quartzConfigFileName);
            return;
        }

        // ����quartz
        StdSchedulerFactory factory = null;
        try {
            factory = new StdSchedulerFactory(properties);
        } catch (SchedulerException e) {
            logger.error("��ʼ��quartzʧ��,ʧ��ԭ��:" ,e);
            return;
        }

        // ����quartz���������
        try {
            scheduler = factory.getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("����quartz�������ʧ��,ʧ��ԭ��:" ,e);
            return;
        }

        logger.info("����quartz������ȳɹ���");
    }



}
