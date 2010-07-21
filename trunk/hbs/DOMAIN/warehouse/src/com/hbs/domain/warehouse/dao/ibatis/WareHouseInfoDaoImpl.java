package com.hbs.domain.warehouse.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.domain.warehouse.dao.WareHouseInfoDao;

/**
 * WareHouseInfoDao接口实现类.
 * @author hbs
 *
 */
public class WareHouseInfoDaoImpl extends SqlMapClientDaoSupport implements WareHouseInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(WareHouseInfoDaoImpl.class);



    /**
     * insert.
     * @param wareHouseInfo wareHouseInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertWareHouseInfo(WareHouseInfo wareHouseInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertWareHouseInfo(WareHouseInfo), 输入参数[" + wareHouseInfo + "]");
    	}


    	getSqlMapClientTemplate().insert("WareHouseInfo_insertWareHouseInfo", wareHouseInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertWareHouseInfo(WareHouseInfo), 返回");
		}

    }

    /**
     * delete.
     * @param wareHouseInfo wareHouseInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteWareHouseInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteWareHouseInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("WareHouseInfo_deleteWareHouseInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteWareHouseInfo(String)");
		}
    }

    /**
     * update.
     * @param wareHouseInfo wareHouseInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateWareHouseInfo(WareHouseInfo wareHouseInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateWareHouseInfo(WareHouseInfo), 输入参数[" + wareHouseInfo + "]");
		}
    	getSqlMapClientTemplate().update("WareHouseInfo_updateWareHouseInfo", wareHouseInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateWareHouseInfo(WareHouseInfo)");
		}
    }
    public void updateWareHouseInfoByMaxMin(WareHouseInfo wareHouseInfo) throws DataAccessException {
    	if (logger.isDebugEnabled()) {
    		logger.debug("进入updateWareHouseInfo(WareHouseInfo), 输入参数[" + wareHouseInfo + "]");
		}
    	getSqlMapClientTemplate().update("WareHouseInfo_updateWareHouseInfoByMaxMin", wareHouseInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateWareHouseInfo(WareHouseInfo)");
		}
    }
    /**
     * find.
     * @param id id
     * @return wareHouseInfo
     * @throws DataAccessException DataAccessException
     */
    public WareHouseInfo findWareHouseInfoById(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findWareHouseInfoById(WareHouseInfo), 输入参数[" + pk + "]");
		}
        WareHouseInfo wareHouseInfo = (WareHouseInfo) getSqlMapClientTemplate().queryForObject("WareHouseInfo_findWareHouseInfoById", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findWareHouseInfoById(WareHouseInfo), 返回[" + wareHouseInfo + "]");
		}
        return wareHouseInfo;
    }

    public WareHouseInfo findWareHouseInfoByBizKey(WareHouseInfo warehouseInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findWareHouseInfoByBizKey(WareHouseInfo), 输入参数[" + warehouseInfo + "]");
		}
        WareHouseInfo wareHouseInfo = (WareHouseInfo) getSqlMapClientTemplate().queryForObject("WareHouseInfo_findWareHouseInfoByBizKey", warehouseInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findWareHouseInfoByBizKey(WareHouseInfo), 返回[" + wareHouseInfo + "]");
		}
        return wareHouseInfo;
    }

    /**
     * list.
     * @param wareHouseInfo wareHouseInfo
     * @return wareHouseInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<WareHouseInfo> listWareHouseInfo(WareHouseInfo wareHouseInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listWareHouseInfo(WareHouseInfo), 输入参数[" + wareHouseInfo + "]");
		}
        List<WareHouseInfo> list = getSqlMapClientTemplate().queryForList("WareHouseInfo_listWareHouseInfo", wareHouseInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWareHouseInfo(WareHouseInfo), 返回[" + list + "]");
		}
        return list;
    }

    /**
     * listCount.
     * @param wareHouseInfo wareHouseInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listWareHouseInfoCount(WareHouseInfo wareHouseInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listWareHouseInfoCount(WareHouseInfo), 输入参数[" + wareHouseInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WareHouseInfo_listWareHouseInfoCount", wareHouseInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWareHouseInfoCount(WareHouseInfo), 返回[" + count + "]");
		}
        return count;
    }
}
