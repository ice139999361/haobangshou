package com.hbs.common.springhelper;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.springframework.core.io.Resource;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * ����ibatis������.
 * ��չ��spring�ļ����֧࣬�ֶ�ibatis�����ļ�����. 
 *
 */
public class SqlMapClientFactoryBean extends
        org.springframework.orm.ibatis.SqlMapClientFactoryBean {
    
    /**
     * ����ibatis�����ļ�.
     * ֧�ּ��ض��sqlMapConfig�ļ������㰴ģ������sqlMapConfig.xml��
     * @param configLocation �����ļ���Դ
     * @param properties ���Լ� 
     * @return SqlMapClient 
     * @throws IOException  io�쳣
     */
    protected SqlMapClient buildSqlMapClient(Resource configLocation, Properties properties) throws IOException {
        /// �õ�ibatis��sqlMapConfig·��
        String path = configLocation.getFilename();
        if (path == null || path.length() == 0) {
            throw new IOException("ibatis sqlMapConfig file not config");
        }
        
        /// ���sqlMapConfig�ļ�ͨ��","�ŷָ�(ÿ����ϵͳһ��)
        String[] sqlMapConfigFiles = path.split(",");
        int size = sqlMapConfigFiles.length;
        
        // �����sqlMapConfig�ļ�ƴ��һ��DOM
        Document totalDocument = null;
        try {
            totalDocument = getDocument(Resources.getResourceAsReader(sqlMapConfigFiles[0]));
        } catch (JDOMException e) {
            throw new IOException("parse ibatis sqlMapConfig file failed, file:" 
                    + sqlMapConfigFiles[0] + "JDOMException:" + e.getMessage());
        } catch (IOException e) {
            throw new IOException("read ibatis sqlMapConfig file failed, file:" 
                    + sqlMapConfigFiles[0] + "JDOMException:" + e.getMessage());
        }
        
        for (int i = 1; i < size; i++) {
            String sqlMapConfigFile = sqlMapConfigFiles[i];
            Reader subReader = Resources.getResourceAsReader(sqlMapConfigFile.trim());
            try {
                Document subDocument = getDocument(subReader);
                addDocument(totalDocument, subDocument);
            } catch (JDOMException e) {
                throw new IOException("parse ibatis sqlMapConfig file failed, file:" + sqlMapConfigFile 
                                       + "JDOMException:" + e.getMessage());
            } catch (IOException e) {
                throw new IOException("read ibatis sqlMapConfig file failed, file:" + sqlMapConfigFile
                                       + "IOException:" + e.getMessage());
            }
        }
        
        // ��ȫ����DOMת��������
        XMLOutputter xmlOutputter = new XMLOutputter();
        StringWriter tmpStringWriter = new StringWriter();
        xmlOutputter.output(totalDocument, tmpStringWriter);
        StringReader totalStringReader = new StringReader(tmpStringWriter.toString());
        return SqlMapClientBuilder.buildSqlMapClient(totalStringReader, properties);
    }
    
    /**
     * ƴװDOM.
     * @param base ȫ��DOM
     * @param sub ��ϵͳDOM
     */
    @SuppressWarnings("unchecked")
	private void addDocument(Document base ,Document sub){
        List list = sub.getRootElement().getChildren();
        int size = list.size();
        for (int i = 0; i < size; i++) {
          Element sqlMap = (Element) list.get(0);
          base.getRootElement().addContent(sqlMap.detach());
        }
    }

    /**
     * ��xml�ļ�ת��DOM.
     * @param reader xml�ļ�
     * @return DOM
     * @throws IOException IO�쳣
     * @throws JDOMException JDOM�쳣
     */
    private Document  getDocument(Reader reader) throws IOException, JDOMException{
        SAXBuilder builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser",false);
        builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        Document sqlmapDoc = builder.build(reader);
        return sqlmapDoc;
    }

}
