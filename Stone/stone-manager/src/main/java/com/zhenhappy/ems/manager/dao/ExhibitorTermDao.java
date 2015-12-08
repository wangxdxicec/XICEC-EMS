package com.zhenhappy.ems.manager.dao;

import java.util.List;

import com.zhenhappy.ems.dao.BaseDao;
import com.zhenhappy.ems.manager.entity.TExhibitorTerm;

/**
 * Created by wujianbin on 2014-07-25.
 */
public interface ExhibitorTermDao extends BaseDao<TExhibitorTerm> {
	public List<TExhibitorTerm> loadTermsByEids(Integer[] eids) ;
}
