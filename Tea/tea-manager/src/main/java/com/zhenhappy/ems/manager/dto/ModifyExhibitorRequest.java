package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-04-28.
 */
public class ModifyExhibitorRequest extends BaseRequest {

	private Integer eid;
    private String exhibitionArea;
    private String username;
    private String password;
    private Integer level = 1;
    private String companyName;
    private String companyNameE;
    private Integer tag;
    private Integer country;
    private Integer province;
    private Integer area;

    public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getExhibitionArea() {
        return exhibitionArea;
    }

    public void setExhibitionArea(String exhibitionArea) {
        this.exhibitionArea = exhibitionArea;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNameE() {
        return companyNameE;
    }

    public void setCompanyNameE(String companyNameE) {
        this.companyNameE = companyNameE;
    }

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

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
    
}
