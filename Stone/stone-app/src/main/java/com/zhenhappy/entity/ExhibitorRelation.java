package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ExhibitorRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ExhibitorRelation", schema = "dbo", catalog = "huizhan")
public class ExhibitorRelation extends AbstractExhibitorRelation implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ExhibitorRelation() {
	}

	/** full constructor */
	public ExhibitorRelation(Integer exhibitorListId, String exhibitionNo, String country, String company,
			String companyE, Integer isDisabled, String createdBy, Timestamp createdTime, String updatedBy,
			Timestamp updateTime) {
		super(exhibitorListId, exhibitionNo, country, company, companyE, isDisabled, createdBy, createdTime, updatedBy,
				updateTime);
	}

}
