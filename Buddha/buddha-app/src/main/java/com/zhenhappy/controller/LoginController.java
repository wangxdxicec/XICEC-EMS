package com.zhenhappy.controller;

import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.BaseResponse;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.FindbackPasswordRequest;
import com.zhenhappy.dto.LoginRequest;
import com.zhenhappy.dto.LoginResponse;
import com.zhenhappy.dto.RegistRequest;
import com.zhenhappy.entity.TUser;
import com.zhenhappy.exception.ReturnException;
import com.zhenhappy.service.UserService;
import com.zhenhappy.system.Constants;
import com.zhenhappy.system.RegistCenterCache;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.LogUtil;
import com.zhenhappy.util.ShortMsgSender;

@Controller
@RequestMapping(value = "/client")
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private SystemConfig systemConfig;

	@Autowired
	private TaskExecutor emailSenderPool;

	@Autowired
	private JavaMailSender javaMailSender;

	public LoginController() {
		super(LoginController.class);
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse login(@RequestBody LoginRequest request) {
		LogUtil.logRequest(log, this, request);
		log.info(Constants.SYSTEM_BASE_PATH);
		LoginResponse response = new LoginResponse();
		try {
			TUser user = userService.login(request.getUserType(), request.getUsername(), request.getPassword());
			if (user != null && user.getIsCheck().intValue() == 1 && user.getIsDisable().intValue() == 0) {
				response.setCompany(user.getCompany());
				response.setName(user.getName());
				response.setPosition(user.getPosition());
				response.setUserId(user.getUserId());
				response.setUserType(user.getUserType());
				response.setUsercode(user.getUsercode());
				response.setEmail(user.getEmail());
				response.setPhone(user.getMobilePhone());
				response.setHeadUrl(systemConfig.getVal("head_base_url") + "userHeadImg?userId=" + user.getUserId());
			} else if (user != null && user.getIsCheck().intValue() == 0 && user.getIsDisable().intValue() == 0) {
				response.setErrorCode(ErrorCode.USERUNVERIFY);
				response.setDes("用户未验证通过");
			} else if (user != null && user.getIsCheck().intValue() == 1 && user.getIsDisable().intValue() == 1) {
				response.setErrorCode(ErrorCode.USERLOCKED);
				response.setDes("用户已被锁定");
			} else {
				response.setErrorCode(ErrorCode.LOGINFAIL);
				response.setDes("账号或密码错误");
			}
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.info("登陆异常：", e);
		}
		return response;
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse regist(@RequestBody RegistRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			if (request.getUserType().intValue() == 1) {
				if (!userService.checkCanRegist(1, request.getPhone())) {
					throw new ReturnException(ErrorCode.USERNAME_REGISTED);
				}
				Object po = RegistCenterCache.getCheckCode(request.getPhone());
				if (po == null) {
					response.setErrorCode(ErrorCode.ERROR);
					response.setDes("验证码超时");
				} else {
					String[] phone_code = ((String) po).split("\\|");
					String phone = phone_code[0];
					String checkCode = phone_code[1];
					if (request.getPhone().equals(phone) && request.getCheckCode().equals(checkCode)) {
						TUser user = buildUser(request);
						user.setIsCheck(1);
						user.setUsername(request.getPhone());
						userService.registUser(user);
						RegistCenterCache.getAndClear(request.getPhone());
					} else {
						response.setErrorCode(ErrorCode.DATAERROR);
						response.setDes("验证码错误");
					}
				}
			} else {
				// 国外用户
				if (!userService.checkCanRegist(2, request.getEmail())) {
					throw new ReturnException(ErrorCode.USERNAME_REGISTED);
				}
				if (StringUtils.isNotEmpty(request.getEmail())) {
					String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
					Pattern regex = Pattern.compile(check);
					Matcher matcher = regex.matcher(request.getEmail());
					if (!matcher.matches()) {
						response.setErrorCode(ErrorCode.DATAERROR);
						response.setDes("邮箱地址不合法");
					} else {
						TUser user = buildUser(request);
						// user.setUsername(request.getEmail());
						userService.registUser(user);
					}
				} else {
					response.setErrorCode(ErrorCode.DATAERROR);
					response.setDes("用户邮箱不能为空");
				}
			}
		} catch (HibernateException e) {
			response.setErrorCode(ErrorCode.DATAERROR);
			log.error("请求注册参数错误。", e);
		} catch (ReturnException e) {
			response.setDes(e.getMessage());
			response.setResultCode(e.getErrorCode());
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("请求注册失败。", e);
		}
		return response;
	}

	@RequestMapping(value = "checkPhone")
	@ResponseBody
	public BaseResponse getPhoneMessageCheck(@RequestParam("phone") String phone, @RequestParam("token") String token,
			HttpServletRequest servletRequest) {
		log.info("手机号：" + phone + "申请短信验证");
		BaseResponse response = new BaseResponse();
		try {
			Pattern p = Pattern.compile("^[1][0-9]{10}$");
			Matcher m = p.matcher(phone);
			if (!m.matches()) {
				response.setErrorCode(ErrorCode.DATAERROR);
			} else {
				RegistCenterCache.canSend(phone);
				if (!userService.checkCanRegist(1, phone)) {
					throw new ReturnException(ErrorCode.USERNAME_REGISTED);
				}
				String url = systemConfig.getVal("short_msg_url");
				Random rand = new Random();
				int result = rand.nextInt(999999);
				String sendCode = "000000".substring((result + "").length()) + result;
				Boolean sendResult = ShortMsgSender.senderBySms100(phone, "感谢您注册中国厦门国际佛事展手机客户端！验证码：" + sendCode + "请返回手机客户端进行验证。【厦门会展金泓信】", url);
				if (sendResult.booleanValue() == false) {
					response.setErrorCode(ErrorCode.ERROR);
				} else {
					RegistCenterCache.put(phone, phone + "|" + sendCode);
					response.setErrorCode(ErrorCode.SUCCESS);
				}
			}
		} catch (ReturnException e) {
			response.setErrorCode(e.getErrorCodeObject());
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("请求发送手机短信错误。", e);
		}
		return response;
	}

	@RequestMapping(value = "findbackPassCheckPhone")
	@ResponseBody
	public BaseResponse findbackPassCheckPhone(@RequestParam("phone") String phone,
			@RequestParam("token") String token, HttpServletRequest servletRequest) {
		log.info("手机号：" + phone + "申请找回密码短信验证");
		BaseResponse response = new BaseResponse();
		try {
			boolean isUser = userService.checkIsUser(phone, 1);
			if (!isUser) {
				response.setErrorCode(ErrorCode.USERUNREGIST);
			} else {
				RegistCenterCache.canSend(phone);
				String url = systemConfig.getVal("short_msg_url");
				Random rand = new Random();
				int result = rand.nextInt(999999);
				String sendCode = "000000".substring((result + "").length()) + result;
				Boolean sendResult = ShortMsgSender.senderBySms100(phone, "找回密码验证码：" + sendCode + "请返回手机客户端进行验证。【厦门会展金泓信】", url);
				if (sendResult.booleanValue() == false) {
					response.setErrorCode(ErrorCode.ERROR);
				} else {
					RegistCenterCache.put(phone, phone + "|" + sendCode);
					response.setErrorCode(ErrorCode.SUCCESS);
				}
			}
		} catch (ReturnException e) {
			response.setErrorCode(e.getErrorCodeObject());
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("请求发送手机短信错误。", e);
		}
		return response;
	}

	/**
	 * 找回密码
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findBackPass", method = RequestMethod.POST)
	public BaseResponse findBackPassword(@RequestBody FindbackPasswordRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			Object po = RegistCenterCache.getCheckCode(request.getPhone());
			if (po == null) {
				response.setErrorCode(ErrorCode.ERROR);
				response.setDes("验证码超时");
			} else {
				String[] phone_code = ((String) po).split("\\|");
				String phone = phone_code[0];
				String checkCode = phone_code[1];
				if (request.getPhone().equals(phone) && request.getCheckCode().equals(checkCode)) {
					userService.changePassword(request.getPhone(), request.getNewPassword());
				} else {
					response.setErrorCode(ErrorCode.DATAERROR);
					response.setDes("验证码错误");
				}
			}
		} catch (Exception e) {
			log.error("find back password error.", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "sendFindbackPassEmail")
	public BaseResponse sendFindbackPassEmail(@RequestParam("email") String email) {
		BaseResponse response = new BaseResponse();
		log.info("email:" + email + " request find back password");
		try {
			boolean isUser = userService.checkIsUser(email, 2);
			if (!isUser) {
				response.setErrorCode(ErrorCode.USERUNREGIST);
			} else {
				userService.sendEmailForFindBackPassword(email);
			}
		} catch (Exception e) {
			log.error("发送找回密码邮件失败.", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	/**
	 * 邮箱重置密码
	 * 
	 * @param verifyId
	 * @param checkCode
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse resetPassword(@RequestParam("verifyId") Integer verifyId,
			@RequestParam("checkCode") String checkCode, String newPassword) {
		BaseResponse response = new BaseResponse();
		log.info("verifyId:" + verifyId + " request to reset password:" + newPassword);
		try {
			userService.checkEmailAndRestPassword(verifyId, checkCode, newPassword);
		} catch (ReturnException e) {
			response.setErrorCode(e.getErrorCodeObject());
		} catch (Exception e) {
			log.error("重置密码失败。", e);
		}
		return response;
	}

	@RequestMapping(value = "resetPassword/{verifyId}/{checkCode}")
	public String resetPassword(@PathVariable Integer verifyId, @PathVariable String checkCode, Model model) {
		log.info("verifyId:" + verifyId + " request verify count,checkCode:" + checkCode);
		model.addAttribute("verifyId", verifyId);
		model.addAttribute("checkCode", checkCode);
		return "findbackpassword";
	}

	@RequestMapping(value = "verify/{verifyId}/{checkCode}")
	public String verifyForeignUser(@PathVariable Integer verifyId, @PathVariable String checkCode) {
		log.info("verifyId:" + verifyId + " request verify count,checkCode:" + checkCode);
		try {
			userService.emailCheck(verifyId, checkCode);
		} catch (ReturnException e) {
			return "verify_error";
		} catch (Exception e) {
			return "server_error";
		}
		return "verify_success";
	}

	/**
	 * build user data
	 * 
	 * @param request
	 * @return
	 */
	private TUser buildUser(RegistRequest request) {
		TUser user = new TUser();
		user.setCompany(request.getCompany());
		user.setCreateTime(new Date());
		user.setEmail(request.getEmail());
		user.setIsCheck(0);
		user.setIsDisable(0);
		user.setMobilePhone(request.getPhone());
		user.setName(request.getName());
		user.setPassword(request.getPassword());
		user.setPosition(request.getPosition());
		user.setUserType(request.getUserType());
		return user;
	}

	public SystemConfig getSystemConfig() {
		return systemConfig;
	}

	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TaskExecutor getEmailSenderPool() {
		return emailSenderPool;
	}

	public void setEmailSenderPool(TaskExecutor emailSenderPool) {
		this.emailSenderPool = emailSenderPool;
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

}
