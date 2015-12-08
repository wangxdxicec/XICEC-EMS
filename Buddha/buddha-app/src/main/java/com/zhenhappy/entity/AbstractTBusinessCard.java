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
	private String qrcode;
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
	private Integer isdefault;

	// Constructors

	@Column(name = "isDelete")
	@JSONField(serialize=false)
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name = "isdefault")
	public Integer getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
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

	@Column(name = "name", length = 100)
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

	@Column(name = "telephone", length = 100)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "phone", length = 100)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "fax", length = 100)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "website", length = 100)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "company", length = 100)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "position", length = 100)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "qrcode", length = 200)
	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
}