package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractVerify entity provides the base persistence definition of the Verify
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractVerify implements java.io.Serializable {

	// Fields

	private Integer verifyId;
	private Integer verifyType;
	private Integer user_id;
	private String checkcode;
	private Integer enable;
	private String email;
	private Integer isSend;

	// Constructors

	/** default constructor */
	public AbstractVerify() {
	}

	/** minimal constructor */
	public AbstractVerify(Integer verifyType, String checkcode, Integer enable, String email, Integer isSend) {
		this.verifyType = verifyType;
		this.checkcode = checkcode;
		this.enable = enable;
		this.email = email;
		this.isSend = isSend;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "verify_id", unique = true, nullable = false)
	public Integer getVerifyId() {
		return this.verifyId;
	}

	public void setVerifyId(Integer verifyId) {
		this.verifyId = verifyId;
	}

	@Column(name = "verify_type", nullable = false)
	public Integer getVerifyType() {
		return this.verifyType;
	}

	public void setVerifyType(Integer verifyType) {
		this.verifyType = verifyType;
	}

	@Column(name = "checkcode", nullable = false, length = 200)
	public String getCheckcode() {
		return this.checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	@Column(name = "enable", nullable = false)
	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	@Column(name = "email", nullable = false, length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "isSend", nullable = false)
	public Integer getIsSend() {
		return this.isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}
	
	@Column(name = "user_id", nullable = false)
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

}