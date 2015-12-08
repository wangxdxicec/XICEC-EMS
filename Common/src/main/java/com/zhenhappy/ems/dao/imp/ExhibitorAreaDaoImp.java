package com.zhenhappy.ems.dao.imp;

import java.util.List;

import com.zhenhappy.ems.dao.ExhibitorAreaDao;
import com.zhenhappy.ems.entity.TExhibitorArea;

import org.springframework.stereotype.Repository;

/**
 * Created by wujianbin on 2014-07-10.
 */
@Repository
public class ExhibitorAreaDaoImp extends BaseDaoHibernateImp<TExhibitorArea> implements ExhibitorAreaDao {

	@Override
	public List<TExhibitorArea> loadExhibitorAreaByFlag(Integer id) {
		List<TExhibitorArea> areas = queryByHql("from TExhibitorArea where flag = ?", new Object[]{id});
		return areas;
	}
}
