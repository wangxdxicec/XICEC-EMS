package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.ShowItemDao;
import com.zhenhappy.entity.TShowItem;

/**
 * 
 * @author rocsky
 *
 */
@Repository
public class ShowItemDaoImp extends BaseDaoHibernateImp<TShowItem> implements ShowItemDao {
    public ShowItemDaoImp() {
        super(TShowItem.class);
    }
}
