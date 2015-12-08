package com.zhenhappy.ems.manager.dao;

import java.util.List;

import com.zhenhappy.ems.dao.BaseDao;
import com.zhenhappy.ems.manager.entity.TExhibitorBooth;

/**
 * Created by wujianbin on 2014-07-25.
 */
public interface ExhibitorBoothDao extends BaseDao<TExhibitorBooth> {
	public List<TExhibitorBooth> loadExhibitorBoothByEids(Integer[] eids) ;
}
