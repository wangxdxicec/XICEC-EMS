package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-04-26.
 */
public class BindBoothRequest extends BaseRequest {
	private Integer id;
	private String area;
	private String boothNumber;
	private Integer eid;
	private String mark;
	private String exhibitionArea;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBoothNumber() {
		return boothNumber;
	}

	public void setBoothNumber(String boothNumber) {
		this.boothNumber = boothNumber;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getExhibitionArea() {
		return exhibitionArea;
	}

	public void setExhibitionArea(String exhibitionArea) {
		this.exhibitionArea = exhibitionArea;
	}

}
