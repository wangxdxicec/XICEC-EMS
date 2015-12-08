package com.zhenhappy.dao.imp;

import com.zhenhappy.dao.UserDao;
import com.zhenhappy.entity.TUser;
import org.springframework.stereotype.Repository;

/**
 * User: Haijian Liang
 * Date: 13-11-18
 * Time: 下午9:29
 * Function:
 */
@Repository
public class UserDaoImp extends BaseDaoHibernateImp<TUser> implements UserDao {
    public UserDaoImp() {
        super(TUser.class);
    }
}
