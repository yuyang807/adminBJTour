package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 线路产品附加项表(t_addition)映射Dto
  * @version 2017-12-30  * */ 
public class TAdditionDto extends DataEntity<TAdditionDto> {
    /**
     * 附加项编号
     * */
    private Integer addNo;
    /**
     * 附加项内容
     * */
    private String addValue;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
    /**
     * setAddNo 附加项编号
     * */
    public void setAddNo(Integer addNo) {
        this.addNo=addNo;
    }

    /**
     * getAddNo 附加项编号
     * */
    public Integer getAddNo() {
        return addNo;
    }

    /**
     * setAddValue 附加项内容
     * */
    public void setAddValue(String addValue) {
        this.addValue=addValue;
    }

    /**
     * getAddValue 附加项内容
     * */
    public String getAddValue() {
        return addValue;
    }

    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    public void setOrderByStr(String orderByStr) {
        this.orderByStr=orderByStr;
    }

    public String getOrderByStr() {
        return orderByStr;
    }

}