package com.zhenhappy.ems.dto;

import com.zhenhappy.ems.entity.TExhibitorJoiner;
import com.zhenhappy.ems.entity.TVisa;

/**
 * Created by wujianbin on 2014-05-22.
 */
public class ContactVisaDto {

    private TExhibitorJoiner joiner;

    private TVisa visa;

    public TExhibitorJoiner getContact() {
        return joiner;
    }

    public void setContact(TExhibitorJoiner joiner) {
        this.joiner = joiner;
    }

    public TVisa getVisa() {
        return visa;
    }

    public void setVisa(TVisa visa) {
        this.visa = visa;
    }
}
