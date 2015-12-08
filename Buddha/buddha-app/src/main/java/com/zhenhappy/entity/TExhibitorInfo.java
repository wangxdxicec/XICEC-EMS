package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author 苏志锋
 * @version 1.0
 * @since 1.0
 */


@Entity
@Table(name = "t_exhibitor_info")
public class TExhibitorInfo implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ExhibitorInfo";
	public static final String ALIAS_EINFOID = "einfoid";
	public static final String ALIAS_EID = "eid";
	public static final String ALIAS_ORGANIZATION_CODE = "organizationCode";
	public static final String ALIAS_COMPANY = "company";
	public static final String ALIAS_COMPANY_EN = "companyEn";
	public static final String ALIAS_PHONE = "phone";
	public static final String ALIAS_FAX = "fax";
	public static final String ALIAS_EMAIL = "email";
	public static final String ALIAS_WEBSITE = "website";
	public static final String ALIAS_ZIPCODE = "zipcode";
	public static final String ALIAS_MAIN_PRODUCT = "mainProduct";
	public static final String ALIAS_MAIN_PRODUCT_EN = "mainProductEn";
	public static final String ALIAS_LOGO = "logo";
	public static final String ALIAS_MARK = "mark";
	public static final String ALIAS_IS_DELETE = "isDelete";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_ADMIN_USER = "adminUser";
	public static final String ALIAS_ADMIN_UPDATE_TIME = "adminUpdateTime";
	public static final String ALIAS_ADDRESS = "address";
	public static final String ALIAS_ADDRESS_EN = "addressEn";
	public static final String ALIAS_MEIPAI = "meipai";
	
	private java.lang.Integer einfoid;
	
	private java.lang.Integer eid;
	private java.lang.String organizationCode;
	private java.lang.String company;
	private java.lang.String companyEn;
	private java.lang.String phone;
	private java.lang.String fax;
	private java.lang.String email;
	private java.lang.String website;
	private java.lang.String zipcode;
	private java.lang.String mainProduct;
	private java.lang.String mainProductEn;
	private java.lang.String logo;
	private java.lang.String mark;
	private java.lang.Integer isDelete;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private java.lang.Integer adminUser;
	private java.util.Date adminUpdateTime;
	private java.lang.String address;
	private java.lang.String addressEn;
	private java.lang.String meipai;

	@Id
	@Column(name = "einfoid")
	public java.lang.Integer getEinfoid() {
		return this.einfoid;
	}
	
	public void setEinfoid(java.lang.Integer value) {
		this.einfoid = value;
	}
	
	@Column(name = "eid")
	public java.lang.Integer getEid() {
		return this.eid;
	}
	
	public void setEid(java.lang.Integer value) {
		this.eid = value;
	}
	
	@Column(name = "organization_code")
	public java.lang.String getOrganizationCode() {
		return this.organizationCode;
	}
	
	public void setOrganizationCode(java.lang.String value) {
		this.organizationCode = value;
	}
	
	@Column(name = "company")
	public java.lang.String getCompany() {
		return this.company;
	}
	
	public void setCompany(java.lang.String value) {
		this.company = value;
	}
	
	@Column(name = "company_en")
	public java.lang.String getCompanyEn() {
		return this.companyEn;
	}
	
	public void setCompanyEn(java.lang.String value) {
		this.companyEn = value;
	}
	
	@Column(name = "phone")
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	@Column(name = "fax")
	public java.lang.String getFax() {
		return this.fax;
	}
	
	public void setFax(java.lang.String value) {
		this.fax = value;
	}
	
	@Column(name = "email")
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	@Column(name = "website")
	public java.lang.String getWebsite() {
		return this.website;
	}
	
	public void setWebsite(java.lang.String value) {
		this.website = value;
	}
	
	@Column(name = "zipcode")
	public java.lang.String getZipcode() {
		return this.zipcode;
	}
	
	public void setZipcode(java.lang.String value) {
		this.zipcode = value;
	}
	
	@Column(name = "main_product")
	public java.lang.String getMainProduct() {
		return this.mainProduct;
	}
	
	public void setMainProduct(java.lang.String value) {
		this.mainProduct = value;
	}
	
	@Column(name = "main_product_en")
	public java.lang.String getMainProductEn() {
		return this.mainProductEn;
	}
	
	public void setMainProductEn(java.lang.String value) {
		this.mainProductEn = value;
	}
	
	@Column(name = "logo")
	public java.lang.String getLogo() {
		return this.logo;
	}
	
	public void setLogo(java.lang.String value) {
		this.logo = value;
	}
	
	@Column(name = "mark")
	public java.lang.String getMark() {
		return this.mark;
	}
	
	public void setMark(java.lang.String value) {
		this.mark = value;
	}
	
	@Column(name = "is_delete")
	public java.lang.Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.Integer value) {
		this.isDelete = value;
	}
	
	@Column(name = "create_time")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	@Column(name = "update_time")
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	@Column(name = "admin_user")
	public java.lang.Integer getAdminUser() {
		return this.adminUser;
	}
	
	public void setAdminUser(java.lang.Integer value) {
		this.adminUser = value;
	}
	
	@Column(name = "admin_update_time")
	public java.util.Date getAdminUpdateTime() {
		return this.adminUpdateTime;
	}
	
	public void setAdminUpdateTime(java.util.Date value) {
		this.adminUpdateTime = value;
	}
	
	@Column(name = "address")
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	@Column(name = "address_en")
	public java.lang.String getAddressEn() {
		return this.addressEn;
	}
	
	public void setAddressEn(java.lang.String value) {
		this.addressEn = value;
	}
	
	@Column(name = "meipai")
	public java.lang.String getMeipai() {
		return this.meipai;
	}
	
	public void setMeipai(java.lang.String value) {
		this.meipai = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Einfoid",getEinfoid())
			.append("Eid",getEid())
			.append("OrganizationCode",getOrganizationCode())
			.append("Company",getCompany())
			.append("CompanyEn",getCompanyEn())
			.append("Phone",getPhone())
			.append("Fax",getFax())
			.append("Email",getEmail())
			.append("Website",getWebsite())
			.append("Zipcode",getZipcode())
			.append("MainProduct",getMainProduct())
			.append("MainProductEn",getMainProductEn())
			.append("Logo",getLogo())
			.append("Mark",getMark())
			.append("IsDelete",getIsDelete())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("AdminUser",getAdminUser())
			.append("AdminUpdateTime",getAdminUpdateTime())
			.append("Address",getAddress())
			.append("AddressEn",getAddressEn())
			.append("Meipai",getMeipai())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TExhibitorInfo == false) return false;
		if(this == obj) return true;
		TExhibitorInfo other = (TExhibitorInfo)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

