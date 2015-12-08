package com.zhenhappy.dao.imp;

import com.zhenhappy.dao.BusinessCardDao;
import com.zhenhappy.entity.TBusinessCard;
import org.springframework.stereotype.Repository;

/**
 * User: Haijian Liang
 * Date: 13-11-20
 * Time: 下午8:03
 * Function:
 */
@Repository
public class BusinessCardDaoImp extends BaseDaoHibernateImp<TBusinessCard> implements BusinessCardDao {

    public BusinessCardDaoImp() {
        super(TBusinessCard.class);
    }
}
