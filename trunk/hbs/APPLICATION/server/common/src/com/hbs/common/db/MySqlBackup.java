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
     * ���ݼ���һ��sql�ļ��Ƿ�����������ļ��õ�һ���жϷ������Ѹ�sql�ļ��ֱ��ü��±���ultra
     * edit�򿪣�������������ľ�����û�����룬����������������Դ�ļ�������sql�ļ��ı����ʽ��Σ�Ҳ����db�ı����ʽ��Σ�
     */
    public static void backupstruct() {
        try {
        	logger.debug("begin to backup database struct!");
            Runtime rt = Runtime.getRuntime();

            // ���� mysql �� cmd:192.168.1.61
            Process child = rt.exec("E:/mysql-5.0.81-win32/bin/mysqldump.exe -h 192.168.1.61 -u yangzj -pyangzj  --no-data --set-charset=utf8 testdb");
            // ���õ�������Ϊutf8�����������utf8
           
            // �ѽ���ִ���еĿ���̨�����Ϣд��.sql�ļ����������˱����ļ���ע��������Կ���̨��Ϣ���ж�������ᵼ�½��̶����޷�����
            InputStream in = child.getInputStream();// ����̨�������Ϣ��Ϊ������
                       
            InputStreamReader xx = new InputStreamReader(in, "utf8");
           
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // ��Ͽ���̨�����Ϣ�ַ���
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
           
            String backName = "d:/temp/testdbTable_" + DateUtils.getCurFormatDate("YYYY-MM-dd")+".sql";
            // Ҫ�����������õ�sqlĿ���ļ���
            FileOutputStream fout = new FileOutputStream(
                    backName);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
            writer.write(outStr);
            // ע����������û��巽ʽд���ļ��Ļ����ᵼ���������룬��flush()��������Ա���
            writer.flush();

            // �����ǹر����������
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

            // ���� mysql �� cmd:192.168.1.61
            Process child = rt.exec("E:/mysql-5.0.81-win32/bin/mysqldump.exe -h 192.168.1.61 -u yangzj -pyangzj  --no-create-info --set-charset=gb2312 testdb");
            // ���õ�������Ϊutf8�����������utf8
           
            // �ѽ���ִ���еĿ���̨�����Ϣд��.sql�ļ����������˱����ļ���ע��������Կ���̨��Ϣ���ж�������ᵼ�½��̶����޷�����
            InputStream in = child.getInputStream();// ����̨�������Ϣ��Ϊ������
                       
            InputStreamReader xx = new InputStreamReader(in, "gb2312");// �������������Ϊutf8�����������utf8����������ж����������
           
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // ��Ͽ���̨�����Ϣ�ַ���
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
            String backName = "d:/temp/testdbData_" + DateUtils.getCurFormatDate("YYYY-MM-dd")+".sql";
            // Ҫ�����������õ�sqlĿ���ļ���
            FileOutputStream fout = new FileOutputStream(
            		backName);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
            writer.write(outStr);
            // ע����������û��巽ʽд���ļ��Ļ����ᵼ���������룬��flush()��������Ա���
            writer.flush();

            // �����ǹر����������
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
     * ����
     *
     */
    public static void loadstruct() {
        try {
        	logger.debug("begin to load database struct!");
            String fPath = "d:/temp/testdbTable.sql";
            Runtime rt = Runtime.getRuntime();

            // ���� mysql �� cmd:
            Process child = rt.exec("E:/mysql-5.0.81-win32/bin/mysql -u root  testdb1 ");
            OutputStream out = child.getOutputStream();//����̨��������Ϣ��Ϊ�����
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
            // ע����������û��巽ʽд���ļ��Ļ����ᵼ���������룬��flush()��������Ա���
            writer.flush();
            // �����ǹر����������
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

            // ���� mysql �� cmd:
            Process child = rt.exec("E:/mysql-5.0.81-win32/bin/mysql -u root  testdb1 ");
            OutputStream out = child.getOutputStream();//����̨��������Ϣ��Ϊ�����
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
            // ע����������û��巽ʽд���ļ��Ļ����ᵼ���������룬��flush()��������Ա���
            writer.flush();
            // �����ǹر����������
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
         * ���ݺ͵�����һ������Ĺ��̡�
         * ���ݣ��������mysql�ı��������������̨��������Ϣ��д��.sql�ļ���
         * ���룺�������mysql�ĵ�������Ѵ�.sql�ļ��ж�������Ϣд�����̨�������
         * ע�⣺��ʱ�����">"��"<"�ǲ����õ�
         */
    	backupstruct();
    	backupdata();
        //loadstruct();
        //loadsData();
    }


}
