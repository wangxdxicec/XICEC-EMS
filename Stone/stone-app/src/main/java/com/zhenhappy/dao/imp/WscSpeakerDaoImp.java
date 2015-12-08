package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.WscSpeakerDao;
import com.zhenhappy.entity.TWscSpeaker;

/**
 * 
 * @author 苏志锋
 * 
 */
@Repository
public class WscSpeakerDaoImp extends BaseDaoHibernateImp<TWscSpeaker>
		implements
 WscSpeakerDao {
    public WscSpeakerDaoImp() {
		super(TWscSpeaker.class);
    }
}
