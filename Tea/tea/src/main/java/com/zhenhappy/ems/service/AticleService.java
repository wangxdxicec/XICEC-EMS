package com.zhenhappy.ems.service;

import com.zhenhappy.ems.entity.TArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wujianbin on 2014-07-07.
 */
@Service
public class AticleService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<TArticle> loadAll(){
        return hibernateTemplate.find("from TArticle");
    }

    @Transactional
    public TArticle loadArticle(Integer id){
        return hibernateTemplate.get(TArticle.class,id);
    }
}
