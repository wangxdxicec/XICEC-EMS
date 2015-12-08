package com.zhenhappy.ems.dto;

/**
 * Created by wujianbin on 2014-04-04.
 */
public class BaseResponse {

    private int resultCode;

    private String description;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
