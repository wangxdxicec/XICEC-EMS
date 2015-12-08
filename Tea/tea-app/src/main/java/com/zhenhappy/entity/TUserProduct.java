package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * TUserProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_user_product"
    ,schema="dbo"
)
public class TUserProduct extends AbstractTUserProduct implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public TUserProduct() {
    }

}
