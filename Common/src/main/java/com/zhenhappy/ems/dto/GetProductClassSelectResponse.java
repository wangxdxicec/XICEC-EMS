package com.zhenhappy.ems.dto;

import java.util.List;

/**
 * Created by wujianbin on 2014-04-16.
 */
public class GetProductClassSelectResponse extends BaseResponse {

    public List<ProductTypeCheck> getSelected() {
        return selected;
    }

    public void setSelected(List<ProductTypeCheck> selected) {
        this.selected = selected;
    }

    private List<ProductTypeCheck> selected;

}
