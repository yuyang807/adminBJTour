package com.yy.bjtours.modules.server.service;

import com.yy.bjtours.common.service.CrudService;
import com.yy.bjtours.modules.server.dao.PosServiceInfoDao;
import com.yy.bjtours.modules.server.entity.PosServiceInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统服务Service
 * Created by user on 2016/1/12.
 */
@Service
public class PosServiceInfoService extends CrudService<PosServiceInfoDao, PosServiceInfo> {

    /**
     * 根据允许交易服务信息
     *
     * @return
     */
    public List<PosServiceInfo> findAllowTradServiceInfo() {

        return dao.findAllowTradServiceInfo();
    }

    /**
     * 根据类型查询业务编码
     *
     * @param bizType
     * @return
     */
    public String getBizCodeByType(String bizType) {
        return dao.getBizCodeByType(bizType);
    }
}
