package com.zhenhappy.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Verify entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "verify", schema = "dbo")
public class Verify extends AbstractVerify implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Verify() {
	}

	/** minimal constructor */
	public Verify(Integer verifyType, String checkcode, Integer enable, String email, Integer isSend) {
		super(verifyType, checkcode, enable, email, isSend);
	}

}
