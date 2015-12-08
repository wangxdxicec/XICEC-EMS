package com.zhenhappy.ems.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhenhappy.ems.dao.CustomerInfoDao;
import com.zhenhappy.ems.entity.WCustomer;
import com.zhenhappy.ems.manager.dto.QueryCustomerRequest;
import com.zhenhappy.ems.manager.dto.QueryCustomerResponse;
import com.zhenhappy.ems.manager.exception.DuplicateCustomerException;
import com.zhenhappy.util.Page;

/**
 * Created by wujianbin on 2014-08-11.
 */
@Service
public class CustomerInfoManagerService {
	@Autowired
	private CustomerInfoDao customerInfoDao;
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 分页获取客商列表
	 * @param request
	 * @return
	 */
	public QueryCustomerResponse queryCustomersByPage(QueryCustomerRequest request) {
        Page page = new Page();
        page.setPageSize(request.getRows());
        page.setPageIndex(request.getPage());
        List<WCustomer> customers = customerInfoDao.queryPageByHQL("select count(*) from WCustomer", "from WCustomer", new Object[]{}, page);
        QueryCustomerResponse response = new QueryCustomerResponse();
        response.setResultCode(0);
        response.setRows(customers);
        response.setTotal(page.getTotalCount());
        return response;
    }
	
	/**
	 * 添加客商账号
	 * 
	 * @param customer
	 * @throws DuplicateCustomerException
	 */
	@Transactional
	public void addCustomer(WCustomer customer) throws DuplicateCustomerException {
		getHibernateTemplate().save(customer);
	}
	
	/**
	 * 修改客商账号
	 * @param customer
	 */
    @Transactional
    public void modifyCustomer(WCustomer customer) {
        getHibernateTemplate().update(customer);
    }
}
