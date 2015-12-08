package com.zhenhappy.util;

import java.util.List;

public class Page {

	public static final int DEFAULT_PAGE_SIZE = 10;

	/**
	 * 当前页面
	 */
	private int pageIndex = 1;
	/**
	 * 分页大小
	 */
	private int pageSize = 10;
	/**
	 * 总记录数
	 */
	private int totalCount;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 上一页索引
	 */
	private int preIndex;
	/**
	 * 下一页索引
	 */
	private int nextIndex;

    private List datas;

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public List getRows(){
        return datas;
    }

    public int getTotal(){
        return getTotalCount();
    }

    public Page(int pageIndex, int pageSize) {
		// check:
		if (pageIndex < 1)
			pageIndex = 1;
		if (pageSize < 1)
			pageSize = 1;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public Page() {
		pageSize = DEFAULT_PAGE_SIZE;
	}

	public Page(int pageIndex) {
		this(pageIndex, DEFAULT_PAGE_SIZE);
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getFirstResult() {
		return (pageIndex - 1) * pageSize;
	}

	public boolean getHasPrevious() {
		return pageIndex > 1;
	}

	public boolean getHasNext() {
		return pageIndex < pageCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
	}

	public boolean isEmpty() {
		return totalCount == 0;
	}

	public int getPreIndex() {
		return (getHasPrevious() ? pageIndex - 1 : pageIndex);
	}

	public void setPreIndex(int preIndex) {
		this.preIndex = preIndex;
	}

	public int getNextIndex() {
		return (getHasNext() ? pageIndex + 1 : pageIndex);
	}

	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
