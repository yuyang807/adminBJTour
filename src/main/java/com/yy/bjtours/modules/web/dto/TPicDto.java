package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 图片表(t_pic)映射Dto
  * @version 2017-12-30  * */ 
public class TPicDto extends DataEntity<TPicDto> {
    /**
     * 图片编号
     * */
    private Integer picNo;
    /**
     * 图片名称
     * */
    private String picName;
    /**
     * 文件路径
     * */
    private String fileUrl;
    /**
     * 文件md5值
     * */
    private String md5;
    /**
     * 是否为主图(0:否；1:是)
     * */
    private Integer isMain;
    /**
     * 主页轮播图
     * */
    private Integer isMainAll;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
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
     * setPicName 图片名称
     * */
    public void setPicName(String picName) {
        this.picName=picName;
    }

    /**
     * getPicName 图片名称
     * */
    public String getPicName() {
        return picName;
    }

    /**
     * setFileUrl 文件路径
     * */
    public void setFileUrl(String fileUrl) {
        this.fileUrl=fileUrl;
    }

    /**
     * getFileUrl 文件路径
     * */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * setMd5 文件md5值
     * */
    public void setMd5(String md5) {
        this.md5=md5;
    }

    /**
     * getMd5 文件md5值
     * */
    public String getMd5() {
        return md5;
    }

    /**
     * setIsMain 是否为主图(0:否；1:是)
     * */
    public void setIsMain(Integer isMain) {
        this.isMain=isMain;
    }

    /**
     * getIsMain 是否为主图(0:否；1:是)
     * */
    public Integer getIsMain() {
        return isMain;
    }

    /**
     * setIsMainAll 主页轮播图
     * */
    public void setIsMainAll(Integer isMainAll) {
        this.isMainAll=isMainAll;
    }

    /**
     * getIsMainAll 主页轮播图
     * */
    public Integer getIsMainAll() {
        return isMainAll;
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