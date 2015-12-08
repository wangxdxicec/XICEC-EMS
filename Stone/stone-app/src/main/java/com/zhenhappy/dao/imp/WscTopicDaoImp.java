package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.WscTopicDao;
import com.zhenhappy.entity.TWscTopic;

/**
 * 
 * @author 苏志锋
 * 
 */
@Repository
public class WscTopicDaoImp extends BaseDaoHibernateImp<TWscTopic> implements
		WscTopicDao {
    public WscTopicDaoImp() {
		super(TWscTopic.class);
    }
}
