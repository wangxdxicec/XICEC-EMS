package com.zhenhappy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.GetWscSpeakerInfoRequest;
import com.zhenhappy.dto.GetWscSpeakersRequest;
import com.zhenhappy.dto.WscSpeakerInfoResponse;
import com.zhenhappy.dto.WscSpeakersResponse;
import com.zhenhappy.entity.TWscSpeaker;
import com.zhenhappy.service.WscSpeakerService;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.LogUtil;
import com.zhenhappy.util.Page;

/**
 * 
 * @author 苏志锋
 * 
 */
@Controller
@RequestMapping(value = "/client")
public class WscSpeakerController extends BaseController {

    @Autowired
	private WscSpeakerService wscSpeakerService;

    @Autowired
    private SystemConfig systemConfig;


	public WscSpeakerController() {
        super(WscSpeakerController.class);
    }

	@RequestMapping(value = "allWscSpeakers", method = RequestMethod.POST)
	@ResponseBody
	public WscSpeakersResponse allWscSpeakers(@RequestBody GetWscSpeakersRequest request) {
		LogUtil.logRequest(log, this, request);
		WscSpeakersResponse response = new WscSpeakersResponse();
		try {
			Page page = new Page(request.getPageIndex(), request.getPageSize());
			List<TWscSpeaker> tWscSpeakers = wscSpeakerService.query(page, request);
			response.setHasNext(page.getHasNext());
			List<WscSpeakerInfoResponse> items = new ArrayList<WscSpeakerInfoResponse>();
			for (TWscSpeaker tWscSpeaker : tWscSpeakers) {
				WscSpeakerInfoResponse item = new WscSpeakerInfoResponse();
				BeanUtils.copyProperties(tWscSpeaker, item);
				items.add(item);
			}
			response.setItems(items);
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "getWscSpeaker", method = RequestMethod.POST)
	@ResponseBody
	public WscSpeakerInfoResponse getWscSpeaker(@RequestBody GetWscSpeakerInfoRequest request) {
		LogUtil.logRequest(log, this, request);
		WscSpeakerInfoResponse response = new WscSpeakerInfoResponse();
		try {
			TWscSpeaker item = wscSpeakerService.get(request.getSpeakerId());
			BeanUtils.copyProperties(item, response);
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

    public SystemConfig getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }
}
