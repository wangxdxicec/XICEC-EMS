package com.zhenhappy.ems.manager.dto;

/**
 * Created by wujianbin on 2014-04-24.
 */
public class QueryJoinersRequest extends EasyuiRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer eid;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}
