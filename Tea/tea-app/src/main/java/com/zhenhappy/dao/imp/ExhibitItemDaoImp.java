package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.ExhibitItemDao;
import com.zhenhappy.entity.TExhibitItem;

/**
 * 
 * @author rocsky
 *
 */
@Repository
public class ExhibitItemDaoImp extends BaseDaoHibernateImp<TExhibitItem> implements ExhibitItemDao {
    public ExhibitItemDaoImp() {
        super(TExhibitItem.class);
    }
}
