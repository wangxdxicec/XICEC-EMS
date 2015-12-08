package com.zhenhappy.dto;

public class GetCompaniesByTypeRequest extends BaseRequest{
	
	private Integer userId;
	
	private String fatherTypeCode;
	
	private String childTypeCode;
	
	private Integer pageIndex;
	
	private Integer pageSize;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFatherTypeCode() {
		return fatherTypeCode;
	}

	public void setFatherTypeCode(String fatherTypeCode) {
		this.fatherTypeCode = fatherTypeCode;
	}

	public String getChildTypeCode() {
		return childTypeCode;
	}

	public void setChildTypeCode(String childTypeCode) {
		this.childTypeCode = childTypeCode;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	} 

}
