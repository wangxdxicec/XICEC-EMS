package com.zhenhappy.ems.dao;

import java.util.List;
import com.zhenhappy.ems.entity.TTag;

/**
 * Created by wujianbin on 2014-07-22.
 */
public interface TagDao extends BaseDao<TTag> {
	List<TTag> loadTagsByTids(Integer[] tids);
}
