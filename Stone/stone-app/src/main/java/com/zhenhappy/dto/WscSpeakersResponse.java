package com.zhenhappy.dto;

import java.util.List;

/**
 * 
 * @author 苏志锋
 * 
 */
public class WscSpeakersResponse extends BaseResponse {
	private List<WscSpeakerInfoResponse> items;
	
	private boolean hasNext;

	public List<WscSpeakerInfoResponse> getItems() {
		return items;
	}

	public void setItems(List<WscSpeakerInfoResponse> items) {
		this.items = items;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
}
