package com.hbs.auth.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.dao.ActionDao;
import com.hbs.domain.auth.pojo.Action;

/**
 * 
 * @author tony.chen
 *
 */


public class ActionMgr {
	
	private static final String ACTION_DAO_NAME = "actionDao";
	private static final Logger logger = Logger.getLogger(ActionMgr.class);
	
	public Integer insertAction(Action action) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking insertAction(Action action): parameter=" + action.toString());
    	}
		ActionDao actionDao = (ActionDao)BeanLocator.getInstance().getBean(ACTION_DAO_NAME);
		Integer ret = actionDao.insertAction(action);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking insertAction(Action action)");
    	}
		return ret;
	}

    /**
     * delete.
     * @param action action
     * @throws DataAccessException DataAccessException
     */
	public void deleteAction(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking deleteAction(String id): parameter=" + id);
    	}
		ActionDao actionDao = (ActionDao)BeanLocator.getInstance().getBean(ACTION_DAO_NAME);
		actionDao.deleteAction(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking deleteAction(String id)");
    	}
	}
    
    /**
     * update.
     * @param action action
     * @throws DataAccessException DataAccessException
     */
	public void updateAction(Action action) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking updateAction(Action action): parameter=" + action.toString());
    	}
		ActionDao actionDao = (ActionDao)BeanLocator.getInstance().getBean(ACTION_DAO_NAME);
		actionDao.updateAction(action);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking updateAction(Action action)");
    	}
	}

    /**
     * find.
     * @param id id
     * @return action
     * @throws DataAccessException DataAccessException
     */
	public Action findAction(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking findAction(String id): parameter=" + id);
    	}
		ActionDao actionDao = (ActionDao)BeanLocator.getInstance().getBean(ACTION_DAO_NAME);
		Action ret = actionDao.findAction(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking findAction(String id)");
    	}
		return ret;
	}
    
    /**
     * list.
     * @param action action
     * @return action list
     * @throws DataAccessException DataAccessException
     */
	public List<Action> listAction(Action action) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listAction(Action action): parameter=" + action.toString());
    	}
		ActionDao actionDao = (ActionDao)BeanLocator.getInstance().getBean(ACTION_DAO_NAME);
		List<Action> ret = actionDao.listAction(action);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listAction(Action action)");
    	}
		return ret;
	}
    
    /**
     * listCount.
     * @param action action
     * @return list count
     * @throws DataAccessException DataAccessException
     */
	public Integer listActionCount(Action action) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listActionCount(Action action): parameter=" + action.toString());
    	}
		ActionDao actionDao = (ActionDao)BeanLocator.getInstance().getBean(ACTION_DAO_NAME);
		Integer ret = actionDao.listActionCount(action);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listActionCount(Action action)");
    	}
		return ret;
	}
}
