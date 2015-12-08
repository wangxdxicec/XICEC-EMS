package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-09-02.
 */
public class ModifyExhibitorGroupRequest extends BaseRequest {
	private Integer id;
	private String groupName;
	private String groupNameEn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupNameEn() {
		return groupNameEn;
	}

	public void setGroupNameEn(String groupNameEn) {
		this.groupNameEn = groupNameEn;
	}

}
