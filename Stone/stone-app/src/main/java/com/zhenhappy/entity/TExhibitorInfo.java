package com.zhenhappy.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TExhibitorInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_exhibitor_info", schema = "dbo")
public class TExhibitorInfo implements java.io.Serializable {

	// Fields

	private Integer einfoid;
	private Integer eid;
	private String organizationCode;
	private String company;
	private String companyEn;
	private String phone;
	private String fax;
	private String email;
	private String website;
	private String address;
	private String addressEn;
	private String zipcode;
	private String mainProduct;
	private String mainProductEn;
	private String logo;
    private String mark;
    private String emark;
    private String meipai;
    private String meipaiEn;
	private Integer isDelete;
	private Date createTime;
	private Date updateTime;
	private Integer adminUser;
	private Date adminUpdateTime;
	@Transient
	private String classjson;

    @Transient
    private String brandsData;

    /**
	 * default constructor
	 */
	public TExhibitorInfo() {
	}

	/**
	 * minimal constructor
	 */
	public TExhibitorInfo(Integer einfoid) {
		this.einfoid = einfoid;
	}

	/**
	 * full constructor
	 */
	public TExhibitorInfo(Integer einfoid, Integer eid, String company,
						  String companyEn, String phone, String fax, String email,
						  String website, String zipcode, String mainProduct,
						  String mainProduceEn, String logo, String mark, Integer isDelete,
						  Date createTime, Date updateTime, Integer adminUser,
						  Date adminUpdateTime) {
		this.einfoid = einfoid;
		this.eid = eid;
		this.company = company;
		this.companyEn = companyEn;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.website = website;
		this.zipcode = zipcode;
		this.mainProduct = mainProduct;
		this.mainProductEn = mainProduceEn;
		this.logo = logo;
		this.mark = mark;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.adminUser = adminUser;
		this.adminUpdateTime = adminUpdateTime;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	@Column(name = "address_en")
	public String getAddressEn() {
		return addressEn;
	}

    @Transient
    public String getBrandsData() {
        return brandsData;
    }

	@Column(name = "admin_update_time", length = 23)
	public Date getAdminUpdateTime() {
		return this.adminUpdateTime;
	}

	@Column(name = "admin_user")
	public Integer getAdminUser() {
		return this.adminUser;
	}

	@Transient
	public String getClassjson() {
		return classjson;
	}

	@Column(name = "company", length = 500)
	public String getCompany() {
		return this.company;
	}

	@Column(name = "company_en", length = 500)
	public String getCompanyEn() {
		return this.companyEn;
	}

	@Column(name = "create_time", length = 23)
	public Date getCreateTime() {
		return this.createTime;
	}

	@Column(name = "eid")
	public Integer getEid() {
		return this.eid;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "einfoid", unique = true, nullable = false)
	public Integer getEinfoid() {
		return this.einfoid;
	}

	@Column(name = "email", length = 300)
	public String getEmail() {
		return this.email;
	}

	@Column(name = "fax", length = 300)
	public String getFax() {
		return this.fax;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	@Column(name = "logo", length = 300)
	public String getLogo() {
		return this.logo;
	}

	@Column(name = "main_product", length = 2000)
	public String getMainProduct() {
		return this.mainProduct;
	}

	@Column(name = "main_product_en", length = 2000)
	public String getMainProductEn() {
		return this.mainProductEn;
	}

	@Column(name = "mark")
	public String getMark() {
		return this.mark;
	}

	@Column(name = "organization_code", length = 10)
	public String getOrganizationCode() {
		return organizationCode;
	}

	@Column(name = "phone", length = 300)
	public String getPhone() {
		return this.phone;
	}

	@Column(name = "update_time", length = 23)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	@Column(name = "website", length = 500)
	public String getWebsite() {
		return this.website;
	}

	@Column(name = "zipcode", length = 500)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddressEn(String addressEn) {
		this.addressEn = addressEn;
	}

	public void setAdminUpdateTime(Date adminUpdateTime) {
		this.adminUpdateTime = adminUpdateTime;
	}

	public void setAdminUser(Integer adminUser) {
		this.adminUser = adminUser;
	}

	public void setClassjson(String classjson) {
		this.classjson = classjson;
	}

	// Constructors

	public void setCompany(String company) {
		this.company = company;
	}

	public void setCompanyEn(String companyEn) {
		this.companyEn = companyEn;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public void setEinfoid(Integer einfoid) {
		this.einfoid = einfoid;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}

    public void setBrandsData(String brandsData) {
        this.brandsData = brandsData;
    }

	public void setMainProductEn(String mainProduceEn) {
		this.mainProductEn = mainProduceEn;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

    @Column(name = "emark")
    public String getEmark() {
        return emark;
    }

    public void setEmark(String emark) {
        this.emark = emark;
    }

    @Column(name = "meipai", length = 1000)
    public String getMeipai() {
        return meipai;
    }

    public void setMeipai(String meipai) {
        this.meipai = meipai;
    }

    @Column(name = "meipai_en", length = 1000)
    public String getMeipaiEn() {
        return meipaiEn;
    }

    public void setMeipaiEn(String meipaiEn) {
        this.meipaiEn = meipaiEn;
    }

    @Override
	public String toString() {
		return "TExhibitorInfo [einfoid=" + einfoid + ", eid=" + eid
				+ ", organizationCode=" + organizationCode + ", company="
				+ company + ", companyEn=" + companyEn + ", phone=" + phone
				+ ", fax=" + fax + ", email=" + email + ", website=" + website
				+ ", address=" + address + ", addressEn=" + addressEn
				+ ", zipcode=" + zipcode + ", mainProduct=" + mainProduct
				+ ", mainProductEn=" + mainProductEn + ", logo=" + logo
				+ ", mark=" + mark + ", isDelete=" + isDelete + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", adminUser="
				+ adminUser + ", adminUpdateTime=" + adminUpdateTime
				+ ", classjson=" + classjson + ", brandsData=" + brandsData
				+ "]";
	}

}