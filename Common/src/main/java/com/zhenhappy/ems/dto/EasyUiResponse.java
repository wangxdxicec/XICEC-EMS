package com.zhenhappy.ems.dto;

import java.util.List;

/**
 * Created by wujianbin on 2014-05-11.
 */
public class EasyUiResponse extends BaseResponse{

    private Integer total;

    private List rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
