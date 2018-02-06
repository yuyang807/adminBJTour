package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 车类型表(t_carkind)映射Dto
  * @version 2017-12-30  * */ 
public class TCarkindDto extends DataEntity<TCarkindDto> {
    /**
     * 车类型编号
     * */
    private Integer carTypeNo;
    /**
     * 车类型(适用几人)
     * */
    private String carTypeName;
    /**
     * 接送机/站价格
     * */
    private Integer transferPrice;
    /**
     * 行李个数
     * */
    private Integer suitcasesNum;
    /**
     * 随身行李个数
     * */
    private Integer carryonNum;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
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
     * setCarTypeName 车类型(适用几人)
     * */
    public void setCarTypeName(String carTypeName) {
        this.carTypeName=carTypeName;
    }

    /**
     * getCarTypeName 车类型(适用几人)
     * */
    public String getCarTypeName() {
        return carTypeName;
    }

    /**
     * setTransferPrice 接送机/站价格
     * */
    public void setTransferPrice(Integer transferPrice) {
        this.transferPrice=transferPrice;
    }

    /**
     * getTransferPrice 接送机/站价格
     * */
    public Integer getTransferPrice() {
        return transferPrice;
    }

    /**
     * setSuitcasesNum 行李个数
     * */
    public void setSuitcasesNum(Integer suitcasesNum) {
        this.suitcasesNum=suitcasesNum;
    }

    /**
     * getSuitcasesNum 行李个数
     * */
    public Integer getSuitcasesNum() {
        return suitcasesNum;
    }

    /**
     * setCarryonNum 随身行李个数
     * */
    public void setCarryonNum(Integer carryonNum) {
        this.carryonNum=carryonNum;
    }

    /**
     * getCarryonNum 随身行李个数
     * */
    public Integer getCarryonNum() {
        return carryonNum;
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