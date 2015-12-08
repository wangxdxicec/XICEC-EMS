package com.zhenhappy.ems.manager.dto;

import java.util.Date;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-08-09.
 */
public class AddExhibitorInfoRequest extends BaseRequest {

	private Integer einfoid;
	private Integer eid;
	private String organizationCode;
	private String company;
	private String companyEn;
	private String phone;
	private String fax;
	private String email;
	private String website;
	private String address;
	private String addressEn;
	private String meipai;
	private String meipaiEn;
	private String zipcode;
	private String mainProduct;
	private String mainProductEn;
	private String logo;
	private String mark;
	private Integer isDelete;
	private Date createTime;
	private Date updateTime;
	private Integer adminUser;
	private Date adminUpdateTime;
	private String productType;
	private String invoiceTitle;
	private String invoiceNo;

	public AddExhibitorInfoRequest() {
		super();
	}

	public AddExhibitorInfoRequest(Integer einfoid, Integer eid,
			String organizationCode, String company, String companyEn,
			String phone, String fax, String email, String website,
			String address, String addressEn, String meipai, String zipcode,
			String mainProduct, String mainProductEn, String logo, String mark,
			Integer isDelete, Date createTime, Date updateTime,
			Integer adminUser, Date adminUpdateTime) {
		super();
		this.einfoid = einfoid;
		this.eid = eid;
		this.organizationCode = organizationCode;
		this.company = company;
		this.companyEn = companyEn;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.website = website;
		this.address = address;
		this.addressEn = addressEn;
		this.meipai = meipai;
		this.zipcode = zipcode;
		this.mainProduct = mainProduct;
		this.mainProductEn = mainProductEn;
		this.logo = logo;
		this.mark = mark;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.adminUser = adminUser;
		this.adminUpdateTime = adminUpdateTime;
	}

	public Integer getEinfoid() {
		return einfoid;
	}

	public void setEinfoid(Integer einfoid) {
		this.einfoid = einfoid;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyEn() {
		return companyEn;
	}

	public void setCompanyEn(String companyEn) {
		this.companyEn = companyEn;
	}

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

	public String getAddressEn() {
		return addressEn;
	}

	public void setAddressEn(String addressEn) {
		this.addressEn = addressEn;
	}

	public String getMeipai() {
		return meipai;
	}

	public void setMeipai(String meipai) {
		this.meipai = meipai;
	}

	public String getMeipaiEn() {
		return meipaiEn;
	}

	public void setMeipaiEn(String meipaiEn) {
		this.meipaiEn = meipaiEn;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}

	public String getMainProductEn() {
		return mainProductEn;
	}

	public void setMainProductEn(String mainProductEn) {
		this.mainProductEn = mainProductEn;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(Integer adminUser) {
		this.adminUser = adminUser;
	}

	public Date getAdminUpdateTime() {
		return adminUpdateTime;
	}

	public void setAdminUpdateTime(Date adminUpdateTime) {
		this.adminUpdateTime = adminUpdateTime;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Override
	public String toString() {
		return "ModifyExhibitorInfoRequest [einfoid=" + einfoid + ", eid="
				+ eid + ", organizationCode=" + organizationCode + ", company="
				+ company + ", companyEn=" + companyEn + ", phone=" + phone
				+ ", fax=" + fax + ", email=" + email + ", website=" + website
				+ ", address=" + address + ", addressEn=" + addressEn
				+ ", meipai=" + meipai + ", zipcode=" + zipcode
				+ ", mainProduct=" + mainProduct + ", mainProductEn="
				+ mainProductEn + ", logo=" + logo + ", mark=" + mark
				+ ", isDelete=" + isDelete + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", adminUser=" + adminUser
				+ ", adminUpdateTime=" + adminUpdateTime + ", productType="
				+ productType + ", invoiceTitle=" + invoiceTitle
				+ ", invoiceNo=" + invoiceNo + ", getEinfoid()=" + getEinfoid()
				+ ", getEid()=" + getEid() + ", getOrganizationCode()="
				+ getOrganizationCode() + ", getCompany()=" + getCompany()
				+ ", getCompanyEn()=" + getCompanyEn() + ", getPhone()="
				+ getPhone() + ", getFax()=" + getFax() + ", getEmail()="
				+ getEmail() + ", getWebsite()=" + getWebsite()
				+ ", getAddress()=" + getAddress() + ", getAddressEn()="
				+ getAddressEn() + ", getMeipai()=" + getMeipai()
				+ ", getZipcode()=" + getZipcode() + ", getMainProduct()="
				+ getMainProduct() + ", getMainProductEn()="
				+ getMainProductEn() + ", getLogo()=" + getLogo()
				+ ", getMark()=" + getMark() + ", getIsDelete()="
				+ getIsDelete() + ", getCreateTime()=" + getCreateTime()
				+ ", getUpdateTime()=" + getUpdateTime() + ", getAdminUser()="
				+ getAdminUser() + ", getAdminUpdateTime()="
				+ getAdminUpdateTime() + ", getProductType()="
				+ getProductType() + ", getInvoiceTitle()=" + getInvoiceTitle()
				+ ", getInvoiceNo()=" + getInvoiceNo() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
