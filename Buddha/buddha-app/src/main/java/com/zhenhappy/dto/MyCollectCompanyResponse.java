package com.zhenhappy.dto;

import java.util.List;

public class MyCollectCompanyResponse extends BaseResponse{
	
	private List<BaseCompanyInfoDto> companies;
	
	private boolean hasNext;

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public List<BaseCompanyInfoDto> getCompanies() {
		return companies;
	}

	public void setCompanies(List<BaseCompanyInfoDto> companies) {
		this.companies = companies;
	}
	
}
