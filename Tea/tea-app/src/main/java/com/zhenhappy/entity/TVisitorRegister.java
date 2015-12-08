package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author rocsky
 * 
 */
@Entity
@Table(name = "t_preregister_visitor", schema = "dbo")
public class TVisitorRegister implements java.io.Serializable {
	private Integer visitorId;
	private String name;
	private String companyName;
	private String tel;
	private String email;
	private String position;
	public TVisitorRegister(){
		
	}

	public TVisitorRegister(Integer visitorId, String name, String companyName,
			String tel, String email, String position) {
		this.visitorId = visitorId;
		this.name = name;
		this.companyName = companyName;
		this.tel = tel;
		this.email = email;
		this.position = position;
	}
	@Id
	@Column(name = "visitor_id", unique = true, nullable = false)
	public Integer getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "position")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	@Transient
	public Boolean getIsNone() {
		return null == visitorId;
	}
}
