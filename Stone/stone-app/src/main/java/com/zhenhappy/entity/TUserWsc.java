package com.zhenhappy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

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
@Table(name = "t_user_wsc", schema = "dbo")
public class TUserWsc implements java.io.Serializable {
	private Integer userWscId;
	private Integer userId;
	private Integer wscId;
	private String remark;
	private Date createTime;
	private Integer isDelete;
	private String wscDate;
	private String location;
	private String speaker;
	private String name;
    private String wscTime;

	public TUserWsc() {
	}

	public TUserWsc(Integer userId, Integer wscId) {
		this.userId = userId;
		this.wscId = wscId;
	}

	public TUserWsc(Integer userId, Integer wscId, String wscDate, String wscTime, String location, String speaker, String name) {
		this.userId = userId;
		this.wscId = wscId;
		this.wscDate = wscDate;
        this.wscTime = wscTime;
		this.location = location;
		this.speaker = speaker;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getUserWscId() {
		return userWscId;
	}

	public void setUserWscId(Integer userWscId) {
		this.userWscId = userWscId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	@Column(name = "wsc_id")
	public Integer getWscId() {
		return wscId;
	}

	public void setWscId(Integer wscId) {
		this.wscId = wscId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "is_delete")
	public Integer getDelete() {
		return isDelete;
	}

	public void setDelete(Integer delete) {
		isDelete = delete;
	}

	@Column(name = "wscDate")
	public String getWscDate() {
		return wscDate;
	}

	public void setWscDate(String wscDate) {
		this.wscDate = wscDate;
	}

	@Column(name = "location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "speaker")
	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Column(name = "wscTime")
    public String getWscTime() {
        return wscTime;
    }

    public void setWscTime(String wscTime) {
        this.wscTime = wscTime;
    }
}