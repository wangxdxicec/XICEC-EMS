package com.zhenhappy.ems.manager.dto;


import com.zhenhappy.ems.manager.entity.TAdminUser;

/**
 * Created by lianghaijian on 2014-04-22.
 */
public class ManagerPrinciple {

    /**
     * the name of principle stored in session.
     */
    public static final String MANAGERPRINCIPLE = "MANAGERPRINCIPLE";

    private TAdminUser admin;

    public TAdminUser getAdmin() {
        return admin;
    }

    public void setAdmin(TAdminUser admin) {
        this.admin = admin;
    }
}
