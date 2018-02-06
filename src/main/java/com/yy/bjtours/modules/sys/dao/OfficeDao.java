/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yy.bjtours.modules.sys.dao;

import com.yy.bjtours.common.persistence.TreeDao;
import com.yy.bjtours.common.persistence.annotation.MyBatisDao;
import com.yy.bjtours.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
