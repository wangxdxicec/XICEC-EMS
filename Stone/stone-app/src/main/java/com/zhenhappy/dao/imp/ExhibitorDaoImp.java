package com.zhenhappy.dao.imp;

import com.zhenhappy.dao.ExhibitorDao;
import com.zhenhappy.entity.TExhibitor;
import org.springframework.stereotype.Repository;

@Repository
public class ExhibitorDaoImp extends BaseDaoHibernateImp<TExhibitor> implements ExhibitorDao {
    public ExhibitorDaoImp() {
        super(TExhibitor.class);
    }
}
