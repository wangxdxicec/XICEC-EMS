package com.zhenhappy.dao.imp;

import com.zhenhappy.dao.UserCardDao;
import com.zhenhappy.entity.TUserBusinessCard;
import org.springframework.stereotype.Repository;

/**
 * User: Haijian Liang
 * Date: 13-11-20
 * Time: 下午8:20
 * Function:
 */
@Repository
public class UserCardDaoImp extends BaseDaoHibernateImp<TUserBusinessCard> implements UserCardDao{
    public UserCardDaoImp() {
        super(TUserBusinessCard.class);
    }
}
