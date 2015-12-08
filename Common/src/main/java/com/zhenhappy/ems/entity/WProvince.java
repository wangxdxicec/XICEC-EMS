package com.zhenhappy.ems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WprovinceInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WProvinceInfo", schema = "dbo")
public class WProvince implements java.io.Serializable {

	// Fields

	private Integer id;
	private String provinceKey;
	private String provinceValue;
	private String chineseName;
	private Integer countryId;
	private String areaCode;

	// Constructors

	/** default constructor */
	public WProvince() {
	}

	/** minimal constructor */
	public WProvince(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WProvince(Integer id, String provinceKey, String provinceValue,
			String chineseName, Integer countryId, String areaCode) {
		this.id = id;
		this.provinceKey = provinceKey;
		this.provinceValue = provinceValue;
		this.chineseName = chineseName;
		this.countryId = countryId;
		this.areaCode = areaCode;
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

	@Column(name = "ProvinceKey")
	public String getProvinceKey() {
		return this.provinceKey;
	}

	public void setProvinceKey(String provinceKey) {
		this.provinceKey = provinceKey;
	}

	@Column(name = "ProvinceValue")
	public String getProvinceValue() {
		return this.provinceValue;
	}

	public void setProvinceValue(String provinceValue) {
		this.provinceValue = provinceValue;
	}

	@Column(name = "ChineseName")
	public String getChineseName() {
		return this.chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	@Column(name = "CountryID")
	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "AreaCode")
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Override
	public String toString() {
		return "WProvinceInfo [id=" + id + ", provinceKey=" + provinceKey
				+ ", provinceValue=" + provinceValue + ", chineseName="
				+ chineseName + ", countryId=" + countryId + ", areaCode="
				+ areaCode + "]";
	}

}