package com.zhenhappy.ems.manager.dto;

/**
 * Created by wujianbin on 2014-04-22.
 */
public class QueryExhibitorRequest extends EasyuiRequest {

	private String boothNumber;
	private String company;
	private String companye;
	private Integer province;
	private Integer country;
	private Integer isLogout;
	private Integer tag;
	private Integer area;
	private Integer group;
	private String contractId;

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

	public Integer getIsLogout() {
		return isLogout;
	}

	public void setIsLogout(Integer isLogout) {
		this.isLogout = isLogout;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
}
