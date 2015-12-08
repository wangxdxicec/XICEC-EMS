package com.zhenhappy.controller;

import com.zhenhappy.entity.TBusinessCard;
import com.zhenhappy.service.BusinessCardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.BaseResponse;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.VisitorRegisterRequest;
import com.zhenhappy.dto.VisitorRegisterResponse;
import com.zhenhappy.entity.TVisitorRegister;
import com.zhenhappy.service.VisitorRegisterService;
import com.zhenhappy.util.LogUtil;

/**
 * 参观预登记
 * 
 * @author rocsky
 * 
 */
@Controller
@RequestMapping(value = "/client/user/")
public class VisitorRegisterController extends BaseController {

	public VisitorRegisterController() {
		super(VisitorRegisterController.class);
	}

	@Autowired
	private VisitorRegisterService visitorRegisterService;
	@Autowired
	private BusinessCardService businessCardService;

	@RequestMapping(value = "getVisitorRegister", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse get(@RequestBody VisitorRegisterRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = null;
		final int visitorId = request.getUserId();
		try {
			TVisitorRegister tVisitorRegister = visitorRegisterService.getTRegister(visitorId);
			tVisitorRegister = null == tVisitorRegister ? new TVisitorRegister():tVisitorRegister;
			response = new VisitorRegisterResponse(
					tVisitorRegister.getVisitorId(),
					tVisitorRegister.getEmail(),
					tVisitorRegister.getName(),
					tVisitorRegister.getSex(),
					tVisitorRegister.getCompanyName(),
					tVisitorRegister.getPosition(),
					tVisitorRegister.getCountry(),
					tVisitorRegister.getProvince(),
					tVisitorRegister.getCity(),
					tVisitorRegister.getAddress(),
					tVisitorRegister.getPhone(),
					tVisitorRegister.getTel(),
					tVisitorRegister.getFax(),
					tVisitorRegister.getBackupEmail(),
					tVisitorRegister.getWebsite(),
					tVisitorRegister.getBusinessActivity(),
					tVisitorRegister.getIntent());
		} catch (Exception e) {
			response = new VisitorRegisterResponse();
			response.setErrorCode(ErrorCode.ERROR);
			log.error("获得参观预登记信息失败.", e);
		}
		return response;
	}

	@RequestMapping(value = "saveVisitorRegister", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse save(@RequestBody VisitorRegisterRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			TVisitorRegister tVisitorRegister = new TVisitorRegister();
			BeanUtils.copyProperties(request, tVisitorRegister);
			if (null == request.getVisitorId()) {
				tVisitorRegister.setVisitorId(request.getUserId());
				visitorRegisterService.create(tVisitorRegister);
				TBusinessCard card = businessCardService.getCardByUserId(request.getUserId());
				BeanUtils.copyProperties(request, card);
				card.setCompany(request.getCompanyName());
				card.setTelephone(request.getTel());
				card.setUserId(request.getUserId());
				card.setIsDefault(0);
				businessCardService.updateCardInfo(card);
			} else {
				visitorRegisterService.update(tVisitorRegister);
				TBusinessCard card = businessCardService.getCardByUserId(request.getUserId());
				BeanUtils.copyProperties(request, card);
				card.setCompany(request.getCompanyName());
				card.setTelephone(request.getTel());
				card.setUserId(request.getUserId());
				card.setIsDefault(0);
				businessCardService.updateCardInfo(card);
			}
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("保存参观预登记信息失败.", e);
		}
		return response;
	}
}
