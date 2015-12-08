package com.zhenhappy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhenhappy.dao.WscSpeakerDao;
import com.zhenhappy.dto.GetWscSpeakersRequest;
import com.zhenhappy.entity.TWscSpeaker;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.Page;

/**
 * 
 * @author 苏志锋
 * 
 */
@Service
public class WscSpeakerService {

	@Autowired
	private WscSpeakerDao wscSpeakerDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SystemConfig systemConfig;

	@SuppressWarnings("unchecked")
	public java.util.List<TWscSpeaker> query(Page page, GetWscSpeakersRequest req) {
		StringBuilder sqlWhere = new StringBuilder(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		return wscSpeakerDao.queryPageByHQL("select count(*) from TWscSpeaker t " + sqlWhere.toString(),
				"select t from TWscSpeaker t " + sqlWhere.toString(), params.toArray(), page);
	}

	public TWscSpeaker get(Integer id) {
		return wscSpeakerDao.query(id);
	}

}
