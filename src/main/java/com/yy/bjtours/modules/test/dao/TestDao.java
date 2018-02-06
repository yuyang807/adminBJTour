/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.yy.bjtours.modules.test.dao;

import com.yy.bjtours.common.persistence.CrudDao;
import com.yy.bjtours.common.persistence.annotation.MyBatisDao;
import com.yy.bjtours.modules.test.entity.Test;

/**
 * 测试DAO接口
 * @author ThinkGem
 * @version 2013-10-17
 */
@MyBatisDao
public interface TestDao extends CrudDao<Test> {
	
}
