package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-09-04.
 */
public class ImportExhibitorsRequest extends BaseRequest {
	private Integer country;
	private Integer province;
	private Integer area;
	private Integer group;
	private Integer tag;

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

}
