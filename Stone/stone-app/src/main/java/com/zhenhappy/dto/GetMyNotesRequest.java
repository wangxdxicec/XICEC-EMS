package com.zhenhappy.dto;

import java.util.List;

/**
 * 
 * @author 苏志锋
 * 
 */
public class GetMyNotesRequest extends AfterLoginRequest {

	private List<Integer> wscIds;
	private List<Integer> companyIds;

	public List<Integer> getWscIds() {
		return wscIds;
	}

	public void setWscIds(List<Integer> wscIds) {
		this.wscIds = wscIds;
	}

	public List<Integer> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Integer> companyIds) {
		this.companyIds = companyIds;
	}

}
