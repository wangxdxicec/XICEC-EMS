package com.zhenhappy.dto;

/**
 * User: Haijian Liang
 * Date: 13-11-20
 * Time: 下午9:16
 * Function:
 */
public class CollectCardRequest extends AfterLoginRequest {

    private String qrcode;

    private String remark;

    private Integer cardType;

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
