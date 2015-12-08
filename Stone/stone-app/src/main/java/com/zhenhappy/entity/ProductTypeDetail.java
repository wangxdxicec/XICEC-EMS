package com.zhenhappy.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ProductTypeDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ProductTypeDetail", schema = "dbo")
public class ProductTypeDetail extends AbstractProductTypeDetail implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ProductTypeDetail() {
	}

	/** minimal constructor */
	public ProductTypeDetail(String typeDetailCode, String fatherTypeCode) {
		super(typeDetailCode, fatherTypeCode);
	}

	/** full constructor */
	public ProductTypeDetail(String typeDetailCode, String tpyeDetailName, String tpyeDetailNameE,
			String fatherTypeCode, Integer isDisabled) {
		super(typeDetailCode, tpyeDetailName, tpyeDetailNameE, fatherTypeCode, isDisabled);
	}

}
