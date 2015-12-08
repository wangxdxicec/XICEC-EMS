package com.zhenhappy.dto;

/**
 * 
 * @author 苏志锋
 * 
 */
public class RemarkInfoResponse extends BaseResponse {
	private WscInfoResponse wsc;
	private CompanyInfoResponse company;
	private Integer remarkType;

	public RemarkInfoResponse() {
	}

	public RemarkInfoResponse(WscInfoResponse wsc, CompanyInfoResponse company, Integer remarkType) {
		this.wsc = wsc;
		this.company = company;
		this.remarkType = remarkType;
	}

	public WscInfoResponse getWsc() {
		return wsc;
	}

	public void setWsc(WscInfoResponse wsc) {
		this.wsc = wsc;
	}

	public CompanyInfoResponse getCompany() {
		return company;
	}

	public void setCompany(CompanyInfoResponse company) {
		this.company = company;
	}

	public Integer getRemarkType() {
		return remarkType;
	}

	public void setRemarkType(Integer remarkType) {
		this.remarkType = remarkType;
	}

}
