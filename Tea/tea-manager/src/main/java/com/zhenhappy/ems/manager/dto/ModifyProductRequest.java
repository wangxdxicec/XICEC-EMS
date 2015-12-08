package com.zhenhappy.ems.manager.dto;

import java.util.Date;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-07-05.
 */
public class ModifyProductRequest extends BaseRequest {
	private Integer id;
	private Integer einfoId;
	private String productName;
	private String productModel;
	private String origin;
	private String keyWords;
	private String description;
	private String productBrand;
	private String productSpec;
	private String productCount;
	private String packageDescription;
	private String priceDescription;
	private Integer flag;
	private Date createTime;
	private Date updateTime;
	private Integer admin;
	private Date adminUpdateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEinfoId() {
		return einfoId;
	}

	public void setEinfoId(Integer einfoId) {
		this.einfoId = einfoId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public String getPriceDescription() {
		return priceDescription;
	}

	public void setPriceDescription(String priceDescription) {
		this.priceDescription = priceDescription;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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
