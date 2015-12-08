package com.zhenhappy.dto;

import java.util.List;

/**
 * 
 * @author 苏志锋
 * 
 */
public class WscTopicsResponse extends BaseResponse {
	private List<WscTopicInfoResponse> items;
	
	private boolean hasNext;

	public List<WscTopicInfoResponse> getItems() {
		return items;
	}

	public void setItems(List<WscTopicInfoResponse> items) {
		this.items = items;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
}
