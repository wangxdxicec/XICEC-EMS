package com.zhenhappy.dto;

import java.util.List;

/**
 * User: Haijian Liang
 * Date: 13-11-21
 * Time: 下午10:43
 * Function:
 */
public class CompaniesResponse extends BaseResponse {
	private List<BaseCompanyInfoDto> companies;
	
	private boolean hasNext;

	public List<BaseCompanyInfoDto> getCompanies() {
		return companies;
	}

	public void setCompanies(List<BaseCompanyInfoDto> companies) {
		this.companies = companies;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
}
