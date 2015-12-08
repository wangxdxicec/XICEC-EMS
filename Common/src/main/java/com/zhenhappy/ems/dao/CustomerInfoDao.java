package com.zhenhappy.ems.dao;

import java.util.List;

import com.zhenhappy.ems.entity.WCustomer;

/**
 * Created by wujianbin on 2014-07-02.
 */
public interface CustomerInfoDao extends BaseDao<WCustomer> {
	public List<WCustomer> loadCustomersByIds(Integer[] ids);
}
