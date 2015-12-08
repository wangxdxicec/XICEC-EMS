package com.zhenhappy.dto;



/**
 * 
 * @author rocsky
 * 
 */
public class ExhibitItemRequest extends AfterLoginRequest {
	private int itemId;
	private String nameEn;
	private String nameZh;
	private String nameTw;

	public String getNameTw() {
		return nameTw;
	}

	public void setNameTw(String nameTw) {
		this.nameTw = nameTw;
	}
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getNameZh() {
		return nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

}
