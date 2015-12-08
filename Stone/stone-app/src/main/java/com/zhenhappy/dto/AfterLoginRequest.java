package com.zhenhappy.dto;

/**
 * User: Haijian Liang
 * Date: 13-11-19
 * Time: 下午9:11
 * Function:
 */
public class AfterLoginRequest extends BaseRequest {

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
