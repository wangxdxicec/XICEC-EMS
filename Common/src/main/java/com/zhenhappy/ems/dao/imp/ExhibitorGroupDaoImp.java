package com.zhenhappy.ems.dao.imp;

import java.util.List;
import com.zhenhappy.ems.dao.ExhibitorGroupDao;
import com.zhenhappy.ems.entity.TExhibitorGroup;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wujianbin on 2014-07-22.
 */
@Repository
public class ExhibitorGroupDaoImp extends BaseDaoHibernateImp<TExhibitorGroup> implements ExhibitorGroupDao {

	@Override
	public List<TExhibitorGroup> loadExhibitorGroupsByIds(Integer[] ids) {
		Query q = this.getSession().createQuery("select new TExhibitorGroup(g.id) from TExhibitorGroup g where g.id in (:ids)");
		q.setParameterList("ids", ids);
		return q.list();
	}

}
