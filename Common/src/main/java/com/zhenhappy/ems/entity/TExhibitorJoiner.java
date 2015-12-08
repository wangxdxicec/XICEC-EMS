package com.zhenhappy.ems.entity;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by wujianbin on 2014-04-20.
 */
@Entity
@Table(name = "t_exhibitor_joiner", schema = "dbo")
public class TExhibitorJoiner {
	private Integer id;
	private Integer eid;
	private String name;
	private String position;
	private String telphone;
	private String email;
	private Integer isDelete;
	private Date createTime;
	private Integer admin;
	private Date adminUpdateTime;

	public TExhibitorJoiner() {
		super();
		this.isDelete = 0;
	}

	public TExhibitorJoiner(Integer eid, String name, String position,
			String telphone, String email, Date createTime, Integer admin,
			Date adminUpdateTime, Integer isDelete) {
		super();
		this.eid = eid;
		this.name = name;
		this.position = position;
		this.telphone = telphone;
		this.email = email;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.admin = admin;
		this.adminUpdateTime = adminUpdateTime;
	}
	
	public TExhibitorJoiner(Integer eid, String name, String position,
			String telphone, String email, Date createTime,
			Integer admin, Date adminUpdateTime) {
		super();
		this.eid = eid;
		this.name = name;
		this.position = position;
		this.telphone = telphone;
		this.email = email;
		this.isDelete = 0;
		this.createTime = createTime;
		this.admin = admin;
		this.adminUpdateTime = adminUpdateTime;
	}
	
	public TExhibitorJoiner(Integer id, Integer eid, String name, String position,
			String telphone, String email, Integer isDelete, Date createTime,
			Integer admin, Date adminUpdateTime) {
		super();
		this.id = id;
		this.eid = eid;
		this.name = name;
		this.position = position;
		this.telphone = telphone;
		this.email = email;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.admin = admin;
		this.adminUpdateTime = adminUpdateTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "eid")
	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	@Column(name = "name", length = 500)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "position", length = 500)
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "telphone", length = 20)
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "email", length = 500)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "admin")
	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	@Column(name = "admin_update_time", length = 500)
	public Date getAdminUpdateTime() {
		return adminUpdateTime;
	}

	public void setAdminUpdateTime(Date adminUpdateTime) {
		this.adminUpdateTime = adminUpdateTime;
	}
}
