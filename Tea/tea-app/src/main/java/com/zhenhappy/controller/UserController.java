package com.zhenhappy.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhenhappy.dto.AfterLoginRequest;
import com.zhenhappy.dto.BaseResponse;
import com.zhenhappy.dto.CheckHasSubSurveyResponse;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.HasSubBanzhengResponse;
import com.zhenhappy.dto.HasSubJoinIntentionResponse;
import com.zhenhappy.dto.JoinZhanhuiRequest;
import com.zhenhappy.dto.ModifyHeadResponse;
import com.zhenhappy.dto.ModifyPasswordRequest;
import com.zhenhappy.dto.ModifyUserInfoRequest;
import com.zhenhappy.dto.SubInterestSurveyRequest;
import com.zhenhappy.dto.SubJoinIntentionRequest;
import com.zhenhappy.entity.TCustomerSurvey;
import com.zhenhappy.entity.TJoinIntention;
import com.zhenhappy.entity.TUser;
import com.zhenhappy.exception.ReturnException;
import com.zhenhappy.service.UserService;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.LogUtil;

/**
 * User: Haijian Liang Date: 13-11-21 Time: 下午11:31 Function:
 */
@Controller
@RequestMapping(value = "/client/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SystemConfig systemConfig;

	public UserController() {
		super(UserController.class);
	}

	@RequestMapping(value = "modifyPassword", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse modifyPassword(@RequestBody ModifyPasswordRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			userService.changePassword(request.getOldPassword(), request.getUserId(), request.getNewPassword());
		} catch (ReturnException e) {
			response.setErrorCode(e.getErrorCodeObject());
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	/**
	 * userType = 1 不允许修改电话 userType = 2 不允许修改邮箱
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "modifyUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse modifyUserInfo(@RequestBody ModifyUserInfoRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			userService.modifyUserInfo(request.getName(), request.getEmail(), request.getPhone(), request.getUserId());
		} catch (Exception e) {
			log.error("修改用户个人信息失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "subBanzhengSurvey", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse subBanzhengSurvey(@RequestBody JoinZhanhuiRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			userService.subSurvey(request);
		} catch (Exception e) {
			log.error("提交客商调查表失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "subInterestSurvey", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse subInterestSurvey(@RequestBody SubInterestSurveyRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			userService.subSurvey(request);
		} catch (Exception e) {
			log.error("提交客商调查表失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "subJoinIntention", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse subJoinIntention(@RequestBody SubJoinIntentionRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			userService.subJoinIntention(request);
		} catch (Exception e) {
			log.error("提交参展意向调查表失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	/**
	 * 是否填写参展申请调查表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checkHasSubCompanySurvey", method = RequestMethod.POST)
	@ResponseBody
	public HasSubJoinIntentionResponse checkCompanySurvey(@RequestBody AfterLoginRequest request) {
		LogUtil.logRequest(log, this, request);
		HasSubJoinIntentionResponse response = new HasSubJoinIntentionResponse();
		try {
			TJoinIntention joinIntention = userService.hasSubCompanySurvey(request.getUserId());
			response.setHasSub(joinIntention != null);
			if (joinIntention != null) {
				BeanUtils.copyProperties(joinIntention, response);
			} else {
				TCustomerSurvey survey = userService.hasSubBanzhengSurvey(request.getUserId());
				if (survey != null) {
					response.setCompany(survey.getCompany());
					response.setExhibitorName(survey.getCustomerName());
					response.setPosition(survey.getPosition());
				} else {
					TUser user = userService.getUserInfo(request.getUserId());
					response.setExhibitorName(user.getName());
				}
			}
		} catch (Exception e) {
			log.error("检查用户是否填写参展意向表错误", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	/**
	 * 是否填写办证调查表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "hasSubBanzhengSurvey", method = RequestMethod.POST)
	@ResponseBody
	public HasSubBanzhengResponse hasSubBanzhengSurvey(@RequestBody AfterLoginRequest request) {
		LogUtil.logRequest(log, this, request);
		HasSubBanzhengResponse response = new HasSubBanzhengResponse();
		try {
			TCustomerSurvey survey = userService.hasSubBanzhengSurvey(request.getUserId());
			if (survey == null || survey.getJoinSurvey().intValue() == 0) {
				TJoinIntention joinIntention = userService.hasSubCompanySurvey(request.getUserId());
				if (joinIntention != null) {
					response.setCompany(joinIntention.getCompany());
					response.setCustomerName(joinIntention.getExhibitorName());
					response.setPosition(joinIntention.getPosition());
				} else {
					TUser user = userService.getUserInfo(request.getUserId());
					response.setCustomerName(user.getName());
				}
				response.setHasSub(false);
			} else {
				response.setHasSub(true);
			}
			if (survey != null) {
				BeanUtils.copyProperties(survey, response);
			}
		} catch (Exception e) {
			log.error("检查用户是否填写办证表格错误", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	/**
	 * 是否填写感兴趣的产品
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "hasSubInterestSurvey", method = RequestMethod.POST)
	@ResponseBody
	public CheckHasSubSurveyResponse hasSubInterestSurvey(@RequestBody AfterLoginRequest request) {
		LogUtil.logRequest(log, this, request);
		CheckHasSubSurveyResponse response = new CheckHasSubSurveyResponse();
		try {
			TCustomerSurvey survey = userService.hasSubBanzhengSurvey(request.getUserId());
			if (survey == null || survey.getInterestSurvey().intValue() == 0) {
				TJoinIntention joinIntention = userService.hasSubCompanySurvey(request.getUserId());
				if (joinIntention != null) {
					response.setCompany(joinIntention.getCompany());
					response.setCustomerName(joinIntention.getExhibitorName());
					response.setPosition(joinIntention.getPosition());
				} else {
					TUser user = userService.getUserInfo(request.getUserId());
					response.setCustomerName(user.getName());
				}
				response.setHasSub(false);
			} else {
				response.setHasSub(true);
			}
			if (survey != null) {
				BeanUtils.copyProperties(survey, response);
			}
		} catch (Exception e) {
			log.error("检查用户是否填写兴趣表格错误", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "uploadUserHead", method = RequestMethod.POST)
	public ModifyHeadResponse modifyHead(@RequestParam("userId") Integer userId,
			@RequestParam("head") MultipartFile head, HttpServletRequest request) {
		ModifyHeadResponse response = new ModifyHeadResponse();
		try {
			log.info("user:" + userId + " modify head, orient file name :" + head.getOriginalFilename());
			userService.uploadHead(userId, head.getBytes());
			response.setNewHeadUrl(systemConfig.getVal("head_base_url") + "userHeadImg?userId=" + userId);
		} catch (ReturnException e) {
			response.setErrorCode(e.getErrorCodeObject());
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("修改头像失败！", e);
		}
		return response;
	}

	@RequestMapping(value = "userHeadImg", method = RequestMethod.GET)
	public void getCardHeadImg(@RequestParam("userId") Integer userId, HttpServletResponse response) {
		OutputStream stream = null;
		try {
			stream = response.getOutputStream();
			byte[] img = userService.loadImg(userId);
			stream.write(img);
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
