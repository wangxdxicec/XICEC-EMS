package com.zhenhappy.ems.manager.dao.impl;

import java.util.List;

import com.zhenhappy.ems.dao.imp.BaseDaoHibernateImp;
import com.zhenhappy.ems.manager.dao.ExhibitorTermDao;
import com.zhenhappy.ems.manager.entity.TExhibitorTerm;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wujianbin on 2014-07-25.
 */
@Repository
public class ExhibitorTermDaoImp extends BaseDaoHibernateImp<TExhibitorTerm> implements ExhibitorTermDao {
	@Transactional
	public List<TExhibitorTerm> loadTermsByEids(Integer[] eids) {
		Query q = this.getSession().createQuery("select new TExhibitorTerm(t.id,t.eid,t.joinTerm,t.boothNumber,t.mark,t.isDelete,t.createUser,t.createTime,t.updateUser,t.updateTime) from TExhibitorTerm t where t.eid in (:eids)");
		q.setParameterList("eids", eids);
		return q.list();
	}
}
