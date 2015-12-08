package com.zhenhappy.dto;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author 苏志锋
 * 
 */
public class ProductInfoResponse extends BaseResponse {
	private Integer productId;
	private String name;
	private String nameE;
	private String nameT;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return StringUtils.isBlank(name) ? nameE : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameE() {
		return StringUtils.isBlank(nameE) ? name : nameE;
	}

	public void setNameE(String nameE) {
		this.nameE = nameE;
	}

	public String getNameT() {
		return StringUtils.isBlank(nameT) ? getName() : nameT;
	}

	public void setNameT(String nameT) {
		this.nameT = nameT;
	}

}
