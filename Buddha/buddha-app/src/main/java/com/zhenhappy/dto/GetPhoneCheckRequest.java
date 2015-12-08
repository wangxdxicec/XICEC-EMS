package com.zhenhappy.dto;

/**
 * User: Haijian Liang
 * Date: 13-11-18
 * Time: 下午9:43
 * Function:
 */
public class GetPhoneCheckRequest extends BaseRequest {
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
