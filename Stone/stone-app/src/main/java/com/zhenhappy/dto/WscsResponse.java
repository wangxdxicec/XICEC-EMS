package com.zhenhappy.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 苏志锋
 * 
 */
public class WscsResponse extends BaseResponse {
	private List<WscInfoResponse> items = new ArrayList<WscInfoResponse>();
	
	private boolean hasNext;

	public List<WscInfoResponse> getItems() {
		return items;
	}

	public void setItems(List<WscInfoResponse> items) {
		this.items = items;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
}
