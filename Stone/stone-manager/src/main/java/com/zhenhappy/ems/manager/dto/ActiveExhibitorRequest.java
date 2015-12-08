package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.dto.BaseRequest;

/**
 * Created by wujianbin on 2014-04-26.
 */
public class ActiveExhibitorRequest extends BaseRequest {

    private Integer id;

    private Integer term;

    private String boothNumber;

    private String mark;

    private Integer eid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getBoothNumber() {
        return boothNumber;
    }

    public void setBoothNumber(String boothNumber) {
        this.boothNumber = boothNumber;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}
