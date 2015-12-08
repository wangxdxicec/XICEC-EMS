package com.zhenhappy.ems.dao.imp;

import java.util.List;

import com.zhenhappy.ems.dao.ContactDao;
import com.zhenhappy.ems.entity.TContact;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wujianbin on 2014-04-24.
 */
@Repository
public class ContactDaoImp extends BaseDaoHibernateImp<TContact> implements ContactDao {

	@Override
	public List<TContact> loadContactByCids(Integer[] cids) {
		Query q = this.getSession().createQuery("select new TContact(c.id, c.name, c.position, c.phone, c.email, c.eid, c.isDelete) from TContact c where c.id in (:cids)");
		q.setParameterList("cids", cids);
		return q.list();
	}

	@Override
	public List<TContact> loadContactByEids(Integer[] eids) {
		Query q = this.getSession().createQuery("select new TContact(c.id, c.name, c.position, c.phone, c.email, c.eid, c.isDelete) from TContact c where c.eid in (:eids)");
		q.setParameterList("eids", eids);
		return q.list();
	}
}
