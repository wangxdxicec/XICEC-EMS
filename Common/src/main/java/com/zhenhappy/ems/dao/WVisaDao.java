package com.zhenhappy.ems.dao;

import com.zhenhappy.ems.entity.WVisa;

import java.util.List;

/**
 * Created by wujianbin on 2014-07-02.
 */
public interface WVisaDao extends BaseDao<WVisa> {

	List<WVisa> loadWVisasByVids(Integer[] vids);
}
