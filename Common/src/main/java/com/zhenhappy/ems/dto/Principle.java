package com.zhenhappy.ems.dto;

import com.zhenhappy.ems.entity.TExhibitor;

/**
 * Created by wujianbin on 2014-04-07.
 */
public class Principle {

    public static final String PRINCIPLE_SESSION_ATTRIBUTE = "PRINCIPLE_SESSION_ATTRIBUTE";

    public Principle(TExhibitor exhibitor) {
        this.exhibitor = exhibitor;
    }

    private TExhibitor exhibitor;

    public TExhibitor getExhibitor() {
        return exhibitor;
    }

    public void setExhibitor(TExhibitor exhibitor) {
        this.exhibitor = exhibitor;
    }
}
