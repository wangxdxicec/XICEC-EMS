package com.zhenhappy.dto;

import java.util.List;
import java.util.Map;

public class CountryResponse extends BaseResponse {
	private List<Map<String, Object>> country;

	public List<Map<String, Object>> getCountry() {
		return country;
	}

	public void setCountry(List<Map<String, Object>> country) {
		this.country = country;
	}
}
