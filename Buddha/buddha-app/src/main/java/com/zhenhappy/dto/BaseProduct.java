package com.zhenhappy.dto;

import java.util.List;

import javax.persistence.Column;

import com.zhenhappy.entity.TExhibitorInfo;
import com.zhenhappy.entity.TProductImage;
import com.zhenhappy.entity.TProductType;

public class BaseProduct {

	private java.lang.Integer id;
	private TExhibitorInfo exhibitorInfo;
	private java.lang.String productName;
	private java.lang.String productNameE;
	private java.lang.String productNameT;
	private java.lang.String productModel;
	private java.lang.String origin;
	private java.lang.String keyWords;

	private java.sql.Clob description;
	private java.lang.String productBrand;
	private java.lang.String productSpec;
	private java.lang.String productCount;
	private java.lang.String packageDescription;
	private java.lang.String priceDescription;

	private java.lang.Integer flag;

	private java.util.Date createTime;

	private java.util.Date updateTime;

	private java.lang.Integer admin;

	private java.util.Date adminUpdateTime;
	private List<TProductType> productTypes;
	private List<TProductImage> productImages;

	public List<TProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<TProductImage> productImages) {
		this.productImages = productImages;
	}

	public List<TProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<TProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public java.lang.Integer getProductId() {
		return this.id;
	}

	public void setProductId(java.lang.Integer value) {
		this.id = value;
	}

	public TExhibitorInfo getExhibitorInfo() {
		return exhibitorInfo;
	}

	public void setExhibitorInfo(TExhibitorInfo exhibitorInfo) {
		this.exhibitorInfo = exhibitorInfo;
	}
	
	public java.lang.String getProductNameT() {
		return this.productNameT;
	}
	
	public void setProductNameT(java.lang.String value) {
		this.productNameT = value;
	}
	
	public java.lang.String getProductNameE() {
		return this.productNameE;
	}
	
	public void setProductNameE(java.lang.String value) {
		this.productNameE = value;
	}

	public java.lang.String getProductName() {
		return this.productName;
	}

	public void setProductName(java.lang.String value) {
		this.productName = value;
	}

	public java.lang.String getProductModel() {
		return this.productModel;
	}

	public void setProductModel(java.lang.String value) {
		this.productModel = value;
	}

	public java.lang.String getOrigin() {
		return this.origin;
	}

	public void setOrigin(java.lang.String value) {
		this.origin = value;
	}

	public java.lang.String getKeyWords() {
		return this.keyWords;
	}

	public void setKeyWords(java.lang.String value) {
		this.keyWords = value;
	}

	public java.sql.Clob getDescription() {
		return this.description;
	}

	public void setDescription(java.sql.Clob value) {
		this.description = value;
	}

	public java.lang.String getProductBrand() {
		return this.productBrand;
	}

	public void setProductBrand(java.lang.String value) {
		this.productBrand = value;
	}

	public java.lang.String getProductSpec() {
		return this.productSpec;
	}

	public void setProductSpec(java.lang.String value) {
		this.productSpec = value;
	}

	public java.lang.String getProductCount() {
		return this.productCount;
	}

	public void setProductCount(java.lang.String value) {
		this.productCount = value;
	}

	public java.lang.String getPackageDescription() {
		return this.packageDescription;
	}

	public void setPackageDescription(java.lang.String value) {
		this.packageDescription = value;
	}

	public java.lang.String getPriceDescription() {
		return this.priceDescription;
	}

	public void setPriceDescription(java.lang.String value) {
		this.priceDescription = value;
	}

	public java.lang.Integer getFlag() {
		return this.flag;
	}

	public void setFlag(java.lang.Integer value) {
		this.flag = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}

	public java.lang.Integer getAdmin() {
		return this.admin;
	}

	public void setAdmin(java.lang.Integer value) {
		this.admin = value;
	}

	public java.util.Date getAdminUpdateTime() {
		return this.adminUpdateTime;
	}

	public void setAdminUpdateTime(java.util.Date value) {
		this.adminUpdateTime = value;
	}
}
