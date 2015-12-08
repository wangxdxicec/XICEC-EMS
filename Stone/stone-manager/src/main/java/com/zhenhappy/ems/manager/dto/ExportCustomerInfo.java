package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.entity.WCustomer;

/**
 * Created by wujianbin on 2014-12-13.
 */
public class ExportCustomerInfo extends WCustomer {
	private String name;
	private String countryString;
	private String phone;
	private String tel;
	private String faxString;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryString() {
		return countryString;
	}

	public void setCountryString(String countryString) {
		this.countryString = countryString;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFaxString() {
		return faxString;
	}

	public void setFaxString(String faxString) {
		this.faxString = faxString;
	}
}
