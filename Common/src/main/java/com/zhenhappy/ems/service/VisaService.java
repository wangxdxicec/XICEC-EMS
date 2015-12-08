package com.zhenhappy.ems.service;

import com.zhenhappy.ems.dto.ContactVisaDto;
import com.zhenhappy.ems.dto.JoinerVisaDto;
import com.zhenhappy.ems.entity.TExhibitorJoiner;
import com.zhenhappy.ems.entity.TVisa;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by wujianbin on 2014-05-11.
 */
@Service
public class VisaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HibernateTemplate hibernateTemplate;

	private static Logger log = Logger.getLogger(VisaService.class);

    public List<TVisa> queryVisasByEid(Integer eid) {
        List<TVisa> visas = hibernateTemplate.find("from TVisa where eid = ?", new Object[]{eid});
        return visas;
    }

	/**
	 * Visa must combine with joiner。
	 * @param eid
	 * @return
	 */
	public List<JoinerVisaDto> queryJoinersWithVisaByEid(Integer eid){
		List<JoinerVisaDto> joinerVisaDtos = new ArrayList<JoinerVisaDto>();
		List<TVisa> visas = hibernateTemplate.find("from TVisa where eid = ?", eid);
		if (visas.size() != 0){
			for (TVisa visa : visas){
				TExhibitorJoiner joiner = new TExhibitorJoiner();
				try{
					joiner = hibernateTemplate.get(TExhibitorJoiner.class, visa.getJoinerId());
				}catch (DataAccessException e){
					log.error("joiner is not found", e);
				}
				if (joiner != null){
					JoinerVisaDto joinerVisaDto = new JoinerVisaDto();
					joinerVisaDto.setVisa(visa);
					joinerVisaDto.setJoiner(joiner);
					joinerVisaDtos.add(joinerVisaDto);
				}
			}
		}
		return joinerVisaDtos;
	}

    /**
     * Visa must combine with contact。
     * @param eid
     * @return
     */
    public List<ContactVisaDto> queryContactsWithVisaByEid(Integer eid){
        List<ContactVisaDto> contactVisaDtos = new ArrayList<ContactVisaDto>();
        List<TExhibitorJoiner> joiners = hibernateTemplate.find("from TExhibitorJoiner where eid = ?",eid);
        Map<Integer,ContactVisaDto> contactId_value_temp = new HashMap<Integer, ContactVisaDto>();
        if(joiners.size()!=0){
            for(TExhibitorJoiner joiner : joiners){
                ContactVisaDto contactVisaDto = new ContactVisaDto();
                contactVisaDto.setContact(joiner);
                contactId_value_temp.put(joiner.getId(),contactVisaDto);
                contactVisaDtos.add(contactVisaDto);
            }
            List<TVisa> visas = hibernateTemplate.find("from TVisa where eid = ?", eid);
            for(TVisa visa : visas){
                ContactVisaDto temp = null;
                if((temp=contactId_value_temp.get(visa.getJoinerId()))!=null){
                    temp.setVisa(visa);
					contactVisaDtos.get(visa.getJoinerId()).setVisa(visa);
				}
            }
        }
        return contactVisaDtos;
    }

    @Transactional
    public void saveOrUpdate(TVisa visa) {
        if (visa.getId() == null) {
			visa.setCreateTime(new Date());
            hibernateTemplate.save(visa);
        } else {
			visa.setUpdateTime(new Date());
            hibernateTemplate.update(visa);
        }
    }

    @Transactional
    public void delete(Integer eid,Integer vid){
        jdbcTemplate.update("delete from t_visa where eid = ? and id=?",new Object[]{eid,vid});
    }

    public TVisa queryByVid(Integer vid){
        TVisa visa = hibernateTemplate.get(TVisa.class,vid);
        return visa;
    }
}
