package com.zhenhappy.ems.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhenhappy.ems.dao.ArticleDao;
import com.zhenhappy.ems.entity.TArticle;

/**
 * Created by wujianbin on 2014-07-02.
 */
@Service
public class ArticleService {
	
	@Autowired
    private ArticleDao articleDao;
	
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger log = Logger.getLogger(ArticleService.class);

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * 添加文章
     * @param article
     */
    @Transactional()
    public void add(TArticle article) {
    	articleDao.create(article);
    }
    
    /**
     * 查询文章
     * @param id
     * @return
     */
    @Transactional()
    public TArticle query(Integer id){
        return articleDao.query(id);
    }
    
    /**
     * 修改文章
     * @param article
     */
    @Transactional()
    public void update(TArticle article) {
    	articleDao.update(article);
    }
    
    
    @Transactional()
    public void remove(TArticle article) {
    	try {
			articleDao.delete(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
