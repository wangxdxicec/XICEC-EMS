package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ExhibitorList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ExhibitorList", schema = "dbo")
public class ExhibitorList extends AbstractExhibitorList implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ExhibitorList() {
	}

	/** minimal constructor */
	public ExhibitorList(String exhibitionNo) {
		super(exhibitionNo);
	}

	/** full constructor */
	public ExhibitorList(String exhibitionNo, String country, String company, String companyE, String shortCompanyName,
			String shortCompanyNameE, String address, String addressE, String postCode, String telephone, String fax,
			String email, String website, String mainProduct, String mainProductE, String productType,
			String productTypeDetal, String productTypeOther, String logoUrl, Timestamp postTime, String fromFlag,
			Integer seq, String remark, Integer isDisabled, String createdBy, Timestamp createdTime, String updatedBy,
			Timestamp updateTime) {
		super(exhibitionNo, country, company, companyE, shortCompanyName, shortCompanyNameE, address, addressE,
				postCode, telephone, fax, email, website, mainProduct, mainProductE, productType, productTypeDetal,
				productTypeOther, logoUrl, postTime, fromFlag, seq, remark, isDisabled, createdBy, createdTime,
				updatedBy, updateTime);
	}

}
