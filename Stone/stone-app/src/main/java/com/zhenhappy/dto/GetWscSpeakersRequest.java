package com.zhenhappy.dto;

/**
 * 
 * @author 苏志锋
 * 
 */
public class GetWscSpeakersRequest extends BaseRequest {

	private Integer pageIndex;

	private Integer pageSize;

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
