package com.hbs.domain.waittask.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;


/**
 * WaitTaskInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface WaitTaskInfoDao {
    /**
     * insert.
     * @param waitTaskInfo waitTaskInfo
     * @return void
     * @throws DataAccessException DataAccessException
     */
    void insertWaitTaskInfo(WaitTaskInfo waitTaskInfo) throws DataAccessException ;

    /**
     * delete.
     * @param waitTaskInfo waitTaskInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteWaitTaskInfo(String businessKey) throws DataAccessException ;
    void deleteWaitTaskInfoByExpireTime(String expireTime) throws DataAccessException ;
    
    /**
     * update.
     * @param waitTaskInfo waitTaskInfo
     * @throws DataAccessException DataAccessException
     */
    void updateWaitTaskInfo(WaitTaskInfo waitTaskInfo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return waitTaskInfo
     * @throws DataAccessException DataAccessException
     */
    WaitTaskInfo findWaitTaskInfo(String businessKey) throws DataAccessException ;
    
    /**
     * list.
     * @param waitTaskInfo waitTaskInfo
     * @return waitTaskInfo list
     * @throws DataAccessException DataAccessException
     */
   
	List<WaitTaskInfo> listWaitTaskInfo(WaitTaskInfo waitTaskInfo) throws DataAccessException ;
   
	List<WaitTaskInfo> listWaitTaskMustInfo(WaitTaskInfo waitTaskInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param waitTaskInfo waitTaskInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listWaitTaskInfoCount(WaitTaskInfo waitTaskInfo) throws DataAccessException ;
}
