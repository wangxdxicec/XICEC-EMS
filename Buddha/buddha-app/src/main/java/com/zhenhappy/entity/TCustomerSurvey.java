package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TCustomerSurvey entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_customer_survey"
    ,schema="dbo"
)
public class TCustomerSurvey extends AbstractTCustomerSurvey implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TCustomerSurvey() {
	}

	/** minimal constructor */
	public TCustomerSurvey(Integer surveyId, String company, String position, String country, String address,
			Timestamp createTime) {
		super(surveyId, company, position, country, address, createTime);
	}

}
