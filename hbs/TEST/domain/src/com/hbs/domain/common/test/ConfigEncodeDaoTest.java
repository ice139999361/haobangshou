package com.hbs.domain.common.test;

import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.dao.ConfigEncodeDao;
import com.hbs.domain.common.pojo.ConfigEncode;

/**
 * test ConfigEncodeDao.
 * @author sims autoCoder1.0
 *
 */
public class ConfigEncodeDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-ConfigEncode.xml)
    	return new String[] { "classpath:/applicationContext.xml", "classpath:/applicationContext.xml" }; 
    } 
    
 

    /**
     * test find.
     */
    public void testFindConfigEncode() {
//        ConfigEncodeDao configEncodeDao = (ConfigEncodeDao) this.getApplicationContext().getBean("configEncodeDao");
        ConfigEncode configEncode1 = new ConfigEncode();
        configEncode1.setEncodeType("WAREHOUSE_TYPE");
        configEncode1.setEncodeKey("1");
        ConfigEncode configEncode = ConfigEncodeMgr.getConfigEncode(configEncode1);
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("encodeType=" + configEncode.getEncodeType());
        System.out.println("encodeKey=" + configEncode.getEncodeKey());
        System.out.println("encodeValue=" + configEncode.getEncodeValue());
        System.out.println("encodeDesc=" + configEncode.getEncodeDesc());
        System.out.println("isValid=" + configEncode.getIsValid());
        System.out.println("sortId=" + configEncode.getSortId());
    }

    /**
     * test list.
     */
//    public void testListConfigEncode() {
//        ConfigEncodeDao configEncodeDao = (ConfigEncodeDao) this.getApplicationContext().getBean("configEncodeDao");
//        ConfigEncode configEncode = new ConfigEncode();
//        configEncode.setEncodeType("yangzj");
//       
//
//        System.out.println("testList result--------------------------------------");
//        List list =  configEncodeDao.listConfigEncode(configEncode);
//        for (int i = 0; i < list.size(); i++) {
//            ConfigEncode tmpConfigEncode = (ConfigEncode) list.get(i);
//            System.out.println(i + " encodeType=" + tmpConfigEncode.getEncodeType());
//            System.out.println(i + " encodeKey=" + tmpConfigEncode.getEncodeKey());
//            System.out.println(i + " encodeValue=" + tmpConfigEncode.getEncodeValue());
//            System.out.println(i + " encodeDesc=" + tmpConfigEncode.getEncodeDesc());
//            System.out.println(i + " isValid=" + tmpConfigEncode.getIsValid());
//            System.out.println(i + " sortId=" + tmpConfigEncode.getSortId());
//        }
//
//    }
    
  
}
