package com.zhenhappy.ems.manager.dto;

import java.util.Date;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-07-03.
 */
public class AddTagRequest extends BaseRequest {
	private Integer id;
	private String name;
	private Date createTime;
	private Date updateTime;
	private Integer adminUser;
	private Date adminUpdateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(Integer adminUser) {
		this.adminUser = adminUser;
	}

	public Date getAdminUpdateTime() {
		return adminUpdateTime;
	}

	public void setAdminUpdateTime(Date adminUpdateTime) {
		this.adminUpdateTime = adminUpdateTime;
	}

}
