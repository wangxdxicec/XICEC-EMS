package com.zhenhappy.entity;

import java.util.List;

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
@Table(name = "t_preregister_show", schema = "dbo")
public class TShowRegister implements java.io.Serializable {
	private Integer showId;
	private String name;
	private String companyName;
	private String tel;
	private String email;
	private String loc;
	private List<Integer> items;
	@Id
	@Column(name = "show_id", unique = true, nullable = false)
	public Integer getShowId() {
		return showId;
	}

	public void setShowId(Integer showId) {
		this.showId = showId;
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

	@Column(name = "loc")
	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Transient
	public List<Integer> getItems() {
		return items;
	}

	public void setItems(List<Integer> items) {
		this.items = items;
	}
	
	@Transient
	public Boolean getIsNone() {
		return null == showId;
	}
	
}
