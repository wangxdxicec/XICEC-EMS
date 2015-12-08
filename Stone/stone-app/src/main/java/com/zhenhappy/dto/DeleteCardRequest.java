package com.zhenhappy.dto;

/**
 * User: Haijian Liang
 * Date: 13-11-20
 * Time: 下午7:57
 * Function:
 */
public class DeleteCardRequest extends AfterLoginRequest {

    private Integer cardId;

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
    
}
