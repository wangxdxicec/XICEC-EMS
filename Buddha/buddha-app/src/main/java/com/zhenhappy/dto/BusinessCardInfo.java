package com.zhenhappy.dto;

import com.zhenhappy.entity.TBusinessCard;

/**
 * User: Haijian Liang
 * Date: 13-11-23
 * Time: 上午10:23
 * Function:
 */
public class BusinessCardInfo extends TBusinessCard {
    private Integer cardType;
    
    private String headUrl;

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
}
