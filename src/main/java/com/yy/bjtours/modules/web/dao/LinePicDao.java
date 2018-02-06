package com.yy.bjtours.modules.web.dao;

import java.util.List;

import com.yy.bjtours.common.persistence.CrudDao;
import com.yy.bjtours.common.persistence.annotation.MyBatisDao;
import com.yy.bjtours.modules.web.dto.TLinePicDto;

/**
 * 
 * @author: yy
 * @date: 2018年1月4日 上午9:36:46 
 * @desc:图片Dao
 */
@MyBatisDao
public interface LinePicDao extends CrudDao<TLinePicDto>{

	/**
	 * 获取线路列表（部分属性）
	 * @return
	 *//*
	List<TLineDto> getLineList();
	
	*//**
	 * 获取线路详情
	 * @param lineDto
	 * @return
	 *//*
	TLineDto getLine(TLineDto lineDto);*/
	
    /**
     * 查询代理商编码
     * @param areaCode
     * @return String
     *//*
    public String getAgentCode(String areaCode);
    *//**
     * 核实商户名称
     * @param posMerchant
     * @return Long
     *//*
    public Long verifyMerchantName(PosMerchant posMerchant);
    *//**
     * 查询商户编码
     * @param 
     * @return List<PosMerchant>
     *//*
    
    public List<PosMerchant> getAgenCodeList();
    *//**
     * 根据代理商id查询商户
     * @param belongTo
     * @return List<PosMerchant>
     *//*
    public List<PosMerchant> getMerchantCodeList(String belongTo);
    *//**
     * 根据商户查询
     * @param posMerchant
     * @return List<PosMerchant>
     *//*
    public List<PosMerchant> findMerchantTwo(PosMerchant posMerchant);
    
    *//**
     * 根据商户code查询商户名称
     * @param merchantCode
     * @return merchantName 
     *//*
    public String getMerchantName(String merchantCode);
    *//**
     * 根据商户code查询商户
     * @param merchantCode
     * @return PosMerchant
     *//*
    public PosMerchant getPosMerchant(String merchantCode);
    *//**
     * 根据商户code查询商户id
     * @param merchantCode
     * @return merchantName 
     *//*
    public String getMerchantMid(String merchantCode);
    *//**
    * 根据商户code查询连锁商户
    * @param merchantCode
    * @return merchantName 
    *//*
    public List<PosMerchant> getHqMerchantList(PosMerchant posMerchant);
    *//**
     * 查询商户最后一个ID
     * @return id
     *//*
    public long getMerchantLastId();
    
    *//**
     * 获取商户总店名称
     * @param hqMerchantCode
     * @return String 总店名称
     *//*
    public String getHqMerchantName(String hqMerchantCode);

    *//**
     * 查询总店商户集合
     * @param posMerchantVO
     * @return List<PosMerchant>商户集合
     *//*
	public List<PosMerchant> getHQMerchant(PosMerchantVO posMerchantVO);
    *//**查询所有的代理商信息*//*
	public List<PosMerchant> findAgenList(PosMerchantVO posMerchantVO);

    *//**
     * 根据商户号和类型查询商户信息
     * @param params
     * @return
     *//*
    PosMerchant getByMerchantCodeAndType(Map<String, Object> params);

    *//**
     * 根据商户ID更新商户终端数量
     * @param
     * @return merchantCode
     *//*
    int updateTerminalNums(String merchantCode);

    *//**
     * 根据代理商编码查询商户编码信息
     * @param agentCode 代理商编码
     * @return List<String>
     *//*
    List<String> findMerchantCodeByAgentCode(String agentCode);

    *//**
     * 查询最近一小时信息有更改商户
     * @return List<String>
     *//*
    List<String> findLastHourChanged();
    
	*//**
	 * 根据商户号修改商户发票服务开通状态
	 * @param pm
	 * @return
	 *//*
	int updateInvoiceByMCode(Map<String, Object> pm);
	
	*//**
	 * 根据商户号集合 查询集合中存在的商户号
	 * @param pm
	 * @return
	 *//*
	List<String> queryExistM(Map<String, Object> pm);*/
}
