package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.QRCodeInfoDao;
import com.zhenhappy.entity.VQrcodeInfo;

@Repository
public class QRCodeInfoDaoImp extends BaseDaoHibernateImp<VQrcodeInfo> implements QRCodeInfoDao{

	public QRCodeInfoDaoImp() {
		super(VQrcodeInfo.class);
	}
}
