package com.zhenhappy.dto;

/**
 * User: Haijian Liang
 * Date: 13-11-20
 * Time: 下午8:49
 * Function:
 */
public class PageDataRequest extends AfterLoginRequest {

    private Integer pageIndex;

    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
