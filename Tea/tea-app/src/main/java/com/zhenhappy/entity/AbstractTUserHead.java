package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractTUserHead entity provides the base persistence definition of the
 * TUserHead entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTUserHead implements java.io.Serializable {

	// Fields

	private Integer userId;
	private byte[] headImg;

	// Constructors

	/** default constructor */
	public AbstractTUserHead() {
	}

	// Property accessors
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "headImg", nullable = false)
	public byte[] getHeadImg() {
		return this.headImg;
	}

	public void setHeadImg(byte[] headImg) {
		this.headImg = headImg;
	}

}