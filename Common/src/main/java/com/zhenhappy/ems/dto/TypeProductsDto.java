package com.zhenhappy.ems.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujianbin on 2014-05-18.
 */
public class TypeProductsDto {

    private Integer typeId;

    private String typeName;

    private String typeNameEn;

    private List<Product> products = new ArrayList<Product>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNameEn() {
        return typeNameEn;
    }

    public void setTypeNameEn(String typeNameEn) {
        this.typeNameEn = typeNameEn;
    }

    public class Product{

        private String productName;

        private String productEnglishName;

        private Integer productId;

        public Product(String productName, String productEnglishName, Integer productId) {
            this.productName = productName;
            this.productEnglishName = productEnglishName;
            this.productId = productId;
            TypeProductsDto.this.products.add(this);
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductEnglishName() {
            return productEnglishName;
        }

        public void setProductEnglishName(String productEnglishName) {
            this.productEnglishName = productEnglishName;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }
    }
}
