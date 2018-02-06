package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 线路价格表(t_line_price)映射Dto
  * @version 2018-01-26  * */ 
public class TLinePriceDto extends DataEntity<TLinePriceDto> {
    /**
     * 线路编号
     * */
    private Integer lineNo;
    /**
     * 1人价
     * */
    private Integer oneP;
    /**
     * 2人价
     * */
    private Integer twoP;
    /**
     * 3人价
     * */
    private Integer threeP;
    /**
     * 4人价
     * */
    private Integer fourP;
    /**
     * 5人价
     * */
    private Integer fiveP;
    /**
     * 6人价
     * */
    private Integer sixP;
    /**
     * 7人价
     * */
    private Integer sevenP;
    /**
     * 8人价
     * */
    private Integer eightP;
    /**
     * 9人价
     * */
    private Integer nineP;
    /**
     * 10人价
     * */
    private Integer tenP;
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
     * setOneP 1人价
     * */
    public void setOneP(Integer oneP) {
        this.oneP=oneP;
    }

    /**
     * getOneP 1人价
     * */
    public Integer getOneP() {
        return oneP;
    }

    /**
     * setTwoP 2人价
     * */
    public void setTwoP(Integer twoP) {
        this.twoP=twoP;
    }

    /**
     * getTwoP 2人价
     * */
    public Integer getTwoP() {
        return twoP;
    }

    /**
     * setThreeP 3人价
     * */
    public void setThreeP(Integer threeP) {
        this.threeP=threeP;
    }

    /**
     * getThreeP 3人价
     * */
    public Integer getThreeP() {
        return threeP;
    }

    /**
     * setFourP 4人价
     * */
    public void setFourP(Integer fourP) {
        this.fourP=fourP;
    }

    /**
     * getFourP 4人价
     * */
    public Integer getFourP() {
        return fourP;
    }

    /**
     * setFiveP 5人价
     * */
    public void setFiveP(Integer fiveP) {
        this.fiveP=fiveP;
    }

    /**
     * getFiveP 5人价
     * */
    public Integer getFiveP() {
        return fiveP;
    }

    /**
     * setSixP 6人价
     * */
    public void setSixP(Integer sixP) {
        this.sixP=sixP;
    }

    /**
     * getSixP 6人价
     * */
    public Integer getSixP() {
        return sixP;
    }

    /**
     * setSevenP 7人价
     * */
    public void setSevenP(Integer sevenP) {
        this.sevenP=sevenP;
    }

    /**
     * getSevenP 7人价
     * */
    public Integer getSevenP() {
        return sevenP;
    }

    /**
     * setEightP 8人价
     * */
    public void setEightP(Integer eightP) {
        this.eightP=eightP;
    }

    /**
     * getEightP 8人价
     * */
    public Integer getEightP() {
        return eightP;
    }

    /**
     * setNineP 9人价
     * */
    public void setNineP(Integer nineP) {
        this.nineP=nineP;
    }

    /**
     * getNineP 9人价
     * */
    public Integer getNineP() {
        return nineP;
    }

    /**
     * setTenP 10人价
     * */
    public void setTenP(Integer tenP) {
        this.tenP=tenP;
    }

    /**
     * getTenP 10人价
     * */
    public Integer getTenP() {
        return tenP;
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