package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.VerifyDao;
import com.zhenhappy.entity.Verify;

@Repository
public class VerifyDaoImp extends BaseDaoHibernateImp<Verify> implements VerifyDao{

	public VerifyDaoImp() {
		super(Verify.class);
	}
	
}
