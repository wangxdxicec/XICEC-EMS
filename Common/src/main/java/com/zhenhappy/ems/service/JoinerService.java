package com.zhenhappy.ems.service;

import com.zhenhappy.ems.dao.JoinerDao;
import com.zhenhappy.ems.entity.TExhibitorJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wujianbin on 2014-04-20.
 */
@Service
public class JoinerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private JoinerDao joinerDao;

    public JoinerDao getJoinerDao() {
        return joinerDao;
    }

    public void setJoinerDao(JoinerDao joinerDao) {
        this.joinerDao = joinerDao;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * query all joiners in exhibitor.
     * @param eid
     * @return
     */
    public List<TExhibitorJoiner> queryAllJoinersByEid(Integer eid){
        List<TExhibitorJoiner> joiners = hibernateTemplate.find("from TExhibitorJoiner where eid = ? order by isDelete",new Object[]{eid});
        return joiners;
    }

    @Transactional
    public void saveOrUpdate(TExhibitorJoiner joiner){
        hibernateTemplate.saveOrUpdate(joiner);
    }

    @Transactional
    public int delete(Integer jid,Integer eid){
        return jdbcTemplate.update("delete from t_exhibitor_joiner where id =? and eid =?",new Object[]{jid,eid});
    }
}
