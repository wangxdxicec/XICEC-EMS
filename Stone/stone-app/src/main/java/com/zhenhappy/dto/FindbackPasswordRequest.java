package com.zhenhappy.dto;

/**
 * 找回密码请求
 * 
 * @author wujianbin
 * 
 */
public class FindbackPasswordRequest extends BaseRequest {

	private String phone;

	private String checkCode;

	private String newPassword;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
