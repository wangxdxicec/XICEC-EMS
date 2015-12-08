package com.zhenhappy.ems.dto;

import com.zhenhappy.ems.entity.TContact;

import java.util.List;

/**
 * Created by wujianbin on 2014-05-17.
 */
public class QueryContactResponse extends BaseResponse {

    private List<TContact> contacts;

    public List<TContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<TContact> contacts) {
        this.contacts = contacts;
    }
}
