package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.entity.TContact;

/**
 * Created by wujianbin on 2015-03-30.
 */
public class ExportContact extends TContact {
	private String boothNumber;
	private String company;
	private String companye;

	public String getBoothNumber() {
		return boothNumber;
	}

	public void setBoothNumber(String boothNumber) {
		this.boothNumber = boothNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanye() {
		return companye;
	}

	public void setCompanye(String companye) {
		this.companye = companye;
	}
}
