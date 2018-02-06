package com.yy.bjtours.modules.web.dto;

import java.util.Date;
import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 留言表(t_advice)映射Dto
  * @version 2017-12-30  * */ 
public class TAdviceDto extends DataEntity<TAdviceDto> {
    /**
     * 邮箱地址
     * */
    private String emailAddress;
    /**
     * first name
     * */
    private String fName;
    /**
     * last name
     * */
    private String lName;
    /**
     * 咨询内容
     * */
    private String content;
    /**
     * ip地址第一位
     * */
    private Integer ip1;
    /**
     * ip地址第二位
     * */
    private Integer ip2;
    /**
     * ip地址第三位
     * */
    private Integer ip3;
    /**
     * ip地址第四位
     * */
    private Integer ip4;
    /**
     * 是否发送邮件通知（0:未发送，1:已发送）
     * */
    private Integer isInform;
    /**
     * 关联线路编号
     * */
    private Integer lineno;
    /**
     * 了解方式(1:google search;2:tripadvisor;3:friend's recommending)
     * */
    private Integer knowWay;
    /**
     * 推荐人姓名
     * */
    private String referralName;
    /**
     * 创建时间
     * */
    private Date createDate;
    /**
     * 范围查询创建时间使用
     * */
    private Date startCreateDate;
    /**
     * 范围查询创建时间使用
     * */
    private Date endCreateDate;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
    /**
     * setEmailAddress 邮箱地址
     * */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress=emailAddress;
    }

    /**
     * getEmailAddress 邮箱地址
     * */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * setFName first name
     * */
    public void setFName(String fName) {
        this.fName=fName;
    }

    /**
     * getFName first name
     * */
    public String getFName() {
        return fName;
    }

    /**
     * setLName last name
     * */
    public void setLName(String lName) {
        this.lName=lName;
    }

    /**
     * getLName last name
     * */
    public String getLName() {
        return lName;
    }

    /**
     * setContent 咨询内容
     * */
    public void setContent(String content) {
        this.content=content;
    }

    /**
     * getContent 咨询内容
     * */
    public String getContent() {
        return content;
    }

    /**
     * setIp1 ip地址第一位
     * */
    public void setIp1(Integer ip1) {
        this.ip1=ip1;
    }

    /**
     * getIp1 ip地址第一位
     * */
    public Integer getIp1() {
        return ip1;
    }

    /**
     * setIp2 ip地址第二位
     * */
    public void setIp2(Integer ip2) {
        this.ip2=ip2;
    }

    /**
     * getIp2 ip地址第二位
     * */
    public Integer getIp2() {
        return ip2;
    }

    /**
     * setIp3 ip地址第三位
     * */
    public void setIp3(Integer ip3) {
        this.ip3=ip3;
    }

    /**
     * getIp3 ip地址第三位
     * */
    public Integer getIp3() {
        return ip3;
    }

    /**
     * setIp4 ip地址第四位
     * */
    public void setIp4(Integer ip4) {
        this.ip4=ip4;
    }

    /**
     * getIp4 ip地址第四位
     * */
    public Integer getIp4() {
        return ip4;
    }

    /**
     * setIsInform 是否发送邮件通知（0:未发送，1:已发送）
     * */
    public void setIsInform(Integer isInform) {
        this.isInform=isInform;
    }

    /**
     * getIsInform 是否发送邮件通知（0:未发送，1:已发送）
     * */
    public Integer getIsInform() {
        return isInform;
    }

    /**
     * setLineno 关联线路编号
     * */
    public void setLineno(Integer lineno) {
        this.lineno=lineno;
    }

    /**
     * getLineno 关联线路编号
     * */
    public Integer getLineno() {
        return lineno;
    }

    /**
     * setKnowWay 了解方式(1:google search;2:tripadvisor;3:friend's recommending)
     * */
    public void setKnowWay(Integer knowWay) {
        this.knowWay=knowWay;
    }

    /**
     * getKnowWay 了解方式(1:google search;2:tripadvisor;3:friend's recommending)
     * */
    public Integer getKnowWay() {
        return knowWay;
    }

    /**
     * setReferralName 推荐人姓名
     * */
    public void setReferralName(String referralName) {
        this.referralName=referralName;
    }

    /**
     * getReferralName 推荐人姓名
     * */
    public String getReferralName() {
        return referralName;
    }

    /**
     * setCreateDate 创建时间
     * */
    public void setCreateDate(Date createDate) {
        this.createDate=createDate;
    }

    /**
     * getCreateDate 创建时间
     * */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * setStartcreateDate 创建时间
     * */
    public void setStartcreateDate(Date startCreateDate) {
        this.startCreateDate=startCreateDate;
    }

    /**
     * getStartcreateDate 创建时间
     * */
    public Date getStartcreateDate() {
        return startCreateDate;
    }

    /**
     * setEndcreateDate 创建时间
     * */
    public void setEndcreateDate(Date endCreateDate) {
        this.endCreateDate=endCreateDate;
    }

    /**
     * getEndcreateDate 创建时间
     * */
    public Date getEndcreateDate() {
        return endCreateDate;
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