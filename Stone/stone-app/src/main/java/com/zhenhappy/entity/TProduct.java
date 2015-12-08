package com.zhenhappy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * @author 苏志锋
 * 
 */
@Entity
@Table(name = "ExhibitorProduct", schema = "dbo")
public class TProduct {
	private Integer productId;
	private Integer eid;
	private String name;
	private String nameE;
	private String nameT;

	@Id
	@Column(name = "product_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name = "eid")
	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	@Column(name = "product_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "product_name_en")
	public String getNameE() {
		return nameE;
	}

	public void setNameE(String nameE) {
		this.nameE = nameE;
	}

	@Column(name = "product_name_tw")
	public String getNameT() {
		return nameT;
	}

	public void setNameT(String nameT) {
		this.nameT = nameT;
	}

}
