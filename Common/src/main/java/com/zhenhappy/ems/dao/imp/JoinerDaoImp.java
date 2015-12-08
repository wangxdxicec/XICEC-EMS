package com.zhenhappy.ems.dao.imp;

import java.util.List;

import com.zhenhappy.ems.dao.JoinerDao;
import com.zhenhappy.ems.entity.TExhibitorJoiner;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wujianbin on 2014-04-24.
 */
@Repository
public class JoinerDaoImp extends BaseDaoHibernateImp<TExhibitorJoiner> implements JoinerDao {

	@Override
	public List<TExhibitorJoiner> loadJoinersByJIds(Integer[] jids) {
		Query q = this.getSession().createQuery("select new TExhibitorJoiner(j.id, j.eid, j.name, j.position, j.telphone, j.email, j.isDelete, j.createTime, j.admin, j.adminUpdateTime) from TExhibitorJoiner j where j.id in (:jids)");
		q.setParameterList("jids", jids);
		return q.list();
	}
	
	@Override
	public List<TExhibitorJoiner> loadJoinersByEids(Integer[] eids) {
		Query q = this.getSession().createQuery("select new TExhibitorJoiner(j.id, j.eid, j.name, j.position, j.telphone, j.email, j.isDelete, j.createTime, j.admin, j.adminUpdateTime) from TExhibitorJoiner j where j.eid in (:eids)");
		q.setParameterList("eids", eids);
		return q.list();
	}
}
