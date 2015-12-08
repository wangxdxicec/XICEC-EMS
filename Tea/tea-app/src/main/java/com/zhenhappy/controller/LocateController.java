package com.zhenhappy.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.LocateRequest;
import com.zhenhappy.dto.LocateResponse;
import com.zhenhappy.dto.NavigateRequest;
import com.zhenhappy.dto.NavigateResponse;
import com.zhenhappy.locate.Point2D;
import com.zhenhappy.service.LocateService;
import com.zhenhappy.util.LogUtil;

@Controller
@RequestMapping(value="client")
public class LocateController {

	@Autowired
	private LocateService locateService;

	private static Logger log = Logger.getLogger(LocateController.class);

	@RequestMapping(value = "locate", method = RequestMethod.POST)
	@ResponseBody
	public LocateResponse locate(@RequestBody LocateRequest request) {
		LocateResponse response = new LocateResponse();
		LogUtil.logRequest(log, this, request);
		try {
			Point2D point2d = locateService.locate(request.getAps());
			response.setX(point2d.getX());
			response.setY(point2d.getY());
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}
	
	/**
	 * 导航
	 */
	@RequestMapping(value="/navigate",method=RequestMethod.POST)
	@ResponseBody
	public NavigateResponse navigate(@RequestBody NavigateRequest request){
		LogUtil.logRequest(log, this, request);
		NavigateResponse response = new NavigateResponse();
		try{
			List<Point2D> points = locateService.navigate(request.getMachineType(), request.getBegin(), request.getEnd());
			response.setPoints(points);
		}catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("导航失败。",e);
		}
		return response;
	}
}
