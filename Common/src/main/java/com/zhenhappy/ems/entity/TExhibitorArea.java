package com.zhenhappy.ems.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TExhibitorArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_exhibitor_area", schema = "dbo")
public class TExhibitorArea implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer flag;
	private String areaName;
	private String areaNameEn;
	private String mark;
	private Integer isDelete;
	private Integer createUser;
	private Timestamp createTime;
	private Integer updateUser;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TExhibitorArea() {
	}

	/** minimal constructor */
	public TExhibitorArea(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TExhibitorArea(Integer flag, String areaName,
			String areaNameEn, String mark, Integer isDelete,
			Integer createUser, Timestamp createTime, Integer updateUser,
			Timestamp updateTime) {
		this.flag = flag;
		this.areaName = areaName;
		this.areaNameEn = areaNameEn;
		this.mark = mark;
		this.isDelete = isDelete;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "flag")
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Column(name = "area_name", length = 500)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "area_name_en", length = 500)
	public String getAreaNameEn() {
		return this.areaNameEn;
	}

	public void setAreaNameEn(String areaNameEn) {
		this.areaNameEn = areaNameEn;
	}

	@Column(name = "mark", length = 500)
	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "create_user")
	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	@Column(name = "create_time", length = 23)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_user")
	public Integer getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "update_time", length = 23)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}