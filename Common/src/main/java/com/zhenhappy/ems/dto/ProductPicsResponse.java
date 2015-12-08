package com.zhenhappy.ems.dto;

import java.util.List;

/**
 * Created by wujianbin on 2014-04-19.
 */
public class ProductPicsResponse extends BaseResponse {

    private List<Integer> pids;

    public List<Integer> getPids() {
        return pids;
    }

    public void setPids(List<Integer> pids) {
        this.pids = pids;
    }
}
