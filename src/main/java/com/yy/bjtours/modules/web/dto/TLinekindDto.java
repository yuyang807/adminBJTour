package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 线路种类型(t_linekind)映射Dto
  * @version 2017-12-30  * */ 
public class TLinekindDto extends DataEntity<TLinekindDto> {
    /**
     * 类型编码
     * */
    private Integer typeNo;
    /**
     * 类型名称
     * */
    private String typeName;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
    /**
     * setTypeNo 类型编码
     * */
    public void setTypeNo(Integer typeNo) {
        this.typeNo=typeNo;
    }

    /**
     * getTypeNo 类型编码
     * */
    public Integer getTypeNo() {
        return typeNo;
    }

    /**
     * setTypeName 类型名称
     * */
    public void setTypeName(String typeName) {
        this.typeName=typeName;
    }

    /**
     * getTypeName 类型名称
     * */
    public String getTypeName() {
        return typeName;
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