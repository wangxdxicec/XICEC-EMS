package com.zhenhappy.dto;

/**
 * User: Haijian Liang
 * Date: 13-11-20
 * Time: 下午8:46
 * Function:
 */
public class GetMyCardsResquest extends PageDataRequest {
    private Integer isMine;

    public Integer getIsMine() {
        return isMine;
    }

    public void setIsMine(Integer isMine) {
        this.isMine = isMine;
    }
}
