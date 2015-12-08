package com.zhenhappy.dto;

import java.util.List;

/**
 * 
 * @author rocsky
 * 
 */
public class RegisterRequest extends AfterLoginRequest {
	private int id;
	private String name;
	private String companyName;
	private String tel;
	private String email;
	private String loc;
	private List<Integer> tExhibitItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Integer> gettExhibitItems() {
		return tExhibitItems;
	}

	public void settExhibitItems(List<Integer> tExhibitItems) {
		this.tExhibitItems = tExhibitItems;
	}

}
