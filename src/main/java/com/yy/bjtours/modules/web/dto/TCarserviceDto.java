package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 车辆服务表(t_carservice)映射Dto
  * @version 2017-12-30  * */ 
public class TCarserviceDto extends DataEntity<TCarserviceDto> {
    /**
     * 车服务编号
     * */
    private Integer carServiceNo;
    /**
     * 车服务名称
     * */
    private String carServiceName;
    /**
     * 是否可以预订多天(0:否；1:是)
     * */
    private Integer canManyDays;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
    /**
     * setCarServiceNo 车服务编号
     * */
    public void setCarServiceNo(Integer carServiceNo) {
        this.carServiceNo=carServiceNo;
    }

    /**
     * getCarServiceNo 车服务编号
     * */
    public Integer getCarServiceNo() {
        return carServiceNo;
    }

    /**
     * setCarServiceName 车服务名称
     * */
    public void setCarServiceName(String carServiceName) {
        this.carServiceName=carServiceName;
    }

    /**
     * getCarServiceName 车服务名称
     * */
    public String getCarServiceName() {
        return carServiceName;
    }

    /**
     * setCanManyDays 是否可以预订多天(0:否；1:是)
     * */
    public void setCanManyDays(Integer canManyDays) {
        this.canManyDays=canManyDays;
    }

    /**
     * getCanManyDays 是否可以预订多天(0:否；1:是)
     * */
    public Integer getCanManyDays() {
        return canManyDays;
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