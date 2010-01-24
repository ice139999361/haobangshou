package com.hbs.domain.customer.customerinfo.test.dao;

import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.common.dao.baseinfo.ContactInfoDao;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;

/**
 * test ContactInfoDao.
 * @author hbs
 *
 */
public class ContactInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-ContactInfo.xml)
        return new String[] { "classpath:/applicationContext-test-contactInfo.xml", "classpath:/applicationContext-test-contactInfo.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertContactInfo() {
        ContactInfoDao contactInfoDao = (ContactInfoDao)this.getApplicationContext().getBean("contactInfoDao");
        ContactInfo contactInfo = new ContactInfo();
       // contactInfo.setSEQID(1);
        contactInfo.setCommCode("1");
        contactInfo.setState("1");
        contactInfo.setConType("1");
        contactInfo.setConName("1");
        contactInfo.setConDuty("1");
        contactInfo.setConTel("1");
        contactInfo.setConMobile("1");
        contactInfo.setConFax("1");
        contactInfo.setConMail("1");
        contactInfo.setConQq("1");
        contactInfo.setConMsn("1");
        contactInfo.setConOther("1");
        contactInfo.setConAddress("1");
        contactInfo.setConZip("1");
        contactInfo.setIsPrimary("1");
       // String id = contactInfoDao.insertContactInfo(contactInfo);
        System.out.println("testInsert result--------------------------------------");
    //    System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteContactInfo() {
        ContactInfoDao contactInfoDao = (ContactInfoDao) this.getApplicationContext().getBean("contactInfoDao");
        ContactInfo contactInfo = new ContactInfo();
       // contactInfoDao.deleteContactInfo("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateContactInfo() {
        ContactInfoDao contactInfoDao = (ContactInfoDao) this.getApplicationContext().getBean("contactInfoDao");
        ContactInfo contactInfo = new ContactInfo();
       // contactInfo.setSEQID(2);
        contactInfo.setCommCode("1");
        contactInfo.setState("1");
        contactInfo.setConType("1");
        contactInfo.setConName("1");
        contactInfo.setConDuty("1");
        contactInfo.setConTel("1");
        contactInfo.setConMobile("1");
        contactInfo.setConFax("1");
        contactInfo.setConMail("1");
        contactInfo.setConQq("1");
        contactInfo.setConMsn("1");
        contactInfo.setConOther("1");
        contactInfo.setConAddress("1");
        contactInfo.setConZip("1");
        contactInfo.setIsPrimary("1");
        contactInfoDao.updateContactInfo(contactInfo);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindContactInfo() {
        ContactInfoDao contactInfoDao = (ContactInfoDao) this.getApplicationContext().getBean("contactInfoDao");
        ContactInfo contactInfo = contactInfoDao.findContactInfo("1");
        
        System.out.println("testFind result--------------------------------------");
       // System.out.println("SEQID=" + contactInfo.getSEQID());
        System.out.println("commCode=" + contactInfo.getCommCode());
        System.out.println("state=" + contactInfo.getState());
        System.out.println("conType=" + contactInfo.getConType());
        System.out.println("conName=" + contactInfo.getConName());
        System.out.println("conDuty=" + contactInfo.getConDuty());
        System.out.println("conTel=" + contactInfo.getConTel());
        System.out.println("conMobile=" + contactInfo.getConMobile());
        System.out.println("conFax=" + contactInfo.getConFax());
        System.out.println("conMail=" + contactInfo.getConMail());
        System.out.println("conQq=" + contactInfo.getConQq());
        System.out.println("conMsn=" + contactInfo.getConMsn());
        System.out.println("conOther=" + contactInfo.getConOther());
        System.out.println("conAddress=" + contactInfo.getConAddress());
        System.out.println("conZip=" + contactInfo.getConZip());
        System.out.println("isPrimary=" + contactInfo.getIsPrimary());
    }

    /**
     * test list.
     */
    public void testListContactInfo() {
        ContactInfoDao contactInfoDao = (ContactInfoDao) this.getApplicationContext().getBean("contactInfoDao");
        ContactInfo contactInfo = new ContactInfo();
       // contactInfo.setSEQID(2);
        contactInfo.setCommCode("1");
        contactInfo.setState("1");
        contactInfo.setConType("1");
        contactInfo.setConName("1");
        contactInfo.setConDuty("1");
        contactInfo.setConTel("1");
        contactInfo.setConMobile("1");
        contactInfo.setConFax("1");
        contactInfo.setConMail("1");
        contactInfo.setConQq("1");
        contactInfo.setConMsn("1");
        contactInfo.setConOther("1");
        contactInfo.setConAddress("1");
        contactInfo.setConZip("1");
        contactInfo.setIsPrimary("1");

        System.out.println("testList result--------------------------------------");
        List list =  contactInfoDao.listContactInfo(contactInfo);
        for (int i = 0; i < list.size(); i++) {
            ContactInfo tmpContactInfo = (ContactInfo) list.get(i);
      //      System.out.println(i + " SEQID=" + tmpContactInfo.getSEQID());
            System.out.println(i + " commCode=" + tmpContactInfo.getCommCode());
            System.out.println(i + " state=" + tmpContactInfo.getState());
            System.out.println(i + " conType=" + tmpContactInfo.getConType());
            System.out.println(i + " conName=" + tmpContactInfo.getConName());
            System.out.println(i + " conDuty=" + tmpContactInfo.getConDuty());
            System.out.println(i + " conTel=" + tmpContactInfo.getConTel());
            System.out.println(i + " conMobile=" + tmpContactInfo.getConMobile());
            System.out.println(i + " conFax=" + tmpContactInfo.getConFax());
            System.out.println(i + " conMail=" + tmpContactInfo.getConMail());
            System.out.println(i + " conQq=" + tmpContactInfo.getConQq());
            System.out.println(i + " conMsn=" + tmpContactInfo.getConMsn());
            System.out.println(i + " conOther=" + tmpContactInfo.getConOther());
            System.out.println(i + " conAddress=" + tmpContactInfo.getConAddress());
            System.out.println(i + " conZip=" + tmpContactInfo.getConZip());
            System.out.println(i + " isPrimary=" + tmpContactInfo.getIsPrimary());
        }

    }
    
    /**
     * test list.
     */
    public void testListContactInfoCount() {
        ContactInfoDao contactInfoDao = (ContactInfoDao) this.getApplicationContext().getBean("contactInfoDao");
        ContactInfo contactInfo = new ContactInfo();
     //   contactInfo.setSEQID(2);
        contactInfo.setCommCode("1");
        contactInfo.setState("1");
        contactInfo.setConType("1");
        contactInfo.setConName("1");
        contactInfo.setConDuty("1");
        contactInfo.setConTel("1");
        contactInfo.setConMobile("1");
        contactInfo.setConFax("1");
        contactInfo.setConMail("1");
        contactInfo.setConQq("1");
        contactInfo.setConMsn("1");
        contactInfo.setConOther("1");
        contactInfo.setConAddress("1");
        contactInfo.setConZip("1");
        contactInfo.setIsPrimary("1");

        System.out.println("testListCount result--------------------------------------");
//        Integer count =  contactInfoDao.listContactInfoCount(contactInfo);
//		System.out.println("count=" + count);
    }
}
