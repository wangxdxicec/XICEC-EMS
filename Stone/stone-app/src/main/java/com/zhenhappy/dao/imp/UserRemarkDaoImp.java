package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.UserRemarkDao;
import com.zhenhappy.entity.TUserRemark;

/**
 * 
 * @author 苏志锋
 * 
 */
@Repository
public class UserRemarkDaoImp extends BaseDaoHibernateImp<TUserRemark> implements UserRemarkDao {
    public UserRemarkDaoImp() {
		super(TUserRemark.class);
    }
}
