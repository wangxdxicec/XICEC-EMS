package com.zhenhappy.dto;

/**
 * 
 * @author rocsky
 * 
 */
public class VisitorRegisterRequest extends AfterLoginRequest {
	private Integer visitorId;
	private String name;
	private String companyName;
	private String tel;
	private String email;
	private String position;

	public Integer getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	public Boolean getIsNone() {
		return null == visitorId;
	}
}
