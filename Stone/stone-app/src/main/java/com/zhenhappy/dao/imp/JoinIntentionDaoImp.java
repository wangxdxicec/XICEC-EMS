package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.JoinIntentionDao;

import com.zhenhappy.entity.TJoinIntention;

@Repository
public class JoinIntentionDaoImp extends BaseDaoHibernateImp<TJoinIntention> implements JoinIntentionDao{

	public JoinIntentionDaoImp() {
		super(TJoinIntention.class);
	}

}
