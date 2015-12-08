package com.zhenhappy.ems.service;

import com.zhenhappy.ems.entity.TExhibitorMeipai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wujianbin on 2014-07-22.
 */
@Service
public class MeipaiService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public TExhibitorMeipai saveOrUpdate(TExhibitorMeipai meipai) {
        if (meipai.getId() != null) {
            //update
            TExhibitorMeipai temp = hibernateTemplate.get(TExhibitorMeipai.class,meipai.getId());
            temp.setUpdateTime(meipai.getUpdateTime());
            temp.setMeipai(meipai.getMeipai());
            temp.setMeipaiEn(meipai.getMeipaiEn());
            hibernateTemplate.update(temp);
        } else {
            //create a new meipai record
            hibernateTemplate.save(meipai);
        }
        return meipai;
    }

    public TExhibitorMeipai getMeiPaiByEid(Integer eid){
        List datas = hibernateTemplate.find("from TExhibitorMeipai where exhibitorId = ?", eid);
        if(datas.size()==0){
            return null;
        }else{
            return (TExhibitorMeipai) datas.get(0);
        }
    }
    
    public void deleteMeiPaiByEid(Integer[] eids){
    	if(eids.length > 0 || eids != null){
    		for(Integer eid:eids){
    			TExhibitorMeipai meipai = getMeiPaiByEid(eid);
    			if(meipai != null){
    				hibernateTemplate.delete(meipai);
    			}
        	}
    	}
    }
}
