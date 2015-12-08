package com.zhenhappy.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ProductType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ProductType", schema = "dbo")
public class ProductType extends AbstractProductType implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ProductType() {
	}

	/** minimal constructor */
	public ProductType(String typeCode) {
		super(typeCode);
	}

	/** full constructor */
	public ProductType(String typeCode, String tpyeName, String tpyeNameE, Integer isDisabled) {
		super(typeCode, tpyeName, tpyeNameE, isDisabled);
	}

}
