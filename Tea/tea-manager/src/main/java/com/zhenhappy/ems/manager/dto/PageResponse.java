package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.util.Page;

/**
 * Created by wujianbin on 2014-04-22.
 */
public class PageResponse extends Page {
    private int resultCode = 0;
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
