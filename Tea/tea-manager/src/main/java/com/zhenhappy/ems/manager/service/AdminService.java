package com.zhenhappy.ems.manager.service;

import com.zhenhappy.ems.manager.entity.TAdminUser;

/**
 * Created by wujianbin on 2014-04-22.
 */
public interface AdminService {

    public TAdminUser login(String username,String password);

}
