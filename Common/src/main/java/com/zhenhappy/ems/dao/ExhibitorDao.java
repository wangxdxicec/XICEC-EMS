package com.zhenhappy.ems.dao;

import java.util.List;

import com.zhenhappy.ems.entity.TExhibitor;

/**
 * Created by wujianbin on 2014-04-03.
 */
public interface ExhibitorDao extends BaseDao<TExhibitor> {
	public List<TExhibitor> loadExhibitorsByEids(Integer[] eids);
}
