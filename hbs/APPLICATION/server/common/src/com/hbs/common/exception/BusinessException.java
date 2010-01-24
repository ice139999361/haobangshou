package com.hbs.common.exception;

/**
 * ҵ���߼��쳣.
 * @author yangzj
 *
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
    /**
     * ����.
     * @param msg ������Ϣ.
     */
    public BusinessException(String msg) {
        super(msg);
    }
    
    /**
     * ����.
     * @param msg ������Ϣ
     * @param t ǰһ�쳣
     */
    public BusinessException(String msg, Throwable t) {
        super(msg, t);
        
        this.setStackTrace(t.getStackTrace());
    }
}
