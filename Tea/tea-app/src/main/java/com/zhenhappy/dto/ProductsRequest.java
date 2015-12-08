package com.zhenhappy.dto;

/**
 * 
 * @author rocsky
 * 
 */
public class ProductsRequest extends PageDataRequest {
	private Integer eid;

	private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

}
