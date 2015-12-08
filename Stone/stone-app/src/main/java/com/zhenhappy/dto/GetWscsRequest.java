package com.zhenhappy.dto;

/**
 * 
 * @author 苏志锋
 * 
 */
public class GetWscsRequest extends AfterLoginRequest {
	private String name;
	private Integer pageIndex;

	private Integer pageSize;

	private Integer type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
