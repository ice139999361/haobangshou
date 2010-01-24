package com.hbs.common.configuration;

import java.io.IOException;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

/**
 * URL�ļ�ˢ�²�����.
 * 
 
 * 
 */
public class RemoteFileChangedReloadingStrategy extends
        FileChangedReloadingStrategy {
    /**
     * logger.
     */
    private static final Logger logger = Logger
                            .getLogger(RemoteFileChangedReloadingStrategy.class);

    /**
     * ����޸�ʱ��.
     */
    private String lastModifiedDate = null;

    /**
     * Check if the configuration has changed since the last time it was loaded.
     * 
     * @return a flag whether the configuration has changed
     */
    protected boolean hasChanged() {
        if (lastModifiedDate == null) {
            return true;
        }

        String tmpLastModifiedDate = getLastModifiedDate();
        if (tmpLastModifiedDate != null) {
            return !tmpLastModifiedDate.equals(lastModifiedDate);
        }

        return false;
    }

    /**
     * Update the last modified time.
     */
    protected void updateLastModified() {
        String tmpLastModifiedDate = getLastModifiedDate();
        if (tmpLastModifiedDate != null) {
            lastModifiedDate = tmpLastModifiedDate;
        }
    }

    /**
     * Get the last modified time.
     * @return ����޸�ʱ��
     */
    protected String getLastModifiedDate() {
        HttpClient httpclient = new HttpClient();
        GetMethod getMethod = new GetMethod(configuration.getURL().toString());
        try {
            httpclient.executeMethod(getMethod);
            Header lastModifiedHeader = getMethod
                    .getResponseHeader("Last-Modified");
            if (lastModifiedHeader != null) {
                return lastModifiedHeader.getValue();
            }
        } catch (HttpException e) {
            logger.error("��URLʧ�ܣ�URL��" + configuration.getURL().toString());
        } catch (IOException e) {
            logger.error("��URLʧ�ܣ�" + configuration.getURL().toString());
        } finally {
            getMethod.releaseConnection();            
        }
        return null;
    }

}
