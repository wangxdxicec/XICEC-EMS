package com.zhenhappy.ems.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by wujianbin on 2014-05-11.
 */
@Entity
@javax.persistence.Table(name = "t_visa", schema = "dbo")
public class TVisa {

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
	private String address;

	public TVisa() {
	}

	public TVisa(Integer id, Integer eid, String passportName, Integer gender, String nationality, String jobTitle, String companyName, String boothNo, String detailedAddress, String tel, String fax, String email, String companyWebsite, String passportNo, Date expDate, Date birth, String applyFor, Date from, Date to, String passportPage, String businessLicense, Integer joinerId, Integer status, Date createTime, Date updateTime) {
		this.id = id;
		this.eid = eid;
		this.passportName = passportName;
		this.gender = gender;
		this.nationality = nationality;
		this.jobTitle = jobTitle;
		this.companyName = companyName;
		this.boothNo = boothNo;
		this.detailedAddress = detailedAddress;
		this.tel = tel;
		this.fax = fax;
		this.email = email;
		this.companyWebsite = companyWebsite;
		this.passportNo = passportNo;
		this.expDate = expDate;
		this.birth = birth;
		this.applyFor = applyFor;
		this.from = from;
		this.to = to;
		this.passportPage = passportPage;
		this.businessLicense = businessLicense;
		this.joinerId = joinerId;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Basic
	@Column(name = "passport_name")
	public String getPassportName() {
		return passportName;
	}

	public void setPassportName(String passportName) {
		this.passportName = passportName;
	}

	@Basic
	@Column(name = "gender")
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

    @Basic
    @Column(name = "eid")
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "job_title")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "booth_no")
    public String getBoothNo() {
        return boothNo;
    }

    public void setBoothNo(String boothNo) {
        this.boothNo = boothNo;
    }

    @Basic
    @Column(name = "detailed_address")
    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "company_website")
    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    @Basic
    @Column(name = "passport_no")
    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    @Basic
    @Column(name = "exp_date")
    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

	@Basic
    @Column(name = "birth")
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "apply_for")
    public String getApplyFor() {
        return applyFor;
    }

    public void setApplyFor(String applyFor) {
        this.applyFor = applyFor;
    }

    @Basic
    @Column(name = "fromDate")
    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    @Basic
    @Column(name = "toDate")
    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    @Basic
    @Column(name = "passport_page")
    public String getPassportPage() {
        return passportPage;
    }

    public void setPassportPage(String passportPage) {
        this.passportPage = passportPage;
    }

    @Basic
    @Column(name = "business_license")
    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

	@Basic
	@Column(name = "joiner_id")
	public Integer getJoinerId() {
		return joinerId;
	}

	public void setJoinerId(Integer joinerId) {
		this.joinerId = joinerId;
	}

	@Basic
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "create_time", length = 23)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 23)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Basic
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
