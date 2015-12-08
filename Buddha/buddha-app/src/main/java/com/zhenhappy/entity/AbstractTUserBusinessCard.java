package com.zhenhappy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractTUserBusinessCard entity provides the base persistence definition of
 * the TUserBusinessCard entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTUserBusinessCard implements java.io.Serializable {

    private Integer userId;
    
    private String cardId;

    private Date createTime;

    private String remark;

    private Integer cardType;


    @Column(name = "cardType")
    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "create_time", nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "card_id")
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}