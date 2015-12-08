package com.zhenhappy.ems.manager.dto;

/**
 * Created by wujianbin on 2014-04-24.
 */
public class QueryProductsRequest extends EasyuiRequest {

    private String productName;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    private Integer eid;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
