package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.UserWscDao;
import com.zhenhappy.entity.TUserWsc;

/**
 * 
 * @author 苏志锋
 * 
 */
@Repository
public class UserWscDaoImp extends BaseDaoHibernateImp<TUserWsc> implements UserWscDao {
    public UserWscDaoImp() {
        super(TUserWsc.class);
    }
}
