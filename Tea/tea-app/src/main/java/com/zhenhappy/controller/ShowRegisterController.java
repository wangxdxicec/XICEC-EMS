package com.zhenhappy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.BaseResponse;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.ShowRegisterRequest;
import com.zhenhappy.dto.ShowRegisterResponse;
import com.zhenhappy.entity.TShowItem;
import com.zhenhappy.entity.TShowRegister;
import com.zhenhappy.service.ShowItemService;
import com.zhenhappy.service.ShowRegisterService;
import com.zhenhappy.util.LogUtil;

/**
 * 预登记
 * 
 * @author rocsky
 * 
 */
@Controller
@RequestMapping(value = "/client/user/")
public class ShowRegisterController extends BaseController {

	public ShowRegisterController() {
		super(ShowRegisterController.class);
	}

	@Autowired
	private ShowRegisterService registerService;
	
	@Autowired
	private ShowItemService showItemService;
	
	@RequestMapping(value = "getShowRegister", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse get(@RequestBody ShowRegisterRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = null;
		final int showId = request.getUserId();
		try {
			TShowRegister tRegister = registerService.getTRegister(showId);
			if(null != tRegister){
				List<TShowItem> tShowItems = showItemService.list(tRegister
						.getShowId());
				List<Integer> items = new ArrayList<Integer>();
				for (TShowItem tShowItem : tShowItems) {
					items.add(tShowItem.getItemId());
				}
				tRegister.setItems(items);
			}
			tRegister= null == tRegister ? new TShowRegister():tRegister;
			response = new ShowRegisterResponse(tRegister.getShowId(),
					tRegister.getName(), tRegister.getCompanyName(),
					tRegister.getTel(), tRegister.getEmail(),
					tRegister.getLoc(), tRegister.getItems(),
					tRegister.getIsNone());
		} catch (Exception e) {
			response = new ShowRegisterResponse();
			response.setErrorCode(ErrorCode.ERROR);
			log.error("获得参展预登记信息失败.", e);
		}
		return response;
	}
	@RequestMapping(value = "saveShowRegister", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse save(@RequestBody ShowRegisterRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			TShowRegister tShowRegister = new TShowRegister();
			tShowRegister.setCompanyName(request.getCompanyName());
			tShowRegister.setEmail(request.getEmail());
			tShowRegister.setShowId(request.getShowId());
			tShowRegister.setItems(request.getItems());
			tShowRegister.setLoc(request.getLoc());
			tShowRegister.setTel(request.getTel());
			tShowRegister.setName(request.getName());
			if (null == request.getShowId()) {
				tShowRegister.setShowId(request.getUserId());
				registerService.save(tShowRegister, false);
			} else {
				registerService.save(tShowRegister, true);
			}

		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("保存参展预登记信息失败.", e);
		}
		return response;
	}
}
