package com.zhenhappy.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TBusinessCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_business_card"
    ,schema="dbo"
)
public class TBusinessCard extends AbstractTBusinessCard implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TBusinessCard() {
	}

	/** minimal constructor */
	public TBusinessCard(Integer cardId) {
		super(cardId);
	}


}
