package com.hbs.domain.warehouse.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;



/**
 * WareHouseInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface WareHouseInfoDao {
    /**
     * insert.
     * @param wareHouseInfo wareHouseInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertWareHouseInfo(WareHouseInfo wareHouseInfo) throws DataAccessException ;

    /**
     * delete.
     * @param wareHouseInfo wareHouseInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteWareHouseInfo(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param wareHouseInfo wareHouseInfo
     * @throws DataAccessException DataAccessException
     */
    void updateWareHouseInfo(WareHouseInfo wareHouseInfo) throws DataAccessException ;
    void updateWareHouseInfoByMaxMin(WareHouseInfo wareHouseInfo) throws DataAccessException ;
    /**
     * find.
     * @param id id
     * @return wareHouseInfo
     * @throws DataAccessException DataAccessException
     */
    WareHouseInfo findWareHouseInfoById(String id) throws DataAccessException ;
    WareHouseInfo findWareHouseInfoByBizKey(WareHouseInfo wareHouseInfo) throws DataAccessException ;
    
    /**
     * list.
     * @param wareHouseInfo wareHouseInfo
     * @return wareHouseInfo list
     * @throws DataAccessException DataAccessException
     */
    List<WareHouseInfo> listWareHouseInfo(WareHouseInfo wareHouseInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param wareHouseInfo wareHouseInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listWareHouseInfoCount(WareHouseInfo wareHouseInfo) throws DataAccessException ;
}
