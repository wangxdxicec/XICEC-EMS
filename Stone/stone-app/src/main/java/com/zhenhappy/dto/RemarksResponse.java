package com.zhenhappy.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 苏志锋
 * 
 */
public class RemarksResponse extends BaseResponse {
	private List<RemarkInfoResponse> items = new ArrayList<RemarkInfoResponse>();
	
	private boolean hasNext;

	public List<RemarkInfoResponse> getItems() {
		return items;
	}

	public void setItems(List<RemarkInfoResponse> items) {
		this.items = items;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
}
