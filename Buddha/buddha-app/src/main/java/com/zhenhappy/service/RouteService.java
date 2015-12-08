package com.zhenhappy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zhenhappy.locate.Point2D;

@Service
public class RouteService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<String> getIosRoutesByPage() {
		return jdbcTemplate.queryForList("select * from t_route_ios", new Object[] {}, String.class);
	}
	
	public List<String> getAndroidRoutesByPage() {
		return jdbcTemplate.queryForList("select * from t_route_android", new Object[] {}, String.class);
	}

	public List<Point2D> getAndroidPoints(){
		List<Map<String, Object>> datas = jdbcTemplate.queryForList("select * from t_route_point_android");
		return JSONArray.parseArray(JSONArray.toJSONString(datas),Point2D.class);
	}

	public List<Point2D> getIosPoints(){
		List<Map<String, Object>> datas = jdbcTemplate.queryForList("select * from t_route_point_ios");
		return JSONArray.parseArray(JSONArray.toJSONString(datas),Point2D.class);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
