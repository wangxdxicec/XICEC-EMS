package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractProductTypeDetail entity provides the base persistence definition of
 * the ProductTypeDetail entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractProductTypeDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private String typeDetailCode;
	private String tpyeDetailName;
	private String tpyeDetailNameE;
	private String fatherTypeCode;
	private Integer isDisabled;

	// Constructors

	/** default constructor */
	public AbstractProductTypeDetail() {
	}

	/** minimal constructor */
	public AbstractProductTypeDetail(String typeDetailCode, String fatherTypeCode) {
		this.typeDetailCode = typeDetailCode;
		this.fatherTypeCode = fatherTypeCode;
	}

	/** full constructor */
	public AbstractProductTypeDetail(String typeDetailCode, String tpyeDetailName, String tpyeDetailNameE,
			String fatherTypeCode, Integer isDisabled) {
		this.typeDetailCode = typeDetailCode;
		this.tpyeDetailName = tpyeDetailName;
		this.tpyeDetailNameE = tpyeDetailNameE;
		this.fatherTypeCode = fatherTypeCode;
		this.isDisabled = isDisabled;
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

	@Column(name = "TypeDetailCode", nullable = false, length = 20)
	public String getTypeDetailCode() {
		return this.typeDetailCode;
	}

	public void setTypeDetailCode(String typeDetailCode) {
		this.typeDetailCode = typeDetailCode;
	}

	@Column(name = "TypeDetailName")
	public String getTpyeDetailName() {
		return this.tpyeDetailName;
	}

	public void setTpyeDetailName(String tpyeDetailName) {
		this.tpyeDetailName = tpyeDetailName;
	}

	@Column(name = "TypeDetailNameE")
	public String getTpyeDetailNameE() {
		return this.tpyeDetailNameE;
	}

	public void setTpyeDetailNameE(String tpyeDetailNameE) {
		this.tpyeDetailNameE = tpyeDetailNameE;
	}

	@Column(name = "FatherTypeCode", nullable = false, length = 20)
	public String getFatherTypeCode() {
		return this.fatherTypeCode;
	}

	public void setFatherTypeCode(String fatherTypeCode) {
		this.fatherTypeCode = fatherTypeCode;
	}

	@Column(name = "IsDisabled")
	public Integer getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

}