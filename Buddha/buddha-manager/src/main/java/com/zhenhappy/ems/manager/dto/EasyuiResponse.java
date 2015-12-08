package com.zhenhappy.ems.manager.dto;

import com.zhenhappy.ems.dto.BaseResponse;

import java.util.List;

/**
 * Created by wujianbin on 2014-04-24.
 */
public class EasyuiResponse extends BaseResponse {

    private List rows;

    private int total;

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
