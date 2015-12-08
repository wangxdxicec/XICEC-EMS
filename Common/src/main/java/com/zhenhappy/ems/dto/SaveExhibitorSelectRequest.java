package com.zhenhappy.ems.dto;

import java.util.List;

/**
 * Created by wujianbin on 2014-04-13.
 */
public class SaveExhibitorSelectRequest extends BaseRequest {

    private List<ProductTypeCheck> selected;

    public List<ProductTypeCheck> getSelected() {
        return selected;
    }

    public void setSelected(List<ProductTypeCheck> selected) {
        this.selected = selected;
    }
}
