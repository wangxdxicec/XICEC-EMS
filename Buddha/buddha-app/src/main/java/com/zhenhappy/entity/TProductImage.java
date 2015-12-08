package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.transaction.annotation.Transactional;

import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.ApplicationContextUtil;

/**
 * @author 苏志锋
 * @version 1.0
 * @since 1.0
 */


@Entity
@Table(name = "t_product_image")
public class TProductImage implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ProductImage";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PRODUCT_ID = "productId";
	public static final String ALIAS_IMAGE = "image";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_ADMIN = "admin";
	public static final String ALIAS_ADMIN_UPDATE_TIME = "adminUpdateTime";
	
	private java.lang.Integer id;
	private java.lang.Integer productId;
	private java.lang.String image;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private java.lang.Integer admin;
	private java.util.Date adminUpdateTime;

	@Id
	@Column(name = "id")
	public java.lang.Integer getProductImgId() {
		return this.id;
	}
	
	public void setProductImgId(java.lang.Integer value) {
		this.id = value;
	}

	@Column(name = "product_id")
	public java.lang.Integer getProductId() {
		return this.productId;
	}
	
	public void setProductId(java.lang.Integer value) {
		this.productId = value;
	}
	@Transient
	public String getImage(){
		return ApplicationContextUtil.getBean(SystemConfig.class).getVal("product_img_action_url")+ "productImage?imageUrl=" +this.image;
	}
	
	@Column(name = "image")
	public java.lang.String getImageName() {
		return this.image;
	}
	
	public void setImageName(java.lang.String value) {
		this.image = value;
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
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getProductImgId())
			.append("ProductId",getProductId())
			.append("Image",getImage())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Admin",getAdmin())
			.append("AdminUpdateTime",getAdminUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TProductImage == false) return false;
		if(this == obj) return true;
		TProductImage other = (TProductImage)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

