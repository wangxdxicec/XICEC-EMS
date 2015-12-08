package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-06-27.
 */
public class AddExpressNumberRequest extends BaseRequest {
	private Integer id;
	private Integer eid;
	private String expressNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

}
