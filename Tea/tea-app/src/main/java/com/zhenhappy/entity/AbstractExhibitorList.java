package com.zhenhappy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractExhibitorList entity provides the base persistence definition of the
 * ExhibitorList entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractExhibitorList implements java.io.Serializable {

	// Fields

	private Integer companyId;
	private String exhibitionNo;
	private String country;
	private String company;
	private String companyE;
	private String shortCompanyName;
	private String firstCompany;
	private String firstCompanyE;
	private String shortCompanyNameE;
	private String shortCompanyNameT;
	private String address;
	private String addressE;
	private String postCode;
	private String telephone;
	private String fax;
	private String email;
	private String website;
	private String mainProduct;
	private String mainProductE;
	private String productType;
	private String productTypeDetal;
	private String productTypeOther;
	private String logoUrl;
	private Timestamp postTime;
	private String fromFlag;
	private Integer seq;
	private String remark;
	private Integer isDisabled;
	private String createdBy;
	private Timestamp createdTime;
	private String updatedBy;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public AbstractExhibitorList() {
	}

	/** minimal constructor */
	public AbstractExhibitorList(String exhibitionNo) {
		this.exhibitionNo = exhibitionNo;
	}

	/** full constructor */
	public AbstractExhibitorList(String exhibitionNo, String country, String company, String companyE,
			String shortCompanyName, String shortCompanyNameE, String address, String addressE, String postCode,
			String telephone, String fax, String email, String website, String mainProduct, String mainProductE,
			String productType, String productTypeDetal, String productTypeOther, String logoUrl, Timestamp postTime,
			String fromFlag, Integer seq, String remark, Integer isDisabled, String createdBy, Timestamp createdTime,
			String updatedBy, Timestamp updateTime) {
		this.exhibitionNo = exhibitionNo;
		this.country = country;
		this.company = company;
		this.companyE = companyE;
		this.shortCompanyName = shortCompanyName;
		this.shortCompanyNameE = shortCompanyNameE;
		this.address = address;
		this.addressE = addressE;
		this.postCode = postCode;
		this.telephone = telephone;
		this.fax = fax;
		this.email = email;
		this.website = website;
		this.mainProduct = mainProduct;
		this.mainProductE = mainProductE;
		this.productType = productType;
		this.productTypeDetal = productTypeDetal;
		this.productTypeOther = productTypeOther;
		this.logoUrl = logoUrl;
		this.postTime = postTime;
		this.fromFlag = fromFlag;
		this.seq = seq;
		this.remark = remark;
		this.isDisabled = isDisabled;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Column(name = "ExhibitionNo", nullable = false, length = 100)
	public String getExhibitionNo() {
		return this.exhibitionNo;
	}

	public void setExhibitionNo(String exhibitionNo) {
		this.exhibitionNo = exhibitionNo;
	}

	@Column(name = "Country", length = 50)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "Company")
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "CompanyE")
	public String getCompanyE() {
		return this.companyE;
	}

	public void setCompanyE(String companyE) {
		this.companyE = companyE;
	}

	@Column(name = "ShortCompanyName")
	public String getShortCompanyName() {
		return this.shortCompanyName;
	}

	public void setShortCompanyName(String shortCompanyName) {
		this.shortCompanyName = shortCompanyName;
	}

	@Column(name = "ShortCompanyNameE")
	public String getShortCompanyNameE() {
		return this.shortCompanyNameE;
	}

	public void setShortCompanyNameE(String shortCompanyNameE) {
		this.shortCompanyNameE = shortCompanyNameE;
	}
	
	@Column(name = "ShortCompanyNameT")
	public String getShortCompanyNameT() {
		return this.shortCompanyNameT;
	}

	public void setShortCompanyNameT(String shortCompanyNameT) {
		this.shortCompanyNameT = shortCompanyNameT;
	}

	@Column(name = "Address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "AddressE")
	public String getAddressE() {
		return this.addressE;
	}

	public void setAddressE(String addressE) {
		this.addressE = addressE;
	}

	@Column(name = "PostCode")
	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name = "Telephone")
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "Fax")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Website")
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "MainProduct")
	public String getMainProduct() {
		return this.mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}

	@Column(name = "MainProductE")
	public String getMainProductE() {
		return this.mainProductE;
	}

	public void setMainProductE(String mainProductE) {
		this.mainProductE = mainProductE;
	}

	@Column(name = "ProductType", length = 200)
	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Column(name = "ProductTypeDetail", length = 200)
	public String getProductTypeDetal() {
		return this.productTypeDetal;
	}

	public void setProductTypeDetal(String productTypeDetal) {
		this.productTypeDetal = productTypeDetal;
	}

	@Column(name = "ProductTypeOther")
	public String getProductTypeOther() {
		return this.productTypeOther;
	}

	public void setProductTypeOther(String productTypeOther) {
		this.productTypeOther = productTypeOther;
	}

	@Column(name = "LogoURL", length = 200)
	public String getLogoUrl() {
		return this.logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@Column(name = "PostTime", length = 23)
	public Timestamp getPostTime() {
		return this.postTime;
	}

	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}

	@Column(name = "FromFlag", length = 20)
	public String getFromFlag() {
		return this.fromFlag;
	}

	public void setFromFlag(String fromFlag) {
		this.fromFlag = fromFlag;
	}

	@Column(name = "Seq")
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Column(name = "Remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "IsDisabled")
	public Integer getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

	@Column(name = "CreatedBy")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CreatedTime", length = 23)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "UpdatedBy")
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "UpdateTime", length = 23)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "FirstCompany")
	public String getFirstCompany() {
		return firstCompany;
	}

	public void setFirstCompany(String firstCompany) {
		this.firstCompany = firstCompany;
	}

	@Column(name = "FirstCompanyE")
	public String getFirstCompanyE() {
		return firstCompanyE;
	}

	public void setFirstCompanyE(String firstCompanyE) {
		this.firstCompanyE = firstCompanyE;
	}

	
}