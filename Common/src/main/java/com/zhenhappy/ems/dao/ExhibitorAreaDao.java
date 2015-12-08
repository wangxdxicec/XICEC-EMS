package com.zhenhappy.ems.dao;

import java.util.List;

import com.zhenhappy.ems.entity.TExhibitorArea;

/**
 * Created by wujianbin on 2014-07-10.
 */
public interface ExhibitorAreaDao extends BaseDao<TExhibitorArea> {
	List<TExhibitorArea> loadExhibitorAreaByFlag(Integer id);
}
