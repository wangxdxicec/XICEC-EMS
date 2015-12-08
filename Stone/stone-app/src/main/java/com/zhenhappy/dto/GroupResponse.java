package com.zhenhappy.dto;

import java.util.List;
import java.util.Map;

public class GroupResponse extends BaseResponse {
	private List<Map<String, Object>> group;

	public List<Map<String, Object>> getGroup() {
		return group;
	}

	public void setGroup(List<Map<String, Object>> group) {
		this.group = group;
	}
}
