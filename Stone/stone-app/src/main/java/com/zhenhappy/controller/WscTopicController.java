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
import com.zhenhappy.dto.GetWscTopicInfoRequest;
import com.zhenhappy.dto.GetWscTopicsRequest;
import com.zhenhappy.dto.WscTopicInfoResponse;
import com.zhenhappy.dto.WscTopicsResponse;
import com.zhenhappy.entity.TWscTopic;
import com.zhenhappy.service.WscTopicService;
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
public class WscTopicController extends BaseController {

    @Autowired
	private WscTopicService wscTopicService;

    @Autowired
    private SystemConfig systemConfig;

	public WscTopicController() {
        super(WscTopicController.class);
    }

	@RequestMapping(value = "allWscTopics", method = RequestMethod.POST)
	@ResponseBody
	public WscTopicsResponse allWscTopics(@RequestBody GetWscTopicsRequest request) {
		LogUtil.logRequest(log, this, request);
		WscTopicsResponse response = new WscTopicsResponse();
		try {
			Page page = new Page(request.getPageIndex(), request.getPageSize());
			List<TWscTopic> tWscTopics = wscTopicService.query(page, request);
			response.setHasNext(page.getHasNext());
			List<WscTopicInfoResponse> items = new ArrayList<WscTopicInfoResponse>();
			for (TWscTopic tWscTopic : tWscTopics) {
				WscTopicInfoResponse item = new WscTopicInfoResponse();
				BeanUtils.copyProperties(tWscTopic, item);
				items.add(item);
			}
			response.setItems(items);
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "getWscTopic", method = RequestMethod.POST)
	@ResponseBody
	public WscTopicInfoResponse getWscTopic(@RequestBody GetWscTopicInfoRequest request) {
		LogUtil.logRequest(log, this, request);
		WscTopicInfoResponse response = new WscTopicInfoResponse();
		try {
			TWscTopic item = wscTopicService.get(request.getTopicId());
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
