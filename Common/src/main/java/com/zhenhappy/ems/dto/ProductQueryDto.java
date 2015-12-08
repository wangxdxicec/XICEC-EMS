package com.zhenhappy.ems.dto;

import com.zhenhappy.util.Page;

import java.util.Locale;

/**
 * Created by wujianbin on 2014-04-14.
 */
public class ProductQueryDto extends Page {

    private String productName;

    private String productModel;

    private String productSpec;

    private String productBrand;

    private Integer eid;

    private Locale locale;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    private int resultCode;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
