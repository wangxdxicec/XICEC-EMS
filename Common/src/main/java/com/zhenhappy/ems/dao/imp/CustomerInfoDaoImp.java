package com.zhenhappy.ems.dao.imp;

import java.util.List;

import com.zhenhappy.ems.dao.CustomerInfoDao;
import com.zhenhappy.ems.entity.WCustomer;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wujianbin on 2014-08-11.
 */
@Repository
public class CustomerInfoDaoImp extends BaseDaoHibernateImp<WCustomer> implements CustomerInfoDao {

	@Override
	public List<WCustomer> loadCustomersByIds(Integer[] ids) {
		Query q = this.getSession().createQuery("select new WCustomer(a.id, a.email, a.checkingNo, a.password, a.firstName, a.lastName, a.sex, a.company, a.position, a.country, a.province, a.city, a.address, a.backupEmail, a.mobilePhoneCode, a.mobilePhone, a.telephoneCode, a.telephone, a.telephoneCode2, a.faxCode, a.fax, a.faxCode2, a.website, a.remark, a.createdIp, a.createdTime, a.updatedIp, a.updateTime, a.sendEmailNum, a.sendEmailDate, a.isDisabled, a.guid) from WCustomer a where a.id in (:ids)");
		q.setParameterList("ids", ids);
		return q.list();
	}
	
}
