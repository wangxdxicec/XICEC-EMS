package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.ShowRegisterDao;
import com.zhenhappy.entity.TShowRegister;

/**
 * 
 * @author rocsky
 *
 */
@Repository
public class ShowRegisterDaoImp extends BaseDaoHibernateImp<TShowRegister> implements ShowRegisterDao {
    public ShowRegisterDaoImp() {
        super(TShowRegister.class);
    }
}
