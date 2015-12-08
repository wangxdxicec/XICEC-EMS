package com.zhenhappy.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: Haijian Liang
 * Date: 13-11-21
 * Time: 下午10:28
 * Function:
 */
public class CompanyInfoResponse extends BaseResponse {

    private Integer companyId;
    private String exhibitionNo;
    private String country;
    private String company;
    private String companyE;
    private String shortCompanyName;
    private String shortCompanyNameE;
    private String shortCompanyNameT;
    private String address;
    private String addressE;
    private String postCode;
    private String telephone;
    private String fax;
    private String email;
    private String website;
    private String mainProduct;
    private String mainProductE;
    private String productType;
    private String productTypeDetal;
    private String productTypeOther;
    private String logoUrl;
    private boolean collected;
    
    private Integer tileId;
    
    private List<Point> points; 

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public String getExhibitionNo() {
        return exhibitionNo;
    }

    public void setExhibitionNo(String exhibitionNo) {
        this.exhibitionNo = exhibitionNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShortCompanyNameT() {
		return shortCompanyNameT;
	}

	public void setShortCompanyNameT(String shortCompanyNameT) {
		this.shortCompanyNameT = shortCompanyNameT;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressE() {
        return addressE;
    }

    public void setAddressE(String addressE) {
        this.addressE = addressE;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct;
    }

    public String getMainProductE() {
        return mainProductE;
    }

    public void setMainProductE(String mainProductE) {
        this.mainProductE = mainProductE;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductTypeDetal() {
        return productTypeDetal;
    }

    public void setProductTypeDetal(String productTypeDetal) {
        this.productTypeDetal = productTypeDetal;
    }

    public String getProductTypeOther() {
        return productTypeOther;
    }

    public void setProductTypeOther(String productTypeOther) {
        this.productTypeOther = productTypeOther;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}
	
	public static class Point{
		private String zhanweiNum;
		private double x;
		private double y;
		private String serviceDesk;
		@JSONField(serialize=false)
		public String getServiceDesk() {
			return serviceDesk;
		}
		public void setServiceDesk(String serviceDesk) {
			this.serviceDesk = serviceDesk;
		}
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		
		@Override
		public String toString() {
			return zhanweiNum;
		}
		
		@JSONField(serialize=false)
		public String getZhanweiNum() {
			return zhanweiNum;
		}
		public void setZhanweiNum(String zhanweiNum) {
			this.zhanweiNum = zhanweiNum;
		}
		
	}
	
	public static class Locations{

		private Integer tileId;
		
		private List<Point> points;

		public Integer getTileId() {
			return tileId;
		}

		public void setTileId(Integer tileId) {
			this.tileId = tileId;
		}

		public List<Point> getPoints() {
			return points;
		}

		public void setPoints(List<Point> points) {
			this.points = points;
		}
	}

	public Integer getTileId() {
		return tileId;
	}

	public void setTileId(Integer tileId) {
		this.tileId = tileId;
	}
}
