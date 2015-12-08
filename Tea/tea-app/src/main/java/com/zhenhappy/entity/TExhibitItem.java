package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author rocsky
 * 
 */
@Entity
@Table(name = "t_exhibit_item", schema = "dbo")
public class TExhibitItem implements java.io.Serializable {
	private int itemId;
	private String nameEn;
	private String nameZh;
	private String nameTw;
	@Id
	@Column(name = "item_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	@Column(name = "name_en")
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	@Column(name = "name_zh")
	public String getNameZh() {
		return nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	@Column(name = "name_tw")
	public String getNameTw() {
		return nameTw;
	}

	public void setNameTw(String nameTw) {
		this.nameTw = nameTw;
	}

}
