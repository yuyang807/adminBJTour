/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yy.bjtours.modules.sys.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yy.bjtours.common.service.CrudService;
import com.yy.bjtours.common.utils.CacheUtils;
import com.yy.bjtours.modules.sys.dao.DictDao;
import com.yy.bjtours.modules.sys.entity.Dict;
import com.yy.bjtours.modules.sys.utils.DictUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典Service
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {

    /**
     * 查询字段类型列表
     *
     * @return
     */
    public List<String> findTypeList() {
        return dao.findTypeList(new Dict());
    }

    @Transactional(readOnly = false)
    public void save(Dict dict) {
        super.save(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

    @Transactional(readOnly = false)
    public void delete(Dict dict) {
        super.delete(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

    public Dict getByLabelAndType(Dict dict) {
        return dao.getByLabelAndType(dict);
    }

    @Transactional(readOnly = false)
    public void realDelete(Dict dict) {
        dao.realDelete(dict);
    }

    /**
     * 查询是否自动开通微信二清服务
     * @return
     */
    public boolean isAutoOpenWeChatSecondClear(){
        List<String> dicts = dao.selectAutoOpenDict();
        if (CollectionUtils.isNotEmpty(dicts)) {
            //获取最新微信二服开通配置字典
            String dictsValue = dicts.get(0);
            if ("true".equalsIgnoreCase(dictsValue.trim())) {
                return true;
            }
        }
        return false;
    }

    public Dict getByValueAndType(Dict dict){
        return dao.getByValueAndType(dict);
    }
}
