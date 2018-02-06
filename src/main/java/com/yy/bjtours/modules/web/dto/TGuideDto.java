package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 导游表(t_guide)映射Dto
  * @version 2017-12-30  * */ 
public class TGuideDto extends DataEntity<TGuideDto> {
    /**
     * 导游服务编号
     * */
    private Integer guideNo;
    /**
     * 导游服务名称
     * */
    private String serviceName;
    /**
     * 服务时长(单位:hour)
     * */
    private Integer serviceDuration;
    /**
     * 是否可预订(0:是；1:否)
     * */
    private Integer customizable;
    /**
     * 
     * */
    private Integer price;
    /**
     * 简介
     * */
    private String introduction;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
    /**
     * setGuideNo 导游服务编号
     * */
    public void setGuideNo(Integer guideNo) {
        this.guideNo=guideNo;
    }

    /**
     * getGuideNo 导游服务编号
     * */
    public Integer getGuideNo() {
        return guideNo;
    }

    /**
     * setServiceName 导游服务名称
     * */
    public void setServiceName(String serviceName) {
        this.serviceName=serviceName;
    }

    /**
     * getServiceName 导游服务名称
     * */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * setServiceDuration 服务时长(单位:hour)
     * */
    public void setServiceDuration(Integer serviceDuration) {
        this.serviceDuration=serviceDuration;
    }

    /**
     * getServiceDuration 服务时长(单位:hour)
     * */
    public Integer getServiceDuration() {
        return serviceDuration;
    }

    /**
     * setCustomizable 是否可预订(0:是；1:否)
     * */
    public void setCustomizable(Integer customizable) {
        this.customizable=customizable;
    }

    /**
     * getCustomizable 是否可预订(0:是；1:否)
     * */
    public Integer getCustomizable() {
        return customizable;
    }

    /**
     * setPrice 
     * */
    public void setPrice(Integer price) {
        this.price=price;
    }

    /**
     * getPrice 
     * */
    public Integer getPrice() {
        return price;
    }

    /**
     * setIntroduction 简介
     * */
    public void setIntroduction(String introduction) {
        this.introduction=introduction;
    }

    /**
     * getIntroduction 简介
     * */
    public String getIntroduction() {
        return introduction;
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