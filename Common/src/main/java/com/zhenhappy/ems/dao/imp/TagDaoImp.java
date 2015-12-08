package com.zhenhappy.ems.dao.imp;

import java.util.List;

import com.zhenhappy.ems.dao.TagDao;
import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.entity.TTag;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wujianbin on 2014-07-22.
 */
@Repository
public class TagDaoImp extends BaseDaoHibernateImp<TTag> implements TagDao {

	@Override
	public List<TTag> loadTagsByTids(Integer[] tids) {
		Query q = this.getSession().createQuery("select new TTag(t.id) from TTag t where t.id in (:tids)");
		q.setParameterList("tids", tids);
		return q.list();
	}

}
