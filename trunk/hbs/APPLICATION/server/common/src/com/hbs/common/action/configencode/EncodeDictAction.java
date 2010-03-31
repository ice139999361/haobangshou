package com.hbs.common.action.configencode;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.exception.BusinessException;
import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;



/**
 * ��Ӧ�����ϵ������б���ʼ��
 * @author yangzj
 *
 */

@SuppressWarnings("serial")
public class EncodeDictAction extends BaseAction {
    
    private static final Logger log = Logger.getLogger(EncodeDictAction.class);

   // ConfigEncodeMgr
    
    private ConfigEncode encodeDict;

    /**
     * @return ���� encodeDict��
     */
    public ConfigEncode getEncodeDict() {
        return encodeDict;
    }

    /**
     * @param encodeDict Ҫ���õ� encodeDict��
     */
    public void setEncodeDict(ConfigEncode encodeDict) {
        this.encodeDict = encodeDict;
    }
    
    /**
     * ��ѯ�ֵ��б���EncodeType����ѯ
     * @return
     */
    public String doList(){
        try{
        	if(encodeDict == null || StringUtils.isEmpty(encodeDict.getEncodeType())){
                setErrorReason("�������Ϊ��");
                return ERROR;
            }
            List<ConfigEncode> encodeDicts = ConfigEncodeMgr.getListConfigEncode(encodeDict);
            setResult("encodeDict", encodeDicts);
        }
        catch(BusinessException e){
            setErrorReason("��ȡ�ֵ�ʧ��,���Ժ�����!");
            log.error("��ȡ�ֵ�ʧ��!" , e);
            return ERROR;
        }
        catch(Exception e){
            log.error("�ֵ��б��ѯ�쳣" , e);
            setErrorReason("�ֵ��б��ѯ�쳣�����Ժ�����!");
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * ��ѯ�ֵ�����飬��EncodeType �� EncodeKey Ϊ����
     * @return
     */
    public String doDetail(){
        try{
            if(encodeDict == null || StringUtils.isEmpty(encodeDict.getEncodeType()) ||
                    StringUtils.isEmpty(encodeDict.getEncodeKey())){
                setErrorReason("�������Ϊ��");
                return ERROR;
            }
            
            ConfigEncode ed = ConfigEncodeMgr.getConfigEncode(encodeDict);
            setResult("encodeDict", ed);
        }
        catch(BusinessException e){
        	setErrorReason("��ȡ�ֵ�ʧ��,���Ժ�����!");
            log.error("��ȡ�ֵ�ʧ��!" , e);
            return ERROR;
        }
        catch(Exception e){
            log.error("�ֵ������ѯ�쳣" , e);
            setErrorReason("�ֵ������ѯ�쳣�����Ժ�����!");
            return ERROR;
        }
        return SUCCESS;
    }
}
