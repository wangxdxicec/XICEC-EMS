package com.zhenhappy.ems.manager.service;

import java.util.List;

import com.zhenhappy.ems.manager.dao.ExhibitorTermDao;
import com.zhenhappy.ems.manager.entity.TExhibitorTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by wujianbin on 2014-07-25.
 */
@Service
public class TermManagerService {
	@Autowired
	private ExhibitorTermDao termDao;
	@Autowired
    private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    
	/**
     * 删除展会
     * @param eids
     */
	public void deleteTermByEids(Integer[] eids) {
		List<TExhibitorTerm> terms = termDao.loadTermsByEids(eids);
		if(terms.size() > 0){
	    	for(TExhibitorTerm term:terms){
	    		getHibernateTemplate().delete(term);
	    	}
		}
	}
}