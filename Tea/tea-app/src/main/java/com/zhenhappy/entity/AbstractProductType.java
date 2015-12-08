package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractProductType entity provides the base persistence definition of the
 * ProductType entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractProductType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String typeCode;
	private String tpyeName;
	private String tpyeNameE;
	private Integer isDisabled;

	// Constructors

	/** default constructor */
	public AbstractProductType() {
	}

	/** minimal constructor */
	public AbstractProductType(String typeCode) {
		this.typeCode = typeCode;
	}

	/** full constructor */
	public AbstractProductType(String typeCode, String tpyeName, String tpyeNameE, Integer isDisabled) {
		this.typeCode = typeCode;
		this.tpyeName = tpyeName;
		this.tpyeNameE = tpyeNameE;
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

	@Column(name = "TypeCode", nullable = false, length = 20)
	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Column(name = "TypeName")
	public String getTpyeName() {
		return this.tpyeName;
	}

	public void setTpyeName(String tpyeName) {
		this.tpyeName = tpyeName;
	}

	@Column(name = "TypeNameE")
	public String getTpyeNameE() {
		return this.tpyeNameE;
	}

	public void setTpyeNameE(String tpyeNameE) {
		this.tpyeNameE = tpyeNameE;
	}

	@Column(name = "IsDisabled")
	public Integer getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

}