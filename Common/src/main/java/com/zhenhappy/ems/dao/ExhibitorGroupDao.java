package com.zhenhappy.ems.dao;

import java.util.List;

import com.zhenhappy.ems.entity.TExhibitorGroup;

/**
 * Created by wujianbin on 2014-09-03.
 */
public interface ExhibitorGroupDao extends BaseDao<TExhibitorGroup> {
	List<TExhibitorGroup> loadExhibitorGroupsByIds(Integer[] ids);
}
