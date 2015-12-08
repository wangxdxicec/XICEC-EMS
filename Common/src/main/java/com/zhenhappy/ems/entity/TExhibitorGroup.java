package com.zhenhappy.ems.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TExhibitorGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_exhibitor_group", schema = "dbo")
public class TExhibitorGroup implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	
	private Integer id;
	private String groupName;
	private String groupNameEn;
	private Date createTime;
	private Date updateTime;
	private Integer createUser;
	private Integer updateUser;

	// Constructors

	/** default constructor */
	public TExhibitorGroup() {
	}

	/** minimal constructor */
	public TExhibitorGroup(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TExhibitorGroup(String groupName, String groupNameEn,
			Date createTime, Date updateTime, Integer createUser,
			Integer updateUser) {
		super();
		this.groupName = groupName;
		this.groupNameEn = groupNameEn;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "group_name", length = 200)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "group_name_en", length = 200)
	public String getGroupNameEn() {
		return groupNameEn;
	}

	public void setGroupNameEn(String groupNameEn) {
		this.groupNameEn = groupNameEn;
	}

	@Column(name = "create_time", length = 23)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 23)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "create_user")
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	@Column(name = "update_user")
	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return "TExhibitorGroup [id=" + id + ", groupName=" + groupName
				+ ", groupNameEn=" + groupNameEn + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createUser=" + createUser
				+ ", updateUser=" + updateUser + "]";
	}

}