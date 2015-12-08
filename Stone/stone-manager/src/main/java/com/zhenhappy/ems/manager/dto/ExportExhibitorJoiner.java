package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.entity.TExhibitorJoiner;

/**
 * Created by wujianbin on 2014-12-12.
 */
public class ExportExhibitorJoiner extends TExhibitorJoiner {
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
