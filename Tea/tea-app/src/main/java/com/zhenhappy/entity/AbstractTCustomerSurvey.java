package com.zhenhappy.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractTCustomerSurvey entity provides the base persistence definition of
 * the TCustomerSurvey entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTCustomerSurvey implements java.io.Serializable {

	private static final long serialVersionUID = 3180625463094339539L;

	private Integer userId;
	private Integer surveyId;
	private String customerName;
	private String company;
	private String position;
	private String country;
	private String province;
	private String city;
	private String postcode;
	private String telephone;
	private String fax;
	private String email;
	private String website;
	private String address;
	private String industry;
	private String interestProduct;
	private String authoritativeMedia;
	private String getFairInfo;
	private Integer isGetData;
	private Integer isSendPost;
	private Date createTime;
	private Integer joinSurvey;
	private Integer interestSurvey;

	// Constructors

	/**
	 * default constructor
	 */
	public AbstractTCustomerSurvey() {
	}

	/**
	 * minimal constructor
	 */
	public AbstractTCustomerSurvey(Integer surveyId, String company, String position, String country, String address,
			Timestamp createTime) {
		this.surveyId = surveyId;
		this.company = company;
		this.position = position;
		this.country = country;
		this.address = address;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@Column(name = "survey_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSurveyId() {
		return this.surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	@Column(name = "company", nullable = false, length = 100)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "position", nullable = false, length = 100)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "country", nullable = false, length = 100)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "province", length = 100)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "postcode", length = 100)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "telephone", length = 20)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "fax", length = 20)
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

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "industry", length = 200)
	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name = "interest_product", length = 50)
	public String getInterestProduct() {
		return this.interestProduct;
	}

	public void setInterestProduct(String interestProduct) {
		this.interestProduct = interestProduct;
	}

	@Column(name = "authoritativeMedia", length = 500)
	public String getAuthoritativeMedia() {
		return this.authoritativeMedia;
	}

	public void setAuthoritativeMedia(String authoritativeMedia) {
		this.authoritativeMedia = authoritativeMedia;
	}

	@Column(name = "get_fair_info", length = 500)
	public String getGetFairInfo() {
		return this.getFairInfo;
	}

	public void setGetFairInfo(String getFairInfo) {
		this.getFairInfo = getFairInfo;
	}

	@Column(name = "isGetData")
	public Integer getIsGetData() {
		return this.isGetData;
	}

	public void setIsGetData(Integer isGetData) {
		this.isGetData = isGetData;
	}

	@Column(name = "isSendPost")
	public Integer getIsSendPost() {
		return this.isSendPost;
	}

	public void setIsSendPost(Integer isSendPost) {
		this.isSendPost = isSendPost;
	}

	@Column(name = "create_time", nullable = false, length = 23)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "joinSurvey")
	public Integer getJoinSurvey() {
		return joinSurvey;
	}

	public void setJoinSurvey(Integer joinSurvey) {
		this.joinSurvey = joinSurvey;
	}

	@Column(name = "interestSurvey")
	public Integer getInterestSurvey() {
		return interestSurvey;
	}

	public void setInterestSurvey(Integer interestSurvey) {
		this.interestSurvey = interestSurvey;
	}

	@Column(name = "customerName")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}