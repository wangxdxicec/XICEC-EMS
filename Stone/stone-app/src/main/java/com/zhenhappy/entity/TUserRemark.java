package com.zhenhappy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author 苏志锋
 * 
 */
@Entity
@Table(name = "t_user_remark", schema = "dbo")
public class TUserRemark implements java.io.Serializable {
	private Integer remarkId;
	private Integer itemId;
	private Integer userId;
	private Integer remarkType;
	private String remark;

	public TUserRemark() {
	}

	public TUserRemark(Integer itemId, Integer userId, Integer remarkType, String remark) {
		this.itemId = itemId;
		this.userId = userId;
		this.remarkType = remarkType;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "remark_id", unique = true, nullable = false)
	public Integer getRemarkId() {
		return remarkId;
	}

	public void setRemarkId(Integer remarkId) {
		this.remarkId = remarkId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "item_id")
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "type")
	public Integer getRemarkType() {
		return remarkType;
	}

	public void setRemarkType(Integer remarkType) {
		this.remarkType = remarkType;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}