package com.zhenhappy.dto;

/**
 * 
 * @author rocsky
 * 
 */
public class ProductRequest extends AfterLoginRequest {
	private Integer productId;

	private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
