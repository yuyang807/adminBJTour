package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 线路图片关系表(t_line_pic)映射Dto
  * @version 2018-01-26  * */ 
public class TLinePicDto extends DataEntity<TLinePicDto> {
    /**
     * 线路编号
     * */
    private Integer lineNo;
    /**
     * 图片编号
     * */
    private Integer picNo;
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
     * setPicNo 图片编号
     * */
    public void setPicNo(Integer picNo) {
        this.picNo=picNo;
    }

    /**
     * getPicNo 图片编号
     * */
    public Integer getPicNo() {
        return picNo;
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