package com.yy.bjtours.modules.web.dto;

import com.yy.bjtours.common.persistence.DataEntity;

/**
  * 包含线路类型的dto
  * @version 2017-12-30  * */ 
public class TLineDtoVo extends DataEntity<TLineDtoVo> {
    /**
     * 类型名称
     * */
    private String type_name;
    
    private TLineDto lineDto;
    
    public TLineDto getLineDto() {
		return lineDto;
	}

	public void setLineDto(TLineDto lineDto) {
		this.lineDto = lineDto;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	
}