package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * TUserCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_user_company"
    ,schema="dbo"
)
public class TUserCompany extends AbstractTUserCompany implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public TUserCompany() {
    }

}
