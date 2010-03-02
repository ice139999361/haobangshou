package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.Action;
import com.hbs.domain.auth.dao.ActionDao;

/**
 * ActionDao�ӿ�ʵ����.
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
    		logger.debug("����insertAction(Action), �������[" + action + "]");
    	}
        
    	Integer id =(Integer)getSqlMapClientTemplate().insert("Action_insertAction", action);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertAction(Action), ����[" + id + "]");
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
    		logger.debug("����deleteAction(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Action_deleteAction", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteAction(String)");
		}
    }
    
    /**
     * update.
     * @param action action
     * @throws DataAccessException DataAccessException
     */
    public void updateAction(Action action) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateAction(Action), �������[" + action + "]");
		}
    	getSqlMapClientTemplate().update("Action_updateAction", action);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateAction(Action)");
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
        	logger.debug("����findAction(Action), �������[" + pk + "]");
		}
        Action action = (Action) getSqlMapClientTemplate().queryForObject("Action_findAction", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findAction(Action), ����[" + action + "]");
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
        	logger.debug("����listAction(Action), �������[" + action + "]");
		}
        List<Action> list = getSqlMapClientTemplate().queryForList("Action_listAction", action);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listAction(Action), ����[" + list + "]");
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
        	logger.debug("����listActionCount(Action), �������[" + action + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("Action_listActionCount", action);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listActionCount(Action), ����[" + count + "]");
		}
        return count;
    }  
}
