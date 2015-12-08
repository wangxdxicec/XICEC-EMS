package com.zhenhappy.ems.dao;

import java.util.List;

import com.zhenhappy.ems.entity.TContact;

/**
 * Created by wujianbin on 2014-04-24.
 */
public interface ContactDao extends BaseDao<TContact> {
	public List<TContact> loadContactByCids(Integer[] cids);
	public List<TContact> loadContactByEids(Integer[] eids);
}
