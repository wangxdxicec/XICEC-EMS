package com.zhenhappy.ems.manager.dao.impl;

import java.util.List;

import com.zhenhappy.ems.dao.imp.BaseDaoHibernateImp;
import com.zhenhappy.ems.manager.dao.ExhibitorBoothDao;
import com.zhenhappy.ems.manager.entity.TExhibitorBooth;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wujianbin on 2014-07-25.
 */
@Repository
public class ExhibitorBoothDaoImp extends BaseDaoHibernateImp<TExhibitorBooth> implements ExhibitorBoothDao {
	@Transactional
	public List<TExhibitorBooth> loadExhibitorBoothByEids(Integer[] eids) {
		Query q = this.getSession().createQuery("select new TExhibitorBooth(b.id, b.eid, b.boothNumber, b.exhibitionArea, b.mark, b.createUser, b.createTime, b.updateUser, b.updateTime) from TExhibitorBooth b where b.eid in (:eids)");
		q.setParameterList("eids", eids);
		return q.list();
	}
}
