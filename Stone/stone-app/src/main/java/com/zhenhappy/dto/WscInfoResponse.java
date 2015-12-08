package com.zhenhappy.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author 苏志锋
 * 
 */
public class WscInfoResponse extends BaseResponse {
	private Integer wscId;
	private String name;
	private String nameE;
	private String nameT;
	private String intrduction;
	private String intrductionE;
	private String intrductionT;
	private Integer isCollect;
	private Date wscDate;
	private String speaker;
	private String topic;
	private List<WscSpeakerInfoResponse> speakers = new ArrayList<WscSpeakerInfoResponse>();
	private List<WscTopicInfoResponse> topics = new ArrayList<WscTopicInfoResponse>();
	private String location;
	private String remark;

	private String wscTime;

	public String getWscTime() {
		return wscTime;
	}

	public void setWscTime(String wscTime) {
		this.wscTime = wscTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<WscSpeakerInfoResponse> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<WscSpeakerInfoResponse> speakers) {
		this.speakers = speakers;
	}

	public List<WscTopicInfoResponse> getTopics() {
		return topics;
	}

	public void setTopics(List<WscTopicInfoResponse> topics) {
		this.topics = topics;
	}

	public WscInfoResponse() {
	}


	public WscInfoResponse(Integer wscId, String name, String nameE, String nameT, String intrduction, String intrductionE, String intrductionT,
			Integer isCollect, Date wscDate, String speaker, String topic) {
		this.wscId = wscId;
		this.name = name;
		this.nameE = nameE;
		this.nameT = nameT;
		this.intrduction = intrduction;
		this.intrductionE = intrductionE;
		this.intrductionT = intrductionT;
		this.isCollect = isCollect;
		this.wscDate = wscDate;
		this.speaker = speaker;
		this.topic = topic;
	}


	public String getIntrductionE() {
		return intrductionE;
	}

	public void setIntrductionE(String intrductionE) {
		this.intrductionE = intrductionE;
	}

	public String getIntrductionT() {
		return intrductionT;
	}

	public void setIntrductionT(String intrductionT) {
		this.intrductionT = intrductionT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getWscId() {
		return wscId;
	}

	public void setWscId(Integer wscId) {
		this.wscId = wscId;
	}

	public Date getWscDate() {
		return wscDate;
	}

	public void setWscDate(Date wscDate) {
		this.wscDate = wscDate;
	}

	public String getIntrduction() {
		return intrduction;
	}

	public void setIntrduction(String intrduction) {
		this.intrduction = intrduction;
	}
}
