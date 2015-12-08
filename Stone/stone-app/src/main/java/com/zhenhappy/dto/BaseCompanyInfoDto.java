package com.zhenhappy.dto;

import org.apache.commons.lang.StringUtils;


/**
 * User: Haijian Liang Date: 13-11-21 Time: 下午10:42 Function:
 */
public class BaseCompanyInfoDto {

    private Integer companyId;
    private String exhibitionNo;
    private String country;
	private String countryE;
    private String company;
    private Integer isCollect;
	private String product;
	private String groupName;
	private String groupNameE;

	public String getCountryE() {
		return StringUtils.isBlank(countryE) ? "" : countryE;
	}

	public void setCountryE(String countryE) {
		this.countryE = countryE;
	}

	public String getGroupNameE() {
		return StringUtils.isBlank(groupNameE) ? "" : groupNameE;
	}

	public void setGroupNameE(String groupNameE) {
		this.groupNameE = groupNameE;
	}

	public String getProduct() {
		return StringUtils.isBlank(product) ? "" : product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getGroupName() {
		return StringUtils.isBlank(groupName) ? "" : groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCountry() {
		return StringUtils.isBlank(country) ? "" : country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return StringUtils.isBlank(company) ? "#" : company;
	}

	public void setCompany(String company) {
		this.company = company;
	}


	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public String getExhibitionNo() {
		return StringUtils.isBlank(exhibitionNo) ? "" : exhibitionNo;
	}

	public void setExhibitionNo(String exhibitionNo) {
		this.exhibitionNo = exhibitionNo;
	}
}
