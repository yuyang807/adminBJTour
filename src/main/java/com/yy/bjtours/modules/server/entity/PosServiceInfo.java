package com.yy.bjtours.modules.server.entity;

import com.yy.bjtours.common.persistence.DataEntity;

import java.util.Date;

/**
 * 系统服务Entity
 * Created by user on 2016/1/12.
 */
public class PosServiceInfo extends DataEntity<PosServiceInfo> {
    /**
     * 服务编码
     */
    private String bizCode;
    /**
     * 服务名称
     */
    private String bizName;
    /**
     * 是否允许交易 0：不允许，1：允许
     */
    private Integer allowTrade;
    /**
     * 业务类型
     */
    private Integer bizType;
    /**
     * 业务系统
     */
    private Integer bizSystem;
    /**
     * 服务开始时间
     */
    private Date beginDate;
    /**
     * 服务结束时间
     */
    private Date endDate;

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public Integer getAllowTrade() {
        return allowTrade;
    }

    public void setAllowTrade(Integer allowTrade) {
        this.allowTrade = allowTrade;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getBizSystem() {
        return bizSystem;
    }

    public void setBizSystem(Integer bizSystem) {
        this.bizSystem = bizSystem;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
