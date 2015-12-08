package com.zhenhappy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhenhappy.dao.ProductDao;
import com.zhenhappy.entity.TProduct;
import com.zhenhappy.util.Page;

/**
 * 
 * @author rocsky
 * 
 */
@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDao productDao;

	@SuppressWarnings("unchecked")
	public java.util.List<TProduct> query(Page page,Integer local, String productName,
			Integer eid) {
		StringBuilder sqlWhere = new StringBuilder(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotBlank(productName)) {
			String column = "t.productName";
			if(2 == local){//en
				column = "t.productNameE"; 
			}else if(3 == local){//tw
				column = "t.productNameT";
			}
			sqlWhere.append(" and "+column+" like ? ");
			params.add("%" + productName + "%");
		}
		if (null != eid) {
			sqlWhere.append(" and t.exhibitorInfo.eid = ? ");
			params.add(eid);
		}
		return productDao.queryPageByHQL("select count(*) from TProduct t "
				+ sqlWhere.toString(),
				"select t from TProduct t " + sqlWhere.toString(),
				params.toArray(), page);
	}

	public TProduct get(Integer id) {
		return productDao.query(id);
	}

}
