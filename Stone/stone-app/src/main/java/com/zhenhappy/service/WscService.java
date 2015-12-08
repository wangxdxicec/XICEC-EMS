package com.zhenhappy.service;

import java.util.ArrayList;
import java.util.List;

import com.zhenhappy.dto.GetWscInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhenhappy.dao.UserWscDao;
import com.zhenhappy.dao.WscDao;
import com.zhenhappy.dto.GetWscsRequest;
import com.zhenhappy.entity.TUserWsc;
import com.zhenhappy.entity.TWsc;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.Page;
import com.zhenhappy.util.QueryFactory;

/**
 * 
 * @author 苏志锋
 * 
 */
@Service
public class WscService {

	@Autowired
	private WscDao wscDao;

	@Autowired
	private UserWscDao userWscDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SystemConfig systemConfig;


	public WscDao getWscDao() {
		return wscDao;
	}

	public void setWscDao(WscDao wscDao) {
		this.wscDao = wscDao;
	}

	@SuppressWarnings("unchecked")
	public java.util.List<TWsc> query(Page page, GetWscsRequest req) {
		StringBuilder sqlWhere = new StringBuilder(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		return wscDao.queryPageByHQL(
				"select count(*) from TWsc t "
				+ sqlWhere.toString(),
				"select t from TWsc t " + sqlWhere.toString(),
				params.toArray(), page);
	}

	public TWsc get(Integer id) {
		return wscDao.query(id);
	}

	public List<TUserWsc> getTUserWscsByUserId(Integer userId) {
		return userWscDao.queryByHql("from TUserWsc t where userId=?", new Object[] { userId });
	}

	@Transactional
	public void collectWsc(GetWscInfoRequest request) {
		userWscDao.update("delete from t_user_wsc where user_id = ? and wsc_id =?", new Object[] { request.getUserId(), request.getWscId() }, QueryFactory.SQL);
		userWscDao.create(new TUserWsc(request.getUserId(), request.getWscId(), request.getWscDate(), request.getWscTime(), request.getLocation(), request.getSpeaker(), request.getName()));
	}

	@Transactional
	public void cancelCollectWsc(Integer userId, Integer wscId) {
		userWscDao.update("delete from t_user_wsc where user_id = ? and wsc_id =?", new Object[] { userId, wscId }, QueryFactory.SQL);
	}

	@Transactional
	public List<TUserWsc> myCollectWSC(Integer userId) {
		return userWscDao.queryByHql("from TUserWsc where user_id = ?", new Object[] { userId });
	}
}
