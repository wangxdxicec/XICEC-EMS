package com.zhenhappy.dto;

import java.util.List;

import com.zhenhappy.locate.Point2D;

public class NavigateResponse extends BaseResponse {

	private List<Point2D> points;

	public List<Point2D> getPoints() {
		return points;
	}

	public void setPoints(List<Point2D> points) {
		this.points = points;
	}
}
