package com.zhenhappy.ems.dao;

import com.zhenhappy.ems.entity.TVisa;

import java.util.List;

/**
 * Created by wujianbin on 2014-07-02.
 */
public interface TVisaDao extends BaseDao<TVisa> {

	List<TVisa> loadTVisasByVids(Integer[] vids);
}
