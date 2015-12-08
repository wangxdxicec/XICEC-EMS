package com.zhenhappy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhenhappy.dao.WscTopicDao;
import com.zhenhappy.dto.GetWscTopicsRequest;
import com.zhenhappy.entity.TWscTopic;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.Page;

/**
 * 
 * @author 苏志锋
 * 
 */
@Service
public class WscTopicService {

	@Autowired
	private WscTopicDao wscTopicDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SystemConfig systemConfig;

	@SuppressWarnings("unchecked")
	public java.util.List<TWscTopic> query(Page page, GetWscTopicsRequest req) {
		StringBuilder sqlWhere = new StringBuilder(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		return wscTopicDao.queryPageByHQL(
"select count(*) from TWscTopic t "
				+ sqlWhere.toString(),
				"select t from TWscTopic t " + sqlWhere.toString(),
				params.toArray(), page);
	}

	public TWscTopic get(Integer id) {
		return wscTopicDao.query(id);
	}


}
