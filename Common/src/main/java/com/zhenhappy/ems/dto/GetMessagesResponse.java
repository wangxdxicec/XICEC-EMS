package com.zhenhappy.ems.dto;

import com.zhenhappy.util.Page;

/**
 * Created by wujianbin on 2014-08-28.
 */
public class GetMessagesResponse extends Page {

    private int resultCode;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
