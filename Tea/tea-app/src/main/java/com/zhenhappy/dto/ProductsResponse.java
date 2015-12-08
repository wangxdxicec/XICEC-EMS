package com.zhenhappy.dto;

import java.util.List;

public class ProductsResponse extends BaseResponse{
	
	private List<BaseProduct> products;
	
	private boolean hasNext;

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public List<BaseProduct> getProducts() {
		return products;
	}

	public void setProducts(List<BaseProduct> products) {
		this.products = products;
	}

	
}
