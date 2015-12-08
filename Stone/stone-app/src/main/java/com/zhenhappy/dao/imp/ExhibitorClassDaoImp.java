package com.zhenhappy.dao.imp;

import com.zhenhappy.dao.ExhibitorClassDao;
import com.zhenhappy.entity.TExhibitorClass;
import org.springframework.stereotype.Repository;

@Repository
public class ExhibitorClassDaoImp extends BaseDaoHibernateImp<TExhibitorClass> implements ExhibitorClassDao {
    public ExhibitorClassDaoImp() {
        super(TExhibitorClass.class);
    }
}
