package com.zhenhappy.dto;

/**
 * User: Haijian Liang
 * Date: 13-11-21
 * Time: 下午11:25
 * Function:
 */
public class QueryCompanyRequest extends GetCompaniesRequest {

    private Integer queryType;

    private String condition;

    /**
     * 来源
     * 1.普通查询
     * 2.地图定位查询
     */

    private Integer fromType;

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
