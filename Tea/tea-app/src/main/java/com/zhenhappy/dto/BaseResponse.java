package com.zhenhappy.dto;

import javax.persistence.Entity;

/**
 * 基本反馈类型
 *
 * @author wujianbin
 */
@Entity
public class BaseResponse {

    private int resultCode = ErrorCode.SUCCESS.getCode();

    private String des = ErrorCode.SUCCESS.getMsg();

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.resultCode = errorCode.getCode();
        this.des = errorCode.getMsg();
    }
}
