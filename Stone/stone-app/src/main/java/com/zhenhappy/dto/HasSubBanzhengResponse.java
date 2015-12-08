package com.zhenhappy.dto;

public class HasSubBanzhengResponse extends BaseResponse{
	
	private String company;
	private String customerName;
	private String position;
	private String country;
	private String interestProduct;
	
	private Boolean hasSub;

	public Boolean getHasSub() {
		return hasSub;
	}

	public void setHasSub(Boolean hasSub) {
		this.hasSub = hasSub;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getInterestProduct() {
		return interestProduct;
	}

	public void setInterestProduct(String interestProduct) {
		this.interestProduct = interestProduct;
	}

}
