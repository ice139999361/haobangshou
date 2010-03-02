package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.Action;
import com.hbs.domain.auth.dao.ActionDao;

/**
 * ActionDao接口实现类.
 * @author hbs
 *
 */
public class ActionDaoImpl extends SqlMapClientDaoSupport implements ActionDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(ActionDaoImpl.class);

    
    
    /**
     * insert.
     * @param action action
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public Integer insertAction(Action action) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertAction(Action), 输入参数[" + action + "]");
    	}
        
    	Integer id =(Integer)getSqlMapClientTemplate().insert("Action_insertAction", action);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertAction(Action), 返回[" + id + "]");
		}
    	return id;
    }

    /**
     * delete.
     * @param action action
     * @throws DataAccessException DataAccessException
     */
    public void deleteAction(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteAction(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Action_deleteAction", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteAction(String)");
		}
    }
    
    /**
     * update.
     * @param action action
     * @throws DataAccessException DataAccessException
     */
    public void updateAction(Action action) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateAction(Action), 输入参数[" + action + "]");
		}
    	getSqlMapClientTemplate().update("Action_updateAction", action);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateAction(Action)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return action
     * @throws DataAccessException DataAccessException
     */
    public Action findAction(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findAction(Action), 输入参数[" + pk + "]");
		}
        Action action = (Action) getSqlMapClientTemplate().queryForObject("Action_findAction", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findAction(Action), 返回[" + action + "]");
		}
        return action;
    }
    
    /**
     * list.
     * @param action action
     * @return action list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<Action> listAction(Action action) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listAction(Action), 输入参数[" + action + "]");
		}
        List<Action> list = getSqlMapClientTemplate().queryForList("Action_listAction", action);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listAction(Action), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param action action
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listActionCount(Action action) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listActionCount(Action), 输入参数[" + action + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("Action_listActionCount", action);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listActionCount(Action), 返回[" + count + "]");
		}
        return count;
    }  
}
