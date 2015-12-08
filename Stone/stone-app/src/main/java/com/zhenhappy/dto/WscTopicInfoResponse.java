package com.zhenhappy.dto;

/**
 * 
 * @author 苏志锋
 * 
 */
public class WscTopicInfoResponse extends BaseResponse {
	private String topicId;
	private String name;
	private String nameE;
	private String nameT;

	public WscTopicInfoResponse() {
	}

	public WscTopicInfoResponse(String topicId, String name, String nameE, String nameT) {
		this.topicId = topicId;
		this.name = name;
		this.nameE = nameE;
		this.nameT = nameT;
	}

	public String getNameE() {
		return nameE;
	}

	public void setNameE(String nameE) {
		this.nameE = nameE;
	}

	public String getNameT() {
		return nameT;
	}

	public void setNameT(String nameT) {
		this.nameT = nameT;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
