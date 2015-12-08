package com.zhenhappy.ems.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TTag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_tag"
	, schema = "dbo"
)
public class TTag implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	
	private Integer id;
	private String name;
	private Date createTime;
	private Date updateTime;
	private Integer adminUser;
	private Date adminUpdateTime;

	// Constructors

	/** default constructor */
	public TTag() {
	}

	/** minimal constructor */
	public TTag(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TTag(String name, Date createTime, Date updateTime,
			Integer adminUser, Date adminUpdateTime) {
		super();
		this.name = name;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.adminUser = adminUser;
		this.adminUpdateTime = adminUpdateTime;
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

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@Column(name = "admin_user")
	public Integer getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(Integer adminUser) {
		this.adminUser = adminUser;
	}
	
	@Column(name = "admin_update_time", length = 23)
	public Date getAdminUpdateTime() {
		return adminUpdateTime;
	}

	public void setAdminUpdateTime(Date adminUpdateTime) {
		this.adminUpdateTime = adminUpdateTime;
	}

	@Override
	public String toString() {
		return "TTag [id=" + id + ", name=" + name + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", adminUser="
				+ adminUser + ", adminUpdateTime=" + adminUpdateTime + "]";
	}

}