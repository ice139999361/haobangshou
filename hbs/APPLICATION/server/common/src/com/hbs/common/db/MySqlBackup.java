package com.hbs.common.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

import com.hbs.common.utils.DateUtils;

public class MySqlBackup {
	
	private static Logger logger = Logger.getLogger(MySqlBackup.class);
	
	/**
     * 备份检验一个sql文件是否可以做导入文件用的一个判断方法：把该sql文件分别用记事本和ultra
     * edit打开，如果看到的中文均正常没有乱码，则可以用来做导入的源文件（不管sql文件的编码格式如何，也不管db的编码格式如何）
     */
    public static void backupstruct() {
        try {
        	logger.debug("begin to backup database struct!");
            Runtime rt = Runtime.getRuntime();

            // 调用 mysql 的 cmd:192.168.1.61
            Process child = rt.exec("E:/mysql-5.0.81-win32/bin/mysqldump.exe -h 192.168.1.61 -u yangzj -pyangzj  --no-data --set-charset=utf8 testdb");
            // 设置导出编码为utf8。这里必须是utf8
           
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
                       
            InputStreamReader xx = new InputStreamReader(in, "utf8");
           
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
           
            String backName = "d:/temp/testdbTable_" + DateUtils.getCurFormatDate("YYYY-MM-dd")+".sql";
            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = new FileOutputStream(
                    backName);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
            writer.write(outStr);
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
            writer.flush();

            // 别忘记关闭输入输出流
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();

            logger.debug("end to backup database struct!");

        } catch (Exception e) {
            logger.error(e);
        }catch(Throwable t){
        	logger.error(t);
        }

    }
    
    public static void backupdata() {
        try {
        	logger.debug("begin to backup database data!");
            Runtime rt = Runtime.getRuntime();

            // 调用 mysql 的 cmd:192.168.1.61
            Process child = rt.exec("E:/mysql-5.0.81-win32/bin/mysqldump.exe -h 192.168.1.61 -u yangzj -pyangzj  --no-create-info --set-charset=gb2312 testdb");
            // 设置导出编码为utf8。这里必须是utf8
           
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
                       
            InputStreamReader xx = new InputStreamReader(in, "gb2312");// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码
           
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
            String backName = "d:/temp/testdbData_" + DateUtils.getCurFormatDate("YYYY-MM-dd")+".sql";
            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = new FileOutputStream(
            		backName);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
            writer.write(outStr);
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
            writer.flush();

            // 别忘记关闭输入输出流
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();

            logger.debug("end to backup database data!");

        } catch (Exception e) {
            logger.error(e);
        }catch(Throwable t){
        	logger.error(t);
        }

    }

    /**
     * 导入
     *
     */
    public static void loadstruct() {
        try {
        	logger.debug("begin to load database struct!");
            String fPath = "d:/temp/testdbTable.sql";
            Runtime rt = Runtime.getRuntime();

            // 调用 mysql 的 cmd:
            Process child = rt.exec("E:/mysql-5.0.81-win32/bin/mysql -u root  testdb1 ");
            OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fPath), "utf8"));
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
            writer.flush();
            // 别忘记关闭输入输出流
            out.close();
            br.close();
            writer.close();

            logger.debug("end to backup database struct!");

        } catch (Exception e) {
            logger.error(e);
        }catch(Throwable t){
        	logger.error(t);
        }

    }
    
    public static void loadsData() {
        try {
        	logger.debug("begin to load database data!");
            String fPath = "d:/temp/testdbData.sql";
            Runtime rt = Runtime.getRuntime();

            // 调用 mysql 的 cmd:
            Process child = rt.exec("E:/mysql-5.0.81-win32/bin/mysql -u root  testdb1 ");
            OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fPath), "utf8"));
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            OutputStreamWriter writer = new OutputStreamWriter(out, "gb2312");
            writer.write(outStr);
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
            writer.flush();
            // 别忘记关闭输入输出流
            out.close();
            br.close();
            writer.close();

            logger.debug("end to load database data!");

        } catch (Exception e) {
            logger.error(e);
        }catch(Throwable t){
        	logger.error(t);
        }

    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
         * 备份和导入是一个互逆的过程。
         * 备份：程序调用mysql的备份命令，读出控制台输入流信息，写入.sql文件；
         * 导入：程序调用mysql的导入命令，把从.sql文件中读出的信息写入控制台的输出流
         * 注意：此时定向符">"和"<"是不能用的
         */
    	backupstruct();
    	backupdata();
        //loadstruct();
        //loadsData();
    }


}
