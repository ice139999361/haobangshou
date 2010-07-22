package com.hbs.common.action.base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hbs.common.authfilter.User;
import com.hbs.common.josn.annotations.JSON;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.pojo.Staff;
import com.hbs.domain.common.pojo.base.BaseDomain;
import com.opensymphony.xwork2.ActionSupport;

/**
 * �Ż���action����.
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	private static final Logger logger = Logger.getLogger(BaseAction.class);

	/**
	 * error msg.
	 */
	private static final String ERROR_MSG = "msg";

	/**
	 * error stack.
	 */
	private static final String ERROR_STACK = "errorStack";

	/**
	 * ȱʡҳ����.
	 */
	private static final int DEFAULT_PAGE_LIMIT = 10;

	/**
	 * �ͻ��������������ʶ��admin��sp�Ż�������.
	 */
	private String domain;

	/**
	 * actionִ�н����true|flase��.
	 */
	private boolean success = true;

	/**
	 * actionִ�н����json������ؿͻ��ˣ�.
	 */
	@SuppressWarnings("unchecked")
	private Map result = null;

	/**
	 * actionִ�н��ԭ��json������ؿͻ��ˣ�.
	 */
	private Map<String,String> errorReason = new HashMap<String,String>();

	/**
	 * ��ʼ�к�.
	 */
	private int start = 0;

	/**
	 * ����.
	 */
	private int limit = DEFAULT_PAGE_LIMIT;

	/**
	 * ����������.
	 */
	private int totalCount = 0;

	/**
	 * �����ֶΣ���sp.spCode��.
	 */
	private String sort;

	/**
	 * ����|������ASC��.
	 */
	private String dir;

	/**
	 * ����excel�ļ���.
	 */
	private String exportExcelName;

	public String getExportExcelName() {
		return exportExcelName;
	}

	public void setExportExcelName(String exportExcelName) {
		this.exportExcelName = exportExcelName;
	}

    /**
	 * �õ���Ӧ�ӿ�.
	 * 
	 * @param key
	 *            �ӿ�����
	 * @return �ӿ�
	 */
	public final Object getBean(String key) {
		return BeanLocator.getInstance().getBean(key);
	}

	/**
	 * �õ�http session.
	 * 
	 * @return http session
	 */
	public final HttpSession getSession() {
		return getHttpServletRequest().getSession(true);
	}

	/**
	 * �õ�http request.
	 * 
	 * @return http request
	 */
	public final HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request;
	}
	
    /**
     * �õ�http session.
     * 
     * @return http request
     */
    public final HttpSession getHttpSession() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request.getSession();
    }

	/**
	 * �õ�http response.
	 * 
	 * @return http response
	 */
	public final HttpServletResponse getHttpServletResponse() {
	    HttpServletResponse response = ServletActionContext.getResponse();
        return response;
	}

	@SuppressWarnings("unchecked")
	@JSON(name = "data")
	public Map getErrorReason() {
		return errorReason;
	}

	@SuppressWarnings("unchecked")
	public void setErrorReason(Map errorReason) {
		this.errorReason = errorReason;
	}

	/**
	 * ����ʧ��ԭ��.
	 * 
	 * @param errorMsg
	 *            ʧ��ԭ��
	 */
	@SuppressWarnings("unchecked")
	public void setErrorReason(String errorMsg) {
		if (errorReason == null) {
			errorReason = new HashMap();
		}

		setSuccess(false);
		this.errorReason.put(ERROR_MSG, errorMsg);
		this.errorReason.put(ERROR_STACK, "");
	}

	/**
	 * ����ʧ��ԭ��.
	 * 
	 * @param errorMsg
	 *            ʧ��ԭ��
	 * @param e
	 *            �쳣
	 */
	public void setErrorReason(String errorMsg, Exception e) {
		if (errorReason == null) {
			errorReason = new HashMap<String,String>();
		}

		setSuccess(false);
		this.errorReason.put(ERROR_MSG, errorMsg);
		this.errorReason.put(ERROR_STACK, generateStackTrace(e));
	}

	/**
	 * ������ʾ��Ϣ.
	 * 
	 * @param alertMsg
	 *            ��ʾ��Ϣ
	 */
	public void setAlertMsg(String alertMsg) {
		this.setResult(ERROR_MSG, alertMsg);
	}

	/**
	 * ���÷�ҳ����.
	 * 
	 * @param domain
	 *            ����DO����
	 */
	public void setPagination(BaseDomain domain) {
		// ���÷�ҳ����: start,limit,sort,order
		domain.setStart(getStart());
		//domain.setEnd(getStart() + getLimit());
		domain.setEnd(getLimit());
		domain.setSort(getSort());
		domain.setOrder(getDir());
	}

	/**
	 * �����쳣��ջ�ַ���.
	 * 
	 * @param e
	 *            �쳣
	 * @return �쳣��ջ�ַ���
	 */
	private String generateStackTrace(Exception e) {
		if (e == null) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(byteArrayOutputStream));
			stringBuffer.append(byteArrayOutputStream.toString());
		} catch (Exception ex) {
		} finally {
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException ex2) {
				}
			}
		}
		return stringBuffer.toString();
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	@JSON(name = "totalCount")
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;		
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@SuppressWarnings("unchecked")
	@JSON(name = "data")
	public Map getResult() {
		return result;
	}

	@SuppressWarnings("unchecked")
	public void setResult(Map result) {
		this.result = result;
	}

	/**
	 * ��������ӹ�.
	 * 
	 * @param key
	 *            ��
	 * @param value
	 *            ֵ
	 */
	@SuppressWarnings("unchecked")
	public void setResult(Object key, Object value) {
		if (result == null) {
			result = new HashMap();
		}
		result.put(key, value);
	}

    public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * ��ȡ�û���¼��Ϣ
	 * @return �û���¼��Ϣ
	 * @throws Exception
	 */
    protected Staff getLoginStaff() throws Exception {
    	// DONE:getLoginStaff
    	User u = null;
    	try {
			u = (User)getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error("catch Exception in getLoginStaff:", e);
		}
    	if(u != null)
    		return new Staff(u.getStaffId(), u.getStarffName());
    	else{
    		logger.error("�޷���ȡSession�е��û���Ϣ��");
    		//return new Staff(1, "user1");
    		return null;
    	}
        //return (Staff) LoginHelper.getLoginStaff(domain);
    }
    /**
     * ��ȡ��¼�û��Ľ�ɫ��Ϣ
     * @return
     */
    protected List<String> getLoginStaffRole() {
		List<String> roleList = null;

		User u = (User) getSession().getAttribute("user");
		if (null != u) {
			roleList = u.getRoleList();
		}

		return roleList;
	}
}
