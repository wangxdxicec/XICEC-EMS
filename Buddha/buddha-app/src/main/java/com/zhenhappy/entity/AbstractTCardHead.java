package com.zhenhappy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractTCardHead entity provides the base persistence definition of the
 * TCardHead entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTCardHead implements java.io.Serializable {

	// Fields

	private Integer cardId;
	private byte[] headImg;

	// Constructors

	/** default constructor */
	public AbstractTCardHead() {
	}


	// Property accessors
	@Id
	@Column(name = "card_id", unique = true, nullable = false)
	public Integer getCardId() {
		return this.cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	@Column(name = "headImg", nullable = false)
	public byte[] getHeadImg() {
		return this.headImg;
	}

	public void setHeadImg(byte[] headImg) {
		this.headImg = headImg;
	}

}