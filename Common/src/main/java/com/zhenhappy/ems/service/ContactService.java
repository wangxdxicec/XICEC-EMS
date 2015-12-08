package com.zhenhappy.ems.service;

import com.zhenhappy.ems.dao.ContactDao;
import com.zhenhappy.ems.dao.JoinerDao;
import com.zhenhappy.ems.entity.TContact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wujianbin on 2014-04-24.
 */
@Service
public class ContactService {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Autowired
    private ContactDao contactDao;

    public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public ContactDao getContactDao() {
		return contactDao;
	}

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	@Transactional
    public void saveContact(TContact contact) {
        hibernateTemplate.saveOrUpdate(contact);
    }

    @Transactional
    public void deleteContact(Integer cid) {
        hibernateTemplate.bulkUpdate("update TContact set isDelete = 1 where id = ?", new Object[]{cid});
    }

    public List<TContact> queryContactsByEid(Integer eid) {
        return hibernateTemplate.find("from TContact where eid=? and isDelete = 0", new Object[]{eid});
    }
}
