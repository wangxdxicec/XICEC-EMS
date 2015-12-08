package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * AbstractTJoinIntention entity provides the base persistence definition of the
 * TJoinIntention entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTJoinIntention implements java.io.Serializable {

    // Fields

    private Integer intentionId;
    private Integer userId;
    private String exhibitorName;
    private String company;
    private String position;
    private String country;
    private String province;
    private String city;
    private String postcode;
    private String mobilePhone;
    private String telephone;
    private String fax;
    private String email;
    private String website;
    private String productTypeOther;
    private Integer locationBuildType;
    private Integer locationNumber;
    private Integer spareArea;
    private String address;
    private String productType;
    private Date createTime;

    // Constructors

    /**
     * default constructor
     */
    public AbstractTJoinIntention() {
    }

    /**
     * minimal constructor
     */
    public AbstractTJoinIntention(Integer intentionId, String company, String position, String country,
                                  String mobilePhone, String telephone, String fax, String email, Date createTime) {
        this.intentionId = intentionId;
        this.company = company;
        this.position = position;
        this.country = country;
        this.mobilePhone = mobilePhone;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.createTime = createTime;
    }

    // Property accessors
    @Id
    @Column(name = "intention_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    public Integer getIntentionId() {
        return this.intentionId;
    }

    public void setIntentionId(Integer intentionId) {
        this.intentionId = intentionId;
    }

    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Column(name = "mobile_phone", nullable = false, length = 20)
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Column(name = "telephone", nullable = false, length = 20)
    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(name = "fax", nullable = false, length = 20)
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = "email", nullable = false, length = 100)
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

    @Column(name = "product_type_other", length = 200)
    public String getProductTypeOther() {
        return this.productTypeOther;
    }

    public void setProductTypeOther(String productTypeOther) {
        this.productTypeOther = productTypeOther;
    }

    @Column(name = "location_build_type")
    public Integer getLocationBuildType() {
        return this.locationBuildType;
    }

    public void setLocationBuildType(Integer locationBuildType) {
        this.locationBuildType = locationBuildType;
    }

    @Column(name = "location_number")
    public Integer getLocationNumber() {
        return this.locationNumber;
    }

    public void setLocationNumber(Integer locationNumber) {
        this.locationNumber = locationNumber;
    }

    @Column(name = "spare_area")
    public Integer getSpareArea() {
        return this.spareArea;
    }

    public void setSpareArea(Integer spareArea) {
        this.spareArea = spareArea;
    }

    @Column(name = "address", length = 200)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "product_type", length = 1000)
    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Column(name = "create_time", nullable = false, length = 23)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "exhibitorName")
	public String getExhibitorName() {
		return exhibitorName;
	}

	public void setExhibitorName(String exhibitorName) {
		this.exhibitorName = exhibitorName;
	}

}