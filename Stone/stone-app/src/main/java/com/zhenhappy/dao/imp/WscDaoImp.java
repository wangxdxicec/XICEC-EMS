package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.WscDao;
import com.zhenhappy.entity.TWsc;

/**
 * 
 * @author 苏志锋
 * 
 */
@Repository
public class WscDaoImp extends BaseDaoHibernateImp<TWsc> implements WscDao {
    public WscDaoImp() {
		super(TWsc.class);
    }
}
