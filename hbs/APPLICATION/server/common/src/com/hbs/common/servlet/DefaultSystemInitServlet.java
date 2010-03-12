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
 * 系统缺省启动servlet(如果子系统有额外的初始化要求,可在web.xml增加新的子系统初始化servlet).
  
 */
@SuppressWarnings("serial")
public class DefaultSystemInitServlet extends HttpServlet {
    

    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(DefaultSystemInitServlet.class);
    
    /**
     * quartz调度器.
     */
    private static Scheduler scheduler = null;
    /**
     * 初始化.
     */
    @Override
    public void init() throws ServletException{
    	System.out.println("进入初始化");
        // BeanUtils使用Convert转换字段，重新注册转换器 add by lhm
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        
        

        // 初始化配置文件根目录
        initRootConfigPath();
        BeanLocator.setServletContext(this.getServletContext());

        // 初始化log4j配置(日志输出目录等)
        initLog4j();
        
     // 初始化quartz配置(如果子系统有用quartz).
        initQuartz();
    }

    /**
     * 关闭时资源清理.
     */
    public void destroy() {
    	 if (scheduler != null) {
             try {
                 scheduler.shutdown();
             } catch (SchedulerException e) {
                 logger.error("关闭quartz任务调度失败，失败原因:",e);
                 return;
             }

             logger.info("关闭quartz任务调度成功！");
         }
    }

    /**
     * 初始化配置文件根目录.
     */
    private void initRootConfigPath() {
        // 初始化配置文件根目录
        /***************************************************************************************************************
         * 常用服务器缺省存放配置文件根目录： TOMCAT：$TOMCAT_HOME/bin/config OC4J: $OC4J_HOME/j2ee/home/sims/config JBOSS
         * $JOBSS_HOME/config
         **************************************************************************************************************/
        String userDir = AppServerUtil.getConfigPath();
        String configRootPath = userDir ;
        ConfigurationHelper.setBasePath(configRootPath);
        
        logger.info("设置系统配置文件根目录成功, 配置文件根目录:" + configRootPath);
       

       
    }

   

    /**
     * 初始化log4j配置(日志输出目录等).
     */
    public void initLog4j() {
        // 初始化log文件根目录
        /***************************************************************************************************************
         * 常用服务器缺省存放log根目录： TOMCAT：$TOMCAT_HOME/bin/log OC4J: $OC4J_HOME/j2ee/home/sims/log $JBOSS_HOME/log
         **************************************************************************************************************/
        String userDir = AppServerUtil.getConfigPath();        
        String strLogPath =userDir.substring(0,userDir.lastIndexOf("conf" + File.separator)) + "logs";
        System.out.println("目录路径：" + strLogPath);
        TimeSizeRollingFileAppender.setLogRootPath(strLogPath);

        // 从init-param中得到log4j.xml配置文件相对路径.
        String log4jConfig = this.getInitParameter("log4jConfig");
        if (log4jConfig == null || log4jConfig.length() == 0) {
            logger.error("没有指定log4j配置文件相对路径！");
            System.out.println("没有指定log4j配置文件相对路径！");
            return;
        }

        // 初始化log4j配置(日志输出目录等).
        String configFileName = ConfigurationHelper.getFullFileName(log4jConfig);
        System.out.println("configFileName " + configFileName);
        // 加载XML配置文件(每10分钟扫描一次配置文件,如果log4j.xml有更新,自动加载)
        DOMConfigurator.configureAndWatch(configFileName, 6000);

        logger.info("加载log4j配置文件成功！");
        System.out.println("加载log4j配置文件成功！");
    }

   

    /**
     * 初始化quartz配置(quartz配置文件目录等).
     */
    public void initQuartz() {
        // 初始化quartz配置(quartz配置文件目录等).
        // 从init-param中得到quartz.properties配置文件相对路径.
        String quartzConfig = this.getInitParameter("quartzConfig");
        if (quartzConfig == null || quartzConfig.length() == 0) {
            logger.info("没有指定quartz配置文件相对路径！");
            return;
        }

        // 得到quartz配置文件全路径名
        String quartzConfigFileName = ConfigurationHelper.getFullFileName(quartzConfig);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(quartzConfigFileName);
        } catch (FileNotFoundException e) {
            logger.error("找不到quartz配置文件,文件名:" + quartzConfigFileName);
            return;
        }

        // 得到quartz配置文件的目录
        String quartzConfigPath = StringUtils.substringBeforeLast(quartzConfigFileName, "/");

        // 加载配置文件
        Properties properties = new Properties();
        try {
            properties.load(fileInputStream);

            // 设置quartz_jobs.xml路径
            properties.setProperty("org.quartz.plugin.jobInitializer.fileName", quartzConfigPath + File.separator
                    + "quartz_jobs.xml");

        } catch (IOException e) {
            logger.error("加载quartz配置文件失败,文件名:" + quartzConfigFileName);
            return;
        }

        // 启动quartz
        StdSchedulerFactory factory = null;
        try {
            factory = new StdSchedulerFactory(properties);
        } catch (SchedulerException e) {
            logger.error("初始化quartz失败,失败原因:" ,e);
            return;
        }

        // 启动quartz任务调度器
        try {
            scheduler = factory.getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("启动quartz任务调度失败,失败原因:" ,e);
            return;
        }

        logger.info("启动quartz任务调度成功！");
    }



}
