package com.zhenhappy.ems.manager.dto;

import java.util.Date;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-06-27.
 */
public class ModifyExhibitorJoinerRequest extends BaseRequest {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public Date getAdminUpdateTime() {
		return adminUpdateTime;
	}

	public void setAdminUpdateTime(Date adminUpdateTime) {
		this.adminUpdateTime = adminUpdateTime;
	}

}
