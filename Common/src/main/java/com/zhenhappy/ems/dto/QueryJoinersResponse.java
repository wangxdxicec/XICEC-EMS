package com.zhenhappy.ems.dto;

import com.zhenhappy.ems.entity.TExhibitorJoiner;

import java.util.List;

/**
 * Created by wujianbin on 2014-04-20.
 */
public class QueryJoinersResponse extends BaseResponse {

    private List<TExhibitorJoiner> joiners;
    private Integer area;

    public List<TExhibitorJoiner> getJoiners() {
        return joiners;
    }

    public void setJoiners(List<TExhibitorJoiner> joiners) {
        this.joiners = joiners;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }
}
