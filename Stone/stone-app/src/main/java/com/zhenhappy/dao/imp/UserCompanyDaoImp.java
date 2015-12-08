package com.zhenhappy.dao.imp;

import com.zhenhappy.dao.UserCompanyDao;
import com.zhenhappy.entity.TUserCompany;
import org.springframework.stereotype.Repository;

/**
 * User: Haijian Liang
 * Date: 13-11-21
 * Time: 下午9:12
 * Function:
 */
@Repository
public class UserCompanyDaoImp extends BaseDaoHibernateImp<TUserCompany> implements UserCompanyDao {
    public UserCompanyDaoImp() {
        super(TUserCompany.class);
    }
}
