package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author rocsky
 * 
 */
@Entity
@Table(name = "t_preregister_visitor", schema = "dbo")
public class TVisitorRegister implements java.io.Serializable {
	private Integer visitorId;
	private String email;
	private String name;
	private String sex;
	private String companyName;
	private String position;
	private String country;
	private String province;
	private String city;
	private String address;
	private String phone;
	private String tel;
	private String fax;
	private String backupEmail;
	private String website;
	private String businessActivity;
	private String intent;

	public TVisitorRegister(){
	}

	public TVisitorRegister(Integer visitorId, String email, String name, String sex, String companyName, String position, String country, String province, String city, String address, String phone, String tel, String fax, String backupEmail, String website, String businessActivity, String intent) {
		this.visitorId = visitorId;
		this.email = email;
		this.name = name;
		this.sex = sex;
		this.companyName = companyName;
		this.position = position;
		this.country = country;
		this.province = province;
		this.city = city;
		this.address = address;
		this.phone = phone;
		this.tel = tel;
		this.fax = fax;
		this.backupEmail = backupEmail;
		this.website = website;
		this.businessActivity = businessActivity;
		this.intent = intent;
	}

	@Id
	@Column(name = "visitor_id", unique = true, nullable = false)
	public Integer getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "position")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "fax")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "backup_email")
	public String getBackupEmail() {
		return backupEmail;
	}

	public void setBackupEmail(String backupEmail) {
		this.backupEmail = backupEmail;
	}

	@Column(name = "website")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "business_activity")
	public String getBusinessActivity() {
		return businessActivity;
	}

	public void setBusinessActivity(String businessActivity) {
		this.businessActivity = businessActivity;
	}

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "intent")
	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	@Transient
	public Boolean getIsNone() {
		return null == visitorId;
	}
}
