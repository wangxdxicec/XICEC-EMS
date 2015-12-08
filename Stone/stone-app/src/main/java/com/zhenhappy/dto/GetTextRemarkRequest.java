package com.zhenhappy.dto;

public class GetTextRemarkRequest extends AfterLoginRequest {

	private Integer itemId;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


}
