package com.zhenhappy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhenhappy.dao.VisitorRegisterDao;
import com.zhenhappy.entity.TVisitorRegister;

/**
 * 
 * @author rocsky
 *
 */
@Service
public class VisitorRegisterService {

	@Autowired
	private VisitorRegisterDao visitorRegisterDao;
	
	public TVisitorRegister getTRegister(Integer visitorId) {
		return visitorRegisterDao.query(visitorId);
	}
	@Transactional
	public void save(TVisitorRegister request){
		visitorRegisterDao.saveOrUpdate(request);
	}

	@Transactional
	public void create(TVisitorRegister request) {
		visitorRegisterDao.create(request);
	}

	@Transactional
	public void update(TVisitorRegister request) {
		visitorRegisterDao.update(request);
	}
}
