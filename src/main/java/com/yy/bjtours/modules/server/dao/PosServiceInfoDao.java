package com.yy.bjtours.modules.server.dao;

import com.yy.bjtours.common.persistence.CrudDao;
import com.yy.bjtours.common.persistence.annotation.MyBatisDao;
import com.yy.bjtours.modules.server.entity.PosServiceInfo;

import java.util.List;

/**
 * 系统服务Dao
 * Created by user on 2016/1/12.
 */
@MyBatisDao
public interface PosServiceInfoDao extends CrudDao<PosServiceInfo>{

    List<PosServiceInfo> findAllowTradServiceInfo();

    String getBizCodeByType(String bizType);
}
