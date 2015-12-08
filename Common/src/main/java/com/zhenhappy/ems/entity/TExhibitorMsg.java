package com.zhenhappy.ems.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TExhibitorMsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_exhibitor_msg", schema = "dbo")
public class TExhibitorMsg implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer eid;
	private String title;
	private String content;
	private Integer adminUser;
	private Date sendTime;
	private Integer isRead;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public TExhibitorMsg() {
	}

	/** minimal constructor */
	public TExhibitorMsg(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TExhibitorMsg(Integer id, Integer eid, String title, String content,
			Integer adminUser, Date sendTime, Integer isRead,
			Integer isDelete) {
		this.id = id;
		this.eid = eid;
		this.title = title;
		this.content = content;
		this.adminUser = adminUser;
		this.sendTime = sendTime;
		this.isRead = isRead;
		this.isDelete = isDelete;
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

	@Column(name = "eid")
	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	@Column(name = "title", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "admin_user")
	public Integer getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(Integer adminUser) {
		this.adminUser = adminUser;
	}

	@Column(name = "send_time", length = 23)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "is_read")
	public Integer getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}