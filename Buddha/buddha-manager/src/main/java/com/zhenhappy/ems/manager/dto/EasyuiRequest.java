package com.zhenhappy.ems.manager.dto;

/**
 * Created by wujianbin on 2014-04-24.
 */
public class EasyuiRequest {

    private int page = 1;
    private int rows = 10;
    private String sort;
    private String order;
    
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
    
}
