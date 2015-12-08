package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-04-26.
 */
public class AddExhibitorRequest extends BaseRequest {
	private String exhibitionArea;
	private String username;
	private String password;
	private String boothNumber;
	private Integer level = 1;
	private String companyName;
	private String companyNameE;
	private Integer tag;
	private Integer province;
	private Integer country;
	private Integer area;

	public String getExhibitionArea() {
		return exhibitionArea;
	}

	public void setExhibitionArea(String exhibitionArea) {
		this.exhibitionArea = exhibitionArea;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBoothNumber() {
		return boothNumber;
	}

	public void setBoothNumber(String boothNumber) {
		this.boothNumber = boothNumber;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNameE() {
		return companyNameE;
	}

	public void setCompanyNameE(String companyNameE) {
		this.companyNameE = companyNameE;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

}
