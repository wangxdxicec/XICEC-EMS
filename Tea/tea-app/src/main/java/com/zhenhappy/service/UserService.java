package com.zhenhappy.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhenhappy.dao.BusinessCardDao;
import com.zhenhappy.dao.JoinIntentionDao;
import com.zhenhappy.dao.SurveyDao;
import com.zhenhappy.dao.UserDao;
import com.zhenhappy.dao.UserHeadDao;
import com.zhenhappy.dao.VerifyDao;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.JoinZhanhuiRequest;
import com.zhenhappy.dto.SubInterestSurveyRequest;
import com.zhenhappy.dto.SubJoinIntentionRequest;
import com.zhenhappy.entity.TBusinessCard;
import com.zhenhappy.entity.TCustomerSurvey;
import com.zhenhappy.entity.TJoinIntention;
import com.zhenhappy.entity.TUser;
import com.zhenhappy.entity.TUserHead;
import com.zhenhappy.entity.Verify;
import com.zhenhappy.exception.ReturnException;
import com.zhenhappy.mail.FindBackMailSender;
import com.zhenhappy.mail.FindBackMailTemplate;
import com.zhenhappy.mail.RegistMailSender;
import com.zhenhappy.mail.RegistMailTemplate;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.QueryFactory;

/**
 * User: Haijian Liang Date: 13-11-18 Time: 下午11:06 Function:
 */
@Service
public class UserService {

	@Autowired
	private UserHeadDao userHeadDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private VerifyDao verifyDao;

	@Autowired
	private TaskExecutor emailSenderPool;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private RegistMailTemplate registMailTemplate;

	@Autowired
	private FindBackMailTemplate findBackMailTemplate;

	@Autowired
	private SurveyDao surveyDao;

	@Autowired
	private JoinIntentionDao joinIntentionDao;

	@Autowired
	private SystemConfig systemConfig;
	
	@Autowired
	private BusinessCardDao cardDao;

	private Object lockGetUerCode = new Object();

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public TUser registUser(TUser user) {
		String zhanhui = systemConfig.getVal("usercode_zhanhui");
		String type = "";
		if (user.getUserType().intValue() == 1) {
			type = systemConfig.getVal("usercode_chinese");
		} else {
			type = systemConfig.getVal("usercode_english");
		}
		String usercode = "";
		userDao.create(user);
		synchronized (lockGetUerCode) {
			usercode = type + zhanhui + StringUtils.leftPad(user.getUserId().toString(), 8, "0");
			user.setUsercode(usercode);
		}
		userDao.update(user);
		if (user.getUserType().intValue() == 2) {
			Verify verify = new Verify();
			verify.setCheckcode(UUID.randomUUID().toString());
			verify.setEnable(1);
			verify.setEmail(user.getEmail());
			verify.setIsSend(0);
			verify.setVerifyType(1);
			verify.setUser_id(user.getUserId());
			verifyDao.create(verify);
			emailSenderPool.execute(new RegistMailSender(verify, javaMailSender, registMailTemplate));
		}else{
			//创建默认名片
			TBusinessCard defaultCard = new TBusinessCard();
			defaultCard.setAddress("");
			defaultCard.setCompany("");
			defaultCard.setName(user.getName());
			defaultCard.setPhone("");
			defaultCard.setIsDelete(0);
			defaultCard.setPosition("");
			defaultCard.setUserId(user.getUserId());
			defaultCard.setTelephone(user.getUsername());
			defaultCard.setQrcode(new Long(new Date().getTime()).toString());
			defaultCard.setIsdefault(1);
			cardDao.create(defaultCard);
		}
		return user;
	}

	public void sendEmailForFindBackPassword(String email) {
		Integer userId = (Integer) userDao.queryForObject(
				"select user_id from t_user where username =? and user_type=?", new Object[] { email, 2 },
				QueryFactory.SQL);
		Verify verify = new Verify();
		verify.setCheckcode(UUID.randomUUID().toString());
		verify.setEnable(1);
		verify.setEmail(email);
		verify.setIsSend(0);
		verify.setVerifyType(2);
		verify.setUser_id(userId);
		verifyDao.create(verify);
		emailSenderPool.execute(new FindBackMailSender(verify, javaMailSender, findBackMailTemplate));
	}

	/**
	 * 校验用户登陆
	 * 
	 * @param userType
	 * @param username
	 * @param password
	 * @return
	 */
	public TUser login(Integer userType, String username, String password) {
		List<TUser> users = null;
		users = userDao
				.queryByHql("from TUser where username = ? and password =?", new Object[] { username, password });
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	public TUser getUserInfo(Integer userId) {
		try {
			TUser user = userDao.query(userId);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public void uploadHead(Integer userId, byte[] headByteArray) {
		TUserHead head = userHeadDao.query(userId);
		if (head != null) {
			head.setHeadImg(headByteArray);
			userHeadDao.update(head);
		} else {
			TUserHead headtemp = new TUserHead();
			headtemp.setUserId(userId);
			headtemp.setHeadImg(headByteArray);
			userHeadDao.create(headtemp);
		}
	}

	public byte[] loadImg(Integer userId) {
		return userHeadDao.query(userId).getHeadImg();
	}

	/**
	 * 通过用户名确认用户是否存在。
	 * 
	 * @param username
	 * @param userType
	 * @return
	 */
	public boolean checkIsUser(String username, Integer userType) {
		Integer count = (Integer) userDao.queryForObject(
				"select count(*) from t_user where username=? and user_type=?", new Object[] { username, userType },
				QueryFactory.SQL);
		return count.intValue() > 0 ? true : false;
	}

	/**
	 * 通过用户类型和用户名获得密码
	 * 
	 * @param username
	 * @param userType
	 * @return
	 */
	public String getPasswordByUsername(String username, Integer userType) {
		Object password = null;
		if (userType.intValue() == 1) {
			password = userDao.queryForObject("select password from t_user where userType=? and mobile_phone=?",
					new Object[] { userType, username }, QueryFactory.SQL);
		} else {
			password = userDao.queryForObject("select password from t_user where userType=? and email=?", new Object[] {
					userType, username }, QueryFactory.SQL);
		}
		return password == null ? null : (String) password;
	}

	@Transactional
	public void modifyUserInfo(String name, String email, String phone, Integer userId) {
		userDao.update("update t_user set mobile_phone = ?,email=?,name=? where user_id = ?", new Object[] { phone,
				email, name, userId }, QueryFactory.SQL);
	}

	/**
	 * 校验用户名是否可以注册
	 * 
	 * @param userType
	 * @param username
	 * @return
	 */
	public Boolean checkCanRegist(Integer userType, String username) {
		List<TUser> users = null;
		if (userType.intValue() == 1) {
			users = userDao.queryByHql("from TUser where userType = ? and username = ?", new Object[] { userType,
					username });
		} else {
			users = userDao.queryByHql("from TUser where userType = ? and username = ?", new Object[] { userType,
					username });
		}
		if (users.size() > 0) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

	@Transactional
	public void changePassword(String oldPassword, Integer userId, String newPassword) {
		String old = (String) userDao.queryForObject("select password from t_user where user_id = ?",
				new Object[] { userId }, QueryFactory.SQL);
		if (old == null || !old.equals(oldPassword)) {
			throw new ReturnException(ErrorCode.OLD_PASSWORD_ERROR);
		}
		userDao.update("update t_user set password = ? where user_id =?", new Object[] { newPassword, userId },
				QueryFactory.SQL);
	}

	@Transactional
	public void changePassword(String username, String newPassword) {
		userDao.update("update t_user set password = ? where username =?", new Object[] { newPassword, username },
				QueryFactory.SQL);
	}

	@Transactional
	public void changeUserInfo(TUser user) {
		userDao.update(user);
	}

	@Transactional
	public void subSurvey(TCustomerSurvey survey) {
		surveyDao.create(survey);
	}

	@Transactional
	public void subSurvey(JoinZhanhuiRequest request) {
		List<TCustomerSurvey> customerSurveies = surveyDao.queryByHql("from TCustomerSurvey where userId = ?",
				new Object[] { request.getUserId() });
		TCustomerSurvey survey = null;
		if (customerSurveies.size() != 0) {
			survey = customerSurveies.get(0);
		} else {
			survey = new TCustomerSurvey();
			survey.setInterestSurvey(0);
			survey.setCreateTime(new Date());
		}
		survey.setJoinSurvey(1);
		BeanUtils.copyProperties(request, survey);
		surveyDao.createOrUpdate(survey);
	}

	@Transactional
	public void subSurvey(SubInterestSurveyRequest request) {
		List<TCustomerSurvey> customerSurveies = surveyDao.queryByHql("from TCustomerSurvey where userId = ?",
				new Object[] { request.getUserId() });
		TCustomerSurvey survey = null;
		if (customerSurveies.size() != 0) {
			survey = customerSurveies.get(0);
		} else {
			survey = new TCustomerSurvey();
			survey.setJoinSurvey(0);
			survey.setCreateTime(new Date());
		}
		survey.setInterestSurvey(1);
		BeanUtils.copyProperties(request, survey);
		surveyDao.createOrUpdate(survey);
	}

	@Transactional
	public void subJoinIntention(SubJoinIntentionRequest request) {
		List<TJoinIntention> intentions = joinIntentionDao.queryByHql("from TJoinIntention where userId = ?",
				new Object[] { request.getUserId() });
		TJoinIntention joinIntention = null;
		if (intentions.size() != 0) {
			joinIntention = intentions.get(0);
		} else {
			joinIntention = new TJoinIntention();
			joinIntention.setCreateTime(new Date());
		}
		BeanUtils.copyProperties(request, joinIntention);
		joinIntentionDao.createOrUpdate(joinIntention);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public TCustomerSurvey hasSubBanzhengSurvey(Integer userId) {
		List<TCustomerSurvey> customerSurvey = surveyDao.queryByHql("from TCustomerSurvey where userId = ?",
				new Object[] { userId });
		return customerSurvey.size() > 0 ? customerSurvey.get(0) : null;
	}

	/**
	 * 是否填写了我感兴趣的产品调查表
	 * 
	 * @param userId
	 * @return
	 */
	public boolean hasSubCustomerSurvey(Integer userId) {
		Integer temp = (Integer) surveyDao.queryForObject("select count(*) from t_customer_survey where user_id = ?",
				new Object[] { userId }, QueryFactory.SQL);
		if (temp.intValue() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否填写了参展意向调查表
	 * 
	 * @param userId
	 * @return
	 */
	public TJoinIntention hasSubCompanySurvey(Integer userId) {
		List<TJoinIntention> intentions = joinIntentionDao.queryByHql("from TJoinIntention where userId = ?",
				new Object[] { userId });
		if (intentions != null && intentions.size() > 0) {
			return intentions.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 邮箱验证
	 * 
	 * @param userid
	 * @param checkcode
	 * @return
	 */
	@Transactional
	public boolean emailCheck(Integer verifyId, String checkcode) {
		List<Verify> verifies = verifyDao.queryByHql("from Verify v where v.verifyId = ?", new Object[] { verifyId });
		if (verifies.size() == 0) {
			throw new ReturnException(ErrorCode.DATAERROR);
		} else {
			Verify v = verifies.get(0);
			if (v.getEnable().intValue() == 0) {
				throw new ReturnException(ErrorCode.VERIFYUSED);
			}
			if (!v.getCheckcode().equals(checkcode)) {
				throw new ReturnException(ErrorCode.DATAERROR);
			} else {
//				userDao.update("update t_user set isCheck = 1,username=email where user_id = ?",
//						new Object[] { v.getUser_id() }, QueryFactory.SQL);
				TUser user = userDao.query(v.getUser_id());
				user.setUsername(user.getEmail());
				user.setIsCheck(1);
				verifyDao.update("update verify set enable = 0 where user_id in (select t1.user_id from t_user t,t_user t1 where t.user_id=? and t.email=t1.email)",
								new Object[] { v.getUser_id() }, QueryFactory.SQL);
				
				//创建默认名片
				TBusinessCard defaultCard = new TBusinessCard();
				defaultCard.setAddress("");
				defaultCard.setCompany("");
				defaultCard.setName(user.getName());
				defaultCard.setPhone("");
				defaultCard.setIsDelete(0);
				defaultCard.setUserId(user.getUserId());
				defaultCard.setPosition("");
				defaultCard.setTelephone(user.getUsername());
				defaultCard.setQrcode(new Long(new Date().getTime()).toString());
				defaultCard.setIsdefault(1);
				cardDao.create(defaultCard);
				return true;
			}
		}
	}

	/**
	 * 邮箱验证
	 * 
	 * @param userid
	 * @param checkcode
	 * @return
	 */
	@Transactional
	public boolean checkEmailAndRestPassword(Integer verifyId, String checkcode, String newPassword) {
		List<Verify> verifies = verifyDao.queryByHql("from Verify v where v.verifyId = ?", new Object[] { verifyId });
		if (verifies.size() == 0) {
			throw new ReturnException(ErrorCode.DATAERROR);
		} else {
			Verify v = verifies.get(0);
			if (v.getEnable().intValue() == 0) {
				throw new ReturnException(ErrorCode.VERIFYUSED);
			}
			if (!v.getCheckcode().equals(checkcode)) {
				throw new ReturnException(ErrorCode.DATAERROR);
			} else {
				userDao.update("update t_user set password = ? where user_id=?",
						new Object[] { newPassword, v.getUser_id() }, QueryFactory.SQL);
				v.setEnable(0);
				return true;
			}
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public VerifyDao getVerifyDao() {
		return verifyDao;
	}

	public void setVerifyDao(VerifyDao verifyDao) {
		this.verifyDao = verifyDao;
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

	public RegistMailTemplate getRegistMailTemplate() {
		return registMailTemplate;
	}

	public void setRegistMailTemplate(RegistMailTemplate registMailTemplate) {
		this.registMailTemplate = registMailTemplate;
	}

	public SurveyDao getSurveyDao() {
		return surveyDao;
	}

	public void setSurveyDao(SurveyDao surveyDao) {
		this.surveyDao = surveyDao;
	}

	public JoinIntentionDao getJoinIntentionDao() {
		return joinIntentionDao;
	}

	public void setJoinIntentionDao(JoinIntentionDao joinIntentionDao) {
		this.joinIntentionDao = joinIntentionDao;
	}

	public FindBackMailTemplate getFindBackMailTemplate() {
		return findBackMailTemplate;
	}

	public void setFindBackMailTemplate(FindBackMailTemplate findBackMailTemplate) {
		this.findBackMailTemplate = findBackMailTemplate;
	}
}
