package com.zhenhappy.dto;

import java.util.List;

/**
 * User: Haijian Liang
 * Date: 13-11-20
 * Time: 下午8:46
 * Function:
 */
public class GetMyCardsResponse extends BaseResponse {

    private List<BusinessCardInfo> cards;
    
    private boolean hasNext;

    public List<BusinessCardInfo> getCards() {
        return cards;
    }

    public void setCards(List<BusinessCardInfo> cards) {
        this.cards = cards;
    }

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
}
