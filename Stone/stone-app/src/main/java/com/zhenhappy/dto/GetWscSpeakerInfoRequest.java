package com.zhenhappy.dto;

/**
 * 
 * @author 苏志锋
 * 
 */
public class GetWscSpeakerInfoRequest extends BaseRequest {
	private Integer speakerId;

	public Integer getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(Integer speakerId) {
		this.speakerId = speakerId;
	}

}
