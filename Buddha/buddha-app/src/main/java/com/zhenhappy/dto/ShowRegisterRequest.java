package com.zhenhappy.dto;

import java.util.List;



/**
 * 
 * @author rocsky
 * 
 */
public class ShowRegisterRequest extends AfterLoginRequest {
	private Integer showId;
	private String name;
	private String companyName;
	private String tel;
	private String email;
	private String loc;
	private List<Integer> items;
	private Boolean isNone ;

	public Integer getShowId() {
		return showId;
	}

	public void setShowId(Integer showId) {
		this.showId = showId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public List<Integer> getItems() {
		return items;
	}

	public void setItems(List<Integer> items) {
		this.items = items;
	}
	
	public Boolean getIsNone() {
		return null == showId;
	}

	public void setIsNone(Boolean isNone) {
		this.isNone = isNone;
	}
	

}
