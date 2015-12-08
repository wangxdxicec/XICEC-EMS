package com.zhenhappy.dto;

import com.zhenhappy.entity.TUserWsc;

import java.util.List;

public class MyCollectWSCResponse extends BaseResponse{
	
	private List<TUserWsc> userWscs;
	
	private boolean hasNext;

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public List<TUserWsc> getUserWscs() {
		return userWscs;
	}

	public void setUserWscs(List<TUserWsc> userWscs) {
		this.userWscs = userWscs;
	}
}
