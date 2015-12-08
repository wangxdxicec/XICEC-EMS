/*
 *
 */

package com.zhenhappy.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author 苏志锋
 * @version 1.0
 * @since 1.0
 */


@Entity
@Table(name = "t_product")
public class TProduct implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Product";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_EINFO_ID = "einfoId";
	public static final String ALIAS_PRODUCT_NAME = "productName";
	public static final String ALIAS_PRODUCT_NAME_EN = "productNameEN";
	public static final String ALIAS_PRODUCT_NAME_TW = "productNameTW";
	public static final String ALIAS_PRODUCT_MODEL = "productModel";
	public static final String ALIAS_ORIGIN = "origin";
	public static final String ALIAS_KEY_WORDS = "keyWords";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_PRODUCT_BRAND = "productBrand";
	public static final String ALIAS_PRODUCT_SPEC = "productSpec";
	public static final String ALIAS_PRODUCT_COUNT = "productCount";
	public static final String ALIAS_PACKAGE_DESCRIPTION = "packageDescription";
	public static final String ALIAS_PRICE_DESCRIPTION = "priceDescription";
	public static final String ALIAS_FLAG = "flag";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_ADMIN = "admin";
	public static final String ALIAS_ADMIN_UPDATE_TIME = "adminUpdateTime";
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
	
	 @OneToMany(targetEntity = TProductImage.class,fetch=FetchType.EAGER)
	 @JoinColumn(name = "product_id")
	public List<TProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<TProductImage> productImages) {
		this.productImages = productImages;
	}

	@ManyToMany(targetEntity = TProductType.class,fetch=FetchType.EAGER)
	@JoinTable(name = "t_product_class_relation", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = { @JoinColumn(name = "class_id") })
	@Fetch(FetchMode.SUBSELECT)
	public List<TProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<TProductType> productTypes) {
		this.productTypes = productTypes;
	}

	@Id
	@Column(name = "id")
	public java.lang.Integer getProductId() {
		return this.id;
	}
	
	public void setProductId(java.lang.Integer value) {
		this.id = value;
	}
	
	@ManyToOne(targetEntity = TExhibitorInfo.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "einfo_id")
	public TExhibitorInfo getExhibitorInfo() {
		return this.exhibitorInfo;
	}
	
	public void setExhibitorInfo(TExhibitorInfo value) {
		this.exhibitorInfo = value;
	}
	
	@Column(name = "product_name")
	public java.lang.String getProductName() {
		return this.productName;
	}
	
	public void setProductName(java.lang.String value) {
		this.productName = value;
	}
	
	@Column(name = "product_name_tw")
	public java.lang.String getProductNameT() {
		return this.productNameT;
	}
	
	public void setProductNameT(java.lang.String value) {
		this.productNameT = value;
	}
	
	@Column(name = "product_name_en")
	public java.lang.String getProductNameE() {
		return this.productNameE;
	}
	
	public void setProductNameE(java.lang.String value) {
		this.productNameE = value;
	}
	
	@Column(name = "product_model")
	public java.lang.String getProductModel() {
		return this.productModel;
	}
	
	public void setProductModel(java.lang.String value) {
		this.productModel = value;
	}
	
	@Column(name = "origin")
	public java.lang.String getOrigin() {
		return this.origin;
	}
	
	public void setOrigin(java.lang.String value) {
		this.origin = value;
	}
	
	@Column(name = "key_words")
	public java.lang.String getKeyWords() {
		return this.keyWords;
	}
	
	public void setKeyWords(java.lang.String value) {
		this.keyWords = value;
	}
	
	@Column(name = "description")
	public java.sql.Clob getDescription() {
		return this.description;
	}
	
	public void setDescription(java.sql.Clob value) {
		this.description = value;
	}
	
	@Column(name = "product_brand")
	public java.lang.String getProductBrand() {
		return this.productBrand;
	}
	
	public void setProductBrand(java.lang.String value) {
		this.productBrand = value;
	}
	
	@Column(name = "product_spec")
	public java.lang.String getProductSpec() {
		return this.productSpec;
	}
	
	public void setProductSpec(java.lang.String value) {
		this.productSpec = value;
	}
	
	@Column(name = "product_count")
	public java.lang.String getProductCount() {
		return this.productCount;
	}
	
	public void setProductCount(java.lang.String value) {
		this.productCount = value;
	}
	
	@Column(name = "package_description")
	public java.lang.String getPackageDescription() {
		return this.packageDescription;
	}
	
	public void setPackageDescription(java.lang.String value) {
		this.packageDescription = value;
	}
	
	@Column(name = "price_description")
	public java.lang.String getPriceDescription() {
		return this.priceDescription;
	}
	
	public void setPriceDescription(java.lang.String value) {
		this.priceDescription = value;
	}
	
	@Column(name = "flag")
	public java.lang.Integer getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.Integer value) {
		this.flag = value;
	}
	
	@Column(name = "create_time")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	@Column(name = "update_time")
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	@Column(name = "admin")
	public java.lang.Integer getAdmin() {
		return this.admin;
	}
	
	public void setAdmin(java.lang.Integer value) {
		this.admin = value;
	}
	
	@Column(name = "admin_update_time")
	public java.util.Date getAdminUpdateTime() {
		return this.adminUpdateTime;
	}
	
	public void setAdminUpdateTime(java.util.Date value) {
		this.adminUpdateTime = value;
	}
	

	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TProduct == false) return false;
		if(this == obj) return true;
		TProduct other = (TProduct)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

