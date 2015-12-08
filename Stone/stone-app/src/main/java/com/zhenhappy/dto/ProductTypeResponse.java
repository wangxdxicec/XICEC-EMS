package com.zhenhappy.dto;

import java.util.List;

public class ProductTypeResponse extends BaseResponse{
	
	private List<ProductType> productTypes;

	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}
}
