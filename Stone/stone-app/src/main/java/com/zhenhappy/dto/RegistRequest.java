package com.zhenhappy.dto;


/**
 * User: Haijian Liang Date: 13-11-18 Time: 下午9:38 Function:
 */
public class RegistRequest extends BaseRequest {

	/**
	 * 注册的手机号（国内用户）
	 */
	private String phone;

	/**
	 * 注册手机验证码（国内用户）
	 */
	private String checkCode;

	/**
	 * 国外用户注册（必填）
	 */
	private String email;

	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户中文名
	 */
	private String name;
	/**
	 * 用户所在公司
	 */
	private String company;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 用户类型 1为国内，2为国外
	 */
	private Integer userType;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
