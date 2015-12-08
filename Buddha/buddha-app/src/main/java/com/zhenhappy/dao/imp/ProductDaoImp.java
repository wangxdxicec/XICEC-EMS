package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.ProductDao;
import com.zhenhappy.entity.TProduct;

/**
 * 
 * @author rocsky
 *
 */
@Repository
public class ProductDaoImp extends BaseDaoHibernateImp<TProduct> implements
		ProductDao {
    public ProductDaoImp() {
		super(TProduct.class);
    }
}
