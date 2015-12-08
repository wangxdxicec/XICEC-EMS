package com.zhenhappy.dto;

/**
 * 修改头像请求的反馈
 * @author wujianbin
 *
 */
public class ModifyHeadResponse extends BaseResponse{
	
	/**
	 * 用户新头像的地址
	 */
	private String newHeadUrl;

	public String getNewHeadUrl() {
		return newHeadUrl;
	}

	public void setNewHeadUrl(String newHeadUrl) {
		this.newHeadUrl = newHeadUrl;
	}
}
