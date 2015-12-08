package com.zhenhappy.dto;

/**
 * 客商调查表
 * @author wujianbin
 */
public class SubInterestSurveyRequest extends AfterLoginRequest{
	
    private String company;
    private String customerName;
    private String position;
    private String country;
    private String province;
    private String city;
    private String postcode;
    private String telephone;
    private String fax;
    private String email;
    private String website;
    private String address;
    private String industry;
    private String interestProduct;
    private String authoritativeMedia;
    private String getFairInfo;
    private Integer isGetData;
    private Integer isSendPost;
    
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getInterestProduct() {
		return interestProduct;
	}
	public void setInterestProduct(String interestProduct) {
		this.interestProduct = interestProduct;
	}
	public String getAuthoritativeMedia() {
		return authoritativeMedia;
	}
	public void setAuthoritativeMedia(String authoritativeMedia) {
		this.authoritativeMedia = authoritativeMedia;
	}
	public String getGetFairInfo() {
		return getFairInfo;
	}
	public void setGetFairInfo(String getFairInfo) {
		this.getFairInfo = getFairInfo;
	}
	public Integer getIsGetData() {
		return isGetData;
	}
	public void setIsGetData(Integer isGetData) {
		this.isGetData = isGetData;
	}
	public Integer getIsSendPost() {
		return isSendPost;
	}
	public void setIsSendPost(Integer isSendPost) {
		this.isSendPost = isSendPost;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
