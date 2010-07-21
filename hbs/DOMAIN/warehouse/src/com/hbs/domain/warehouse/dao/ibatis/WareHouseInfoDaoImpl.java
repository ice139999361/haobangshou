package com.hbs.domain.warehouse.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;
import com.hbs.domain.warehouse.dao.WareHouseInfoDao;

/**
 * WareHouseInfoDao�ӿ�ʵ����.
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
    		logger.debug("����insertWareHouseInfo(WareHouseInfo), �������[" + wareHouseInfo + "]");
    	}


    	getSqlMapClientTemplate().insert("WareHouseInfo_insertWareHouseInfo", wareHouseInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertWareHouseInfo(WareHouseInfo), ����");
		}

    }

    /**
     * delete.
     * @param wareHouseInfo wareHouseInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteWareHouseInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteWareHouseInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("WareHouseInfo_deleteWareHouseInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteWareHouseInfo(String)");
		}
    }

    /**
     * update.
     * @param wareHouseInfo wareHouseInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateWareHouseInfo(WareHouseInfo wareHouseInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateWareHouseInfo(WareHouseInfo), �������[" + wareHouseInfo + "]");
		}
    	getSqlMapClientTemplate().update("WareHouseInfo_updateWareHouseInfo", wareHouseInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateWareHouseInfo(WareHouseInfo)");
		}
    }
    public void updateWareHouseInfoByMaxMin(WareHouseInfo wareHouseInfo) throws DataAccessException {
    	if (logger.isDebugEnabled()) {
    		logger.debug("����updateWareHouseInfo(WareHouseInfo), �������[" + wareHouseInfo + "]");
		}
    	getSqlMapClientTemplate().update("WareHouseInfo_updateWareHouseInfoByMaxMin", wareHouseInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateWareHouseInfo(WareHouseInfo)");
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
        	logger.debug("����findWareHouseInfoById(WareHouseInfo), �������[" + pk + "]");
		}
        WareHouseInfo wareHouseInfo = (WareHouseInfo) getSqlMapClientTemplate().queryForObject("WareHouseInfo_findWareHouseInfoById", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findWareHouseInfoById(WareHouseInfo), ����[" + wareHouseInfo + "]");
		}
        return wareHouseInfo;
    }

    public WareHouseInfo findWareHouseInfoByBizKey(WareHouseInfo warehouseInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findWareHouseInfoByBizKey(WareHouseInfo), �������[" + warehouseInfo + "]");
		}
        WareHouseInfo wareHouseInfo = (WareHouseInfo) getSqlMapClientTemplate().queryForObject("WareHouseInfo_findWareHouseInfoByBizKey", warehouseInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findWareHouseInfoByBizKey(WareHouseInfo), ����[" + wareHouseInfo + "]");
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
        	logger.debug("����listWareHouseInfo(WareHouseInfo), �������[" + wareHouseInfo + "]");
		}
        List<WareHouseInfo> list = getSqlMapClientTemplate().queryForList("WareHouseInfo_listWareHouseInfo", wareHouseInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWareHouseInfo(WareHouseInfo), ����[" + list + "]");
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
        	logger.debug("����listWareHouseInfoCount(WareHouseInfo), �������[" + wareHouseInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WareHouseInfo_listWareHouseInfoCount", wareHouseInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWareHouseInfoCount(WareHouseInfo), ����[" + count + "]");
		}
        return count;
    }
}
