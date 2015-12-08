package com.zhenhappy.dto;

import java.util.List;

public class ProductTypeResponse extends BaseResponse{
	
	private List<ProductTypeDTO> productTypes;

	public List<ProductTypeDTO> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductTypeDTO> productTypes) {
		this.productTypes = productTypes;
	}
	
}
