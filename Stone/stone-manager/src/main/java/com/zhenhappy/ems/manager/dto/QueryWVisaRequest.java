package com.zhenhappy.ems.manager.dto;

import java.util.Date;

/**
 * Created by wujianbin on 2014-10-30.
 */
public class QueryWVisaRequest extends EasyuiRequest {

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWCustomerInfo() {
		return WCustomerInfo;
	}

	public void setWCustomerInfo(Integer WCustomerInfo) {
		this.WCustomerInfo = WCustomerInfo;
	}

	public String getFullPassportName() {
		return fullPassportName;
	}

	public void setFullPassportName(String fullPassportName) {
		this.fullPassportName = fullPassportName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getChineseEmbassy() {
		return chineseEmbassy;
	}

	public void setChineseEmbassy(String chineseEmbassy) {
		this.chineseEmbassy = chineseEmbassy;
	}

	public String getConsulateOfCity() {
		return consulateOfCity;
	}

	public void setConsulateOfCity(String consulateOfCity) {
		this.consulateOfCity = consulateOfCity;
	}

	public Date getDurationBeginTime() {
		return durationBeginTime;
	}

	public void setDurationBeginTime(Date durationBeginTime) {
		this.durationBeginTime = durationBeginTime;
	}

	public Date getDurationEndTime() {
		return durationEndTime;
	}

	public void setDurationEndTime(Date durationEndTime) {
		this.durationEndTime = durationEndTime;
	}

	public String getPassportPage() {
		return passportPage;
	}

	public void setPassportPage(String passportPage) {
		this.passportPage = passportPage;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public Integer getWthInfoId() {
		return wthInfoId;
	}

	public void setWthInfoId(Integer wthInfoId) {
		this.wthInfoId = wthInfoId;
	}

	public Date getcreateTime() {
		return createTime;
	}

	public void setcreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getNeedPost() {
		return needPost;
	}

	public void setNeedPost(Boolean needPost) {
		this.needPost = needPost;
	}

	public String getExpressTp() {
		return expressTp;
	}

	public void setExpressTp(String expressTp) {
		this.expressTp = expressTp;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
}
