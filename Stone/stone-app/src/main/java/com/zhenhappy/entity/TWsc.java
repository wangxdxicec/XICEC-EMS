package com.zhenhappy.entity;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author 苏志锋
 * 
 */
public class TWsc {
	private Integer wscId;
	private Date wscDate;
	private String intrduction;
	private TWscSpeaker speaker;
	private List<TWscTopic> topics;

	public List<TWscTopic> getTopics() {
		return topics;
	}

	public void setTopics(List<TWscTopic> topics) {
		this.topics = topics;
	}

	//
	public TWscSpeaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(TWscSpeaker speaker) {
		this.speaker = speaker;
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
