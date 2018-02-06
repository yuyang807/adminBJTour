package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 车辆服务价格对应表(t_carkind_service)映射Dto
  * @version 2017-12-30  * */ 
public class TCarkindServiceDto extends DataEntity<TCarkindServiceDto> {
    /**
     * 车服务编号
     * */
    private Integer carServiceNo;
    /**
     * 车服务价格
     * */
    private Integer carServicePrice;
    /**
     * 关联车车类型编号
     * */
    private Integer carTypeNo;
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
     * setCarServicePrice 车服务价格
     * */
    public void setCarServicePrice(Integer carServicePrice) {
        this.carServicePrice=carServicePrice;
    }

    /**
     * getCarServicePrice 车服务价格
     * */
    public Integer getCarServicePrice() {
        return carServicePrice;
    }

    /**
     * setCarTypeNo 关联车车类型编号
     * */
    public void setCarTypeNo(Integer carTypeNo) {
        this.carTypeNo=carTypeNo;
    }

    /**
     * getCarTypeNo 关联车车类型编号
     * */
    public Integer getCarTypeNo() {
        return carTypeNo;
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