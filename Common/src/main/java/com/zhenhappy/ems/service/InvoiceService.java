package com.zhenhappy.ems.service;

import com.zhenhappy.ems.entity.TInvoiceApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wujianbin on 2014-05-13.
 */
@Service
public class InvoiceService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void create(TInvoiceApply invoiceApply) {
        hibernateTemplate.saveOrUpdate(invoiceApply);
    }

    public TInvoiceApply getByEid(Integer eid) {
        List<TInvoiceApply> applyList = hibernateTemplate.find("from TInvoiceApply where eid = ?", eid);
        return applyList.size() == 0 ? null : applyList.get(0);
    }

}
