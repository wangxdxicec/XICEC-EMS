package com.zhenhappy.dto;

/**
 * User: Haijian Liang
 * Date: 13-11-22
 * Time: 下午8:25
 * Function:
 */
public class ModifyUserInfoRequest extends AfterLoginRequest {
	
	private String email;
	
	private String phone;
	
	private String name;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
