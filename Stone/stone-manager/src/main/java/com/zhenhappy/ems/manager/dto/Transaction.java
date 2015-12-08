package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-07-03.
 */
public class Transaction extends BaseRequest {
	private String boothNumber;
	private String company;
	private String companye;
	private String address;
	private String addressEn;
//	private String zipcode;
	private String phone;
	private String fax;
	private String website;
	private String email;
//	private String mark;
	private String mainProduct;
	private String mainProductEn;
	private String product;
	private String productEn;
	private String productOther;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressEn() {
		return addressEn;
	}

	public void setAddressEn(String addressEn) {
		this.addressEn = addressEn;
	}

	/*public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}*/

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}*/

	public String getMainProductEn() {
		return mainProductEn;
	}

	public void setMainProductEn(String mainProductEn) {
		this.mainProductEn = mainProductEn;
	}

	public String getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductEn() {
		return productEn;
	}

	public void setProductEn(String productEn) {
		this.productEn = productEn;
	}

	public String getProductOther() {
		return productOther;
	}

	public void setProductOther(String productOther) {
		this.productOther = productOther;
	}
}
