package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.context.annotation.Lazy;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * AbstractTBusinessCard entity provides the base persistence definition of the
 * TBusinessCard entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTBusinessCard implements java.io.Serializable {

	/**
     *
     */
	private static final long serialVersionUID = 8615430173904504938L;
	private Integer cardId;
	private Integer userId;
	private String name;
	private String telephone;
	private String phone;
	private String fax;
	private String email;
	private String website;
	private String company;
	private String position;
	private String address;
	private Integer isDelete;
	private String qrcode;
	private Integer isDefault;
	private String country;
	private String city;
	private String province;
	private String businessActivity;
	private String sex;
	private String intent;
	private String backupEmail;

	// Constructors

	@Column(name = "isDelete")
	@JSONField(serialize=false)
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name = "isDefault")
	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * default constructor
	 */
	public AbstractTBusinessCard() {
	}

	/**
	 * minimal constructor
	 */
	public AbstractTBusinessCard(Integer cardId) {
		this.cardId = cardId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id", unique = true, nullable = false)
	public Integer getCardId() {
		return this.cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	@Column(name = "name", length = 255)
	public String getName() {
		return this.name;
	}
	
	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "telephone", length = 255)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "phone", length = 255)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "fax", length = 255)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "email", length = 255)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "website", length = 255)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "company", length = 255)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "position", length = 255)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "address", length = 255)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "qrcode", length = 255)
	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	@Column(name = "country", length = 255)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "city", length = 255)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "province", length = 255)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "business_activity", length = 255)
	public String getBusinessActivity() {
		return businessActivity;
	}

	public void setBusinessActivity(String businessActivity) {
		this.businessActivity = businessActivity;
	}

	@Column(name = "sex", length = 255)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "intent", length = 255)
	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	@Column(name = "backup_email", length = 255)
	public String getBackupEmail() {
		return backupEmail;
	}

	public void setBackupEmail(String backupEmail) {
		this.backupEmail = backupEmail;
	}
}