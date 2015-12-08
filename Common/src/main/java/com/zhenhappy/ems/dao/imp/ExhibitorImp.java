package com.zhenhappy.ems.dao.imp;

import java.util.List;

import com.zhenhappy.ems.dao.ExhibitorDao;
import com.zhenhappy.ems.entity.TExhibitor;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wujianbin on 2014-04-03.
 */
@Repository
public class ExhibitorImp extends BaseDaoHibernateImp<TExhibitor> implements ExhibitorDao {
    /**
     * <b>Summary: </b> 构造一个 NBaseDaoHibernateImp <b>Remarks: </b> 构造类
     * NBaseDaoHibernateImp 的构造函数 NBaseDaoHibernateImp
     *
     */
    public ExhibitorImp() {
        super();
    }

	@Override
	public List<TExhibitor> loadExhibitorsByEids(Integer[] eids) {
		Query q = this.getSession().createQuery("select new TExhibitor(e.eid, "
																	+ "e.username, "
																	+ "e.password, "
																	+ "e.level, "
																	+ "e.company, "
																	+ "e.companye, "
																	+ "e.area, "
																	+ "e.lastLoginTime, "
																	+ "e.lastLoginIp, "
																	+ "e.isLogout, "
																	+ "e.createUser, "
																	+ "e.createTime, "
																	+ "e.updateUser, "
																	+ "e.updateTime, "
																	+ "e.tag, "
																	+ "e.province, "
																	+ "e.country, "
																	+ "e.group, "
																	+ "e.contractId) "
																	+ "from TExhibitor e where e.isLogout = 0 and  e.eid in (:eids)");
		q.setParameterList("eids", eids);
		return q.list();
	}
}
