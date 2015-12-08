package com.zhenhappy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhenhappy.dao.ProductDao;
import com.zhenhappy.entity.TProduct;

/**
 * 
 * @author 苏志锋
 * 
 */
@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	public List<TProduct> all() {
		return productDao.query();
	}

	public List<TProduct> queryByEid(Integer eid) {
		StringBuilder sqlWhere = new StringBuilder(" where 1=1 ");
		sqlWhere.append(" and t.eid=?");
		return productDao.queryByHql("select t from TProduct t " + sqlWhere.toString(), new Object[] { eid });
	}

}
