package com.zhenhappy.ems.dao.imp;

import com.zhenhappy.ems.dao.WVisaDao;
import com.zhenhappy.ems.entity.WVisa;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wujianbin on 2014-07-02.
 */
@Repository
public class WVisaDaoImp extends BaseDaoHibernateImp<WVisa> implements WVisaDao {

	@Override
	public List<WVisa> loadWVisasByVids(Integer[] vids) {
		Query q = this.getSession().createQuery("select new WVisa(v.id,v.WCustomerInfo,v.fullPassportName,v.gender,v.nationality,v.passportNo,v.expDate,v.dateOfBirth,v.chineseEmbassy,v.consulateOfCity,v.durationBeginTime,v.durationEndTime,v.passportPage,v.businessLicense,v.wthInfoId,v.createTime,v.updateTime,v.needPost,v.expressTp,v.expressNo,v.isDisabled) from WVisa v where v.id in (:vids)");
		q.setParameterList("vids", vids);
		return q.list();
	}
	
}
