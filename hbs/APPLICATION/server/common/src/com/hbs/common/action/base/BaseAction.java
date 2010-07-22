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
 * 门户的action基类.
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
	 * 缺省页行数.
	 */
	private static final int DEFAULT_PAGE_LIMIT = 10;

	/**
	 * 客户端请求参数（标识是admin、sp门户的请求）.
	 */
	private String domain;

	/**
	 * action执行结果（true|flase）.
	 */
	private boolean success = true;

	/**
	 * action执行结果（json插件返回客户端）.
	 */
	@SuppressWarnings("unchecked")
	private Map result = null;

	/**
	 * action执行结果原因（json插件返回客户端）.
	 */
	private Map<String,String> errorReason = new HashMap<String,String>();

	/**
	 * 起始行号.
	 */
	private int start = 0;

	/**
	 * 行数.
	 */
	private int limit = DEFAULT_PAGE_LIMIT;

	/**
	 * 返回总行数.
	 */
	private int totalCount = 0;

	/**
	 * 排序字段（例sp.spCode）.
	 */
	private String sort;

	/**
	 * 正序|反序（例ASC）.
	 */
	private String dir;

	/**
	 * 导出excel文件名.
	 */
	private String exportExcelName;

	public String getExportExcelName() {
		return exportExcelName;
	}

	public void setExportExcelName(String exportExcelName) {
		this.exportExcelName = exportExcelName;
	}

    /**
	 * 得到相应接口.
	 * 
	 * @param key
	 *            接口名称
	 * @return 接口
	 */
	public final Object getBean(String key) {
		return BeanLocator.getInstance().getBean(key);
	}

	/**
	 * 得到http session.
	 * 
	 * @return http session
	 */
	public final HttpSession getSession() {
		return getHttpServletRequest().getSession(true);
	}

	/**
	 * 得到http request.
	 * 
	 * @return http request
	 */
	public final HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request;
	}
	
    /**
     * 得到http session.
     * 
     * @return http request
     */
    public final HttpSession getHttpSession() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request.getSession();
    }

	/**
	 * 得到http response.
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
	 * 设置失败原因.
	 * 
	 * @param errorMsg
	 *            失败原因
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
	 * 设置失败原因.
	 * 
	 * @param errorMsg
	 *            失败原因
	 * @param e
	 *            异常
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
	 * 设置提示信息.
	 * 
	 * @param alertMsg
	 *            提示信息
	 */
	public void setAlertMsg(String alertMsg) {
		this.setResult(ERROR_MSG, alertMsg);
	}

	/**
	 * 设置分页属性.
	 * 
	 * @param domain
	 *            输入DO对象
	 */
	public void setPagination(BaseDomain domain) {
		// 设置分页属性: start,limit,sort,order
		domain.setStart(getStart());
		//domain.setEnd(getStart() + getLimit());
		domain.setEnd(getLimit());
		domain.setSort(getSort());
		domain.setOrder(getDir());
	}

	/**
	 * 生成异常堆栈字符串.
	 * 
	 * @param e
	 *            异常
	 * @return 异常堆栈字符串
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
	 * 设置输出接果.
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
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
	 * 获取用户登录信息
	 * @return 用户登录信息
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
    		logger.error("无法获取Session中的用户信息！");
    		//return new Staff(1, "user1");
    		return null;
    	}
        //return (Staff) LoginHelper.getLoginStaff(domain);
    }
    /**
     * 获取登录用户的角色信息
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
