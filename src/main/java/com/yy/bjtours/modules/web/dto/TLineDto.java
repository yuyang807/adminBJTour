package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 线路产品表(t_line)映射Dto
  * @version 2017-12-30  * */ 
public class TLineDto extends DataEntity<TLineDto> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 线路号
     * */
    private Integer lineNo;
    /**
     * 线路简称
     * */
    private String lineNameShort;
    /**
     * 线路名称
     * */
    private String lineName;
    /**
     * 可预订时段
     * */
    private String available;
    /**
     * 持续时长
     * */
    private String duration;
    /**
     * 是否私人游(0:是；1:否)
     * */
    private Integer isPrivate;
    /**
     * 适用语种
     * */
    private String language;
    /**
     * 线路承载点(0:旅馆；1:机场；2:自定义)
     * */
    private Integer pickUpPoint;
    /**
     * 结束点
     * */
    private Integer finishPoint;
    /**
     * 建议开始时间(如果kind为layover,则your arrival time)
     * */
    private String pickUpTime;
    /**
     * 结束时间(默认为0)
     * */
    private Integer endTime;
    /**
     * 物理强度(1:moderate；2:moderate-intermediate；3:intermediate；4:intermediate-advanced；5:advanced)
     * */
    private Integer phyLevel;
    /**
     * 徒步距离
     * */
    private String hikingDis;
    /**
     * 徒步区域
     * */
    private String hikingArea;
    /**
     * 吸引力
     * */
    private String attractions;
    /**
     * 可定制(0:可以；1:不可以)
     * */
    private Integer customizable;
    /**
     * 预留时间(如果kind为layover的)
     * */
    private Integer layoverTime;
    /**
     * 首页展示权重(默认可编辑1-12，数字越小权重越靠前，0不参与l)
     * */
    private Integer popularLevel;
    /**
     * 线路类型(线路类型表)
     * */
    private Integer lineTypeNo;
    /**
     * 线路详情介绍
     * */
    private String detail;
    /**
     * 排序字段加排序规则组合体columnName desc
     * */
    private String orderByStr;
    /**
     * setLineNo 线路号
     * */
    public void setLineNo(Integer lineNo) {
        this.lineNo=lineNo;
    }

    /**
     * getLineNo 线路号
     * */
    public Integer getLineNo() {
        return lineNo;
    }

    /**
     * setLineNameShort 线路简称
     * */
    public void setLineNameShort(String lineNameShort) {
        this.lineNameShort=lineNameShort;
    }

    /**
     * getLineNameShort 线路简称
     * */
    public String getLineNameShort() {
        return lineNameShort;
    }

    /**
     * setLineName 线路名称
     * */
    public void setLineName(String lineName) {
        this.lineName=lineName;
    }

    /**
     * getLineName 线路名称
     * */
    public String getLineName() {
        return lineName;
    }

    /**
     * setAvailable 可预订时段
     * */
    public void setAvailable(String available) {
        this.available=available;
    }

    /**
     * getAvailable 可预订时段
     * */
    public String getAvailable() {
        return available;
    }

    /**
     * setDuration 持续时长
     * */
    public void setDuration(String duration) {
        this.duration=duration;
    }

    /**
     * getDuration 持续时长
     * */
    public String getDuration() {
        return duration;
    }

    /**
     * setIsPrivate 是否私人游(0:是；1:否)
     * */
    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate=isPrivate;
    }

    /**
     * getIsPrivate 是否私人游(0:是；1:否)
     * */
    public Integer getIsPrivate() {
        return isPrivate;
    }

    /**
     * setLanguage 适用语种
     * */
    public void setLanguage(String language) {
        this.language=language;
    }

    /**
     * getLanguage 适用语种
     * */
    public String getLanguage() {
        return language;
    }

    /**
     * setPickUpPoint 线路承载点(0:旅馆；1:机场；2:自定义)
     * */
    public void setPickUpPoint(Integer pickUpPoint) {
        this.pickUpPoint=pickUpPoint;
    }

    /**
     * getPickUpPoint 线路承载点(0:旅馆；1:机场；2:自定义)
     * */
    public Integer getPickUpPoint() {
        return pickUpPoint;
    }

    /**
     * setFinishPoint 结束点
     * */
    public void setFinishPoint(Integer finishPoint) {
        this.finishPoint=finishPoint;
    }

    /**
     * getFinishPoint 结束点
     * */
    public Integer getFinishPoint() {
        return finishPoint;
    }

    /**
     * setPickUpTime 建议开始时间(如果kind为layover,则your arrival time)
     * */
    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime=pickUpTime;
    }

    /**
     * getPickUpTime 建议开始时间(如果kind为layover,则your arrival time)
     * */
    public String getPickUpTime() {
        return pickUpTime;
    }

    /**
     * setEndTime 结束时间(默认为0)
     * */
    public void setEndTime(Integer endTime) {
        this.endTime=endTime;
    }

    /**
     * getEndTime 结束时间(默认为0)
     * */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * setPhyLevel 物理强度(1:moderate；2:moderate-intermediate；3:intermediate；4:intermediate-advanced；5:advanced)
     * */
    public void setPhyLevel(Integer phyLevel) {
        this.phyLevel=phyLevel;
    }

    /**
     * getPhyLevel 物理强度(1:moderate；2:moderate-intermediate；3:intermediate；4:intermediate-advanced；5:advanced)
     * */
    public Integer getPhyLevel() {
        return phyLevel;
    }

    /**
     * setHikingDis 徒步距离
     * */
    public void setHikingDis(String hikingDis) {
        this.hikingDis=hikingDis;
    }

    /**
     * getHikingDis 徒步距离
     * */
    public String getHikingDis() {
        return hikingDis;
    }

    /**
     * setHikingArea 徒步区域
     * */
    public void setHikingArea(String hikingArea) {
        this.hikingArea=hikingArea;
    }

    /**
     * getHikingArea 徒步区域
     * */
    public String getHikingArea() {
        return hikingArea;
    }

    /**
     * setAttractions 吸引力
     * */
    public void setAttractions(String attractions) {
        this.attractions=attractions;
    }

    /**
     * getAttractions 吸引力
     * */
    public String getAttractions() {
        return attractions;
    }

    /**
     * setCustomizable 可定制(0:可以；1:不可以)
     * */
    public void setCustomizable(Integer customizable) {
        this.customizable=customizable;
    }

    /**
     * getCustomizable 可定制(0:可以；1:不可以)
     * */
    public Integer getCustomizable() {
        return customizable;
    }

    /**
     * setLayoverTime 预留时间(如果kind为layover的)
     * */
    public void setLayoverTime(Integer layoverTime) {
        this.layoverTime=layoverTime;
    }

    /**
     * getLayoverTime 预留时间(如果kind为layover的)
     * */
    public Integer getLayoverTime() {
        return layoverTime;
    }

    /**
     * setPopularLevel 首页展示权重(默认可编辑1-12，数字越小权重越靠前，0不参与l)
     * */
    public void setPopularLevel(Integer popularLevel) {
        this.popularLevel=popularLevel;
    }

    /**
     * getPopularLevel 首页展示权重(默认可编辑1-12，数字越小权重越靠前，0不参与l)
     * */
    public Integer getPopularLevel() {
        return popularLevel;
    }

    /**
     * setLineTypeNo 线路类型(线路类型表)
     * */
    public void setLineTypeNo(Integer lineTypeNo) {
        this.lineTypeNo=lineTypeNo;
    }

    /**
     * getLineTypeNo 线路类型(线路类型表)
     * */
    public Integer getLineTypeNo() {
        return lineTypeNo;
    }

    /**
     * setDetail 线路详情介绍
     * */
    public void setDetail(String detail) {
        this.detail=detail;
    }

    /**
     * getDetail 线路详情介绍
     * */
    public String getDetail() {
        return detail;
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