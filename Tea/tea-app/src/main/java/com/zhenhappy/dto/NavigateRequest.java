package com.zhenhappy.dto;

import com.zhenhappy.locate.Point2D;

/**
 * 导航请求
 * 
 * @author wujianbin
 * 
 */
public class NavigateRequest extends BaseRequest {

	private Point2D begin;

	private Point2D end;

	public Point2D getBegin() {
		return begin;
	}

	public void setBegin(Point2D begin) {
		this.begin = begin;
	}

	public Point2D getEnd() {
		return end;
	}

	public void setEnd(Point2D end) {
		this.end = end;
	}

}
