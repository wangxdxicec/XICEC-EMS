package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.CardHeadDao;
import com.zhenhappy.entity.TCardHead;

@Repository
public class CardHeadDaoImp extends BaseDaoHibernateImp<TCardHead> implements CardHeadDao{

	public CardHeadDaoImp() {
		super(TCardHead.class);
	}

}
