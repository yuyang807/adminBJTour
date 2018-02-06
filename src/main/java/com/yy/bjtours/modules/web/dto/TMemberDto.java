package com.yy.bjtours.modules.web.dto;

import java.util.Date;
import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 用户表(t_member)映射Dto
  * @version 2017-12-30  * */ 
public class TMemberDto extends DataEntity<TMemberDto> {
    /**
     * 会员编号
     * */
    private Integer memberNo;
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
     * 
     * */
    private String passportNum;
    /**
     * 电话
     * */
    private String phoneNum;
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
     * 会员状态(0:待确认；1:已确认待审核；2:已审核；3:已拉黑)
     * */
    private Integer status;
    /**
     * 
     * */
    private Date createDate;
    /**
     * 范围查询使用
     * */
    private Date startCreateDate;
    /**
     * 范围查询使用
     * */
    private Date endCreateDate;
    /**
     * 
     * */
    private Date updateDate;
    /**
     * 范围查询使用
     * */
    private Date startUpdateDate;
    /**
     * 范围查询使用
     * */
    private Date endUpdateDate;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
    /**
     * setMemberNo 会员编号
     * */
    public void setMemberNo(Integer memberNo) {
        this.memberNo=memberNo;
    }

    /**
     * getMemberNo 会员编号
     * */
    public Integer getMemberNo() {
        return memberNo;
    }

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
     * setPassportNum 
     * */
    public void setPassportNum(String passportNum) {
        this.passportNum=passportNum;
    }

    /**
     * getPassportNum 
     * */
    public String getPassportNum() {
        return passportNum;
    }

    /**
     * setPhoneNum 电话
     * */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum=phoneNum;
    }

    /**
     * getPhoneNum 电话
     * */
    public String getPhoneNum() {
        return phoneNum;
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
     * setStatus 会员状态(0:待确认；1:已确认待审核；2:已审核；3:已拉黑)
     * */
    public void setStatus(Integer status) {
        this.status=status;
    }

    /**
     * getStatus 会员状态(0:待确认；1:已确认待审核；2:已审核；3:已拉黑)
     * */
    public Integer getStatus() {
        return status;
    }

    /**
     * setCreateDate 
     * */
    public void setCreateDate(Date createDate) {
        this.createDate=createDate;
    }

    /**
     * getCreateDate 
     * */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * setStartcreateDate 
     * */
    public void setStartcreateDate(Date startCreateDate) {
        this.startCreateDate=startCreateDate;
    }

    /**
     * getStartcreateDate 
     * */
    public Date getStartcreateDate() {
        return startCreateDate;
    }

    /**
     * setEndcreateDate 
     * */
    public void setEndcreateDate(Date endCreateDate) {
        this.endCreateDate=endCreateDate;
    }

    /**
     * getEndcreateDate 
     * */
    public Date getEndcreateDate() {
        return endCreateDate;
    }

    /**
     * setUpdateDate 
     * */
    public void setUpdateDate(Date updateDate) {
        this.updateDate=updateDate;
    }

    /**
     * getUpdateDate 
     * */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * setStartupdateDate 
     * */
    public void setStartupdateDate(Date startUpdateDate) {
        this.startUpdateDate=startUpdateDate;
    }

    /**
     * getStartupdateDate 
     * */
    public Date getStartupdateDate() {
        return startUpdateDate;
    }

    /**
     * setEndupdateDate 
     * */
    public void setEndupdateDate(Date endUpdateDate) {
        this.endUpdateDate=endUpdateDate;
    }

    /**
     * getEndupdateDate 
     * */
    public Date getEndupdateDate() {
        return endUpdateDate;
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