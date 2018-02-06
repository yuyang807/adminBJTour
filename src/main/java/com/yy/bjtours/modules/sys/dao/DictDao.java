/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yy.bjtours.modules.sys.dao;

import com.yy.bjtours.common.persistence.CrudDao;
import com.yy.bjtours.common.persistence.annotation.MyBatisDao;
import com.yy.bjtours.modules.sys.entity.Dict;

import java.util.List;

/**
 * 字典DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);

	/**
	 * 根据字典标签和字典类型查询字典信息
	 * @param dict
	 * @return
	 */
	Dict getByLabelAndType(Dict dict);

	/**
	 * 物理删除字典数据
	 * @param dict
	 */
	void realDelete(Dict dict);

	/**
	 * 查询微信二清服务开通数据字典
	 * @return
	 */
	List<String> selectAutoOpenDict();

    /**
     * 根据字典值和字典类型查询字典信息
     * @param dict
     * @return
     */
    Dict getByValueAndType(Dict dict);
    /**
     * 查询最后一个sort值
     * @author  2017年8月10日 zs
     */
	public int findLastSort();
	
}
