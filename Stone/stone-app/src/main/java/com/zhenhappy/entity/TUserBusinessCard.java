package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * TUserBusinessCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_user_business_card"
    ,schema="dbo"
)
public class TUserBusinessCard extends AbstractTUserBusinessCard implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public TUserBusinessCard() {
    }

}
