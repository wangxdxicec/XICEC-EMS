package com.zhenhappy.ems.dao.imp;

import java.util.List;

import com.zhenhappy.ems.dao.ProductDao;
import com.zhenhappy.ems.entity.TProduct;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wujianbin on 2014-04-14.
 */
@Repository
public class ProductDaoImp extends BaseDaoHibernateImp<TProduct> implements ProductDao {

	@Override
	public List<TProduct> loadProductByPIds(Integer[] pids) {
		Query q = this.getSession().createQuery("select new TProduct(p.id) from TProduct p where p.id in (:pids)");
		q.setParameterList("pids", pids);
		return q.list();
	}
}
