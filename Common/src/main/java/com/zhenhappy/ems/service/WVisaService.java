package com.zhenhappy.ems.service;

import com.zhenhappy.ems.entity.WVisa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by wujianbin on 2014-10-30.
 */
@Service
public class WVisaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HibernateTemplate hibernateTemplate;

	/**
	 * 根据WCustomerInfo的id查询客商VISA信息
	 * @param cid
	 * @return
	 */
    public List<WVisa> queryWVisasByCid(Integer cid) {
        List<WVisa> wvisas = hibernateTemplate.find("from WVisa where WCustomerInfo = ?", new Object[]{cid});
        return wvisas;
    }

    @Transactional
    public void saveOrUpdate(WVisa wvisa) {
        if (wvisa.getId() == null) {
            hibernateTemplate.save(wvisa);
        } else {
            hibernateTemplate.update(wvisa);
        }
    }

    @Transactional
    public void delete(Integer cid,Integer vid){
        jdbcTemplate.update("delete from WVisa where WCustomerInfo = ? and id=?",new Object[]{cid,vid});
    }
}
