package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.VisitorRegisterDao;
import com.zhenhappy.entity.TVisitorRegister;

/**
 * 
 * @author rocsky
 *
 */
@Repository
public class VisitorRegisterDaoImp extends BaseDaoHibernateImp<TVisitorRegister> implements VisitorRegisterDao {
    public VisitorRegisterDaoImp() {
        super(TVisitorRegister.class);
    }
}
