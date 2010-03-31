package com.hbs.common.action.configencode;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.common.exception.BusinessException;
import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;



/**
 * 对应界面上的下拉列表框初始化
 * @author yangzj
 *
 */

@SuppressWarnings("serial")
public class EncodeDictAction extends BaseAction {
    
    private static final Logger log = Logger.getLogger(EncodeDictAction.class);

   // ConfigEncodeMgr
    
    private ConfigEncode encodeDict;

    /**
     * @return 返回 encodeDict。
     */
    public ConfigEncode getEncodeDict() {
        return encodeDict;
    }

    /**
     * @param encodeDict 要设置的 encodeDict。
     */
    public void setEncodeDict(ConfigEncode encodeDict) {
        this.encodeDict = encodeDict;
    }
    
    /**
     * 查询字典列表，以EncodeType来查询
     * @return
     */
    public String doList(){
        try{
        	if(encodeDict == null || StringUtils.isEmpty(encodeDict.getEncodeType())){
                setErrorReason("请求参数为空");
                return ERROR;
            }
            List<ConfigEncode> encodeDicts = ConfigEncodeMgr.getListConfigEncode(encodeDict);
            setResult("encodeDict", encodeDicts);
        }
        catch(BusinessException e){
            setErrorReason("获取字典失败,请稍后再试!");
            log.error("获取字典失败!" , e);
            return ERROR;
        }
        catch(Exception e){
            log.error("字典列表查询异常" , e);
            setErrorReason("字典列表查询异常，请稍后再试!");
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * 查询字典表详情，以EncodeType 和 EncodeKey 为条件
     * @return
     */
    public String doDetail(){
        try{
            if(encodeDict == null || StringUtils.isEmpty(encodeDict.getEncodeType()) ||
                    StringUtils.isEmpty(encodeDict.getEncodeKey())){
                setErrorReason("请求参数为空");
                return ERROR;
            }
            
            ConfigEncode ed = ConfigEncodeMgr.getConfigEncode(encodeDict);
            setResult("encodeDict", ed);
        }
        catch(BusinessException e){
        	setErrorReason("获取字典失败,请稍后再试!");
            log.error("获取字典失败!" , e);
            return ERROR;
        }
        catch(Exception e){
            log.error("字典详情查询异常" , e);
            setErrorReason("字典详情查询异常，请稍后再试!");
            return ERROR;
        }
        return SUCCESS;
    }
}
