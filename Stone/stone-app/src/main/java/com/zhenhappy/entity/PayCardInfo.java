package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PayCardInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PlayCardInfo", schema = "dbo")
public class PayCardInfo extends AbstractPayCardInfo implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public PayCardInfo() {
	}

	/** full constructor */
	public PayCardInfo(String name, String design, String title, String depart, String company, String address,
			String postCode, String pobox, String tel, String homeTel, String hp, String did, String fax, String email,
			String web, String acct, String bank, String cable, String telex, String icq, String addCountry,
			String addProvince, String addCity, String addStreet, String addBuliding, String addNo, String extra,
			String memo, String barCode, String qrcode, Integer printNumber, String photoUrl, Integer isDisabled,
			String createdBy, Timestamp createdTime, String updatedBy, Timestamp updateTime) {
		super(name, design, title, depart, company, address, postCode, pobox, tel, homeTel, hp, did, fax, email, web,
				acct, bank, cable, telex, icq, addCountry, addProvince, addCity, addStreet, addBuliding, addNo, extra,
				memo, barCode, qrcode, printNumber, photoUrl, isDisabled, createdBy, createdTime, updatedBy, updateTime);
	}

}
