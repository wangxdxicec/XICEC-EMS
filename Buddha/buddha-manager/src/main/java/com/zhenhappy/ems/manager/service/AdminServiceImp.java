package com.zhenhappy.ems.manager.service;

import com.zhenhappy.ems.manager.dao.AdminDao;
import com.zhenhappy.ems.manager.entity.TAdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wujianbin on 2014-04-22.
 */
@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminDao adminDao;

    /**
     * if login success,return admin user,otherwise return null;
     * @param username
     * @param password
     * @return
     */
    @Override
    public TAdminUser login(String username, String password) {
        List<TAdminUser> adminUsers = adminDao.queryByHql("from TAdminUser where username = ? and password = ?", new Object[]{username, password});
        return adminUsers.size()>0?adminUsers.get(0):null;
    }

}
