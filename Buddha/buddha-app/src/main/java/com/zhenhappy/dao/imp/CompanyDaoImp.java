package com.zhenhappy.dao.imp;

import com.zhenhappy.dao.CompanyDao;
import com.zhenhappy.entity.ExhibitorList;
import org.springframework.stereotype.Repository;

/**
 * User: Haijian Liang
 * Date: 13-11-21
 * Time: 下午8:21
 * Function:
 */
@Repository
public class CompanyDaoImp extends BaseDaoHibernateImp<ExhibitorList> implements CompanyDao {
    public CompanyDaoImp() {
        super(ExhibitorList.class);
    }
}
