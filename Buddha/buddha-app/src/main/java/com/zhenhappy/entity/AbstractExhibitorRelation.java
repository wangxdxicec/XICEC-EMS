package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractExhibitorRelation entity provides the base persistence definition of
 * the ExhibitorRelation entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractExhibitorRelation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer exhibitorListId;
	private String exhibitionNo;
	private String country;
	private String company;
	private String companyE;
	private Integer isDisabled;
	private String createdBy;
	private Timestamp createdTime;
	private String updatedBy;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public AbstractExhibitorRelation() {
	}

	/** full constructor */
	public AbstractExhibitorRelation(Integer exhibitorListId, String exhibitionNo, String country, String company,
			String companyE, Integer isDisabled, String createdBy, Timestamp createdTime, String updatedBy,
			Timestamp updateTime) {
		this.exhibitorListId = exhibitorListId;
		this.exhibitionNo = exhibitionNo;
		this.country = country;
		this.company = company;
		this.companyE = companyE;
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
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ExhibitorListID")
	public Integer getExhibitorListId() {
		return this.exhibitorListId;
	}

	public void setExhibitorListId(Integer exhibitorListId) {
		this.exhibitorListId = exhibitorListId;
	}

	@Column(name = "ExhibitionNo", length = 100)
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

}