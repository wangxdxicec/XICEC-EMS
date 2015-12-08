package com.zhenhappy.entity;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_user"
    ,schema="dbo"
)
public class TUser extends AbstractTUser implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public TUser() {
    }

	/** minimal constructor */
    public TUser(Integer userId, String password, Integer isCheck, Integer userType, Integer isDisable, Timestamp createTime) {
        super(userId, password, isCheck, userType, isDisable, createTime);        
    }
    
}
