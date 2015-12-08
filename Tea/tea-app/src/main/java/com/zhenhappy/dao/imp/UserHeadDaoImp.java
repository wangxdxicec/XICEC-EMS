package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.UserHeadDao;
import com.zhenhappy.entity.TUserHead;

@Repository
public class UserHeadDaoImp extends BaseDaoHibernateImp<TUserHead> implements UserHeadDao{

	public UserHeadDaoImp() {
		super(TUserHead.class);
	}

}
