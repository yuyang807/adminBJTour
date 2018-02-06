package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 车辆表(t_car)映射Dto
  * @version 2017-12-30  * */ 
public class TCarDto extends DataEntity<TCarDto> {
    /**
     * 车编号
     * */
    private Integer carNo;
    /**
     * 车款式
     * */
    private String carName;
    /**
     * 车类型编号
     * */
    private Integer carTypeNo;
    /**
     * 图片编号
     * */
    private Integer picNo;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
    /**
     * setCarNo 车编号
     * */
    public void setCarNo(Integer carNo) {
        this.carNo=carNo;
    }

    /**
     * getCarNo 车编号
     * */
    public Integer getCarNo() {
        return carNo;
    }

    /**
     * setCarName 车款式
     * */
    public void setCarName(String carName) {
        this.carName=carName;
    }

    /**
     * getCarName 车款式
     * */
    public String getCarName() {
        return carName;
    }

    /**
     * setCarTypeNo 车类型编号
     * */
    public void setCarTypeNo(Integer carTypeNo) {
        this.carTypeNo=carTypeNo;
    }

    /**
     * getCarTypeNo 车类型编号
     * */
    public Integer getCarTypeNo() {
        return carTypeNo;
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