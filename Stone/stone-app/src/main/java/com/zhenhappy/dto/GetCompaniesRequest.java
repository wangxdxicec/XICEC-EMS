package com.zhenhappy.dto;

import java.util.List;

public class GetCompaniesRequest extends PageDataRequest {
	private String condition;
	private List<Integer> ids;
	private Integer type;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
