package com.zhenhappy.ems.manager.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by wujianbin on 2014-11-26.
 */
public class QueryTVisaRequest extends EasyuiRequest {

	private Integer id;
	private Integer eid;
	private String passportName;
	private Integer gender;
	private String nationality;
	private String jobTitle;
	private String companyName;
	private String boothNo;
	private String detailedAddress;
	private String tel;
	private String fax;
	private String email;
	private String companyWebsite;
	private String passportNo;
	@JSONField(format="yyyy-MM-dd")
	private Date expDate;
	@JSONField(format="yyyy-MM-dd")
	private Date birth;
	private String applyFor;
	@JSONField(format="yyyy-MM-dd")
	private Date from;
	@JSONField(format="yyyy-MM-dd")
	private Date to;
	private String passportPage;
	private String businessLicense;
	private Integer joinerId;
	private Integer status;
	private Date createTime;
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getPassportName() {
		return passportName;
	}

	public void setPassportName(String passportName) {
		this.passportName = passportName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBoothNo() {
		return boothNo;
	}

	public void setBoothNo(String boothNo) {
		this.boothNo = boothNo;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
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

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getApplyFor() {
		return applyFor;
	}

	public void setApplyFor(String applyFor) {
		this.applyFor = applyFor;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
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

	public Integer getJoinerId() {
		return joinerId;
	}

	public void setJoinerId(Integer joinerId) {
		this.joinerId = joinerId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
}
