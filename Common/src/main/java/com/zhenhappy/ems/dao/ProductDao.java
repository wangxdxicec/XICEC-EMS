package com.zhenhappy.ems.dao;

import java.util.List;

import com.zhenhappy.ems.entity.TProduct;

/**
 * Created by wujianbin on 2014-04-08.
 */
public interface ProductDao extends BaseDao<TProduct> {
	public List<TProduct> loadProductByPIds(Integer[] pids);
}
