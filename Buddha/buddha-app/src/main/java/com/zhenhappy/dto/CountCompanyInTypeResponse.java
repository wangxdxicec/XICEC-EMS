package com.zhenhappy.dto;

import java.util.Map;

public class CountCompanyInTypeResponse extends BaseResponse{
	
	private Map<String, Integer> type_count;

	public Map<String, Integer> getType_count() {
		return type_count;
	}

	public void setType_count(Map<String, Integer> type_count) {
		this.type_count = type_count;
	}
	
}
