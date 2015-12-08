package com.zhenhappy.dto;

import java.util.List;

/**
 * User: Haijian Liang
 * Date: 13-11-21
 * Time: 下午10:42
 * Function:
 */
public class BaseCompanyInfoDto {

    private Integer companyId;
    private String exhibitionNo;
    private String country;
    private String company;
    private String companyE;
    private String firstCompany;
    private String firstCompanyE;
    private String shortCompanyName;
    private String shortCompanyNameE;
    private String shortCompanyNameT;
    private String logoUrl;
    private Integer isCollect;
    private Integer mapId;
    private List<CompanyInfoResponse.Point> points;

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public List<CompanyInfoResponse.Point> getPoints() {
        return points;
    }

    public void setPoints(List<CompanyInfoResponse.Point> points) {
        this.points = points;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyE() {
        return companyE;
    }

    public void setCompanyE(String companyE) {
        this.companyE = companyE;
    }

    public String getShortCompanyName() {
        return shortCompanyName;
    }

    public void setShortCompanyName(String shortCompanyName) {
        this.shortCompanyName = shortCompanyName;
    }

    public String getShortCompanyNameE() {
        return shortCompanyNameE;
    }

    public void setShortCompanyNameE(String shortCompanyNameE) {
        this.shortCompanyNameE = shortCompanyNameE;
    }
    
    public String getShortCompanyNameT() {
        return shortCompanyNameT;
    }

    public void setShortCompanyNameT(String shortCompanyNameT) {
        this.shortCompanyNameT = shortCompanyNameT;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getFirstCompany() {
		return firstCompany;
	}

	public void setFirstCompany(String firstCompany) {
		this.firstCompany = firstCompany;
	}

	public String getFirstCompanyE() {
		return firstCompanyE;
	}

	public void setFirstCompanyE(String firstCompanyE) {
		this.firstCompanyE = firstCompanyE;
	}

	public String getExhibitionNo() {
		return exhibitionNo;
	}

	public void setExhibitionNo(String exhibitionNo) {
		this.exhibitionNo = exhibitionNo;
	}
}
