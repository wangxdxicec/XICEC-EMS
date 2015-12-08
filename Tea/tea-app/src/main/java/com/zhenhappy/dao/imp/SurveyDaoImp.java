package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.SurveyDao;
import com.zhenhappy.entity.TCustomerSurvey;

@Repository
public class SurveyDaoImp extends BaseDaoHibernateImp<TCustomerSurvey> implements SurveyDao {

	public SurveyDaoImp() {
		super(TCustomerSurvey.class);
	}

}
