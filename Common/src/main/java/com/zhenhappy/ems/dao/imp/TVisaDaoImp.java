package com.zhenhappy.ems.dao.imp;

import com.zhenhappy.ems.dao.TVisaDao;
import com.zhenhappy.ems.dao.WVisaDao;
import com.zhenhappy.ems.entity.TVisa;
import com.zhenhappy.ems.entity.WVisa;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wujianbin on 2014-07-02.
 */
@Repository
public class TVisaDaoImp extends BaseDaoHibernateImp<TVisa> implements TVisaDao {

	@Override
	public List<TVisa> loadTVisasByVids(Integer[] vids) {
		Query q = this.getSession().createQuery("select new TVisa(v.id, v.eid, v.passportName, v.gender, v.nationality, v.jobTitle, v.companyName, v.boothNo, v.detailedAddress, v.tel, v.fax, v.email, v.companyWebsite, v.passportNo, v.expDate, v.birth, v.applyFor, v.from, v.to, v.passportPage, v.businessLicense, v.joinerId, v.status, v.createTime, v.updateTime) from TVisa v where v.id in (:vids)");
		q.setParameterList("vids", vids);
		return q.list();
	}
	
}
