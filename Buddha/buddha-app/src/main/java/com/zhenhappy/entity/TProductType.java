package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author 苏志锋
 * @version 1.0
 * @since 1.0
 */


@Entity
@Table(name = "t_product_type")
public class TProductType implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ProductType";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CLASS_NAME = "className";
	public static final String ALIAS_CLASS_NAME_EN = "classNameEn";
	public static final String ALIAS_LEVEL = "level";
	public static final String ALIAS_PARENT_ID = "parentId";
	public static final String ALIAS_SORT = "sort";
	public static final String ALIAS_MARK = "mark";
	public static final String ALIAS_CREATE_BY = "createBy";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_USER = "updateUser";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_IS_OTHER = "isOther";
	
	private java.lang.Integer id;
	private java.lang.String className;
	private java.lang.String classNameEn;
	private java.lang.Integer level;
	private java.lang.Integer parentId;
	private java.lang.Integer sort;
	private java.lang.String mark;
	
	private java.lang.Integer createBy;
	
	private java.util.Date createTime;
	
	private java.lang.Integer updateUser;
	
	private java.util.Date updateTime;
	
	private java.lang.Integer isOther;

	@Id
	@Column(name = "id")
	public java.lang.Integer getProductTypeId() {
		return this.id;
	}
	
	public void setProductTypeId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Column(name = "class_name")
	public java.lang.String getClassName() {
		return this.className;
	}
	
	public void setClassName(java.lang.String value) {
		this.className = value;
	}
	
	@Column(name = "class_name_en")
	public java.lang.String getClassNameEn() {
		return this.classNameEn;
	}
	
	public void setClassNameEn(java.lang.String value) {
		this.classNameEn = value;
	}
	
	@Column(name = "level")
	public java.lang.Integer getLevel() {
		return this.level;
	}
	
	public void setLevel(java.lang.Integer value) {
		this.level = value;
	}
	
	@Column(name = "parent_id")
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	@Column(name = "sort")
	public java.lang.Integer getSort() {
		return this.sort;
	}
	
	public void setSort(java.lang.Integer value) {
		this.sort = value;
	}
	
	@Column(name = "mark")
	public java.lang.String getMark() {
		return this.mark;
	}
	
	public void setMark(java.lang.String value) {
		this.mark = value;
	}
	
	@Column(name = "create_by")
	public java.lang.Integer getCreateBy() {
		return this.createBy;
	}
	
	public void setCreateBy(java.lang.Integer value) {
		this.createBy = value;
	}
	
	@Column(name = "create_time")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	@Column(name = "update_user")
	public java.lang.Integer getUpdateUser() {
		return this.updateUser;
	}
	
	public void setUpdateUser(java.lang.Integer value) {
		this.updateUser = value;
	}
	
	@Column(name = "update_time")
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	@Column(name = "is_other")
	public java.lang.Integer getIsOther() {
		return this.isOther;
	}
	
	public void setIsOther(java.lang.Integer value) {
		this.isOther = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getProductTypeId())
			.append("ClassName",getClassName())
			.append("ClassNameEn",getClassNameEn())
			.append("Level",getLevel())
			.append("ParentId",getParentId())
			.append("Sort",getSort())
			.append("Mark",getMark())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateUser",getUpdateUser())
			.append("UpdateTime",getUpdateTime())
			.append("IsOther",getIsOther())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ProductType == false) return false;
		if(this == obj) return true;
		ProductType other = (ProductType)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

