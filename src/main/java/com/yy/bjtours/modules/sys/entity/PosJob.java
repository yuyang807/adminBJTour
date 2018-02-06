package com.yy.bjtours.modules.sys.entity;

import com.yy.bjtours.common.persistence.DataEntity;

public class PosJob extends DataEntity<PosJob>{

	private static final long serialVersionUID = 3276842737745563959L;
	/** 任务日期(yyyy-MM-dd)*/
	private String jobDate;
	/**任务名称(常量名称)*/
	private String jobName;
	/**'任务状态 1:创建,2:执行中,3:执行成功,执行失败',*/
	private Integer jobState;
	/**任务类型 POS:线下通道，PAY:线上通道 , qblife:钱包生活*/
	private String jobType;
	/**任务统计状态1:未统计；2:统计成功；3:统计失败*/
	private Integer collectState;
	
	
	
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public Integer getCollectState() {
		return collectState;
	}
	public void setCollectState(Integer collectState) {
		this.collectState = collectState;
	}
	public String getJobDate() {
		return jobDate;
	}
	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public Integer getJobState() {
		return jobState;
	}
	public void setJobState(Integer jobState) {
		this.jobState = jobState;
	}
	
	

}
