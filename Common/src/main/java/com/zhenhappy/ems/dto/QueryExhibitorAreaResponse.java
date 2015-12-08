package com.zhenhappy.ems.dto;

import java.util.List;

/**
 * Created by wujianbin on 2014-07-10.
 */
public class QueryExhibitorAreaResponse extends BaseRequest {
	private List<ExhibitorAreaResponse> areas;

	public List<ExhibitorAreaResponse> getAreas() {
		return areas;
	}

	public void setAreas(List<ExhibitorAreaResponse> areas) {
		this.areas = areas;
	}
	
}
