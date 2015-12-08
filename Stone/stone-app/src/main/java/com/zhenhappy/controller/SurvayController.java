package com.zhenhappy.controller;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.BaseResponse;
import com.zhenhappy.dto.CustomerSurveyRequest;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.security.SecurityUtil;
import com.zhenhappy.service.UserService;
import com.zhenhappy.util.LogUtil;

/**
 * User: Haijian Liang Date: 13-11-19 Time: 下午9:04 Function:
 */
@Controller
@RequestMapping(value = "/client/user")
public class SurvayController extends BaseController {

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	private UserService userService;

	public SurvayController() {
		super(SurvayController.class);
	}

	@RequestMapping(value = "customerSurvey", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse customerSurvey(@RequestBody CustomerSurveyRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {

		} catch (HibernateException e) {
			response.setErrorCode(ErrorCode.DATAERROR);

		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("客户填写调查表失败。", e);
		}
		return response;
	}

	@RequestMapping(value = "customerSurvey", method = RequestMethod.GET)
	public String getCustomerSurveyForm(@RequestParam(value = "token") String token,
			@RequestParam("username") String username, @RequestParam("userType") Integer userType) {
		log.info("user:" + username + " token:" + token + " 申请调查表");
		try {
			String password = userService.getPasswordByUsername(username, userType);
			if (StringUtils.isNotEmpty(password) && token.equals(SecurityUtil.hash5(password))) {
				return "customerSurveyForm";
			} else {
				return "AccessDenied";
			}
		} catch (Exception e) {
			log.error("请求客户调查界面失败。", e);
			return "error";
		}
	}
}
