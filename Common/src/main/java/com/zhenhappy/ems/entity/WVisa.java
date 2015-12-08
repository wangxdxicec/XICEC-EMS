package com.zhenhappy.ems.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WVisa entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WVisa", schema = "dbo")
public class WVisa implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer WCustomerInfo;
	private String fullPassportName;
	private String gender;
	private String nationality;
	private String passportNo;
	private Date expDate;
	private Date dateOfBirth;
	private String chineseEmbassy;
	private String consulateOfCity;
	private Date durationBeginTime;
	private Date durationEndTime;
	private String passportPage;
	private String businessLicense;
	private Integer wthInfoId;
	private Date createTime;
	private Date updateTime;
	private Boolean needPost;
	private String expressTp;
	private String expressNo;
	private Boolean isDisabled;

	// Constructors

	/** default constructor */
	public WVisa() {
	}

	/** minimal constructor */
	public WVisa(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WVisa(Integer id, Integer WCustomerInfo, String fullPassportName,
			String gender, String nationality, String passportNo,
			Date expDate, Date dateOfBirth, String chineseEmbassy,
			String consulateOfCity, Date durationBeginTime,
			Date durationEndTime, String passportPage,
			String businessLicense, Integer wthInfoId, Date createTime,
			Date updateTime, Boolean needPost, String expressTp,
			String expressNo, Boolean isDisabled) {
		this.id = id;
		this.WCustomerInfo = WCustomerInfo;
		this.fullPassportName = fullPassportName;
		this.gender = gender;
		this.nationality = nationality;
		this.passportNo = passportNo;
		this.expDate = expDate;
		this.dateOfBirth = dateOfBirth;
		this.chineseEmbassy = chineseEmbassy;
		this.consulateOfCity = consulateOfCity;
		this.durationBeginTime = durationBeginTime;
		this.durationEndTime = durationEndTime;
		this.passportPage = passportPage;
		this.businessLicense = businessLicense;
		this.wthInfoId = wthInfoId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.needPost = needPost;
		this.expressTp = expressTp;
		this.expressNo = expressNo;
		this.isDisabled = isDisabled;
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

	@Column(name = "WCustomerInfo")
	public Integer getWCustomerInfo() {
		return this.WCustomerInfo;
	}

	public void setWCustomerInfo(Integer WCustomerInfo) {
		this.WCustomerInfo = WCustomerInfo;
	}

	@Column(name = "FullPassportName")
	public String getFullPassportName() {
		return this.fullPassportName;
	}

	public void setFullPassportName(String fullPassportName) {
		this.fullPassportName = fullPassportName;
	}

	@Column(name = "Gender", length = 200)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "Nationality")
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "PassportNo", length = 200)
	public String getPassportNo() {
		return this.passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	@Column(name = "ExpDate", length = 23)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "DateOfBirth", length = 23)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "ChineseEmbassy")
	public String getChineseEmbassy() {
		return this.chineseEmbassy;
	}

	public void setChineseEmbassy(String chineseEmbassy) {
		this.chineseEmbassy = chineseEmbassy;
	}

	@Column(name = "ConsulateOfCity")
	public String getConsulateOfCity() {
		return this.consulateOfCity;
	}

	public void setConsulateOfCity(String consulateOfCity) {
		this.consulateOfCity = consulateOfCity;
	}

	@Column(name = "DurationBeginTime", length = 23)
	public Date getDurationBeginTime() {
		return this.durationBeginTime;
	}

	public void setDurationBeginTime(Date durationBeginTime) {
		this.durationBeginTime = durationBeginTime;
	}

	@Column(name = "DurationEndTime", length = 23)
	public Date getDurationEndTime() {
		return this.durationEndTime;
	}

	public void setDurationEndTime(Date durationEndTime) {
		this.durationEndTime = durationEndTime;
	}

	@Column(name = "PassportPage", length = 300)
	public String getPassportPage() {
		return this.passportPage;
	}

	public void setPassportPage(String passportPage) {
		this.passportPage = passportPage;
	}

	@Column(name = "BusinessLicense", length = 300)
	public String getBusinessLicense() {
		return this.businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	@Column(name = "WThInfoID")
	public Integer getWthInfoId() {
		return this.wthInfoId;
	}

	public void setWthInfoId(Integer wthInfoId) {
		this.wthInfoId = wthInfoId;
	}

	@Column(name = "createTime", length = 23)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateTime", length = 23)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "NeedPost")
	public Boolean getNeedPost() {
		return this.needPost;
	}

	public void setNeedPost(Boolean needPost) {
		this.needPost = needPost;
	}

	@Column(name = "ExpressTp", length = 50)
	public String getExpressTp() {
		return this.expressTp;
	}

	public void setExpressTp(String expressTp) {
		this.expressTp = expressTp;
	}

	@Column(name = "ExpressNo", length = 50)
	public String getExpressNo() {
		return this.expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	@Column(name = "IsDisabled")
	public Boolean getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

}