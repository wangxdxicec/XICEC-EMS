package com.zhenhappy.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * TJoinIntention entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_join_intention"
    ,schema="dbo"
)
public class TJoinIntention extends AbstractTJoinIntention implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public TJoinIntention() {
    }

}
