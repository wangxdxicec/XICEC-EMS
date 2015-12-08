package com.zhenhappy.ems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WcountryInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WCountryInfo", schema = "dbo")
public class WCountry implements java.io.Serializable {

	// Fields

	private Integer id;
	private String countryKey;
	private String countryValue;
	private String chineseName;
	private String abbreviation;
	private String nationality;
	private Boolean visaneedBl;
	private String mobileCode;
	private String phoneCode;
	private String faxCode;

	// Constructors

	/** default constructor */
	public WCountry() {
	}

	/** minimal constructor */
	public WCountry(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WCountry(Integer id, String countryKey, String countryValue,
			String chineseName, String abbreviation, String nationality,
			Boolean visaneedBl, String mobileCode, String phoneCode,
			String faxCode) {
		this.id = id;
		this.countryKey = countryKey;
		this.countryValue = countryValue;
		this.chineseName = chineseName;
		this.abbreviation = abbreviation;
		this.nationality = nationality;
		this.visaneedBl = visaneedBl;
		this.mobileCode = mobileCode;
		this.phoneCode = phoneCode;
		this.faxCode = faxCode;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CountryKey")
	public String getCountryKey() {
		return this.countryKey;
	}

	public void setCountryKey(String countryKey) {
		this.countryKey = countryKey;
	}

	@Column(name = "CountryValue")
	public String getCountryValue() {
		return this.countryValue;
	}

	public void setCountryValue(String countryValue) {
		this.countryValue = countryValue;
	}

	@Column(name = "ChineseName")
	public String getChineseName() {
		return this.chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	@Column(name = "Abbreviation")
	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@Column(name = "Nationality")
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "VISANeedBL")
	public Boolean getVisaneedBl() {
		return this.visaneedBl;
	}

	public void setVisaneedBl(Boolean visaneedBl) {
		this.visaneedBl = visaneedBl;
	}

	@Column(name = "MobileCode")
	public String getMobileCode() {
		return this.mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	@Column(name = "PhoneCode")
	public String getPhoneCode() {
		return this.phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	@Column(name = "FaxCode")
	public String getFaxCode() {
		return this.faxCode;
	}

	public void setFaxCode(String faxCode) {
		this.faxCode = faxCode;
	}

	@Override
	public String toString() {
		return "WCountryInfo [id=" + id + ", countryKey=" + countryKey
				+ ", countryValue=" + countryValue + ", chineseName="
				+ chineseName + ", abbreviation=" + abbreviation
				+ ", nationality=" + nationality + ", visaneedBl=" + visaneedBl
				+ ", mobileCode=" + mobileCode + ", phoneCode=" + phoneCode
				+ ", faxCode=" + faxCode + "]";
	}
	
	

}