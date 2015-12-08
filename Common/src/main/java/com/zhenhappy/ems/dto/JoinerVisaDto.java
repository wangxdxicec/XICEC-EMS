package com.zhenhappy.ems.dto;

import com.zhenhappy.ems.entity.TExhibitorJoiner;
import com.zhenhappy.ems.entity.TVisa;

/**
 * Created by wujianbin on 2014-11-11.
 */
public class JoinerVisaDto {

    private TExhibitorJoiner joiner;
    private TVisa visa;

	public TExhibitorJoiner getJoiner() {
		return joiner;
	}

	public void setJoiner(TExhibitorJoiner joiner) {
		this.joiner = joiner;
	}

	public TVisa getVisa() {
		return visa;
	}

	public void setVisa(TVisa visa) {
		this.visa = visa;
	}
}
