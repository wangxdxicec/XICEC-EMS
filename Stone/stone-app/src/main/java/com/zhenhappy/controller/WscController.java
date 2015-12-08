package com.zhenhappy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.zhenhappy.dto.*;
import com.zhenhappy.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.entity.TUserWsc;
import com.zhenhappy.exception.ReturnException;
import com.zhenhappy.service.CompanyService;
import com.zhenhappy.service.WscService;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.CMSUtils;
import com.zhenhappy.util.LogUtil;

/**
 * 
 * @author 苏志锋
 * 
 */
@Controller
@RequestMapping(value = "/client/user")
public class WscController extends BaseController {
	@Autowired
	private CompanyService companyService;
    @Autowired
	private WscService wscService;

    @Autowired
    private SystemConfig systemConfig;

	public WscService getWscService() {
		return wscService;
	}

	public void setWscService(WscService wscService) {
		this.wscService = wscService;
	}

	public WscController() {
        super(WscController.class);
    }

	@RequestMapping(value = "allWscs", method = RequestMethod.POST)
	@ResponseBody
	public WscsResponse allWscs(@RequestBody GetWscsRequest request) {
		LogUtil.logRequest(log, this, request);
		WscsResponse response = new WscsResponse();
		try {
			CMSUtils.check();
			response.setHasNext(false);
			fillCollected(request.getUserId());
			List<WscInfoResponse> items = new ArrayList<WscInfoResponse>();
			SimpleDateFormat format = new SimpleDateFormat("dd");
			for (WscInfoResponse item : CMSUtils.wscs) {
				if (null != request.getType()) {
					if (Integer.parseInt(format.format(item.getWscDate())) == request.getType()) {
						items.add(getWscInfoResponse(item, request));
					}
				} else {
					items.add(getWscInfoResponse(item, request));
				}
			}
			response.setItems(items);
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "searchWsc", method = RequestMethod.POST)
	@ResponseBody
	public WscsResponse searchWsc(@RequestBody GetWscsRequest request) {
		LogUtil.logRequest(log, this, request);
		WscsResponse response = new WscsResponse();
		try {
			CMSUtils.check();
			response.setHasNext(false);
			fillCollected(request.getUserId());
			List<WscInfoResponse> items = new ArrayList<WscInfoResponse>();
			if (request.getPageIndex() > 1) {
				response.setItems(items);
				return response;
			}
			for (WscInfoResponse item : CMSUtils.wscs) {
                if(StringUtils.isNotEmpty(request.getName())){
                    System.out.println(getWscInfoResponse(item, request).getName());
                    System.out.println(getWscInfoResponse(item, request).getNameE());
                    System.out.println(getWscInfoResponse(item, request).getNameT());
                    if(null != getWscInfoResponse(item, request).getName()){
                        if(!getWscInfoResponse(item, request).getName().contains(request.getName())) continue;
                    }
                    if(null != getWscInfoResponse(item, request).getNameE()){
                        if(!getWscInfoResponse(item, request).getNameE().contains(request.getName())) continue;
                    }
                    if(null != getWscInfoResponse(item, request).getNameT()){
                        if(!getWscInfoResponse(item, request).getNameT().contains(request.getName())) continue;
                    }
                }
				items.add(getWscInfoResponse(item, request));
			}
			response.setItems(items);
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	private WscInfoResponse getWscInfoResponse(WscInfoResponse item, GetWscsRequest request) {
		WscInfoResponse wscInfoResponse = new WscInfoResponse();
		wscInfoResponse.setWscId(item.getWscId());
		wscInfoResponse.setWscDate(item.getWscDate());
		wscInfoResponse.setLocation(item.getLocation());
		wscInfoResponse.setIsCollect(item.getIsCollect());
        wscInfoResponse.setWscTime(item.getWscTime());
		if (request.getLocal() == 1) {// zh
			wscInfoResponse.setName(item.getName());
			if (item.getSpeakers().size() > 0) {
				wscInfoResponse.setSpeaker(item.getSpeakers().get(0).getName());
                wscInfoResponse.setTopic(item.getTopics().get(0).getName());
			}
		} else if (2 == request.getLocal()) {// en
			wscInfoResponse.setName(item.getNameE());
			if (item.getSpeakers().size() > 0) {
				wscInfoResponse.setSpeaker(item.getSpeakers().get(0).getNameE());
                wscInfoResponse.setTopic(item.getTopics().get(0).getNameE());
			}
		} else if (3 == request.getLocal()) {// tw
			wscInfoResponse.setName(item.getNameT());
			if (item.getSpeakers().size() > 0) {
				wscInfoResponse.setSpeaker(item.getSpeakers().get(0).getNameT());
                wscInfoResponse.setTopic(item.getTopics().get(0).getNameT());
			}
		}
		return wscInfoResponse;
	}

	private void fillCollected(Integer userId) {
		if (null != userId) {
			List<TUserWsc> tUserWscs = wscService.getTUserWscsByUserId(userId);
			for (WscInfoResponse wsc : CMSUtils.wscs) {
				wsc.setIsCollect(0);
				for (TUserWsc tUserWsc : tUserWscs) {
					if (wsc.getWscId().intValue() == tUserWsc.getWscId().intValue()) {
						wsc.setIsCollect(1);
						break;
					}
				}
			}
		}
	}

	@RequestMapping(value = "getWsc", method = RequestMethod.POST)
	@ResponseBody
	public WscInfoResponse getWsc(@RequestBody GetWscInfoRequest request) {
		LogUtil.logRequest(log, this, request);
		WscInfoResponse response = null;
		try {
			CMSUtils.check();
			fillCollected(request.getUserId());
			for (WscInfoResponse item : CMSUtils.wscs) {
				if (item.getWscId().equals(request.getWscId())) {
					response = item;
				}
			}
			if (null == response) {
				throw new RuntimeException("找不到id=" + request.getWscId());
			}
			response.setRemark(companyService.getRemark(request.getUserId(), request.getWscId(), 0));
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}
	@ResponseBody
	@RequestMapping(value = "collectWsc", method = RequestMethod.POST)
	public BaseResponse collectWsc(@RequestBody GetWscInfoRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			wscService.collectWsc(request);
		} catch (ReturnException e) {
			log.error(e);
			response.setResultCode(e.getErrorCode());
			response.setDes(e.getMessage());
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "cancelCollectWsc", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse cancelCollectWsc(@RequestBody GetWscInfoRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			wscService.cancelCollectWsc(request.getUserId(), request.getWscId());
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "myCollectWSC", method = RequestMethod.POST)
	public WscsResponse myCollectWSC(@RequestBody PageDataRequest request) {

		LogUtil.logRequest(log, this, request);
		WscsResponse response = new WscsResponse();
		try {
			Page page = new Page(request.getPageIndex(), request.getPageSize());
			List<TUserWsc> userWscs = wscService.myCollectWSC(request.getUserId());
			List<WscInfoResponse> items = new ArrayList<WscInfoResponse>();
			SimpleDateFormat format = new SimpleDateFormat("dd");
			for (TUserWsc userWsc:userWscs){
				WscInfoResponse item = new WscInfoResponse();
//				BeanUtils.copyProperties(userWsc,item);
				item.setName(userWsc.getName());
				item.setSpeaker(userWsc.getSpeaker());
				item.setWscDate(format.parse(userWsc.getWscDate()));
                item.setWscTime(userWsc.getWscTime());
				item.setWscId(userWsc.getWscId());
				item.setLocation(userWsc.getLocation());
				items.add(item);
			}
			response.setHasNext(page.getHasNext());
			response.setItems(items);
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
