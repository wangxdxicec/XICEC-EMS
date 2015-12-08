package com.zhenhappy.dao.imp;

import com.zhenhappy.dao.PayCardDao;
import com.zhenhappy.entity.PayCardInfo;
import org.springframework.stereotype.Repository;

/**
 * User: Haijian Liang
 * Date: 13-11-23
 * Time: 上午10:09
 * Function:
 */
@Repository
public class PayCardDaoImp extends BaseDaoHibernateImp<PayCardInfo> implements PayCardDao {
    public PayCardDaoImp() {
        super(PayCardInfo.class);
    }
}
