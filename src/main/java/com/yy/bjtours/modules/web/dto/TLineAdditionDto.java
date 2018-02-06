package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 线路与附加项关系表(t_line_addition)映射Dto
  * @version 2018-01-26  * */ 
public class TLineAdditionDto extends DataEntity<TLineAdditionDto> {
    /**
     * 线路编号
     * */
    private Integer lineNo;
    /**
     * 附加项编号
     * */
    private Integer addNo;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
    /**
     * setLineNo 线路编号
     * */
    public void setLineNo(Integer lineNo) {
        this.lineNo=lineNo;
    }

    /**
     * getLineNo 线路编号
     * */
    public Integer getLineNo() {
        return lineNo;
    }

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
     * 排序字段加排序规则组合体columnName desc
     * */
    public void setOrderByStr(String orderByStr) {
        this.orderByStr=orderByStr;
    }

    public String getOrderByStr() {
        return orderByStr;
    }

}