package com.zhenhappy.ems.dto;

import com.zhenhappy.util.Page;

/**
 * Created by lianghaijian on 2014-08-25.
 */
public class GetMailSendDetailsResponse extends Page{

    private int resultCode;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
