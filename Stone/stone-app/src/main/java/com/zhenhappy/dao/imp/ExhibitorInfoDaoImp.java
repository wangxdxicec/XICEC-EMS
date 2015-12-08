package com.zhenhappy.dao.imp;

import com.zhenhappy.dao.ExhibitorInfoDao;
import com.zhenhappy.entity.TExhibitorInfo;
import org.springframework.stereotype.Repository;

@Repository
public class ExhibitorInfoDaoImp extends BaseDaoHibernateImp<TExhibitorInfo> implements ExhibitorInfoDao {
    public ExhibitorInfoDaoImp() {
        super(TExhibitorInfo.class);
    }
}
